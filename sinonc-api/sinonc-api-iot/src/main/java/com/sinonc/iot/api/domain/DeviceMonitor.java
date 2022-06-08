package com.sinonc.iot.api.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 摄像头设备信息对象 device_monitor
 *
 * @author ruoyi
 * @date 2020-04-29
 */
public class DeviceMonitor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 设备名称
     */
    @Excel(name = "设备名称")
    private String name;

    /**
     * 类型
     */
    @Excel(name = "类型")
    private String type;

    /**
     * 设备编号
     */
    @Excel(name = "设备编号")
    private String devSn;

    private String factory;
    private String openness;
    private String lat;
    private String lng;
    /**
     * 萤石云设备编号
     */
    @Excel(name = "萤石云设备编号")
    private String ysSn;

    /**
     * ip地址
     */
    @Excel(name = "ip地址")
    private String ip;

    /**
     * tcp端口
     */
    @Excel(name = "tcp端口")
    private Long tcpPort;

    /**
     * http端口
     */
    @Excel(name = "http端口")
    private Long httpPort;

    /**
     * 访问账号
     */
    @Excel(name = "访问账号")
    private String account;

    /**
     * 访问密码
     */
    @Excel(name = "访问密码")
    private String password;

    /**
     * 设备通道号
     */
    @Excel(name = "设备通道号")
    private Integer devChannel;

    /**
     * 品牌
     */
    @Excel(name = "品牌")
    private String brand;

    public String getIsEsys() {
        return isEsys;
    }

    public void setIsEsys(String isEsys) {
        this.isEsys = isEsys;
    }

    /**
     * 0不是萤石商居监控设备，1是萤石商居设备
     */
    private String isEsys;

    /**
     * 删除标记
     */
    private Integer delFlag;

    private String httpUrl;

    private String rtmpUrl;

    private String playUrl;

    private String ezopen;

    private String appKey;

    private String appSecret;

    private String token;

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getOpenness() {
        return openness;
    }

    public void setOpenness(String openness) {
        this.openness = openness;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEzopen() {
        return ezopen;
    }

    public void setEzopen(String ezopen) {
        this.ezopen = ezopen;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDevSn(String devSn) {
        this.devSn = devSn;
    }

    public String getDevSn() {
        return devSn;
    }

    public void setYsSn(String ysSn) {
        this.ysSn = ysSn;
    }

    public String getYsSn() {
        return ysSn;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setTcpPort(Long tcpPort) {
        this.tcpPort = tcpPort;
    }

    public Long getTcpPort() {
        return tcpPort;
    }

    public void setHttpPort(Long httpPort) {
        this.httpPort = httpPort;
    }

    public Long getHttpPort() {
        return httpPort;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDevChannel(Integer devChannel) {
        this.devChannel = devChannel;
    }

    public Integer getDevChannel() {
        return devChannel;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("type", getType())
                .append("devSn", getDevSn())
                .append("ysSn", getYsSn())
                .append("ip", getIp())
                .append("tcpPort", getTcpPort())
                .append("httpPort", getHttpPort())
                .append("account", getAccount())
                .append("password", getPassword())
                .append("devChannel", getDevChannel())
                .append("brand", getBrand())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
