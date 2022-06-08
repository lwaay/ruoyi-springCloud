package com.sinonc.agriculture.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * msg_content
 *
 * @author
 */
@ApiModel
@Data
public class MsgContent implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long msgId;

    @NotEmpty(message = "sessionId不能为空")
    @ApiModelProperty(required = true)
    private String sessionId;

    @NotEmpty(message = "content不能为空")
    @ApiModelProperty(required = true)
    private String content;

    /**
     * 消息类型，0，文本，1，图片，2，语音，3，视频
     */
    @NotNull(message = "消息类型不能为空")
    @ApiModelProperty(required = true)
    private Integer type;

    /**
     * 消息发送者
     */
    @ApiModelProperty(hidden = true)
    private Long fromMemberId;

    /**
     * 消息接收者
     */
    @ApiModelProperty(required = true)
    private Long toMemberId;

    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty(hidden = true)
    private Date updateTime;

    @ApiModelProperty(hidden = true)
    private Integer status;

}