package com.tom.redisdiscover.jedispool;

import redis.clients.jedis.Jedis;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName JedisUsingPoolMain.java
 * @Description TODO
 * @createTime 2024年12月27日 11:47:00
 */
public class PooledJedisMain {
    public static void main(String[] args) {
        PooledJedisFactory busiJedisFactoryUsingPool = PooledJedisFactory.build();
        // 从jedis连接池获取jedis对象
        Jedis jedis = busiJedisFactoryUsingPool.getJedis();
        System.out.println(jedis);
        try {
            // 执行操作
            jedis.set("user01", "zhagnsan01");
            System.out.println(jedis.get("user01"));
            // 执行操作
            jedis.set("user02", "zhagnsan02");
            System.out.println(jedis.get("user02"));
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            if (Objects.nonNull(jedis)) {
                // JedisPool连接池返回的Jedis对象，其close方法不是关闭连接，而是归还给连接池
                jedis.close();
            }
        }
    }
}
