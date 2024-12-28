package com.tom.redisdiscover;

import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName TomeJedisClient.java
 * @Description TODO
 * @createTime 2024年12月25日 06:30:00
 */
public class TomeJedisClient01Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Jedis jedis = null;
        try {
            jedis = new Jedis("192.168.163.211", 6379, 10000, 3000);
            jedis.set("tom:jedis:user1", "tom1");
            System.out.println(jedis.get("user1"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(jedis)) {
                // 记得关闭redis连接
                jedis.close();
            }
            System.out.println("耗时(秒)=" + (System.currentTimeMillis() - start) / 1000);
        }
    }
}
