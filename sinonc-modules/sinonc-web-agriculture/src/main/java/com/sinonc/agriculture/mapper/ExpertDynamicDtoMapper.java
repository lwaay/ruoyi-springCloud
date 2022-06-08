package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.ExpertDynamic;
import com.sinonc.agriculture.dto.ExpertDynamicDetailDto;
import com.sinonc.agriculture.dto.ExpertDynamicDto;

import java.util.List;

public interface ExpertDynamicDtoMapper {

    /**
     * 查询专家动态
     *
     * @param expertDynamic
     * @return
     */
    List<ExpertDynamicDto> selectExpertDynamicDtoList(ExpertDynamic expertDynamic);

    /**
     * 根据作物id查询专家动态
     *
     * @param cropIds
     * @return
     */
    List<ExpertDynamicDto> selectExpertDynamicDtoListByCropIds(List<Long> cropIds);

    /**
     * 查询专家动态详情
     *
     * @param dynamicId
     * @return
     */
    ExpertDynamicDetailDto selectExpertDynamicDetailDtoByDynamicId(Long dynamicId);
}
