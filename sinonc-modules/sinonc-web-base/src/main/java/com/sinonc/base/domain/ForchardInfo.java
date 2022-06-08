package com.sinonc.base.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 果园信息对象 forchard_info
 *
 * @author ruoyi
 * @date 2022-02-21
 */
public class ForchardInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long orchId;

    /**
     * 经营主体主键
     */
    @Excel(name = "经营主体主键")
    private Long entityId;

    /**
     * 果园名称
     */
    @Excel(name = "果园名称")
    private String orchName;

    /**
     * 果园地址
     */
    @Excel(name = "果园地址")
    private String orchAddress;

    /**
     * 果园图片，多个,隔开
     */
    @Excel(name = "果园图片，多个,隔开")
    private String orchPic;

    /**
     * 果树主键（多个,隔开，预留字段）
     */
    @Excel(name = "果树主键", readConverterExp = "多=个,隔开，预留字段")
    private String fruIds;

    public void setOrchId(Long orchId) {
        this.orchId = orchId;
    }

    public Long getOrchId() {
        return orchId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setOrchName(String orchName) {
        this.orchName = orchName;
    }

    public String getOrchName() {
        return orchName;
    }

    public void setOrchAddress(String orchAddress) {
        this.orchAddress = orchAddress;
    }

    public String getOrchAddress() {
        return orchAddress;
    }

    public void setOrchPic(String orchPic) {
        this.orchPic = orchPic;
    }

    public String getOrchPic() {
        return orchPic;
    }

    public void setFruIds(String fruIds) {
        this.fruIds = fruIds;
    }

    public String getFruIds() {
        return fruIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("orchId", getOrchId())
                .append("entityId", getEntityId())
                .append("orchName", getOrchName())
                .append("orchAddress", getOrchAddress())
                .append("orchPic", getOrchPic())
                .append("fruIds", getFruIds())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
