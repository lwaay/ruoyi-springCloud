package com.sinonc.orders.vo;

import com.sinonc.orders.domain.AdoptItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdoptItemVo extends AdoptItem {
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 会员ID
     */
    private Long memberId;

    private Map<String,String> params;
    public Map<String, String> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
}
