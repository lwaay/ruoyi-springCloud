package com.sinonc.base.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 作物字典对象 crop_dict
 *
 * @author ruoyi
 * @date 2020-03-06
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CropDict extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long cropId;

    /** 作物名称 */
    @Excel(name = "作物名称")
    private String cropName;

    /** 图标 */
    @Excel(name = "图标")
    private String iconImg;

    /**默认为false  是否被关注**/
    private boolean checked=false;

    /***被关注数**/
    private Long concernNum;

    public CropDict() {
    }

    public CropDict(Long cropId, String cropName) {
        this.cropId = cropId;
        this.cropName = cropName;
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

    public void setCropId(Long cropId)
    {
        this.cropId = cropId;
    }

    public Long getCropId()
    {
        return cropId;
    }
    public void setCropName(String cropName)
    {
        this.cropName = cropName;
    }

    public String getCropName()
    {
        return cropName;
    }
    public void setIconImg(String iconImg)
    {
        this.iconImg = iconImg;
    }

    public String getIconImg()
    {
        return iconImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cropId", getCropId())
            .append("cropName", getCropName())
            .append("parentId", getParentId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("iconImg", getIconImg())
            .toString();
    }
}
