package com.galaxy.redis.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class JedisTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JedisTest.class);

    private static JedisPool jedisPool = null;
    private String script;
    // rate.frequency.limiting:12580
    private static final String KEY_PREFIX = "friend:ip:";

    @Before
    public void init() throws Exception{
        JedisPoolConfig poolConfig = buildPoolConfig();
        jedisPool = new JedisPool(poolConfig, "localhost",6379);

        ClassPathResource resource = new ClassPathResource("script/ratelimiting.lua");
        script = FileCopyUtils.copyToString(new EncodedResource(resource, "UTF-8").getReader());
    }

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }

    @Test
    public void test1() {
        String key = KEY_PREFIX + "12580";
        long expireTime = 120;
        int count = 2;
        boolean result = isExceedRate(key, expireTime,count );
        System.out.println(result);
    }

    /**
     * 提供限制速率的功能
     *
     * @param key        关键字
     * @param expireTime 过期时间
     * @param count      在过期时间内可以访问的次数
     * @return 没有超过指定次数则返回true, 否则返回false
     */
    public boolean isExceedRate(String key, long expireTime, int count) {
        // 设置限制次数的lua脚本所需的参数：有效访问时间和访问次数
        List<String> params = new ArrayList<>();
        params.add(Long.toString(expireTime));
        params.add(Integer.toString(count));

        try(Jedis jedis = jedisPool.getResource()) {
            // 设置要在redis里保存的手机号信息：
            List<String> keys = new ArrayList<>(1);
            keys.add(key);

            Long canSend = (Long) jedis.eval(script, keys, params);
            return canSend == 0;
        }
    }

    @After
    public void destory(){

    }
}
