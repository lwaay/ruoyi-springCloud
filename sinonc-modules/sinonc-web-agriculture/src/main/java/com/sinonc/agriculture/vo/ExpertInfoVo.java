package com.sinonc.agriculture.vo;

import com.sinonc.agriculture.domain.ExpertCertified;
import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.domain.SectionDict;
import com.sinonc.base.api.domain.CropDict;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ExpertInfoVo extends ExpertInfo {

    /**
     * 擅长作物
     */
    private List<CropDict> cropDictList;
    /**
     * 擅长领域
     */
    private List<SectionDict> sectionDicts;

    /**
     * 一级作物ID
     */
    private Long parentCropId;
}
