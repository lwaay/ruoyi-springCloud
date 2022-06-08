package com.sinonc.orders.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图层用
 * @anthor wang
 */
@Data
public class AccountDto {

    private Long shopId;
    private String nowDate;
    private int orderType;
    //标识
    private int mark;
    private Map<String,String> params;
    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
}
