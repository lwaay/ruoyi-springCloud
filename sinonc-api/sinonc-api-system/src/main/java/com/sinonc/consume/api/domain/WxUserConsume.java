package com.sinonc.consume.api.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
/**
 * 消费版用户对象 wx_user_consume
 *
 * @author ruoyi
 * @date 2022-03-26
 */
public class WxUserConsume extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名 ")
    private String name;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phone;

    /**
     * 0:男 1:女
     */
    @Excel(name = "0:男 1:女")
    private Long sex;

    /**
     * 客户类型
     */
    @Excel(name = "客户类型")
    private String type;

    /**
     * 客户生日
     */
    @Excel(name = "客户生日")
    private String birthday;

    /**
     * 区域
     */
    @Excel(name = "区域")
    private String area;

    /**
     * 客户爱好
     */
    @Excel(name = "客户爱好")
    private String hobby;

    /**
     * 客户家庭地址
     */
    @Excel(name = "客户家庭地址")
    private String addr;

    /**
     * 客户工作情况
     */
    @Excel(name = "客户工作情况")
    private String work;

    /**
     * 客户家庭情况
     */
    @Excel(name = "客户家庭情况")
    private String family;

    /**
     * openid
     */
    @Excel(name = "openid")
    private String openid;

    /**
     * 头像
     */
    @Excel(name = "头像")
    private String headimg;

    /**
     * unionid
     */
    @Excel(name = "unionid")
    private String unionid;

    /**
     * 微信昵称
     */
    @Excel(name = "微信昵称")
    private String wxname;

    /**
     * 初始密码
     */
    @Excel(name = "初始密码")
    private String password;

    /**
     * 经营主体id
     */
    @Excel(name = "经营主体id")
    private Long entityId;

    /**
     * 个性签名
     */
    @Excel(name = "个性签名")
    private String signature;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getWxname() {
        return wxname;
    }

    public void setWxname(String wxname) {
        this.wxname = wxname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public WxUserConsume(String name, String phone, String wxname, String birthday, String password, String openid, Long sex, String unionid) {
        this.name = name;
        this.phone = phone;
        this.wxname = wxname;
        this.birthday = birthday;
        this.password = password;
        this.openid = openid;
        this.sex = sex;
        this.unionid = unionid;
    }

    public WxUserConsume() {

    }

}
