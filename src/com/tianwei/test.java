package com.tianwei;


import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-3-22
 * Time: 下午3:25
 * Description: No Description
 */

public class test {

    public static void main(String[] s){
        MyList myList =new MyList() ;
        myList.append("7like" ,123 ,246)
                .append("7l5ike" ,58 ,"333")
                .append("l7ike" ,458 ,"io5265n") ;
        getMyList(myList);
    }

    public static void getMyList(MyList list){
            System.out.println(list.values());
    }
}
