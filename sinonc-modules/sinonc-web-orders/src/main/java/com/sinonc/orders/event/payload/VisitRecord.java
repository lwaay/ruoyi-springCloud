package com.sinonc.orders.event.payload;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/31 15:41
 */
@Data
@Builder
public class VisitRecord {

    private Long userId;

    private Long targetId;

    private Date visitDate;
}
