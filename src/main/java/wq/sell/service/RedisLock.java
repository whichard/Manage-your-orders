package wq.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wq
 * @date 2019/3/26
 */
@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁（锁获取）
     *  1. setnx 如果成功则直接获取锁；如果失败则当前锁非空
     *  2. 判断当前锁是否已经超时，如果没超时，直接退出，获取失败；如果已过期，则处理多线程获取
     *  3. 多线程获取锁，则只有第一个线程能获取到锁
     *  注意：需要Redis分布式系统的系统时间保持一致，否则可能出现异常bug。
     * @param key 锁对象
     * @param value 当前系统时间 + 超时时间
     * @return
     */

    public boolean lock(String key, String value) {
        if(redisTemplate.opsForValue().setIfAbsent(key, value))
            return true;
        String currentValue = redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(currentValue)
        && Long.parseLong(currentValue) < System.currentTimeMillis()) { //如果当前占有的锁已过期
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue))
                return true;
        }
        return false;
    }

    /**
     * 释放锁 如果当前占用锁的value符合，则释放键值对
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if(!StringUtils.isEmpty(currentValue) && currentValue.equals(value))
                redisTemplate.opsForValue().getOperations().delete(key);
        } catch (Exception e) {
            log.error("【Redis分布式锁】解锁异常, {}", e);
        }
    }
}
