package com.sinonc.agriculture.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 专家动态详情
 */
@Getter
@Setter
public class ExpertDynamicDetailDto extends ExpertDynamicDto {
    /**
     * 评论类型
     */
    private List<ExpertDynamicCommentDto> commentList;
}
