package com.sinonc.agriculture.service;

import java.util.List;
import com.sinonc.agriculture.domain.GrowtechLike;

/**
 * 种养殖技术点赞Service接口
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
public interface GrowtechLikeService
{
    /**
     * 查询种养殖技术点赞
     *
     * @param likeId 种养殖技术点赞ID
     * @return 种养殖技术点赞
     */
    public GrowtechLike selectGrowtechLikeById(Long likeId);

    /**
     * 查询种养殖技术点赞列表
     *
     * @param growtechLike 种养殖技术点赞
     * @return 种养殖技术点赞集合
     */
    public List<GrowtechLike> selectGrowtechLikeList(GrowtechLike growtechLike);

    /**
     * 新增种养殖技术点赞
     *
     * @param growtechLike 种养殖技术点赞
     * @return 结果
     */
    public int insertGrowtechLike(GrowtechLike growtechLike);

    /**
     * 修改种养殖技术点赞
     *
     * @param growtechLike 种养殖技术点赞
     * @return 结果
     */
    public int updateGrowtechLike(GrowtechLike growtechLike);

    /**
     * 批量删除种养殖技术点赞
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrowtechLikeByIds(String ids);

    /**
     * 删除种养殖技术点赞信息
     *
     * @param likeId 种养殖技术点赞ID
     * @return 结果
     */
    public int deleteGrowtechLikeById(Long likeId);

    /**
     * 增加种植技术点赞
     * @param growtechIdP
     * @param memberIdP
     * @return
     */
    public int addGrowtechLike(Long growtechIdP,Long memberIdP);

    /**
     * 取消点赞
     * @param growId
     * @param memberId
     * @return
     */
    public int deleteGrowtechLike(Long growId, Long memberId);
}
