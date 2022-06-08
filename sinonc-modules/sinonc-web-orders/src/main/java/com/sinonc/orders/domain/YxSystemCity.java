/**
* Copyright (C) 2018-2021
* All rights reserved, Designed By www.yixiang.co
* 注意：
* 本软件为www.yixiang.co开发研制，未经购买不得使用
* 购买后可获得全部源代码（禁止转卖、分享、上传到码云、github等开源平台）
* 一经发现盗用、分享等行为，将追究法律责任，后果自负
*/
package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
* @author hupeng
* @date 2020-06-29
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YxSystemCity implements Serializable {

    private Integer id;

    /** 城市id */
    @NotNull
    @JsonProperty(value = "city_id")
    private Integer cityId;


    /** 省市级别 */
    @NotNull
    private Integer level;


    /** 父级id */
    @NotNull
    private Integer parentId;


    /** 区号 */
    @NotBlank
    private String areaCode;


    /** 名称 */
    @NotBlank
    private String name;


    /** 合并名称 */
    @NotBlank
    private String mergerName;


    /** 经度 */
    @NotBlank
    private String lng;


    /** 纬度 */
    @NotBlank
    private String lat;


    /** 是否展示 */
    @NotNull
    private Integer isShow;

    private List<YxSystemCity> children;
}
