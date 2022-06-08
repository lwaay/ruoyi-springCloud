package com.sinonc.orders.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 访问记录对象 od_member_visit
 *
 * @author ruoyi
 * @date 2022-03-31
 */
@Data
public class OdMemberVisit extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private Long id;

    /**
     * 用户id
     */
    @Excel(name = "用户id")
    private Long userId;

    /**
     * 目标id
     */
    @Excel(name = "目标id")
    private Long targetId;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date visitTime;

}
