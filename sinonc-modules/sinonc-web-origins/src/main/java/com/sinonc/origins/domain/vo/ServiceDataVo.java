package com.sinonc.origins.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/4/22 10:03
 */
@Data
@AllArgsConstructor
public class ServiceDataVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key;

    private int value;

    private String unit;
}
