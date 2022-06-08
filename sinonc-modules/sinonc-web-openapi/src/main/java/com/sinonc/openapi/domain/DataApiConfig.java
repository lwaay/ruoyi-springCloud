package com.sinonc.openapi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author hhao
 * 配置
 */
@Data
public class DataApiConfig {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long dataApiId;
    private Long dataApiItemId;
    private String config;
    private String appLabel;
}
