package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.MemberFollowDescription;
import com.sinonc.orders.domain.OdMemberAttention;

/**
 * 用户收藏商品Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-31
 */
public interface OdMemberAttentionMapper {
    /**
     * 查询用户收藏商品
     *
     * @param id 用户收藏商品ID
     * @return 用户收藏商品
     */
    public OdMemberAttention selectOdMemberAttentionById(Long id);

    /**
     * 获取收藏数
     * @param userId
     * @return
     */
    public int getCountByUserId(Long userId);

    /**
     * 查询用户收藏商品列表
     *
     * @param odMemberAttention 用户收藏商品
     * @return 用户收藏商品集合
     */
    public List<OdMemberAttention> selectOdMemberAttentionList(OdMemberAttention odMemberAttention);

    /**
     * 查询用户收藏商品列表
     *
     * @param odMemberAttention 用户收藏商品
     * @return 用户收藏商品集合
     */
    public List<MemberFollowDescription> selectOdMemberAttentionDescriptionList(OdMemberAttention odMemberAttention);

    /**
     * 新增用户收藏商品
     *
     * @param odMemberAttention 用户收藏商品
     * @return 结果
     */
    public int insertOdMemberAttention(OdMemberAttention odMemberAttention);

    /**
     * 修改用户收藏商品
     *
     * @param odMemberAttention 用户收藏商品
     * @return 结果
     */
    public int updateOdMemberAttention(OdMemberAttention odMemberAttention);

    /**
     * 删除用户收藏商品
     *
     * @param id 用户收藏商品ID
     * @return 结果
     */
    public int deleteOdMemberAttentionById(Long id);

    /**
     * 批量删除用户收藏商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdMemberAttentionByIds(Long[] ids);
}
