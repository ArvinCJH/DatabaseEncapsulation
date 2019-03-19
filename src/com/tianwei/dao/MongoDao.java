package com.tianwei.dao;

/**
 * Created with IntelliJ IDEA.
 * User: arvin
 * Date: 19-3-13
 * Time: 下午3:56
 * Description: for mongo link ,create ,update ,del ,change
 */

public class MongoDao {
    private static class MongoDaoHolder{
        private static final MongoDao INSTANCE =new MongoDao() ;
    }

    private MongoDao(){}
    public static final MongoDao getInstance(){
        return MongoDaoHolder.INSTANCE ;
    }




}
