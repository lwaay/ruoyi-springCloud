package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 退款申请日志表 od_refund_log
 *
 * @author sinonc
 * @date 2019-11-11
 */

public class RefundLog {

    private static final long serialVersionUID = 1L;

    /** 日志id */
    private Long logId;
    /** 退款申请ID */
    private Long rfId;
    /** 日志内容 */
    private String content;
    /** 创建时间 */
    private Date createTime;

    public void setLogId(Long logId) {
            this.logId = logId;
    }

    public Long getLogId() {
            return logId;
    }
    public void setRfId(Long rfId) {
            this.rfId = rfId;
    }

    public Long getRfId() {
            return rfId;
    }
    public void setContent(String content) {
            this.content = content;
    }

    public String getContent() {
            return content;
    }
    public void setCreateTime(Date createTime) {
            this.createTime = createTime;
    }

    public Date getCreateTime() {
            return createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("logId",getLogId())
                .append("rfId",getRfId())
                .append("content",getContent())
                .append("createTime",getCreateTime())
                .toString();
    }
}
