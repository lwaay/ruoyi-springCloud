package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.ExpertDynamic;
import com.sinonc.agriculture.domain.MemberInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * 专家动态dto
 */
@Getter
@Setter
public class ExpertDynamicDto extends ExpertDynamic {

    /**
     * 发布动态的会员信息
     */
    private MemberInfo memberInfo;

    /**
     * 点过赞的会员简要信息列表
     */
    private List<Map<String, Object>> likeList;

    /**
     * 作物名称
     */
    private String cropName;

    /**
     * 板块名称
     */
    private String sectionName;

    /**
     * 是否已经点赞
     */
    private Boolean isLike = false;
}
