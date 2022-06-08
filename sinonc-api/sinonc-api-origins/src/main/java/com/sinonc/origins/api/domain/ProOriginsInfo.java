package com.sinonc.origins.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 溯源信息对象 pro_origins_info
 *
 * @author ruoyi
 * @date 2020-10-23
 */
public class ProOriginsInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 溯源信息主键
     */
    private Long originsId;

    /**
     * 产品信息主键
     */
    @Excel(name = "产品信息主键")
    private Long productIdP;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date originsDate;

    /**
     * 溯源信息类型：01生产过程 02产品介绍 03认证信息 04流通信息 05 生产环境
     */
    @Excel(name = "溯源信息类型：01生产过程 02产品介绍 03认证信息 04流通信息 05 生产环境")
    private String originsType;

    /**
     * 溯源生产信息类型
     */
    @Excel(name="生产类型")
    private String originsProType;

    /**
     * 作业方式
     */
    @Excel(name="作业方式")
    private String jobWay;

    /**
     * 是否公开
     */
    @Excel(name = "是否公开")
    private String isOpen;

    /**
     * 操作人
     */
    @Excel(name = "操作人")
    private String operaMan;

    /**
     * 用工人数
     */
    @Excel(name = "用工人数")
    private String useNum;

    /**
     * 简述
     */
    @Excel(name = "简述")
    private String introduce;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String originsDescribe;

    /**
     * 图片  ，隔开
     */
    @Excel(name = "图片  ，隔开")
    private String orImages;

    /**
     * 视频地址  ,隔开
     */
    @Excel(name = "视频地址  ,隔开")
    private String orVideo;


    public String getUseNum() {
        return useNum;
    }

    public void setUseNum(String useNum) {
        this.useNum = useNum;
    }

    public void setOriginsId(Long originsId) {
        this.originsId = originsId;
    }

    public Long getOriginsId() {
        return originsId;
    }

    public void setProductIdP(Long productIdP) {
        this.productIdP = productIdP;
    }

    public Long getProductIdP() {
        return productIdP;
    }

    public void setOriginsDate(Date originsDate) {
        this.originsDate = originsDate;
    }

    public Date getOriginsDate() {
        return originsDate;
    }

    public void setOriginsType(String originsType) {
        this.originsType = originsType;
    }

    public String getOriginsType() {
        return originsType;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setOperaMan(String operaMan) {
        this.operaMan = operaMan;
    }

    public String getOperaMan() {
        return operaMan;
    }

    public void setOriginsDescribe(String originsDescribe) {
        this.originsDescribe = originsDescribe;
    }

    public String getOriginsDescribe() {
        return originsDescribe;
    }

    public void setOrImages(String orImages) {
        this.orImages = orImages;
    }

    public String getOrImages() {
        return orImages;
    }

    public void setOrVideo(String orVideo) {
        this.orVideo = orVideo;
    }

    public String getOrVideo() {
        return orVideo;
    }

    public String getOriginsProType() {
        return originsProType;
    }

    public void setOriginsProType(String originsProType) {
        this.originsProType = originsProType;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getJobWay() {
        return jobWay;
    }

    public void setJobWay(String jobWay) {
        this.jobWay = jobWay;
    }
}
