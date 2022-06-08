package com.sinonc.orders.dto;

import com.sinonc.orders.domain.CarryMode;
import com.sinonc.orders.domain.FareTemplate;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FareDto {

    /**
     * 运费模板
     */
    private FareTemplate fareTemplate;

    /**
     * 默认
     */
    private CarryMode carryModeldefault;

    /**
     * 运送方式
     */
    private List<CarryMode> carryModeList;



}
