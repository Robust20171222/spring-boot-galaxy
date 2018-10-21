package com.galaxy.redis.test;

import org.junit.Test;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.exceptions.JedisAskDataException;

import java.util.List;

/**
 * Redis集群测试
 *
 * Created by wangpeng
 * Date: 2018/10/16
 * Time: 10:11
 */
public class JedisClusterTest {

    @Test
    public void mgetOnAskTest() {
        JedisCluster jedisCluster = new JedisCluster(new HostAndPort("127.0.0.1", 6382));
//        List<String> results = jedisCluster.mget("key:test:68253","key:test:79212");
//        System.out.println(results);
//        results = jedisCluster.mget("key:test:5028","key:test:68253","key:test:79212");
//        System.out.println(results);
        jedisCluster.get("1");

        JedisClusterConnection jedisClusterConnection;
    }
}
