package com.sinonc.base.api.domain;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 运输信息（水产）
 * 对象 origins_fish_tran
 *
 * @author ruoyi
 * @date 2021-04-25
 */
public class OriginsFishTran extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */
    private Long id;
    /**
     * 主键
     */
    private String tranId;

    /**
     * 批次ID
     */
    @Excel(name = "批次ID")
    private String batchId;

    /**
     * 运输方式
     */
    @Excel(name = "运输方式")
    private String tranWay;

    /**
     * 保鲜用药名称
     */
    @Excel(name = "保鲜用药名称")
    private String bxyymc;

    /**
     * 用药量
     */
    @Excel(name = "用药量")
    private BigDecimal yyl;

    /**
     * 用药量单位
     */
    @Excel(name = "用药量单位")
    private String yydw;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;


    @JSONField(name = "TRAN_ID")
    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getTranId() {
        return tranId;
    }

    @JSONField(name = "BATCH_ID")
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchId() {
        return batchId;
    }

    @JSONField(name = "TRAN_WAY")
    public void setTranWay(String tranWay) {
        this.tranWay = tranWay;
    }

    public String getTranWay() {
        return tranWay;
    }

    @JSONField(name = "BXYYMC")
    public void setBxyymc(String bxyymc) {
        this.bxyymc = bxyymc;
    }

    public String getBxyymc() {
        return bxyymc;
    }

    @JSONField(name = "YYL")
    public void setYyl(BigDecimal yyl) {
        this.yyl = yyl;
    }

    public BigDecimal getYyl() {
        return yyl;
    }

    @JSONField(name = "YYDW")
    public void setYydw(String yydw) {
        this.yydw = yydw;
    }

    public String getYydw() {
        return yydw;
    }

    @JSONField(name = "REMARKS")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tranId", getTranId())
                .append("batchId", getBatchId())
                .append("tranWay", getTranWay())
                .append("bxyymc", getBxyymc())
                .append("yyl", getYyl())
                .append("yydw", getYydw())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("remarks", getRemarks())
                .toString();
    }
}
