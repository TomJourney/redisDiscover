package com.tom.redisdiscover;

import com.tom.redisdiscover.domain.entity.Car;
import com.tom.redisdiscover.utils.serialization.ProtostuffSerializationUtils;
import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName TomeJedisClient02Main.java
 * @Description jedis使用序列化接口操作redis
 * @createTime 2024年12月25日 09:30:00
 */
public class TomeJedisClientMainUsingSerialization {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.163.211", 6379, 10000, 3000);
        byte[] keyByteArr = ProtostuffSerializationUtils.serialize("car01");
        try {
            // 序列化后设置redis键值对
            jedis.set(keyByteArr, ProtostuffSerializationUtils.serialize(Car.build(2, "tesla", "shanghai")));
            // 根据key获取redis值并反序列化
            Car deserializeCar = ProtostuffSerializationUtils.deserialize(jedis.get(keyByteArr), Car.class);
            System.out.println(deserializeCar);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            if (Objects.nonNull(jedis)) {
                jedis.close();
            }
        }
    }
}
