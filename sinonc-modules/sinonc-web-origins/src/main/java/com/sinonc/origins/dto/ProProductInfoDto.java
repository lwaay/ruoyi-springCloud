package com.sinonc.origins.dto;

import com.sinonc.origins.api.domain.PmBusiness;
import com.sinonc.origins.api.domain.ProProductInfo;
import com.sinonc.origins.domain.ProBrand;
import lombok.Data;

/**
 * 产品信息对象 pro_product_info
 *
 * @author zhangxl
 * @date 2020-10-21
 */
@Data
public class ProProductInfoDto extends ProProductInfo {
    private static final long serialVersionUID = 1L;


    //经营主体信息
    private PmBusiness pmBusiness;

    //品牌信息
    private ProBrand proBrand;

    //查询使用字段
    //经营主体名称
    private String pmBusinessName;

    //品牌名称
    private String proBrandName;

    /**
     * 摄像头
     */
    private Long[] monitors;
}
