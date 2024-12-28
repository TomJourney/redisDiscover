package com.tom.redisdiscover;

import redis.clients.jedis.Jedis;

import java.util.Objects;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName TomeJedisClient02Main.java
 * @Description jedis操作5种数据类型
 * @createTime 2024年12月25日 09:30:00
 */
public class TomeJedisClient02Main {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.163.211", 6379, 10000, 3000);
        try {
            oprFiveTypeKey(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(jedis)) {
                jedis.close();
            }
        }
    }

    private static void oprFiveTypeKey(Jedis jedis) {
        System.out.println("\n========== 字符串类型 ==========");
        // 1 字符串类型
        // 实际可以是字符串（简单字符串，复杂字符串如json），数字（整数，浮点数），二进制（图片，音视频），值最大不超过512M
        jedis.set("name01", "tom01");
        System.out.println(jedis.get("name01")); // tom01

        // 1.1 数字
        System.out.println(jedis.incr("counter01")); // 4
        System.out.println(jedis.get("counter01")); // 4

        System.out.println("\n========== hash类型 ==========");
        // 2 hash类型
        // 哈希类型定义：指键的值本身又是一个键值对结构。 如value={{field1, value1}, {field2, value2}}
        jedis.hset("person", "name", "tom01");
        jedis.hset("person", "addr", "chengdu");
        System.out.println(jedis.hget("person", "name")); // tom01
        System.out.println(jedis.hgetAll("person"));// {name=tom01, addr=chengdu}

        System.out.println("\n========== list有序列表 ==========");
        // 3 list有序列表
        // 列表类型定义： 用来存储多个有序的字符串， 如a,b,c这3个元素从左到右组成了一个有序列表
        jedis.del("userList"); // 先删除key
        jedis.rpush("userList", "tom01", "tom02", "tom03");
        System.out.println(jedis.lrange("userList", 0, -1)); // [tom01, tom02, tom03]

        System.out.println("\n========== set无序集合 ==========");
        // 4 set无序集合
        // 集合类型定义：集合类型用于保存多个字符串元素，但不允许重复元素，且元素无序，不能通过通过下标获取元素；
        jedis.sadd("userSet", "tom01", "tom02", "tom03");
        System.out.println(jedis.spop("userSet")); // tom03
        System.out.println(jedis.smembers("userSet"));// [tom02, tom01]

        System.out.println("\n========== zset有序集合 ==========");
        // 5 zset有序集合
        jedis.zadd("userSortedSet", 3, "tom03");
        jedis.zadd("userSortedSet", 2, "tom02");
        jedis.zadd("userSortedSet", 1, "tom01");
        System.out.println(jedis.zrangeByScore("userSortedSet", 2, 3)); // [tom02, tom03]
    }
}
