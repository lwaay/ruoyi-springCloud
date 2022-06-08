package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.dto.AgriQuestionDetailDto;
import com.sinonc.agriculture.dto.AgriQuestionDto;
import com.sinonc.agriculture.dto.AgriQuestionSectionDto;
import com.sinonc.agriculture.dto.AgriQuestionSimpleDto;

import java.util.List;

public interface AgriQuestionDtoService {

    /**
     * 查询问题简要列表
     *
     * @param isLogin 是否登陆
     * @return 结果
     */
    List<AgriQuestionSimpleDto> getAgriQuestionSimpleDtoList(Boolean isLogin, String ask,Long cropId,Long[] parentId,Long sectionId);

    /**
     * 查询问题详细情况
     *
     * @param questionId 问题ID
     * @return 结果
     */
    AgriQuestionDetailDto getAgriQuestionDetailDto(Boolean isLogin, Long questionId);

    /**
     * 查询我提的问题
     *
     * @param agriQuestion
     * @return
     */
    public List<AgriQuestionSimpleDto> getAgriQuestionList(Boolean isLogin, AgriQuestion agriQuestion);

    /**
     * 根据回答查询问题
     *
     * @param agriQuestion
     * @return
     */
    public List<AgriQuestionSimpleDto> selectAgriQuestionListByAnswer(Boolean isLogin, AgriQuestion agriQuestion);

    /**
     * 根据关注查询问题
     *
     * @param agriQuestion
     * @return
     */
    public List<AgriQuestionSimpleDto> selectAgriQuestionListByConcernInfo(Boolean isLogin, AgriQuestion agriQuestion);


    /**
     * 查询我的问题板块列表
     * @param memberId
     * @return
     */
    List<AgriQuestionSectionDto> selectAgriQuestionSectionList(Long memberId);

    /**
     * 推送问题列表
     * @param sectionIds
     * @return
     */
    List<AgriQuestionDto> pushAgriQuestionBySection(List<Long> sectionIds);
}
