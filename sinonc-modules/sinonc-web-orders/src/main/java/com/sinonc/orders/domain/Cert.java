package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * 认养证书表 od_cert
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Alias("Cert")
public class Cert {

    private static final long serialVersionUID = 1L;

    /** 证书表主键ID */
    private Long certId;
    /** 图片路径 */
    private String imgUrl;
    /** 证书类型 0：认养牌 1：认养证书 2：公益证书 */
    private Integer certType;
    /** 橙树编号 */
    private String servNo;
    /** 用户ID */
    private Long userId;
    /**
     * 订单ID
     */
    private Long orderId;
    /** 认养牌祝福语 */
    private String blessDesc;
    /** 橙树主人 */
    private String masterName;
    /** 城市 */
    private String city;
    /** 模板编号 */
    private String templetNo;
    /** 认养时间 */
    private Date adoptTime;
    /** 创建人 */
    private String createBy;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**  */
    private String updateBy;

    /**套餐名称*/
    private String specsName;
    private String searchDate;
    public void setCertId(Long certId) {
            this.certId = certId;
    }

    public Long getCertId() {
            return certId;
    }
    public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
            return imgUrl;
    }
    public void setCertType(Integer certType) {
            this.certType = certType;
    }

    public Integer getCertType() {
            return certType;
    }
    public void setServNo(String servNo) {
            this.servNo = servNo;
    }

    public String getServNo() {
            return servNo;
    }
    public void setUserId(Long userId) {
            this.userId = userId;
    }

    public Long getUserId() {
            return userId;
    }
    public void setBlessDesc(String blessDesc) {
            this.blessDesc = blessDesc;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getBlessDesc() {
            return blessDesc;
    }
    public void setMasterName(String masterName) {
            this.masterName = masterName;
    }

    public String getMasterName() {
            return masterName;
    }
    public void setCity(String city) {
            this.city = city;
    }

    public String getCity() {
            return city;
    }
    public void setTempletNo(String templetNo) {
            this.templetNo = templetNo;
    }

    public String getTempletNo() {
            return templetNo;
    }
    public void setAdoptTime(Date adoptTime) {
            this.adoptTime = adoptTime;
    }

    public Date getAdoptTime() {
            return adoptTime;
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
    public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
            return updateTime;
    }
    public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
    }

    public String getUpdateBy() {
            return updateBy;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("certId",getCertId())
                .append("imgUrl",getImgUrl())
                .append("certType",getCertType())
                .append("servNo",getServNo())
                .append("userId",getUserId())
                .append("orderId",getOrderId())
                .append("blessDesc",getBlessDesc())
                .append("masterName",getMasterName())
                .append("city",getCity())
                .append("templetNo",getTempletNo())
                .append("adoptTime",getAdoptTime())
                .append("createBy",getCreateBy())
                .append("createTime",getCreateTime())
                .append("updateTime",getUpdateTime())
                .append("updateBy",getUpdateBy())
                .toString();
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }
}
