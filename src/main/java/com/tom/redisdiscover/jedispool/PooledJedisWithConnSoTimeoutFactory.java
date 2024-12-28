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
public class PooledJedisWithConnSoTimeoutFactory {
    private JedisPool jedisPool;

    public static PooledJedisWithConnSoTimeoutFactory build(int maxTotal, int maxWaitMillis) {
        // 创建连接池配置
        GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
        // 设置连接池属性
        poolConfig.setMaxTotal(maxTotal); // 最大连接数
        poolConfig.setMaxIdle(5); // 最大空闲连接数
        poolConfig.setMinIdle(1); // 最小空闲连接数
        poolConfig.setJmxEnabled(true); // 开启jmx
        poolConfig.setMaxWait(Duration.ofMillis(maxWaitMillis)); // 连接池没有连接后客户端的最大等待时间(单位毫秒)
        poolConfig.setBlockWhenExhausted(true); // 当连接池用尽后，调用者是否等待；该参数为true时，maxWait才起作用
        return new PooledJedisWithConnSoTimeoutFactory(poolConfig, "192.168.163.211", 6379);
    }

    private PooledJedisWithConnSoTimeoutFactory(
            GenericObjectPoolConfig<Jedis> poolConfig, String host, int port) {
        jedisPool = new JedisPool(poolConfig, host, port);
    }

    public Jedis getJedis(int index) {
        long start = System.currentTimeMillis();
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            long costOfSecond = (System.currentTimeMillis() - start) / 1000;
            System.out.printf("index=%s, 获取池化Jedis连接的耗时（秒）=%d\n", index, costOfSecond);
        }
    }
}
