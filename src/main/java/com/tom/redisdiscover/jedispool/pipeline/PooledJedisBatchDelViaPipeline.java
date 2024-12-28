package com.tom.redisdiscover.jedispool.pipeline;

import com.tom.redisdiscover.jedispool.PooledJedisFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName JedisOprPipelineByPoolMain.java
 * @Description TODO
 * @createTime 2024年12月27日 16:31:00
 */
public class PooledJedisBatchDelViaPipeline {
    public static void main(String[] args) {
        PooledJedisFactory busiJedisFactoryUsingPool = PooledJedisFactory.build();
        // 从jedis连接池获取jedis对象
        Jedis jedis = busiJedisFactoryUsingPool.getJedis();
        try {
            System.out.println(jedis.get("user01"));
            System.out.println(jedis.get("user02"));
            batchDelete(jedis, Arrays.asList("user01", "user02"));
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            if (Objects.nonNull(jedis)) {
                jedis.close();
            }
        }

        // 删除键之后获取键
        // 从jedis连接池获取jedis对象
        System.out.println("========== 删除键之后获取键 ========== ");
        jedis = busiJedisFactoryUsingPool.getJedis();
        try {
            System.out.println(jedis.get("user01"));
            System.out.println(jedis.get("user02"));
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            if (Objects.nonNull(jedis)) {
                jedis.close();
            }
        }
    }

    private static void batchDelete(Jedis jedis, List<String> keys) {
        // 生成pipeline对象
        Pipeline pipeline = jedis.pipelined();
        for (String key : keys) {
            pipeline.del(key);
        }
        // 提交命令到redis
        pipeline.sync();
    }
}
