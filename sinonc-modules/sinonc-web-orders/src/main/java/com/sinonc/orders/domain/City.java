package com.sinonc.orders.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 省市县数据表 pro_city
 *
 * @author sinonc
 * @date 2019-12-04
 */

public class City {

    private static final long serialVersionUID = 1L;

    /** 地区代码 */
    private Integer id;
    /** 当前地区的上一级地区代码 */
    private Integer pid;
    /** 地区名称 */
    private String name;

    public void setId(Integer id) {
            this.id = id;
    }

    public Integer getId() {
            return id;
    }
    public void setPid(Integer pid) {
            this.pid = pid;
    }

    public Integer getPid() {
            return pid;
    }
    public void setName(String name) {
            this.name = name;
    }

    public String getName() {
            return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("pid",getPid())
                .append("name",getName())
                .toString();
    }
}
