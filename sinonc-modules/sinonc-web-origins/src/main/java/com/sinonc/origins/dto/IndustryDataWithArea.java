package com.sinonc.origins.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/4/21 16:51
 */
@Data
public class IndustryDataWithArea implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaCode;

    private String breed;

    private String yield;

    private String plantScale;

    private String value;
}
