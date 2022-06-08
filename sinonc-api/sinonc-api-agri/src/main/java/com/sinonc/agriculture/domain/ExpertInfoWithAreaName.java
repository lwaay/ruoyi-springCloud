package com.sinonc.agriculture.domain;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/7 11:33
 */
public class ExpertInfoWithAreaName extends ExpertInfo{
    private static final long serialVersionUID = 1L;

    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
