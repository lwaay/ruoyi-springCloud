package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.AdoptionCircleReply;

/**
 * 朋友圈评论Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-23
 */
public interface AdoptionCircleReplyMapper {
    /**
     * 查询朋友圈评论
     *
     * @param replyId 朋友圈评论ID
     * @return 朋友圈评论
     */
    public AdoptionCircleReply selectAdoptionCircleReplyById(Long replyId);

    /**
     * 查询朋友圈评论列表
     *
     * @param adoptionCircleReply 朋友圈评论
     * @return 朋友圈评论集合
     */
    public List<AdoptionCircleReply> selectAdoptionCircleReplyList(AdoptionCircleReply adoptionCircleReply);

    /**
     * 新增朋友圈评论
     *
     * @param adoptionCircleReply 朋友圈评论
     * @return 结果
     */
    public int insertAdoptionCircleReply(AdoptionCircleReply adoptionCircleReply);

    /**
     * 修改朋友圈评论
     *
     * @param adoptionCircleReply 朋友圈评论
     * @return 结果
     */
    public int updateAdoptionCircleReply(AdoptionCircleReply adoptionCircleReply);

    /**
     * 修改朋友圈评论(限定id)
     *
     * @param adoptionCircleReply 朋友圈评论
     * @return 结果
     */
    public int updateAdoptionCircleReplyWithId(AdoptionCircleReply adoptionCircleReply);

    /**
     * 删除朋友圈评论
     *
     * @param replyId 朋友圈评论ID
     * @return 结果
     */
    public int deleteAdoptionCircleReplyById(Long replyId);

    /**
     * 批量删除朋友圈评论
     *
     * @param replyIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAdoptionCircleReplyByIds(Long[] replyIds);

    /**
     * 计算评论个数
     * @param adoptionCircleReply
     * @return
     */
    int selectAdoptionCircleReplyCount(AdoptionCircleReply adoptionCircleReply);
}
