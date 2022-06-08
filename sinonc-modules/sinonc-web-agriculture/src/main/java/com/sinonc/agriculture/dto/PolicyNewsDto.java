package com.sinonc.agriculture.dto;

import com.sinonc.agriculture.domain.PolicyNews;
import com.sinonc.agriculture.domain.PolicynewsLike;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 政策新闻对象 policy_news
 *
 * @author zhang.xl
 * @date 2020-03-05
 */
public class PolicyNewsDto extends PolicyNews
{
    /**
     * 点赞列表
     */
    private List<PolicynewsLike> likes;

    /**
     * 点赞数
     */
    private int likeSize;

    public List<PolicynewsLike> getLikes() {
        return likes;
    }

    public void setLikes(List<PolicynewsLike> likes) {
        this.likes = likes;
    }

    public int getLikeSize() {
        return likeSize;
    }

    public void setLikeSize(int likeSize) {
        this.likeSize = likeSize;
    }

}
