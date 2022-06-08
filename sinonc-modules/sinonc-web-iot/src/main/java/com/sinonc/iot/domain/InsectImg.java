package com.sinonc.iot.domain;


import com.sinonc.common.core.web.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 虫情图片对象 insect_img
 *
 * @author ruoyi
 * @date 2020-12-10
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel("虫情图片")
public class InsectImg extends BaseEntity {

    /**
     * ID
     */
    private Long id;

    /**
     * $column.columnComment
     */
    @ApiModelProperty(value = "${comment}")
    private String deviceId;

    /**
     * $column.columnComment
     */
    @ApiModelProperty(value = "${comment}")
    private String imgUrl;
    /**
     * 识别结果
     */
    private String resultJson;
    /**
     * 识别图片结果
     */
    private String resultImgUrl;

}
