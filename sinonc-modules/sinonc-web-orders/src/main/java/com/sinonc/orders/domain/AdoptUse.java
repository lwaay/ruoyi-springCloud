package com.sinonc.orders.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 服务使用记录表 od_adopt_use
 *
 * @author sinonc
 * @date 2019-08-14
 */

@Getter
@Setter
public class AdoptUse {

    private static final long serialVersionUID = 1L;

    /**  */
    private Long useId;
    /** 认养服务明细表ID */
    private Long itemIdP;
    /**  */
    private String specsValue;
    /** 使用数量 */
    private Integer numbers;
    /** 统计单位 */
    private String unit;
    /** 使用信息 */
    private String useInfo;
    /** 0，物品配送，1，其他服务 */
    private Integer itemType;
    /** 创建时间 */
    private Date createTime;
    /** 创建者 */
    private String createBy;
    /** 删除标记 */
    private Integer delFlag;


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("useId",getUseId())
                .append("itemIdP",getItemIdP())
                .append("specsValue",getSpecsValue())
                .append("numbers",getNumbers())
                .append("unit",getUnit())
                .append("useInfo",getUseInfo())
                .append("itemType",getItemType())
                .append("createTime",getCreateTime())
                .append("createBy",getCreateBy())
                .append("delFlag",getDelFlag())
                .toString();
    }
}
