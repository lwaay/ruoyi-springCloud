package com.sinonc.system.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.Date;

/**
 * 数据导入日志对象 sys_import_log
 *
 * @author ruoyi
 * @date 2021-02-05
 */
@ApiModel("数据导入日志")
public class SysImportLog extends BaseEntity {

    /** 主键 */
    private Long logId;

    /**
     * 操作模块
     */
    @ApiModelProperty(name = "操作模块")
    private String title;

    /** 任务名称 */
    @ApiModelProperty(value="任务名称")
    private String taskName;

    /** 操作时间 */
    @ApiModelProperty(value="操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "浏览时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operatorTime;

    /** 操作人 */
    @ApiModelProperty(value="操作人")
    private Long operatorBy;

    /** 操作人名称 */
    @ApiModelProperty(value="操作人名称")
    private String operatorName;

    /** 成功插入条数 */
    @ApiModelProperty(value="成功插入条数")
    private Long successCount;

    /** 插入失败条数 */
    @ApiModelProperty(value="插入失败条数")
    private Long failCount;

    /** 详情 */
    @ApiModelProperty(value="详情")
    private String detail;

    /** 操作人ip地址 */
    @ApiModelProperty(value="操作人ip地址")
    private String operatorIp;

    private Date beginOperatorTime;
    private Date endOperatorTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Long getOperatorBy() {
        return operatorBy;
    }

    public void setOperatorBy(Long operatorBy) {
        this.operatorBy = operatorBy;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Long successCount) {
        this.successCount = successCount;
    }

    public Long getFailCount() {
        return failCount;
    }

    public void setFailCount(Long failCount) {
        this.failCount = failCount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    public Date getBeginOperatorTime() {
        return beginOperatorTime;
    }

    public void setBeginOperatorTime(Date beginOperatorTime) {
        this.beginOperatorTime = beginOperatorTime;
    }

    public Date getEndOperatorTime() {
        return endOperatorTime;
    }

    public void setEndOperatorTime(Date endOperatorTime) {
        this.endOperatorTime = endOperatorTime;
    }

    @Override
    public String toString() {
        return "SysImportLog{" +
                "logId=" + logId +
                ", title='" + title + '\'' +
                ", taskName='" + taskName + '\'' +
                ", operatorTime=" + operatorTime +
                ", operatorBy=" + operatorBy +
                ", operatorName='" + operatorName + '\'' +
                ", successCount=" + successCount +
                ", failCount=" + failCount +
                ", detail='" + detail + '\'' +
                ", operatorIp='" + operatorIp + '\'' +
                ", beginOperatorTime=" + beginOperatorTime +
                ", endOperatorTime=" + endOperatorTime +
                '}';
    }
}
