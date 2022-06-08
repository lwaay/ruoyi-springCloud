package com.sinonc.agriculture.vo;

import com.sinonc.agriculture.domain.ExpertCertifiedModify;
import com.sinonc.agriculture.domain.ExpertInfoModify;

import java.util.List;

/**
 * 专家信息暂存对象 expert_info_modify
 *
 * @author ruoyi
 * @date 2020-04-07
 */
public class ExpertInfoModifyVo extends ExpertInfoModify {
    /**
     * 专家资格证明暂存
     */
    private List<ExpertCertifiedModify> expertCertifiedModifyList;

    public List<ExpertCertifiedModify> getExpertCertifiedModifyList() {
        return expertCertifiedModifyList;
    }

    public void setExpertCertifiedModifyList(List<ExpertCertifiedModify> expertCertifiedModifyList) {
        this.expertCertifiedModifyList = expertCertifiedModifyList;
    }
}
