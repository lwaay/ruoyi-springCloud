package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.ExpertInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 历史咨询
 *
 * @author: lw
 * @date: 2022/5/11 11:17
 * @description:
 */
@Getter
@Setter
public class HistoryDto {

    /**
     * 专家信息
     */
    private ExpertInfo expertInfo;

    /**
     * 问题列表
     */
    private List<AgriQuestionSimpleDto> questionSimpleDtoList;

}
