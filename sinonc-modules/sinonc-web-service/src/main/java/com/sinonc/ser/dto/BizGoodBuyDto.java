package com.sinonc.ser.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangxinlong
 */
@Data
@ApiModel(value = "采购商品信息")
public class BizGoodBuyDto {

    /**
     * 主键
     */
    @ApiModelProperty(value = "商品主键")
    private Long infoId;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    /**
     * 会员名称
     */
    @ApiModelProperty(value = "会员名称")
    private String realName;


    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;

    /**
     * 采购商品ID
     */
    @ApiModelProperty(value = "采购商品ID")
    private Long buyId;

    /**
     * 上下架 (0上架 1下架)
     */
    @ApiModelProperty(value = "上下架 (0上架 1下架)")
    private String saleAble;


    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String infoName;

    /**
     * 采购商公司名称
     */
    @ApiModelProperty(value = "采购商公司名称")
    private String companyName;

    /**
     * 品种
     */
    @ApiModelProperty(value = "品种")
    private String breed;

    /**
     * 会员电话号码
     */
    @ApiModelProperty(value = "会员电话号码")
    private String phone;


    /**
     * 优选(1优选 2普通)
     */
    @ApiModelProperty(value = "优选(1优选 2普通)")
    private String preference;

    /**
     * 置顶标记，1置顶，2不置顶
     */
    @ApiModelProperty(value = "置顶标记，1置顶，2不置顶")
    private String topFlag;

    /**
     * 货源地址area_code编码
     */
    @ApiModelProperty(value = "货源地址area_code编码")
    private String shipAddress;

    /**
     * 货源地址area_code名称
     */
    @ApiModelProperty(value = "货源地址area_code名称")
    private String shipAddressName;

    /**
     * 收货地址area_code编码
     */
    @ApiModelProperty(value = "收货地址area_code编码")
    private String receiveAddress;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址")
    private String receiveAddressName;

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
    @ApiModelProperty(value = "采购重量")
    private BigDecimal buyWeight;

    /**
     * 采购发布时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "采购发布时间")
    private Date issueTime;

    /** 交货时间 */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value="交货时间")
    private Date deliveryTime;

    /** 品质要求以及运输方式或其他补充 */
    @ApiModelProperty(value="品质要求以及运输方式或其他补充")
    private String qualityDescribe;
}
