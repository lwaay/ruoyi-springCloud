package com.sinonc.agriculture.mapper;

import java.util.List;
import java.util.Map;

import com.sinonc.agriculture.domain.GrowtechComment;

/**
 * 养殖技术评论Mapper接口
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
public interface GrowtechCommentMapper
{
    /**
     * 查询养殖技术评论
     *
     * @param commentId 养殖技术评论ID
     * @return 养殖技术评论
     */
    public GrowtechComment selectGrowtechCommentById(Long commentId);

    /**
     * 查询养殖技术评论列表
     *
     * @param growtechComment 养殖技术评论
     * @return 养殖技术评论集合
     */
    public List<GrowtechComment> selectGrowtechCommentList(GrowtechComment growtechComment);

    /**
     * 新增养殖技术评论
     *
     * @param growtechComment 养殖技术评论
     * @return 结果
     */
    public int insertGrowtechComment(GrowtechComment growtechComment);

    /**
     * 修改养殖技术评论
     *
     * @param growtechComment 养殖技术评论
     * @return 结果
     */
    public int updateGrowtechComment(GrowtechComment growtechComment);

    /**
     * 删除养殖技术评论
     *
     * @param commentId 养殖技术评论ID
     * @return 结果
     */
    public int deleteGrowtechCommentById(Long commentId);

    /**
     * 批量删除养殖技术评论
     *
     * @param commentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrowtechCommentByIds(String[] commentIds);

    public List<Map<String, Object>> selectGrowtechCommentListByGrowTechIdForMap(Long growTechId);
}
