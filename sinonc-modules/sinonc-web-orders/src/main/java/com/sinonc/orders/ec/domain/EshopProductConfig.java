package com.sinonc.orders.ec.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;

/**
 * 定时任务配置对象 eshop_product_config
 *
 * @author ruoyi
 * @date 2021-04-10
 */
public class EshopProductConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long configId;

    /**
     * 5分钟任务状态
     */
    @Excel(name = "5分钟任务状态")
    private Integer fiveminute;

    /**
     * 半小时任务状态
     */
    @Excel(name = "半小时任务状态")
    private Integer halfhour;

    /**
     * 每日任务状态
     */
    @Excel(name = "每日任务状态")
    private Integer daily;

    /**
     * $column.columnComment
     */
    @Excel(name = "每日任务状态")
    private String updateName;

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setFiveminute(Integer fiveminute) {
        this.fiveminute = fiveminute;
    }

    public Integer getFiveminute() {
        return fiveminute;
    }

    public void setHalfhour(Integer halfhour) {
        this.halfhour = halfhour;
    }

    public Integer getHalfhour() {
        return halfhour;
    }

    public void setDaily(Integer daily) {
        this.daily = daily;
    }

    public Integer getDaily() {
        return daily;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public String getUpdateName() {
        return updateName;
    }
}
