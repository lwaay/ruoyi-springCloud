package com.sinonc.origins.vo;

import lombok.Data;


@Data
public class QueryParamVo {
    private String startTime;
    private String endTime;
    private String market;
    /**
     * 多城市， 逗号分隔
     */
    private String province;
    private String category;
    private String orderBy = "asc";

    private String parentCategory;

    private int current;
    private int size;


    /**
     * 多市场， 逗号分隔
     */
    private String markets;

    /**
     * 多类别，逗号分隔
     */
    private String categorys;

    private String groupBy;
}
