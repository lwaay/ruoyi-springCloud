package com.sinonc.service.domain;

import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息（主）对象 biz_good_info
 *
 * @author ruoyi
 * @date 2020-07-29
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel("商品信息（主）")
public class BizGoodInfo extends BaseEntity {

    /** 主键 */
    private Long infoId;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberIdP;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String infoName;

    /** 副标题 */
    @ApiModelProperty(value="副标题")
    private String subtitle;

    /**
     * 1 先货后款  2先款后货
     */
    @ApiModelProperty(value = "1 先货后款  2先款后货")
    private Integer priorCargo;

    /**
     * 商品类型（1采购 2供应）
     */
    @ApiModelProperty(value = "商品类型")
    private String infoType;

    /**
     * 置顶标记，1置顶，2不置顶
     */
    @ApiModelProperty(value = "置顶标记，1置顶，2不置顶")
    private String topFlag;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    /**
     * 视频地址
     */
    @ApiModelProperty(value = "视频地址")
    private String videoUrl;

    /**
     * 图文描述
     */
    @ApiModelProperty(value="图文描述")
    private String content;

    /** 图片详情多个,隔开 */
    @ApiModelProperty(value="图片详情多个,隔开")
    private String detailImages;

    /** 最小价格，用于展示 */
    @ApiModelProperty(value="最小价格，用于展示")
    private BigDecimal minPrice;

    /** 最大价格，用于展示 */
    @ApiModelProperty(value="最大价格，用于展示")
    private BigDecimal maxPrice;

    /** 上下架 (0上架 1下架) */
    @ApiModelProperty(value="上下架 (0上架 1下架)")
    private String saleAble;

    /** 品种 */
    @ApiModelProperty(value="品种")
    private String breed;

    /**
     * 是否已删除 (0 是 1不是)
     */
    private String delFlag;
    private Date createTime;
}
