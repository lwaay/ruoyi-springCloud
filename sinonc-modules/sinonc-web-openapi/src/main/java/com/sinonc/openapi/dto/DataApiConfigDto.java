package com.sinonc.openapi.dto;


import lombok.Data;

/**
 * "name": "经营主体名字",
 * "require": 1,
 * "key": "busiEntityname",
 * "check": 1
 *
 * @author hhao
 */
@Data
public class DataApiConfigDto {
    private String name;
    private String key;
    /**
     * 是否选中
     */
    private byte check;
    /**
     * 必选
     */
    private byte require;
}
