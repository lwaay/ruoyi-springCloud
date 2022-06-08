package com.sinonc.agriculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * answer_option
 *
 * @author
 */
@Data
public class AnswerOption implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long opId;


    /**
     * 问题ID
     */
    @NotNull(message = "questionId不能为空")
    private Long questionId;


    /**
     * 回答ID
     */
    @ApiModelProperty(required = true)
    @NotNull(message = "answerId不能为空")
    private Long answerId;

    /**
     * 会员ID
     */
    @NotNull(message = "memberId不能为空")
    @ApiModelProperty(hidden = true)
    private Long memberId;


    /**
     * 选择类型，0，点赞，1，反对
     */
    @NotNull(message = "选择类型不能为空")
    @ApiModelProperty(required = true)
    private Integer opType;


    @ApiModelProperty(hidden = true)
    private Date createTime;

    @JsonIgnore
    private static final long serialVersionUID = 1L;
}