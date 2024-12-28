package com.tom.redisdiscover.utils.timecost;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author Tom
 * @version 1.0.0
 * @ClassName TimeCostUtils.java
 * @Description TODO
 * @createTime 2024年12月27日 17:50:00
 */
public class TimeCostUtils {

    public static void cost(TimeUnit timeUnit, long timeout) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private TimeCostUtils() {
        // do nothing.
    }
}
