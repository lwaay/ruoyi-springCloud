package com.sinonc.base.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.base.api.domain.CropDict;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 作物vo
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CropChildVo extends CropDict {

    private List<CropDict> list;

    private List<CropDict> cropChildren;

}
