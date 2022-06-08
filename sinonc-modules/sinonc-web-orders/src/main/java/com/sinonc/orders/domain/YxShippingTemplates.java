/**
* Copyright (C) 2018-2021
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package com.sinonc.orders.domain;

import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
* @author hupeng
* @date 2020-06-29
*/
@Data
public class YxShippingTemplates extends BaseEntity {

    /** 模板ID */
    private Integer id;


    /** 模板名称 */
    private String name;


    /** 计费方式 */
    private Integer type;


    /** 地域以及费用 */
    private String regionInfo;


    /** 指定包邮开关 */
    private Integer appoint;


    /** 指定包邮内容 */
    private String appointInfo;


    /** 是否删除*/
    private Integer isDel;

    /** 排序 */
    private Integer sort;

    /** 关联主体id*/
    private Long entityId;

    /** 关联店铺id*/
    private Long shopId;
}
