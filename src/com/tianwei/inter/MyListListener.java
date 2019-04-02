package com.tianwei.inter;

import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-3-28
 * Time: 上午10:05
 * Description: No Description
 */

public interface MyListListener {
    void respon(List<Map<String, Object>> documents) ;
    void error(String reason,Object error) ;

}
