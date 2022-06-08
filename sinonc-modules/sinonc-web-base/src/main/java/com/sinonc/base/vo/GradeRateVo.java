package com.sinonc.base.vo;

import lombok.Data;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-05-10  17:01
 */
@Data
public class GradeRateVo {

    /**
     * 基地ID
     */
    private Long farmId;

    /**
     * 种植经验
     */
    private String plantRate;

    /**
     * 环境
     */
    private String milieuRate;

    /**
     * 品种
     */
    private String breadRate;

    /**
     * 质量
     */
    private String qualityRate;

    /**
     * 商品果率
     */
    private String saleRate;

}
