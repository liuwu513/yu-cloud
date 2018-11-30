package org.yuhan.ziyu.auth.dao.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liuwu on 2018/11/30 0030.
 */
@Repository
public class MongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void batchAdd(List<? extends Object> list, String collectionName) {
        mongoTemplate.insert(list, collectionName);
    }
}
