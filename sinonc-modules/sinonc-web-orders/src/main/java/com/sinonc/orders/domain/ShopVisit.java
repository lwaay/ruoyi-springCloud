package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 店铺访问量表 od_shop_visit
 *
 * @author sinonc
 * @date 2019-11-19
 */

public class ShopVisit {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long visitId;
    /** 店铺ID */
    private Long shopIdP;
    /** 会员ID */
    private Long memberIdP;
    /** 访问日期 */
    private Date visitDate;

    /** 访问量 */
    private Long visitNumber;

    private Map<String,String> params;
    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public Long getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(Long visitNumber) {
        this.visitNumber = visitNumber;
    }

    public void setVisitId(Long visitId) {
            this.visitId = visitId;
    }

    public Long getVisitId() {
            return visitId;
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
    public void setVisitDate(Date visitDate) {
            this.visitDate = visitDate;
    }

    public Date getVisitDate() {
            return visitDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("visitId",getVisitId())
                .append("shopIdP",getShopIdP())
                .append("memberIdP",getMemberIdP())
                .append("visitDate",getVisitDate())
                .append("visitNumber",getVisitNumber())
                .toString();
    }
}
