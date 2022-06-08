package com.sinonc.orders.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户关系实体
 */
@Getter
@Setter
public class UserRelation {

    private Long userId;

    private Long deptId;

    private Long farmId;

    private Long shopId;

    private String deptName;

    private String shopName;
}
