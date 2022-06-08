package com.sinonc.service.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购对象 biz_good_buy
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel("采购")
public class BizGoodBuy extends BaseEntity {

    /** 主键 */
    @ApiModelProperty(value = "主键", hidden = true)
    private Long buyId;

    /** 商品主键 */
    @ApiModelProperty(value="商品主键",hidden = true)
    private Long infoIdP;

    /** 采购商ID */
    @ApiModelProperty(value = "采购商ID")
    private Long memberIdP;

    /** 优选(1优选 2普通) */
    @ApiModelProperty(value = "优选(1优选 2普通)", hidden = true)
    private String preference;

    /** 联系电话 */
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 货源地址area_code编码
     */
    @ApiModelProperty(value = "货源地址area_code编码，货源地")
    private String shipAddress;

    /**
     * 收货地址area_code编码
     */
    @ApiModelProperty(value = "收货地址area_code编码，收货地址")
    private String receiveAddress;

    /**
     * 货源详细地址
     */
    @ApiModelProperty(value = "货源详细地址")
    private String shipDetail;

    /**
     * 收货详细地址
     */
    @ApiModelProperty(value = "收货详细地址")
    private String receiveDetail;


    /**
     * 期望规格描述
     */
    @ApiModelProperty(value = "期望规格描述")
    private String buySpecinfo;

    /**
     * 采购重量
     */
    @ApiModelProperty(value = "求购数量")
    private BigDecimal buyWeight;

    /**
     * 采购发布时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "采购发布时间")
    private Date issueTime;

    /** 交货时间 */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="交货时间")
    private Date deliveryTime;

    /** 品质要求以及运输方式或其他补充 */
    @ApiModelProperty(value="品质要求以及运输方式或其他补充")
    private String qualityDescribe;

    /** 是否已删除 (0 是 1不是) */
    @ApiModelProperty(value="是否已删除",hidden = true)
    private String delFlag;

    /** 物品名称 */
    private String infoName;

}
