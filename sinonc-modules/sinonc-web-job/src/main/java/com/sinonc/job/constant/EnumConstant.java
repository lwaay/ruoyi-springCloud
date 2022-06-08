package com.sinonc.job.constant;

import com.sinonc.common.core.utils.StringUtils;

public class EnumConstant {

    public enum GEOMORPHIC_TYPE{
        HILLY(0,"丘陵"),BASIN(1,"宽谷盆地"),mountain(2,"山地")
        ,plain(3,"平原中阶");
        private int key;
        private String name;

        GEOMORPHIC_TYPE(int key,String name){
            this.setKey(key);
            this.setName(name);
        }

        public static GEOMORPHIC_TYPE getByName(String name){
            for (GEOMORPHIC_TYPE c : GEOMORPHIC_TYPE.values()) {
                if (c.getName().equals(name)){
                    return c;
                }
            }
            return null;
        }

        public int getKey() {
            return key;
        }
        public void setKey(int key) {
            this.key = key;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

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

    public enum JD{
        E("东风","E"),S("南风","S"),W("西风","W"),N("北风","N")
        ,NE("东北风","NE"),SW("西南风","SW"),SE("东南风","SE"),NW("西北风","NW");
        private String label;
        private String key;

        //构造方法
        JD(String label,String key){
            this.setLabel(label);
            this.setKey(key);
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public static String getLabelByKey(String key){
            if (StringUtils.isEmpty(key)){
                return "";
            }
            for (JD item : JD.values()){
                if (item.getKey().equals(key)){
                    return item.label;
                }
            }
            return "";
        }
    }

    public enum ADS_LIST{
        AS("安胜乡","101042300001"),
        BJ("柏家镇","101042300002"),
        BS("碧山镇","101042300003"),
        CB("城北乡","101042300004"),
        DG("大观镇","101042300005"),
        FL("福禄镇","101042300006"),
        FP("复平乡","101042300007"),
        HX("合兴镇","101042300008"),
        HL("和林镇","101042300009"),
        HC("虎城镇","101042300010"),
        HLZ("回龙镇","101042300012"),
        JD("金带镇","101042300013"),
        JK("聚奎镇","101042300014"),
        LR("礼让镇","101042300015"),
        LSJD("梁山街道","101042300016"),
        LPNC("梁平县农场","101042300017"),
        SGGYY("双桂工业园区","101042300018"),
        SHJD("双桂街道","101042300019"),
        LM("龙门镇","101042300020"),
        LSX("龙胜乡","101042300021"),
        MD("明达镇","101042300022"),
        PL("蟠龙镇","101042300023"),
        PJ("屏锦镇","101042300024"),
        QX("七星镇","101042300025"),
        QS("曲水乡","101042300026"),
        XR("仁贤镇","101042300027"),
        SA("石安镇","101042300028"),
        TM("铁门乡","101042300029"),
        WH("文化镇","101042300030"),
        XS("新盛镇","101042300031"),
        YP("荫平镇","101042300033"),
        YY("袁驿镇","101042300034"),
        YL("云龙镇","101042300035"),
        ZS("竹山镇","101042300036"),
        ZZ("紫照乡","101042300037");
        private String label;
        private String key;
        //构造方法
        ADS_LIST(String label,String key){
            this.setLabel(label);
            this.setKey(key);
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public static String getLabelByKey(String key){
            if (StringUtils.isEmpty(key)){
                return "";
            }
            for (ADS_LIST item : ADS_LIST.values()){
                if (item.getKey().equals(key)){
                    return item.label;
                }
            }
            return "";
        }
    }

}
