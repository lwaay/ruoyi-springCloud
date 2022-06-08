package com.sinonc.orders.ec.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huanghao
 * @apiNote 实时数据销售 按店铺
 * @date 2021/3/23 14:55
 */
@Data
public class RealTimeStoreStatisticVo {
    private String productName;
    private double sale;
    private BigDecimal salesRate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
}
