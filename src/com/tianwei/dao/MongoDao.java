package com.tianwei.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.tianwei.MyList;
import com.tianwei.utils.Utils;
import com.tianwei.value.BasedValue;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-3-13
 * Time: 下午3:56
 * Description: for mongo link ,create ,update ,del ,change
 */

public class MongoDao {
    MongoDatabase database ;
    MongoCollection<Document> collection ;
    private static class MongoDaoHolder{
        private static final MongoDao INSTANCE =new MongoDao() ;
    }

    private MongoDao(){
        initConnected();
    }
    public static final MongoDao getInstance(){
        return MongoDaoHolder.INSTANCE ;
    }


    //region init databases connect
    private void initConnected(){       // no pass
        try{
            MongoClient mongoClient =new MongoClient(BasedValue.MONGODB_HOST) ;
            database =mongoClient.getDatabase(BasedValue.MONGODB_DATABASE_NAME) ;
        }catch (Exception e){
            Utils.printE(e);
        }
    }

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


    /**
     *
     * @param  colName  集合名称
     * */
    public void createCollection(String colName){
        try {
            database.createCollection(colName);
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

    public MongoDao setCollection(String colName){      //  设定集合
        try {
            collection =database.getCollection(colName) ;
        }catch (Exception e){
            Utils.printE(e);
        }
        return this;
    }

    public void insertDocument(List<Document> documents){
        try{
            collection.insertMany(documents);
        }catch (Exception e){
            Utils.printE(e);
        }
    }

    public MongoCursor<Document> searchAllDocument(){
        try {
            FindIterable<Document> findIterable =collection.find() ;
            MongoCursor<Document> mongoCursor =findIterable.iterator() ;
            //  show data what you find
//            while (mongoCursor.hasNext()){
//                Utils.p(mongoCursor.hasNext());
//            }
            return mongoCursor ;
        }catch (Exception e) {
            Utils.printE(e);
        }
        return null;
    }

    public void updateDocument(MyList myList){
        collection.updateMany(Filters.eq("likes" ,100) ,new Document("$set",new Document("likes",200))) ;



    }
}
