package org.geeksforgeeks.digitallibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, Object value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return this.redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        this.redisTemplate.delete(key);
    }
}
