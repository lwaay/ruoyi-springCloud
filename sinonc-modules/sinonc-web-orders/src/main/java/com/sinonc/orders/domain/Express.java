package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 物流表 od_express
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("Express")
public class Express {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long expressId;
    /** 订单ID */
    private Long orderIdP;
    /** 店铺ID */
    private Long shopIdP;
    /** 所属用户（买家）id */
    private Long memberIdP;
    /** 物流公司ID */
    private Long expressCompanyId;
    /** 快递公司名称 */
    @NotEmpty(message="快递公司名称不能为空！")
    private String expressName;
    /** 快递单号 */
    @NotEmpty(message="快递单号不能为空！")
    private String expressNo;
    /** 创建者用户(卖家）ID */
    private String createBy;
    /** 由谁发货 */
    private Long expressBy;
    /** 创建时间 */
    private Date createTime;
    /**  */
    private String updateBy;
    /** 更新时间 */
    private Date updateTime;

    private Map<String, String> params;
    /** 视图层用*/
    private String shopName;
    /** 视图层用*/
    private String memberName;
    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setExpressId(Long expressId) {
            this.expressId = expressId;
    }

    public Long getExpressId() {
            return expressId;
    }
    public void setOrderIdP(Long orderIdP) {
            this.orderIdP = orderIdP;
    }

    public Long getOrderIdP() {
            return orderIdP;
    }
    public void setShopIdP(Long shopIdP) {
            this.shopIdP = shopIdP;
    }

    public Long getShopIdP() {
            return shopIdP;
    }
    public void setMemberIdP(Long memberIdP) {
            this.memberIdP = memberIdP;
    }

    public Long getMemberIdP() {
            return memberIdP;
    }
    public void setExpressCompanyId(Long expressCompanyId) {
            this.expressCompanyId = expressCompanyId;
    }

    public Long getExpressCompanyId() {
            return expressCompanyId;
    }
    public void setExpressName(String expressName) {
            this.expressName = expressName;
    }

    public String getExpressName() {
            return expressName;
    }
    public void setExpressNo(String expressNo) {
            this.expressNo = expressNo;
    }

    public String getExpressNo() {
            return expressNo;
    }
    public void setCreateBy(String createBy) {
            this.createBy = createBy;
    }

    public String getCreateBy() {
            return createBy;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }
    public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
    }

    public String getUpdateBy() {
            return updateBy;
    }
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }

    public Long getExpressBy() {
        return expressBy;
    }

    public void setExpressBy(Long expressBy) {
        this.expressBy = expressBy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("expressId",getExpressId())
                .append("orderIdP",getOrderIdP())
                .append("shopIdP",getShopIdP())
                .append("memberIdP",getMemberIdP())
                .append("expressCompanyId",getExpressCompanyId())
                .append("expressName",getExpressName())
                .append("expressNo",getExpressNo())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateBy",getUpdateBy())
                .append("updateTime",getUpdateTime())
                .toString();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
