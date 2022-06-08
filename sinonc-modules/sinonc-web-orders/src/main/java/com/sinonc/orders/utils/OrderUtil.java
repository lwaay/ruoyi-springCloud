package com.sinonc.orders.utils;

import java.util.Random;

/**
 * 订单工具类
 */
public class OrderUtil {

    public static final Random random = new Random();

    /**
     * 生成订单编号
     *
     * @return
     */
    public static String createOrderNo() {

        StringBuilder stringBuilder = new StringBuilder();

        long currentTimeMillis = System.currentTimeMillis();

        stringBuilder.append(currentTimeMillis);

        for (int i = 0; i < 3; i++) {
            stringBuilder.append(random.nextInt(10));
        }

        return stringBuilder.toString();
    }


}
