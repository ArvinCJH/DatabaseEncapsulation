package com.tianwei.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.WriteModel;
import com.tianwei.inter.MyListListener;
import com.tianwei.utils.MongoDocument;
import com.tianwei.utils.Utils;
import com.tianwei.value.BasedValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.management.loading.MLet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-3-13
 * Time: 下午3:56
 * Description: for mongo link ,create ,update ,del ,change
 *
 *              // searchDocument ,num -now num  ok
 *              // searchDocument by id  view ->baikeid     5c98bca2f9bf8d242659e326
 */

public class MongoDao {
    private MongoClient mongoClient ;
    private MongoDatabase database ;
    private MongoCollection<Document> collection ;
    private static int saveNum =0 ;
    private static class MongoDaoHolder{
        private static final MongoDao INSTANCE =new MongoDao() ;
    }

    private MongoDao(){
        initConnected();
//        initView() ;
    }


    public static final MongoDao getInstance(){
        return MongoDaoHolder.INSTANCE ;
    }


    //region init databases connect
    private void initConnected(){       // no pass
        try{
            mongoClient =new MongoClient(BasedValue.MONGODB_HOST) ;
            database =mongoClient.getDatabase(BasedValue.MONGODB_DATABASE_NAME) ;
        }catch (Exception e){
            Utils.printE(e);
        }
    }

    //region search part        ----- about database ,collection ,document search -----
    public void getDataBaseList(){
        try{
            mongoClient.listDatabaseNames() ;       //  databaseNameList
        }catch (Exception e){
            Utils.printE(e);
        }
    }


    public List<Map<String, Object>> searchAllDocument(){       //  need set collection name ;
        return MongoDocument.toList(collection.find()) ;
    }

    /**
     * @param num how many data what you want to show
     * */
    public List<Map<String, Object>> searchAllDocument(int num){       //  need set collection name ;
        return MongoDocument.toList(collection.find().limit(num)) ;
    }

    /**
     * @param num how many data what you want to show
     * @param skipNum how many data what you want to skip
     * */
    public List<Map<String, Object>> searchAllDocument(int num ,int skipNum){       //  need set collection name ;
        return MongoDocument.toList(collection.find().limit(num).skip(skipNum)) ;
    }

    /**
     * @param startNum how many data what you want start to show , if startNum ==1 going to look it up from the previous one
     * @param showNum how many data what you want to skip
     * */
    public List<Map<String, Object>> searchNextDocument(int startNum ,int showNum){       //  need set collection name ;
        if (startNum >=0){
            saveNum =startNum ;
        }else {
            saveNum +=showNum;
        }
        return MongoDocument.toList(collection.find().limit(showNum).skip(saveNum)) ;
    }

    /**
     * @param id id
     * */
    public List<Map<String, Object>> searchByID(String id){
        DBObject filter =new BasicDBObject() ;
        filter.put("_id" ,new ObjectId(id)) ;
        return MongoDocument.toList(collection.find((Bson) filter)) ;
    }

    public void getCollections(){
        MongoIterable<String> mongoIterable = database.listCollectionNames() ;
        for (String s:mongoIterable){       //  list
            Utils.pln(s);
        }

    }

    //endregion


//    private void initConnectedTwo(){       //  need pass
//        try {
//            ServerAddress serverAddress =new ServerAddress(BasedValue.MONGODB_HOST) ;
//            List<ServerAddress> addresses =new ArrayList<ServerAddress>() ;
//            addresses.add(serverAddress) ;
//
//            MongoCredential credential =MongoCredential.createScramSha1Credential(BasedValue.MONGODB_LOGIN_NAME ,BasedValue.MONGODB_DATABASE_NAME ,BasedValue.MONGODB_LOGIN_PASS.toCharArray()) ;
//            List<MongoCredential> credentials =new ArrayList<MongoCredential>() ;
//            credentials.add(credential) ;
//            MongoClient mongoClient =new MongoClient(addresses ,credentials) ;
//            database =mongoClient.getDatabase(BasedValue.MONGODB_DATABASE_NAME) ;
//
//        }catch (Exception e){
//            Utils.printE(e);
//        }
//    }
    //endregion


    public MongoDao setCollection(String colName){      //  设定接下来要操作的集合
        try {
            collection =database.getCollection(colName) ;
        }catch (Exception e){
            Utils.printE(e);
        }
        return this;
    }
    
    /**
     * 
     * Default collName is "view"
     * */
    public MongoDao setCollection(){      //  设定接下来要操作的集合
        try {
            String colName="view" ;
            collection =database.getCollection(colName) ;
        }catch (Exception e){
            Utils.printE(e);
        }
        return this;
    }

    /**
     *
     * @param  colName  集合名称
     * */
    public void createCollection(String colName){       //  创建集合
        try {
            database.createCollection(colName);
        }catch (Exception e){
            Utils.printE(e);
        }
    }
    /**
     *
     * @param  databaseName  数据库名称
     * */
    public void createDatabase(String databaseName){
        try {
            mongoClient.getDatabase(databaseName) ;
        }catch (Exception e){
            Utils.printE(e);
        }
    }


    public void insertDocument(List<Document> documents){   // document ==> map
        try{
            collection.insertMany(documents);
        }catch (NullPointerException e){
            Utils.printE("please set a collections ");
        }catch (Exception e){
            Utils.printE(e);
        }
    }


    public boolean getCollectionStatus(String colName){     //  查询集合是否创建成功(未完成)
        try {
            database.createCollection(colName);
            return true ;
        }catch (Exception e){
            Utils.printE(e);
            return false ;
        }
    }

    /**
     *
     * @param updateBson 条件
     * @param document 更新的内容
     * */
    public void updateDocument(Bson updateBson ,Document document){
//        for (Map.Entry<String ,Object> entry :myList.entrySet()){
//            System.out.print(entry.getKey()+"\t");
//            System.out.println(entry.getValue());
//        }
//        collection.updateMany(Filters.eq("likes" ,100) ,new Document("$set",new Document("likes",200))) ;
        List<WriteModel<Document>> writeModelList = new ArrayList<WriteModel<Document>>();
//        for (int i=0 ;i<3 ;i++){
//        Bson updateBson = Filters.eq("name" ,"value") ;
//        Document document =new Document() ;
//        document.append("$set" ,new Document("name" ,"value")) ;
        UpdateOneModel<Document> updateOneModel =new UpdateOneModel<Document>(updateBson ,document) ;
        writeModelList.add(updateOneModel);
//        }
        collection.bulkWrite(writeModelList ,new BulkWriteOptions().ordered(false)) ;

    }


    public void deleteDocument(Bson bson){
        collection.deleteOne(bson) ;
        collection.deleteMany(bson) ;
    }


    public long getDocCount(){
        try {
            return collection.count() ;
        }catch (NullPointerException e){
            Utils.printE("please set a collections ");
        }
        return 0;
    }


    private void close(){
        try {
            mongoClient.close();
        }catch (Exception e){
            Utils.printE(e);
        }
    }

//  ---------------------------------------------------------- **** other part **** ----------------------------------------------------------

//    public void searchAllDocument(MyListListener myListListener){       //  need set collection name ;
//        List<Document> documents =new ArrayList<>() ;
//        MongoCursor<Document> mongoCursor =null ;
//        try {
//            FindIterable<Document> findIterable =collection.find() ;
//            mongoCursor =findIterable.iterator() ;
//            //  show data what you find
//            while (mongoCursor.hasNext()){
//                documents.add(mongoCursor.next()) ;
//            }
//            myListListener.respon(documents);
//        }catch (NullPointerException err) {
//            myListListener.error("please set a collections" ,err);
//        }finally {
//            if (mongoCursor !=null) mongoCursor.close();
//        }
//    }
//
//    public void searchAllDocument(int num ,MyListListener myListListener){       //  need set collection name ;
//        List<Document> documents =new ArrayList<>() ;
//        MongoCursor<Document> mongoCursor =null ;
//        try {
//            FindIterable<Document> findIterable =collection.find() ;
//            mongoCursor =findIterable.iterator() ;
//            //  show data what you find
//            while (mongoCursor.hasNext()){
//                documents.add(mongoCursor.next()) ;
//            }
//            myListListener.respon(documents);
//        }catch (NullPointerException err) {
//            myListListener.error("please set a collections" ,err);
//        }finally {
//            if (mongoCursor !=null) mongoCursor.close();
//        }
//    }

    public void searchAllDocument(MyListListener listener){       //  need set collection name ;
        try {
            listener.respon(MongoDocument.toList(collection.find()));
        }catch (NullPointerException err) {
            listener.error("please set a collections" ,err);
        }
    }

    /**
     * @param num how many data what you want to show
     * */
    public void searchAllDocument(int num ,MyListListener listener){       //  need set collection name ;
        try {
            listener.respon(MongoDocument.toList(collection.find().limit(num)));
        }catch (NullPointerException err) {
            listener.error("please set a collections" ,err);
        }
    }

    /**
     * @param num how many data what you want to show
     * @param skipNum how many data what you want to skip
     * */
    public void searchAllDocument(int num ,int skipNum ,MyListListener listener){       //  need set collection name ;
        try {
            listener.respon(MongoDocument.toList(collection.find().limit(num).skip(skipNum)));
        }catch (NullPointerException err) {
            listener.error("please set a collections" ,err);
        }
    }

    /**
     * @param startNum how many data what you want start to show , if startNum ==1 going to look it up from the previous one
     * @param showNum how many data what you want to skip
     * */
    public void searchNextDocument(int startNum ,int showNum ,MyListListener listener){       //  need set collection name ;
        try {
            if (startNum >=0){
                saveNum =startNum ;
            }else {
                saveNum +=showNum;
            }
            listener.respon(MongoDocument.toList(collection.find().limit(showNum).skip(saveNum)));
        }catch (NullPointerException err) {
            listener.error("please set a collections" ,err);
        }
    }

    /**
     * @param id id
     * */
    public void searchByID(String id , MyListListener listener){
        try {
            DBObject filter =new BasicDBObject() ;
            filter.put("_id" ,new ObjectId(id)) ;
            listener.respon(MongoDocument.toList(collection.find((Bson) filter)));
        }catch (NullPointerException err) {
            listener.error("please set a collections" ,err);
        }
    }
}
