package com.tianwei.utils;

import com.tianwei.dao.MongoDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-3-22
 * Time: 上午10:00
 * Description: No Description
 */

public class Utils {

    public static void printE(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    public static void printE(String s){
        System.err.println(s);
    }

    public static void p(Object obj){
        System.out.print(obj);
    }

    public static void pln(Object obj){
        System.out.println(obj);
    }


//    public static String getString(String s){       //
//        try {
//            String pattern = "\\D+";
//            Matcher m = Pattern.compile(pattern).matcher(s) ;
//            if (m.find()){
//                return m.group() ;
//            }
//        }catch (Exception e){
//            printE(e);
//        }
//        return null ;
//    }
}
