package com.sinonc.orders.vo;

import com.sinonc.orders.domain.OdGoods;
import lombok.Data;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-04-01  16:54
 */
@Data
public class OdGoodsVo extends OdGoods {

    /**
     * 规格属性
     */
    private String specsJson;

}
