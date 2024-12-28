package com.tom.redisdiscover.jedispool.clientcommonexception;

import com.tom.redisdiscover.jedispool.PooledJedisFactory;
import redis.clients.jedis.Jedis;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName JedisConnectionTimeoutMain.java
 * @Description TODO
 * @createTime 2024年12月27日 20:58:00
 */
public class JedisClientConnectionOverMaxClientsExceptionMain08 {

    public static void main(String[] args) {
        new Jedis("192.168.163.211", 6379, 3000, 1000);
    }
}
