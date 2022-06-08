package com.sinonc.origins.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 产品摄像头关联关系对象 pro_monitors_relation
 *
 * @author ruoyi
 * @date 2020-10-22
 */
@Data
public class ProMonitorsRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long proMonitorsId;

    /**
     * 产品信息主键
     */
    @Excel(name = "产品信息主键")
    private Long productIdP;

    /**
     * 摄像头主键
     */
    @Excel(name = "摄像头主键")
    private Long monitorsIdP;


}
