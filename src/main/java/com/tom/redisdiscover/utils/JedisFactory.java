package com.tom.redisdiscover.utils;

import redis.clients.jedis.Jedis;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName JedisFactory.java
 * @Description TODO
 * @createTime 2024年12月26日 06:22:00
 */
public class JedisFactory {

    public static Jedis create(String host, int port, int connectionTimeout, int soTimeout) {
        return new Jedis("192.168.163.211", 6379, 10000, 3000);
    }
}
