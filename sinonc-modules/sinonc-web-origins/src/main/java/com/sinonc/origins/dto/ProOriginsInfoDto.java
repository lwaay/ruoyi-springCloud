package com.sinonc.origins.dto;

import com.sinonc.origins.api.domain.ProOriginsInfo;
import com.sinonc.origins.api.domain.ProProductInfo;
import lombok.Data;

/**
 * 溯源信息对象 pro_origins_info
 *
 * @author ruoyi
 * @date 2020-10-23
 */
@Data
public class ProOriginsInfoDto extends ProOriginsInfo {
    private static final long serialVersionUID = 1L;

    //产品信息
    private ProProductInfo proProductInfo;

    //查询使用
    //产品名称
    private String productInfoName;
}
