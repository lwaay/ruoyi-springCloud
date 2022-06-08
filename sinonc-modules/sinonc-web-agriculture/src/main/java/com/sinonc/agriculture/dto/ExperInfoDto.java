package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.domain.MemberInfo;
import com.sinonc.agriculture.domain.SectionDict;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.system.api.domain.WxUser;

import java.util.List;

public class ExperInfoDto extends ExpertInfo {



    /**
     * 擅长作物
     */
    private List<CropDict> cropDictList;
    /**
     * 擅长领域
     */
    private List<SectionDict> sectionDicts;

    public List<CropDict> getCropDictList() {
        return cropDictList;
    }

    public void setCropDictList(List<CropDict> cropDictList) {
        this.cropDictList = cropDictList;
    }

    public List<SectionDict> getSectionDicts() {
        return sectionDicts;
    }

    public void setSectionDicts(List<SectionDict> sectionDicts) {
        this.sectionDicts = sectionDicts;
    }

}
