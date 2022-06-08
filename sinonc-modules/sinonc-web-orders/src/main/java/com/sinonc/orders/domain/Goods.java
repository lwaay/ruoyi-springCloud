package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商品表 od_goods
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("Goods")
@Data
public class Goods {

    private static final long serialVersionUID = 1L;

    /**
     * id自增
     */
    @ApiModelProperty(hidden = true)
    private Long goodsId;
    /**
     * 品牌id
     */
    @ApiModelProperty(hidden = true)
    private Long brandId;
    /**
     * 店铺id
     */
    @ApiModelProperty(hidden = true)
    private Long shopId;
    /**
     * 商品名称
     */
    @ApiModelProperty(hidden = true)
    @NotBlank(message = "商品名称不能为空")
    private String name;
    /**
     * 商品描述
     */
    @ApiModelProperty(hidden = true)
    @NotBlank(message = "商品描述不能为空")
    private String remark;
    /**
     * 商品图片
     */
    @ApiModelProperty(hidden = true)
    private String image;
    /**
     * 商品视频
     */
    @ApiModelProperty(hidden = true)
    private String video;
    /**
     * 商品类型 0认养产品 1普通产品
     */
    @ApiModelProperty(hidden = true)
    private Integer type;
    /**
     * 商品图文描述
     */
    @ApiModelProperty(hidden = true)
    private String content;
    /**
     * 上下架：0下架 1上架
     */
    @ApiModelProperty(hidden = true)
    private Integer saleAble;
    /**
     * 商品折扣
     */
    @ApiModelProperty(hidden = true)
    private String discount;
    /**
     * 折扣价
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal finalPrice;
    /**
     * 商品原价
     */
    @ApiModelProperty(hidden = true)
    private BigDecimal costPrice;
    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date createTime;
    /**
     * 修改人
     */
    @ApiModelProperty(hidden = true)
    private String updateBy;
    /**
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private Date updateTime;
    /**
     * 商品分类id
     */
    @ApiModelProperty(hidden = true)
    private Long categoryId;
    /**
     * 商品规格id
     */
    @ApiModelProperty(hidden = true)
    private String specsIds;

    /**
     * 规格属性
     */
    private String specsJson;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 规格名称
     */
    private String specsName;
    /**
     * 规格价钱
     **/
    private BigDecimal specsPrice;
    /**
     * 规格属性值ID集合,隔开
     */
    private String specsValueids;

    /**
     * 拼接脐橙类型及树龄
     * */
    private String orangeTypeAndAge;
    /**
     * 预估产量
     */
    private BigDecimal preYearCrop;
    /**
     * 脐橙类型
     */
    private String orangeType;
    /**
     * 树龄
     */
    private BigDecimal treeAge;

    /**
     * 0为未推荐，1为已推荐
     */
    private String isRecommend;
    /**
     * 每份重量
     * **/
    private BigDecimal perWeight;
    /**
     * 运费模板ID
     */
    private Integer fareIdP;

    /** 溯源产品ID */
    @Excel(name = "溯源产品ID")
    private Long productId;

    /**
     * 基地id,果园
     */
    @Excel(name = "基地id,果园")
    private Long farmId;

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", brandId=" + brandId +
                ", shopId=" + shopId +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", saleAble=" + saleAble +
                ", discount='" + discount + '\'' +
                ", finalPrice=" + finalPrice +
                ", costPrice=" + costPrice +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", categoryId=" + categoryId +
                ", specsId=" + specsIds +
                ", farmId=" + farmId +
                '}';
    }
}
