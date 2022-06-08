package com.sinonc.ser.dto;

import com.sinonc.service.domain.BizGoodBuy;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangxinlong
 * @apiNote 采购商品后台传输对象
 * @date 2020/8/5  14:13
 */
@Data
public class BizGoodBuyBackDto extends BizGoodBuy {

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
