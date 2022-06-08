package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.ExpertDynamic;
import com.sinonc.agriculture.dto.ExpertDynamicDetailDto;
import com.sinonc.agriculture.dto.ExpertDynamicDto;

import java.util.List;

/**
 * 专家动态
 */
public interface ExpertDynamicDtoService {

    /**
     * 查询专家动态列表
     *
     * @param isLogin
     * @return
     */
    List<ExpertDynamicDto> listExpertDynamicDto(Boolean isLogin, ExpertDynamic expertDynamic);

    /**
     * 查询专家动态详情
     *
     * @param isLogin
     * @param dynamicId
     * @return
     */
    ExpertDynamicDetailDto getExpertDynamicDetailDto(Boolean isLogin, Long dynamicId);

}
