package com.sinonc.orders.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.order.api.domain.Shop;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 商品详情Vo
 * 包含商品信息和商品规格信息
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsDetailVo implements Serializable {
    //商品ID
    private Long goodsId;
    //商品名称
    private String name;
    //店铺ID
    private Long shopId;
    //商品描述
    private String remark;
    //商品图片
    private String image;
    //商品视频
    private String video;
    //商品图文详情
    private String content;
    //商品类型
    private Integer type;
    //商品价格区间（用于前端显示）
    private String price;
    //预订商品定金（仅限预订商品）
    private String earnest;
    //预订开始时间
    private String bkStart;
    //预订结束时间
    private String bkEnd;
    //预订状态
    private String bkStatus;
    //商品规格列表
    private List<SpecsDetailVo> specsList;
    // 0为未推荐，1为已推荐
    private String isRecommend;
    //销量
    private Integer saleCount;
    //图片集合
    private String[] imgList;
    //溯源id
    private String productId;
    //店铺信息
    private Shop shop;
    /**
     * 果园id
     */
    private Long farmId;

    /**
     * 是否收藏(0-是,1否)
     */
    private String isAttention;

}
