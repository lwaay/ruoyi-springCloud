package com.sinonc.agriculture.vo;

import com.sinonc.base.api.domain.CropDict;
import lombok.Data;

import java.util.Date;

@Data
public class CropDictVo extends CropDict {
    private Long cropId;
    private String cropName;

    /**
     * 签名
     */
    private String sign;

    /**
     * 时间戳
     */
    private Date timestamp;
}
