package com.sinonc.origins.vo;

import com.sinonc.origins.api.domain.ProProductInfo;
import lombok.Data;

/**
 * @author zhangxinlong
 * @date 2020/10/22  14:42
 */
@Data
public class ProProductInfoVo extends ProProductInfo {

    /**
     * 摄像头
     */
    private Long[] monitors;

}
