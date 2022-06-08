package com.sinonc.iot.constant;

public class EnumConstant {

    public enum MONITOR_STATUS{
        DOME("0","新建"),GUN("1","已处理"),VIDEO("2","已审核");
        private String key;
        private String name;

        MONITOR_STATUS(String key,String name){
            this.setKey(key);
            this.setName(name);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum IS_ENBLE{
        YES(1,"是"),NO(0,"否");
        private Integer key;
        private String name;

        IS_ENBLE(Integer key,String name){
            this.setKey(key);
            this.setName(name);
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
