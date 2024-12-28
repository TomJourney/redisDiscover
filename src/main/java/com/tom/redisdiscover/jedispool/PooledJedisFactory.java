package com.tom.redisdiscover.jedispool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.time.Duration;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName BusiJedisFactoryUsingPool.java
 * @Description 使用池化技术的jedis工厂
 * @createTime 2024年12月27日 11:42:00
 */
public class PooledJedisFactory {
    private JedisPool jedisPool;

    public static PooledJedisFactory build() {
        // 创建连接池配置
        GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
        // 设置连接池属性
        poolConfig.setMaxTotal(10); // 最大连接数
        poolConfig.setMaxIdle(5); // 最大空闲连接数
        poolConfig.setMinIdle(1); // 最小空闲连接数
        poolConfig.setJmxEnabled(true); // 开启jmx
        poolConfig.setMaxWait(Duration.ofSeconds(3)); // 连接池没有连接后客户端的最大等待时间(单位毫秒)
        PooledJedisFactory busiJedisFactoryUsingPool =
                new PooledJedisFactory(poolConfig, "192.168.163.211", 6379);
        return busiJedisFactoryUsingPool;
    }

    public static PooledJedisFactory build(int maxConnection) {
        // 创建连接池配置
        GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
        // 设置连接池属性
        poolConfig.setMaxTotal(maxConnection); // 最大连接数
        poolConfig.setMaxIdle(5); // 最大空闲连接数
        poolConfig.setMinIdle(1); // 最小空闲连接数
        poolConfig.setJmxEnabled(true); // 开启jmx
        poolConfig.setMaxWait(Duration.ofSeconds(3)); // 连接池没有连接后客户端的最大等待时间
        PooledJedisFactory busiJedisFactoryUsingPool =
                new PooledJedisFactory(poolConfig, "192.168.163.211", 6379);
        return busiJedisFactoryUsingPool;
    }

    private PooledJedisFactory(GenericObjectPoolConfig<Jedis> poolConfig, String host, int port) {
        jedisPool = new JedisPool(poolConfig, host, port, 1000);
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }
}
