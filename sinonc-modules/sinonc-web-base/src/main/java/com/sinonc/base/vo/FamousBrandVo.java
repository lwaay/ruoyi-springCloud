package com.sinonc.base.vo;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 著名品牌对象 famous_brand
 *
 * @author ruoyi
 * @date 2021-05-15
 */
@Data
public class FamousBrandVo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long brandId;

    /**
     * 评定机构
     */
    @Excel(name = "评定机构")
    private String assessment;

    /**
     * 品牌声誉
     */
    @Excel(name = "品牌声誉")
    private String reputation;

    /**
     * 品牌名称
     */
    @Excel(name = "品牌名称")
    private String brandName;

    /**
     * 品牌主体
     */
    @Excel(name = "品牌主体")
    private String company;

    /**
     * 评定时间
     */
    @Excel(name = "评定时间")
    private String assessmentTime;

    /**
     * 有效期限
     */
    @Excel(name = "有效期限")
    private String validityTerm;

    /**
     * 证明文件
     */
    @Excel(name = "证明文件")
    private String evidence;

    private Integer status;

    /**
     * 分页
     */
    private Integer pageNum;
    private Integer pageSize;
}
