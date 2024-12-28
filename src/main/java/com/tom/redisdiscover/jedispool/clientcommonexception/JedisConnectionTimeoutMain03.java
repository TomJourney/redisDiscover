package com.tom.redisdiscover.jedispool.clientcommonexception;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName JedisConnectionTimeoutMain.java
 * @Description TODO
 * @createTime 2024年12月27日 20:58:00
 */
public class JedisConnectionTimeoutMain03 {
    public static void main(String[] args) {
        int connectTimeout = 3000;
        int soTimeout = 500;
        long start = System.currentTimeMillis();
        try {
            Jedis jedis = new Jedis("192.168.163.211", 6379, connectTimeout, soTimeout);
            List<String> charList01 = jedis.lrange("charList01", 0, -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            long costOfSecond = (System.currentTimeMillis() - start) / 1000;
            System.out.printf("耗时（秒）=%d\n", costOfSecond);
        }
    }
}
