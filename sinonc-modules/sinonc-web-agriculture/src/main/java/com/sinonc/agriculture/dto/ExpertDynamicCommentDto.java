package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.ExpertDynamicComment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExpertDynamicCommentDto extends ExpertDynamicComment {

    private String toNikeName;

    private Long toMemberId;

}
