package com.sinonc.order.api.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;

/**
 * 店铺信息对象 od_shop
 *
 * @author ruoyi
 * @date 2022-03-30
 */
public class Shop extends BaseEntity {
    private static final long serialVersionUID = 1L;

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
    private Long ownerMemberid;

    /**
     * 店铺logo
     */
    @Excel(name = "店铺logo")
    private String shopLogo;

    /** 店铺背景图 */
    @Excel(name = "店铺背景图")
    private String backPic;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getOwnerMemberid() {
        return ownerMemberid;
    }

    public void setOwnerMemberid(Long ownerMemberid) {
        this.ownerMemberid = ownerMemberid;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getBackPic() {
        return backPic;
    }

    public void setBackPic(String backPic) {
        this.backPic = backPic;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getConcats() {
        return concats;
    }

    public void setConcats(String concats) {
        this.concats = concats;
    }

    public String getConcatNumber() {
        return concatNumber;
    }

    public void setConcatNumber(String concatNumber) {
        this.concatNumber = concatNumber;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }
}
