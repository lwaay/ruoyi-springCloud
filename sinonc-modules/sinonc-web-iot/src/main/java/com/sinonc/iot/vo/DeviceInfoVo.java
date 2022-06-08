package com.sinonc.iot.vo;

import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.iot.api.domain.DeviceInfo;

import java.util.List;

/**
 * @author: yehuiwang
 * @date: 2020/11/9 11:43
 * @description:
 */
public class DeviceInfoVo extends DeviceInfo {
    private String typeName;
    private String type;

    private BaseFarm baseFarm;
    private List<DataPointVo> points;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BaseFarm getBaseFarm() {
        return baseFarm;
    }

    public void setBaseFarm(BaseFarm baseFarm) {
        this.baseFarm = baseFarm;
    }

    public List<DataPointVo> getPoints() {
        return points;
    }

    public void setPoints(List<DataPointVo> points) {
        this.points = points;
    }
}
