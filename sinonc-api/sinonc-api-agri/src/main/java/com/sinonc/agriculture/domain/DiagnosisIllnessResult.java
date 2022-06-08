package com.sinonc.agriculture.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosisIllnessResult {

    private Long id;
    /**
     * 资料方案 名称
     */
    private String title;
    /**
     * 具体内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 疾病字典表id
     */
    private Long illnessIdP;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getIllnessIdP() {
        return illnessIdP;
    }

    public void setIllnessIdP(Long illnessIdP) {
        this.illnessIdP = illnessIdP;
    }
}

