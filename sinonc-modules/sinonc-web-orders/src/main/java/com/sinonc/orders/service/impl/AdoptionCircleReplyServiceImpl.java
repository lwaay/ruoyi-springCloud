package com.sinonc.orders.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.AdoptionCircleReplyMapper;
import com.sinonc.orders.domain.AdoptionCircleReply;
import com.sinonc.orders.service.IAdoptionCircleReplyService;

/**
 * 朋友圈评论Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-23
 */
@Service
public class AdoptionCircleReplyServiceImpl implements IAdoptionCircleReplyService {
    @Autowired
    private AdoptionCircleReplyMapper adoptionCircleReplyMapper;

    /**
     * 查询朋友圈评论
     *
     * @param replyId 朋友圈评论ID
     * @return 朋友圈评论
     */
    @Override
    public AdoptionCircleReply selectAdoptionCircleReplyById(Long replyId) {
        return adoptionCircleReplyMapper.selectAdoptionCircleReplyById(replyId);
    }

    /**
     * 查询朋友圈评论列表
     *
     * @param adoptionCircleReply 朋友圈评论
     * @return 朋友圈评论
     */
    @Override
    public List<AdoptionCircleReply> selectAdoptionCircleReplyList(AdoptionCircleReply adoptionCircleReply) {
        return adoptionCircleReplyMapper.selectAdoptionCircleReplyList(adoptionCircleReply);
    }

    @Override
    public int selectAdoptionCircleReplyListCount(AdoptionCircleReply adoptionCircleReply) {
        return adoptionCircleReplyMapper.selectAdoptionCircleReplyCount(adoptionCircleReply);
    }

    /**
     * 新增朋友圈评论
     *
     * @param adoptionCircleReply 朋友圈评论
     * @return 结果
     */
    @Override
    public int insertAdoptionCircleReply(AdoptionCircleReply adoptionCircleReply) {
        adoptionCircleReply.setCreateTime(DateUtils.getNowDate());
        return adoptionCircleReplyMapper.insertAdoptionCircleReply(adoptionCircleReply);
    }

    /**
     * 修改朋友圈评论
     *
     * @param adoptionCircleReply 朋友圈评论
     * @return 结果
     */
    @Override
    public int updateAdoptionCircleReply(AdoptionCircleReply adoptionCircleReply) {
        return adoptionCircleReplyMapper.updateAdoptionCircleReply(adoptionCircleReply);
    }

    /**
     * 批量删除朋友圈评论
     *
     * @param replyIds 需要删除的朋友圈评论ID
     * @return 结果
     */
    @Override
    public int deleteAdoptionCircleReplyByIds(Long[] replyIds) {
        return adoptionCircleReplyMapper.deleteAdoptionCircleReplyByIds(replyIds);
    }

    /**
     * 删除朋友圈评论信息(逻辑删除)
     *
     * @param replyId 朋友圈评论ID
     * @return 结果
     */
    @Override
    public int deleteAdoptionCircleReplyById(Long replyId, Long memberId) {
        AdoptionCircleReply reply = new AdoptionCircleReply();
        reply.setReplyId(replyId);
        reply.setDelFlg("1");
        return adoptionCircleReplyMapper.updateAdoptionCircleReplyWithId(reply);
    }
}
