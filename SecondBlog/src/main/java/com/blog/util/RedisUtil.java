package com.blog.util;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.Set;


public class RedisUtil {
    private Logger logger= LoggerFactory.getLogger(RedisUtil.class);
    private RedisTemplate<Serializable,Object> redisTemplate;


    public RedisTemplate<Serializable, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void remove(final String...keys){
        for(String key:keys){
            remove(key);
        }
    }


    public void removePattern(final String pattern){
        Set<Serializable> keys=redisTemplate.keys(pattern);
        if(keys.size()>0){
            redisTemplate.delete(keys);
        }
    }


    public void remove(final String key){
        if (exists(key)){
            redisTemplate.delete(key);
        }
    }


    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    public Object get(String key) {
        Object result=null;
        ValueOperations<Serializable,Object>operations=redisTemplate.opsForValue();
        result=operations.get(key);
        return result;
    }

    public boolean set(String tkey, Object tvalue, Long xxxRecordManagerTime) {
        boolean result=false;
        try{
            ValueOperations<Serializable,Object>operations=redisTemplate.opsForValue();
            operations.set(tkey,tvalue);
            result=true;
        }catch (Exception e){
            logger.error("系统异常",e);
        }
        return result;
    }
}
