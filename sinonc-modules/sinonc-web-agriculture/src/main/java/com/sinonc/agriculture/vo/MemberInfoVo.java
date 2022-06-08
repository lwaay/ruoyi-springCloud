package com.sinonc.agriculture.vo;

import com.sinonc.agriculture.domain.GrowExper;
import com.sinonc.agriculture.domain.MemberInfo;

import java.util.List;

/**
 * 会员传输对象
 */
public class MemberInfoVo extends MemberInfo {

    /**
     * 种植经验
     */
    private List<GrowExper> growExperList;

    public List<GrowExper> getGrowExperList() {
        return growExperList;
    }

    public void setGrowExperList(List<GrowExper> growExperList) {
        this.growExperList = growExperList;
    }


}
