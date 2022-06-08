package com.sinonc.agriculture.mapper;


import com.sinonc.agriculture.domain.AgriQuestion;
import com.sinonc.agriculture.dto.AgriQuestionDetailDto;
import com.sinonc.agriculture.dto.AgriQuestionDto;
import com.sinonc.agriculture.dto.AgriQuestionSectionDto;
import com.sinonc.agriculture.dto.AgriQuestionSimpleDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgriQuestionDtoMapper {

    /**
     * 查询首页问题列表数据
     *
     * @return 结果
     */
    List<AgriQuestionSimpleDto> selectAgriQuestionSimpleDtoList(@Param("ask")String ask, @Param("cropId")Long cropId, @Param("parentId")Long[] parentId, @Param("sectionId")Long sectionId);

    /**
     * 查询问题详情
     *
     * @param questionId 问题ID
     * @return 结果
     */
    AgriQuestionDetailDto selectAgriQuestionDetailDto(Long questionId);

    /**
     * 查询我提的问题
     *
     * @param agriQuestion
     * @return
     */
    List<AgriQuestionSimpleDto> selectOwmAgriQuestionList(AgriQuestion agriQuestion);

    List<AgriQuestionSimpleDto> selectAgriQuestionListByAnswer(AgriQuestion agriQuestion);

    List<AgriQuestionSimpleDto> selectAgriQuestionListByConcernInfo(AgriQuestion agriQuestion);

    /**
     * 查询我的问题板块列表（关注的问题板块）
     * @param memberId
     * @return
     */
    List<AgriQuestionSectionDto> selectAgriQuestionSectionList(@Param("memberId")Long memberId);

    /**
     * 推送关注的问题列表
     * @param sectionIds
     * @return
     */
    List<AgriQuestionDto> selectAgriQuestionBySection(@Param("sectionIds")List<Long> sectionIds, @Param("limiter")Integer limiter);
}
