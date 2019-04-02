package com.tianwei.utils;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-4-2
 * Time: 上午9:53
 * Description: No Description
 */

public class MongoDocument {

    public static List<Map<String, Object>> toList(FindIterable<Document> findIterable){
        MongoCursor<Document> mongoCursor =null ;
        try {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                mongoCursor =findIterable.iterator() ;
                while (mongoCursor.hasNext()){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.putAll(mongoCursor.next());
                    list.add(map);
                }
                return list ;
            }catch (Exception err) {
                Utils.printE(err);
            }finally {
                if (mongoCursor !=null) mongoCursor.close();
            }
        return null;
    }
    public static List<Map<String, Object>> toList(FindIterable<Document> findIterable ,String str){
        MongoCursor<Document> mongoCursor =null ;
        try {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                findIterable.forEach(new Block<Document>() {
                    @Override
                    public void apply(Document document) {
                        Map<String ,Object> map =new HashMap<>() ;
                        map.putAll(document);
                        list.add(map) ;
                    }
                });
            return list ;
            }catch (Exception err) {
            Utils.printE(err);
            }finally {
                if (mongoCursor !=null) mongoCursor.close();
            }
        return null;
    }
}
