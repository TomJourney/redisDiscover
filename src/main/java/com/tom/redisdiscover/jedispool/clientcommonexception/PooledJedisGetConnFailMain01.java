package com.tom.redisdiscover.jedispool.clientcommonexception;

import com.tom.redisdiscover.jedispool.PooledJedisWithConnSoTimeoutFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName JedisUsingPoolMain.java
 * @Description TODO
 * @createTime 2024年12月27日 11:47:00
 */
public class PooledJedisGetConnFailMain01 {
    public static void main(String[] args) {
        // 注意： 连接超时时间给定为2000毫秒
        PooledJedisWithConnSoTimeoutFactory pooledJedisFactory = PooledJedisWithConnSoTimeoutFactory.build(3, 2000);
        // 新建带有10个线程的线程池
        int threadCount = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executorService.execute((() -> pooledJedisFactory.getJedis(index)));
        }
        // 关闭连接池
        executorService.shutdown();
    }
}
