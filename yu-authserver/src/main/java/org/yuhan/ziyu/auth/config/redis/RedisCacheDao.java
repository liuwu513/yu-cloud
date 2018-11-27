package org.yuhan.ziyu.auth.config.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.yuhan.ziyu.base.common.AuthConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * redis缓存
 * Created by liuwu on 2018/9/14 0014.
 */
@Slf4j
@Component
public class RedisCacheDao {

    @Autowired
    private StringRedisSerializer stringRedisSerializer;

    @Autowired
    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer;

    @Autowired
    private JedisSentinelPool jedisSentinelPool;

    /**
     * redis缓存数据权限
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        Jedis jedis = null;
        try{
            jedis = jedisSentinelPool.getResource();
            byte[] serializeCacheKey = stringRedisSerializer.serialize(AuthConstant.CACHE_AUTH_PERMS);
            byte[] serializeValue = jackson2JsonRedisSerializer.serialize(value);
            jedis.set(serializeCacheKey,serializeValue);
        }catch (Exception e){
            log.error("Redis缓存数据错误\n"+e.toString());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * redis缓存数据权限
     * @param key
     * @param expireTime
     * @param value
     */
    public void setEx(String key, int expireTime, Object value){
        Jedis jedis = null;
        try{
            jedis = jedisSentinelPool.getResource();
            byte[] serializeCacheKey = stringRedisSerializer.serialize(AuthConstant.CACHE_AUTH_PERMS);
            byte[] serializeValue = jackson2JsonRedisSerializer.serialize(value);
            jedis.setex(serializeCacheKey,expireTime,serializeValue);
        }catch (Exception e){
            log.error("Redis缓存数据错误\n"+e.toString());
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }
}
