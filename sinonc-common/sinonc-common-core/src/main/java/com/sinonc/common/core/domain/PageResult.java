package com.sinonc.common.core.domain;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PageResult<T> {

    /**
     * 数据
     */
    @ApiModelProperty(value="数据列表")
    List<T> rows;

    /**
     * 总页数
     */
    @ApiModelProperty(value="总页数")
    private int totalPage;

    /**
     * 是否有下一页
     */
    @ApiModelProperty(value="是否有下一页")
    private boolean hasNext;

    /**
     * 是否有前一页
     */
    @ApiModelProperty(value="是否有前一页")
    private boolean hasPre;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPre() {
        return hasPre;
    }

    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }
}
