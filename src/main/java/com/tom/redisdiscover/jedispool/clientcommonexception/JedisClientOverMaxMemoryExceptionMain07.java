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
public class JedisClientOverMaxMemoryExceptionMain07 {

    private static final String VALUE = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        int connectTimeout = 3000;
        int soTimeout = 6000 * 3600;
        long start = System.currentTimeMillis();
        try {
            Jedis jedis = new Jedis("192.168.163.211", 6379, connectTimeout, soTimeout);
            int size = 10000;
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < size; i++) {
                result.append(VALUE + i).append("#");
            }
            // 执行写操作
            jedis.set("bigKey", result.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            long costOfMilliSecond = System.currentTimeMillis() - start;
            System.out.printf("耗时（毫秒）=%d\n", costOfMilliSecond);
        }
    }
}
