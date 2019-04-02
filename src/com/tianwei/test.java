package com.tianwei;


import com.mongodb.client.MongoCursor;
import com.tianwei.dao.MongoDao;
import com.tianwei.inter.MyListListener;
import com.tianwei.utils.Utils;
import org.bson.Document;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-3-22
 * Time: 下午3:25
 * Description: No Description
 */

public class test {

    public static void main(String[] s){
//        MyList myList =new MyList() ;
//        myList.append("7like" ,123 ,246)
//                .append("7l5ike" ,58 ,"333")
//                .append("l7ike" ,458 ,"io5265n") ;          // for change
////        getMyList(myList);
//
//        List list2 =new ArrayList() ;
//        String[] list =new String[5] ;
//        for (int i =0 ;i<list.length ;i++){
//            list[i] = String.valueOf(i);
//            list2.add(list) ;
//        }
//        Utils.pln(list2);
//        for (int i =0 ;i<list2.size() ;i++){
//            for (int j =0 ;j<list.length ;j++){
//                Utils.pln(list2.get(i) +"+j"+j);
//            }
//        }
        Utils.pln(MongoDao.getInstance().setCollection("view").searchByID("5c98bca2f9bf8d242659e326"));
//        MongoDao.getInstance().setCollection("view").searchAllDocument(new MyListListener() {
//            @Override
//            public void error(String reason, Object error) {
//                Utils.p(reason);
//                Utils.p(error);
//            }
//
//            @Override
//            public void respon(List list) {
//                Utils.p(list.size());
//            }
//        });

//        getMyList(myList);
//        MongoDao.getInstance().setCollection("col").searchAllDocument() ;
    }

    public static void getMyList(MyList list){
//        System.out.println(list.keySet());

        Map<String ,String> map =new HashMap<String, String>() ;

        for (Map.Entry<String ,Object> entry :list.entrySet()){
            System.out.print(entry.getKey()+"\t");
            System.out.println(entry.getValue());
        }

        for (String key:map.keySet()){
            System.out.print(map.get(key)+"\t");
            System.out.println(key);

        }

        Map map1 =new HashMap() ;
        Iterator iterator =map1.entrySet().iterator() ;
        while (iterator.hasNext()){
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key =entry.getKey() ;
            Object val =entry.getValue() ;
            System.out.print(key+"\t");
            System.out.println(val);
        }
    }
}
