package com.sinonc.orders.domain;

import lombok.Data;

import java.util.Date;

/**
 * 认养服务明细表 od_adopt_item
 *
 * @author sinonc
 * @date 2019-08-09
 */

@Data
public class AdoptItem {

    private static final long serialVersionUID = 1L;

    /** 服务项目ID */
    private Long adoptItemId;
    /** 会员id */
    private Long memberIdP;
    /** 订单ID */
    private Long orderIdP;
    /** 订单号 */
    private String orderNo;
    /**备注信息*/
    private String remark;
    /** 商品名称 */
    private String goodsName;
    /** 规格(套餐)名称 */
    private String specsName;
    /** 商品套餐服务项（规格值） */
    private String itemName;
    /** 服务状态，0：未使用，1，已使用 */
    private Integer itemStatus;
    /** 服务类别：0，物品配送；1：其他服务 */
    private Integer itemType;
    /**
     * 总数
     */
    private Integer number;
    /**
     * 已使用数
     */
    private Integer useNumber;
    /**
     * 单位
     */
    private String unit;
    /**  */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;
    /** 服务使用时间 */
    private Date useTime;
    /** 服务生效时间 */
    private Date startTime;
    /** 服务失效时间 */
    private Date endTime;
    /** 更新操作员用户名 */
    private String updateBy;
    /**  */
    private Integer delFlag;



}
