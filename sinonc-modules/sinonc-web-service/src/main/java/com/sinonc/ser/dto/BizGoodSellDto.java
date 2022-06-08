package com.sinonc.ser.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.service.domain.BizGoodSpecs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zxyn
 */
@Data
@ApiModel(value = "供货商品信息dto")
public class BizGoodSellDto {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", hidden = true)
    private Long sellId;

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
     * 主键
     */
    @ApiModelProperty(value = "商品主键")
    private Long infoId;

    /**
     * 发布时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private Date issueTime;

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

    /**
     * 会员电话号码
     */
    @ApiModelProperty(value = "会员电话号码")
    private String phone;

    /**
     * 商品类型（1采购 2供应）
     */
    @ApiModelProperty(value = "商品类型")
    private String infoType;

    /** 图片地址 */
    @ApiModelProperty(value="图片地址")
    private String imageUrl;

    /** 图文描述 */
    @ApiModelProperty(value="图文描述")
    private String content;

    /** 图片详情多个,隔开 */
    @ApiModelProperty(value="图片详情多个,隔开")
    private String detailImages;

    /** 最小价格，用于展示 */
    @ApiModelProperty(value="最小价格，用于展示")
    private BigDecimal minPrice;

    /**
     * 最大价格，用于展示
     */
    @ApiModelProperty(value = "最大价格，用于展示")
    private BigDecimal maxPrice;

    /**
     * 品种
     */
    @ApiModelProperty(value = "品种")
    private String breed;

    /**
     * 上下架 (0上架 1下架)
     */
    @ApiModelProperty(value = "上下架 (0上架 1下架)")
    private String saleAble;

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
     * 热卖(1热卖 2普通)
     */
    @ApiModelProperty(value = "热卖(1热卖 2普通)")
    private String passion;

    /**
     * 置顶标记，1置顶，2不置顶
     */
    @ApiModelProperty(value = "置顶标记，1置顶，2不置顶")
    private String topFlag;

    /**
     * 主图地址列表
     */
    @ApiModelProperty(value = "主图地址列表")
    private String[] imageUrlArray;

    /**
     * 图片详情列表
     */
    @ApiModelProperty(value = "图片详情列表")
    private String[] detailImagesArray;

    /**
     * 商品规格列表
     */
    @ApiModelProperty(value = "商品规格列表")
    private List<BizGoodSpecs> goodSpecsList;

}
