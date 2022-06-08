package com.sinonc.openapi.constant;

public class EnumConstant {

    public enum UploadSource{
        //多个个枚举值,注意名字并不是构造方法名
        Upload(1,"接口上报"),SysInsert(0,"系统新增");
        //枚举值所包含的属性
        private int key;
        private String value;
        //构造方法
        UploadSource(int key,String value){
            this.setKey(key);
            this.setValue(value);
        }
        public int getKey() {
            return key;
        }
        public void setKey(int key) {
            this.key = key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }
}
