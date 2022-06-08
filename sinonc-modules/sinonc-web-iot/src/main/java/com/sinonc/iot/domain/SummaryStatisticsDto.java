package com.sinonc.iot.domain;

import lombok.Data;

/**
 * @author zhangxinlong
 * @date 2021/5/8  10:16
 */
@Data
public class SummaryStatisticsDto {

    /**
     * 活动积温
     */
    private double accumulateTemperature;

    /**
     * 平均光照强度
     */
    private double avgIllumination;

    /**
     * 平均土壤温度
     */
    private double avgSoilTemperature;

    /**
     * 平均土壤湿度
     */
    private double avgSoilHumidity;

    /**
     * 平均二氧化碳溶度
     */
    private double avgCO2Solubility;

    /**
     * 平均空气湿度
     */
    private double avgAirHumidity;

    /**
     * 平均空气温度
     */
    private double avgAirTemperature;

    /**
     * 平均昼夜温差
     */
    private double avgCivilDayTemperature;

    /**
     * 最高温度
     */
    private double maxTemperature;

    /**
     * 最低温度
     */
    private double minTemperature;

    /**
     * 下雨天数
     */
    private double countRainDay;

    /**
     * 刮3级及以上风的天数
     */
    private double levelThreeDay;

    /**
     * 累计雨量
     */
    private double sumRainQuantum;

    /**
     * 平均土壤盐度
     */
    private double avgSalinity;

    /**
     * 乡镇名称
     */
    private String townName;

    /**
     * 乡镇编号
     */
    private String townCode;
}
