package com.tom.redisdiscover.jedispool.clientcommonexception;

import com.tom.redisdiscover.utils.timecost.TimeCostUtils;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName JedisConnectionTimeoutMain.java
 * @Description TODO
 * @createTime 2024年12月27日 20:58:00
 */
public class JedisRwSocketTimeoutMain02 {
    public static void main(String[] args) {
        int connectTimeout = 3000;
        int soTimeout = 60000 * 3600;
        long start = System.currentTimeMillis();
        try {
            Jedis jedis = new Jedis("192.168.163.211", 6379, connectTimeout, soTimeout);
            for (int i = 0; i < 100000; i++) {
                List<String> charList01 = jedis.lrange("charList01", 0, -1);
                System.out.println(charList01.size());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            long costOfMilliSecond = System.currentTimeMillis() - start;
            System.out.printf("耗时（毫秒）=%d\n", costOfMilliSecond);
        }
    }
}
