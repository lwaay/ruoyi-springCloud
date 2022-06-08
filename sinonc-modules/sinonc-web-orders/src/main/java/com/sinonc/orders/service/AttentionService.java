package com.sinonc.orders.service;

import com.sinonc.order.api.domain.Shop;

import java.util.List;

/**
 * 关注服务层
 * @author Administrator
 * @anthor wang
 */
public interface AttentionService {
    /**
     * 关注
     * @param memberId
     * @param shopId
     * @return
     */
    public int like(Long memberId,String shopId);

    /**
     * 取消关注
     * @param memberId
     * @param shopId
     * @return
     */
    public int dislike(Long memberId,String shopId);

    /**
     * 查询用户是否收藏店铺
     * @param memberId
     * @param shopId
     * @return
     */
    public Boolean selectCollect(Long memberId,String shopId);

    /**
     * 查询用户收藏的店铺
     * @param memberById
     * @return
     */
    List<Shop> selectLikeListByMemberId(long memberById);
}
