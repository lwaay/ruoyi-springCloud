package com.sinonc.agriculture.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 我关注的问题板块
 */
@Getter
@Setter
public class AgriQuestionSectionDto {

    /**
     * 版块ID
     */
    private Long sectionId;

    /**
     * 版块名称
     */
    private String sectionName;
}
