package com.sinonc.iot.rabbitmq.consumer;

/**
 * @author zhangxinlong
 * @date 2021/4/19  9:32
 */
public class DeviceStatusContents {

    /**
     * 离线
     */
    public final static long OFF_ONLINE = 0;

    /**
     * 正常
     */
    public final static long STATUS_NORMAL = 1;

    /**
     * 上线
     */
    public final static long ON_ONLINE = 2;

    /**
     * 虫情图片结果
     */
    public final static long IMG_WARM = 3;

}
