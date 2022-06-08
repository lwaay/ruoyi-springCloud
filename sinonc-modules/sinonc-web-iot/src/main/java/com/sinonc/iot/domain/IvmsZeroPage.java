package com.sinonc.iot.domain;

import lombok.Data;

import java.util.List;

/**
 * @author zhangxinlong
 * @date 2021/3/30  18:24
 */
@Data
public class IvmsZeroPage {

    /**
     * 0表示成功
     */
    private String errorCode;

    /**
     * 查询数据记录总数
     */
    private String total;

    /**
     * 摄像头列表
     */
    private List<IvmsZeroCamera> ivmsZeroCameraList;
}
