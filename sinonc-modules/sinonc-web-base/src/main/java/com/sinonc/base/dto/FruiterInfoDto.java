package com.sinonc.base.dto;

import com.sinonc.base.api.domain.FruiterInfo;
import lombok.Data;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2022-05-05  15:58
 */
@Data
public class FruiterInfoDto  extends FruiterInfo {


    /**
     * 基地名称
     */
    private String farmName;

}
