package com.sinonc.ser.dto;

import com.sinonc.service.domain.BizGoodSell;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangxinlong
 * @apiNote 用于后台功能的供货传输对象
 * @date 2020/8/5  11:29
 */
@Data
public class BizGoodSellBackDto extends BizGoodSell {

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String infoName;

    /**
     * 货源地址area_code名称
     */
    @ApiModelProperty(value = "货源地址area_code名称")
    private String shipAddressName;

}
