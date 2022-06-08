package com.sinonc.orders.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import lombok.Data;

/**
 * 轮播图对象 od_advertisement
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@Data
public class OdAdvertisement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 广告ID
     */
    private Long adverId;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String type;

    /**
     * 广告图片路径
     */
    @Excel(name = "广告图片路径")
    private String imageUrl;

    /**
     * 网址
     */
    @Excel(name = "网址")
    private String webUrl;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String description;

    /**
     * 关联业务ID(如：商品ID)
     */
    @Excel(name = "关联业务ID(如：商品ID)")
    private Long buId;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createUser;

}
