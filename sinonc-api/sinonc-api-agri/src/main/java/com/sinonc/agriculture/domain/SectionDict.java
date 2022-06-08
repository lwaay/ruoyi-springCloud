package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 板块字典对象 section_dict
 *
 * @author ruoyi
 * @date 2020-03-06
 */
public class SectionDict extends TreeEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 版块ID
     */
    private Long sectionId;

    /**
     * 版块名称
     */
    @Excel(name = "版块名称")
    private String sectionName;

    /**
     * 默认为false  是否被关注
     **/
    private boolean checked = false;

    /***被关注数**/
    private Long concernNum;

    /**
     * 图标
     */
    @Excel(name = "图标")
    private String iconImg;

    public String getIconImg() {
        return iconImg;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Long getConcernNum() {
        return concernNum;
    }

    public void setConcernNum(Long concernNum) {
        this.concernNum = concernNum;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getSectionId() {
        return sectionId;
    }
    public void setSectionName(String sectionName)
    {
        this.sectionName = sectionName;
    }

    public String getSectionName()
    {
        return sectionName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sectionId", getSectionId())
            .append("sectionName", getSectionName())
            .append("createTime", getCreateTime())
            .append("parentId", getParentId())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
