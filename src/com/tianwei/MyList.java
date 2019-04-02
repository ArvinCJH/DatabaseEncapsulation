package com.tianwei;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-3-22
 * Time: 下午2:15
 * Description: No Description
 */

public class MyList implements Map<String ,Object> {
    LinkedHashMap<String ,Object> updateAsMap ;

    public MyList() {
        updateAsMap =new LinkedHashMap() ;
    }

    /**
     *
     * @param keyName keyname
     * @param convertValue originalValue
     * @param convertValue convertValue
     * */
    public MyList append(String keyName , Object originalValue , Object convertValue){
        updateAsMap.put(keyName ,originalValue);
        updateAsMap.put(keyName+"2" ,convertValue);
        return this;
    }

    @Override
    public int size() {
        return updateAsMap.size();
    }

    @Override
    public boolean isEmpty() {
        return updateAsMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return updateAsMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return updateAsMap.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return updateAsMap.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return updateAsMap.put(key ,value);
    }

    @Override
    public Object remove(Object key) {
        return updateAsMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        updateAsMap.putAll(m);
    }

    @Override
    public void clear() {
        updateAsMap.clear();
    }

    @Override
    public Set<String> keySet() {
        return updateAsMap.keySet();
    }

    @Override
    public Collection<Object> values() {
        return updateAsMap.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return updateAsMap.entrySet();
    }


    @Override
    public String toString() {
        return ""+updateAsMap+"";
    }
}
