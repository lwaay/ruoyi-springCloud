package com.sinonc.exchange.vo;

import java.util.Date;


public class PolicyNewsVo {

    /**
     * 所属系统
     */
    private String sysName;
    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 新闻类型
     **/
    private String newsType;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 发布时间开始
     */
    private Date beginIssueTime;

    /**
     * 发布时间结束
     */
    private Date endIssueTime;

    /**
     * 签名
     */
    private String sign;

    /**
     * 时间戳
     */
    private Date timestamp;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getBeginIssueTime() {
        return beginIssueTime;
    }

    public void setBeginIssueTime(Date beginIssueTime) {
        this.beginIssueTime = beginIssueTime;
    }

    public Date getEndIssueTime() {
        return endIssueTime;
    }

    public void setEndIssueTime(Date endIssueTime) {
        this.endIssueTime = endIssueTime;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

}
