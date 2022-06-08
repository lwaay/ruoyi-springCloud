package com.sinonc.orders.vo;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import com.sinonc.orders.domain.OdCart;
import lombok.Data;

import java.util.List;

/**
 * 店铺信息对象 od_shop
 *
 * @author ruoyi
 * @date 2022-03-30
 */
@Data
public class ShopVo {

    /**
     * 主键id自增
     */
    private Long shopId;

    /**
     * 经营主体ID
     */
    @Excel(name = "经营主体ID")
    private Long entityId;

    /**
     * 店铺号
     */
    @Excel(name = "店铺号")
    private String shopCode;

    /**
     * 店铺名称
     */
    @Excel(name = "店铺名称")
    private String shopName;

    /**
     * 店长会员ID
     */
    @Excel(name = "店长会员ID")
    private String ownerMemberid;

    /**
     * 店铺logo
     */
    @Excel(name = "店铺logo")
    private String shopLogo;

    /**
     * 店铺地址
     */
    @Excel(name = "店铺地址")
    private String shopAddress;

    /**
     * 联系人
     */
    @Excel(name = "联系人")
    private String concats;

    /**
     * 联系人号码
     */
    @Excel(name = "联系人号码")
    private String concatNumber;

    /**
     * 是否删除
     */
    private Integer delFlag;

    /**
     * 审核状态
     */
    @Excel(name = "审核状态")
    private Integer status;

    @Excel(name = "粉丝数")
    private Integer fansCount;

    /**
     * 0为未推荐，1为已推荐
     */
    @Excel(name = "0为未推荐，1为已推荐")
    private String isRecommend;

    private List<OdCartVo> carts;
}
