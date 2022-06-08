package com.sinonc.agriculture.domain;

import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 轮播图对象 od_advertisement
 *
 * @author ruoyi
 * @date 2020-04-15
 */
public class OdAdvertisement extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 广告ID
     */
    private Long adverId;

    /**
     * 广告图片路径
     */
    @Excel(name = "广告图片路径")
    private String imageUrl;

    /**
     * null
     */
    @Excel(name = "null")
    private String webUrl;

    /**
     * 描述
     */
    @Excel(name = "描述")
    private String description;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createUser;

    public void setAdverId(Long adverId) {
        this.adverId = adverId;
    }

    public Long getAdverId() {
        return adverId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateUser() {
        return createUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("adverId", getAdverId())
                .append("imageUrl", getImageUrl())
                .append("webUrl", getWebUrl())
                .append("description", getDescription())
                .append("createTime", getCreateTime())
                .append("createUser", getCreateUser())
                .toString();
    }
}
