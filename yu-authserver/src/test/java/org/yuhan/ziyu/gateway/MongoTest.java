package org.yuhan.ziyu.gateway;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yuhan.ziyu.auth.AuthServerApplication;
import org.yuhan.ziyu.auth.dao.UserDao;
import org.yuhan.ziyu.auth.dao.mongo.MongoRepository;
import org.yuhan.ziyu.auth.entity.User;
import org.yuhan.ziyu.auth.entity.mongo.UserMongo;

import java.util.List;

/**
 * Created by liuwu on 2018/11/30 0030.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class MongoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoRepository userMongoRepository;

    @Test
    public void batchAddUser() {
        List<User> userList = userDao.findByDepartmentId("40322777781112832");
        List<UserMongo> userMongoList = Lists.newArrayList();
        userList.forEach(item -> {
            UserMongo userMongo = new UserMongo();
            BeanUtils.copyProperties(item, userMongo);
            userMongoList.add(userMongo);
        });
        userMongoRepository.batchAdd(userMongoList,"user");
    }
}
