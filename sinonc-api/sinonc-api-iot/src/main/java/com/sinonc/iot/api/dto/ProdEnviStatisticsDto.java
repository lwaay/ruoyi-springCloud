package com.sinonc.iot.api.dto;


/**
 * @author zhangxinlong
 * @date 2021-11-17  11:10
 */
public class ProdEnviStatisticsDto {

    /**
     * 活动积温
     */
    private double accumulateTemperature;

    /**
     * 平均光照强度
     */
    private double avgIllumination;

    /**
     * 平均昼夜温差
     */
    private double avgCivilDayTemperature;

    public double getAccumulateTemperature() {
        return accumulateTemperature;
    }

    public void setAccumulateTemperature(double accumulateTemperature) {
        this.accumulateTemperature = accumulateTemperature;
    }

    public double getAvgIllumination() {
        return avgIllumination;
    }

    public void setAvgIllumination(double avgIllumination) {
        this.avgIllumination = avgIllumination;
    }

    public double getAvgCivilDayTemperature() {
        return avgCivilDayTemperature;
    }

    public void setAvgCivilDayTemperature(double avgCivilDayTemperature) {
        this.avgCivilDayTemperature = avgCivilDayTemperature;
    }
}
