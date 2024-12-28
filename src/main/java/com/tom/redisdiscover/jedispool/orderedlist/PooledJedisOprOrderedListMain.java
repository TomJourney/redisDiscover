package com.tom.redisdiscover.jedispool.orderedlist;

import com.tom.redisdiscover.jedispool.PooledJedisWithConnSoTimeoutFactory;
import com.tom.redisdiscover.utils.timecost.TimeCostUtils;
import redis.clients.jedis.Jedis;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName JedisUsingPoolMain.java
 * @Description TODO
 * @createTime 2024年12月27日 11:47:00
 */
public class PooledJedisOprOrderedListMain {

    private static final String BASE_TEXT = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        PooledJedisWithConnSoTimeoutFactory jedisWithConnSoTimeoutFactory = PooledJedisWithConnSoTimeoutFactory.build(1, 500);
        // 从jedis连接池获取jedis对象
        Jedis jedis = jedisWithConnSoTimeoutFactory.getJedis(1);
        try {
            int size = 1000000;
            String[] valueArr = new String[size];
            // 对有序列表执行操作
            for (int i = 0; i < size; i++) {
                StringBuilder joinResult = new StringBuilder(BASE_TEXT).append(i);
                valueArr[i] = joinResult.toString();
            }
            // 批量写入元素到列表
            jedis.rpush("charList01", valueArr);
            // 执行耗时
            TimeCostUtils.cost(TimeUnit.DAYS, 1);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            if (Objects.nonNull(jedis)) {
                jedis.close();
            }
        }
    }
}
