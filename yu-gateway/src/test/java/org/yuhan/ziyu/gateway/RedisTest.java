package org.yuhan.ziyu.gateway;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.yuhan.ziyu.base.authserver.PermissionDto;
import org.yuhan.ziyu.base.common.AuthConstant;

import java.util.List;

/**
 * Created by liuwu on 2018/9/18 0018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getPerms() {
        List<PermissionDto> permissionDtoList = (List<PermissionDto>) redisTemplate.opsForValue().get(AuthConstant.CACHE_AUTH_PERMS);
        Assert.assertNotNull(permissionDtoList);
    }
}
