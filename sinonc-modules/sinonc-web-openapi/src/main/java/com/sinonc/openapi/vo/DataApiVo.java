package com.sinonc.openapi.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sinonc.openapi.dto.DataApiConfigDto;
import lombok.Data;

import java.util.List;

@Data
public class DataApiVo {

    private Long id;

    @TableField(exist = false)
    private Long dataApiItemId;
    /**
     * 配置
     */
    @TableField(exist = false)
    private List<DataApiConfigDto> configList;
}

