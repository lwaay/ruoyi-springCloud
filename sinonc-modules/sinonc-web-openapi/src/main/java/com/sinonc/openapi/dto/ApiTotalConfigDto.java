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
public class ApiTotalConfigDto {

    private Long pageNum;

    private Long pageSize;

    private String tables;

    private String columns;

    private Long start;
    private Long end;
}
