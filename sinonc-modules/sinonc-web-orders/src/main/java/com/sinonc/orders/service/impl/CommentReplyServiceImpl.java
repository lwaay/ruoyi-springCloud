package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.CommentReply;
import com.sinonc.orders.mapper.CommentReplyMapper;
import com.sinonc.orders.service.CommentReplyService;
import com.sinonc.orders.vo.CommentReplyUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品评论 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
public class CommentReplyServiceImpl implements CommentReplyService {
	@Autowired
	private CommentReplyMapper commentReplyMapper;

	/**
     * 查询商品评论信息
     *
     * @param commentreplyId 商品评论ID
     * @return 商品评论信息
     */
    @Override
	public CommentReply getCommentReplyById(Long commentreplyId) {
	    return commentReplyMapper.selectCommentReplyById(commentreplyId);
	}

	/**
     * 查询商品评论列表
     *
     * @param commentReply 商品评论信息
     * @return 商品评论集合
     */
	@Override
	public List<CommentReply> listCommentReply(CommentReply commentReply) {
	    return commentReplyMapper.selectCommentReplyList(commentReply);
	}

    /**
     * 新增商品评论
     *
     * @param commentReply 商品评论信息
     * @return 结果
     */
	@Override
	public int addCommentReply(CommentReply commentReply) {
		commentReply.setType(1);//1认养圈 0商品
	    return commentReplyMapper.insertCommentReply(commentReply);
	}

	/**
     * 修改商品评论
     *
     * @param commentReply 商品评论信息
     * @return 结果
     */
	@Override
	public int updateCommentReply(CommentReply commentReply) {
	    return commentReplyMapper.updateCommentReply(commentReply);
	}

	/**
     * 删除商品评论对象
     *
     * @param commentreplyIds 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCommentReplyByIds(String ids) {
		return commentReplyMapper.deleteCommentReplyByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据商品id查询该商品下的评论
	 * @param goodsId
	 * @return
	 */
    @Override
    public List<CommentReplyUserVo> findCommentList(Long goodsId) {
        return commentReplyMapper.selectCommentList(goodsId);
    }

	/**
	 * 查询第一层评论下面的回复
	 * @param commentreplyId
	 * @return
	 */
	@Override
	public List<CommentReplyUserVo> findCommentreplyByid(Long commentreplyId) {
		return commentReplyMapper.findCommentreplyByid(commentreplyId);
	}

	/**
	 * API工程根据认养圈查询评论回复
	 * @param adoptionId
	 * @return
	 */
    @Override
    public  List<Map<String,Object>> selectCommontReplyByTypeAndGoodIdP(Long adoptionId) {
        return commentReplyMapper.selectCommontReplyByTypeAndGoodIdP(adoptionId);
    }

}
