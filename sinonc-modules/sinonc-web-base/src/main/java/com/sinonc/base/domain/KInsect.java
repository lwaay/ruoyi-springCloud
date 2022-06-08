package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 病虫害对象 k_insect
 *
 * @author ruoyi
 * @date 2022-04-19
 */
@Data
public class KInsect extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 病虫害编号
     */
    @Excel(name = "病虫害编号")
    private String insectCode;

    /**
     * 病虫害名称
     */
    @Excel(name = "病虫害名称")
    private String insectName;

    /**
     * 病虫害图片
     */
    @Excel(name = "病虫害图片")
    private String insectImage;

    /**
     * 防治指标或时期
     */
    @Excel(name = "防治指标或时期")
    private String cureTime;

    /**
     * 病虫害特征
     */
    @Excel(name = "病虫害特征")
    private String insectFeature;

    /**
     * 盛行期或流行期
     */
    @Excel(name = "盛行期或流行期")
    private String insectTime;

    /**
     * 创建人
     */
    private String createUser;

}
