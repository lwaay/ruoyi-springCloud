package com.sinonc.service.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 供应对象 biz_good_sell
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel("供应")
public class BizGoodSell extends BaseEntity {

    /** 主键 */
    @ApiModelProperty(value = "主键", hidden = true)
    private Long sellId;


    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id", hidden = true)
    private Long infoIdP;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date issueTime;

    /**
     * 热卖(1热卖 2普通)
     */
    @ApiModelProperty(value = "热卖(1热卖 2普通)")
    private String passion;

    /**
     * 货源地址area_code编码
     */
    @ApiModelProperty(value = "货源地址area_code编码")
    private String shipAddress;

    /**
     * 货源详细地址
     */
    @ApiModelProperty(value = "货源详细地址")
    private String shipDetail;

    /**
     * 是否已删除 (0 是 1不是)
     */
    @ApiModelProperty(value = "是否已删除", hidden = true)
    private String delFlag;

    private String infoName;

    /** 联系电话 */
    private String phone;

}
