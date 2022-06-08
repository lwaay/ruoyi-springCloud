package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.CommentReply;
import com.sinonc.orders.vo.CommentReplyUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 商品评论 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface CommentReplyMapper {
	/**
     * 查询商品评论信息
     *
     * @param commentreplyId 商品评论ID
     * @return 商品评论信息
     */
	public CommentReply selectCommentReplyById(Long commentreplyId);

	/**
     * 查询商品评论列表
     *
     * @param commentReply 商品评论信息
     * @return 商品评论集合
     */
	public List<CommentReply> selectCommentReplyList(CommentReply commentReply);

	/**
     * 新增商品评论
     *
     * @param commentReply 商品评论信息
     * @return 结果
     */
	public int insertCommentReply(CommentReply commentReply);

	/**
     * 修改商品评论
     *
     * @param commentReply 商品评论信息
     * @return 结果
     */
	public int updateCommentReply(CommentReply commentReply);

	/**
     * 删除商品评论
     *
     * @param commentreplyId 商品评论ID
     * @return 结果
     */
	public int deleteCommentReplyById(Long commentreplyId);

	/**
     * 批量删除商品评论
     *
     * @param commentreplyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCommentReplyByIds(String[] commentreplyIds);

	/**
	 * 根据商品id查询该商品下的评论
	 * @param goodsId
	 * @return
	 */
    List<CommentReplyUserVo> selectCommentList(Long goodsId);

	/**
	 * 查询第一层评论下面的回复
	 * @param commentreplyId
	 * @return
	 */
	List<CommentReplyUserVo> findCommentreplyByid(Long commentreplyId);

	/**
	 *API工程根据认养圈查询评论回复
	 * @param adoptionId
	 * @return
	 */
	List<Map<String,Object>> selectCommontReplyByTypeAndGoodIdP(Long adoptionId);
}
