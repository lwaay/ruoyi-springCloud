package com.sinonc.agriculture.vo;

import com.sinonc.agriculture.domain.GrowTech;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GrowTechVo extends GrowTech {

    /**
     * 签名
     */
    private String sign;

    /**
     * 时间戳
     */
    private Date timestamp;

    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 页面大小
     */
    private Integer pageSize;

    private String cropsDictvalue;

    /**
     * 多个需删除的内容
     */
    private String ids;

    /**
     * 格式化发布时间
     */
    private String issueTimeString;

    Map tempMap = new HashMap();


    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getIssueTimeString() {
        return issueTimeString;
    }

    public void setIssueTimeString(String issueTimeString) {
        this.issueTimeString = issueTimeString;
    }


    @Override
    public String getCropsDictvalue() {
        return cropsDictvalue;
    }

    @Override
    public void setCropsDictvalue(String cropsDictvalue) {
        this.cropsDictvalue = cropsDictvalue;
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



    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Map getTempMap() {
        return tempMap;
    }

    public void setTempMap(Map tempMap) {
        this.tempMap = tempMap;
    }
}
