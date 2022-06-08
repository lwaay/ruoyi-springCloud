package com.sinonc.iot.constant;

/**
 * @author: lqqu
 * @date: 2021/5/10 14:58
 * @description: 天气类型字典
 */
public class EnumConstants {

    public enum JA{
        fine("晴",0L),cloudy("多云",1L),darkClouds("阴",2L),shower("阵雨",3L),
        thunderShower("雷阵雨",4L),ThunderstormWithHail("雷阵雨伴有冰雹",5L),sleet("雨夹雪",6L),sprinkle("小雨",7L),
        moderateRain("中雨",8L),heavyRain("大雨",9L),rainstorm("暴雨",10L),downpour("大暴雨",11L),
        extraordinaryStorm("特大暴雨",12L),snowShower("阵雪",13L),lightSnow("小雪",14L),moderateSnow("中雪",15L),
        heavySnow("大雪",16L),blizzard("暴雪",17L),denseFog("浓雾",18L),FreezingRain("冻雨",19L),
        sandStorm("沙尘暴",20L),moderateRain2("中雨",21L),heavyRain2("大雨",22L),rainstorm2("暴雨",23L),
        downpour2("大暴雨",24L),extraordinaryStorm2("特大暴雨",25L),moderateSnow2("中雪",26L),heavySnow2("大雪",27L),
        blizzard2("暴雪",28L),floatingDust("浮尘",29L),sandBlowing("扬沙",30L),strongSandstorm("强沙尘暴",31L),
        denseFog2("浓雾",32L);
        private String label;
        private Long value;
        //构造方法
        JA(String label,Long value){
            this.setLabel(label);
            this.setValue(value);
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        public static String getLabelByValue(Long value){
            if (value == null){
                return "";
            }
            for (JA item : JA.values()){
                if (item.getValue().equals(value)){
                    return item.label;
                }
            }
            return "";
        }
    }
}
