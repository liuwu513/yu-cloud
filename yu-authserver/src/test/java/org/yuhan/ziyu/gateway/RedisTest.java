package org.yuhan.ziyu.gateway;

import org.yuhan.ziyu.auth.AuthServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.yuhan.ziyu.base.common.AuthConstant;

/**
 * Created by liuwu on 2018/9/18 0018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void getPerms(){
        redisTemplate.opsForValue().get(AuthConstant.CACHE_AUTH_PERMS);
    }
}
