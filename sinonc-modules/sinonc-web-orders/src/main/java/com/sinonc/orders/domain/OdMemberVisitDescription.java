package com.sinonc.orders.domain;

import lombok.Data;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/31 17:16
 */
@Data
public class OdMemberVisitDescription extends OdMemberVisit{

    private String title;

    private String image;

    private String price;
}
