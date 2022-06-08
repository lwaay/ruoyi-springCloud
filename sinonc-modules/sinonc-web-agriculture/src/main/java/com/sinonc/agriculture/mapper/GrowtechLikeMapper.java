package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.GrowtechLike;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 种养殖技术点赞Mapper接口
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
public interface GrowtechLikeMapper
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
     * 删除种养殖技术点赞
     *
     * @param likeId 种养殖技术点赞ID
     * @return 结果
     */
    public int deleteGrowtechLikeById(Long likeId);

    /**
     * 批量删除种养殖技术点赞
     *
     * @param likeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrowtechLikeByIds(String[] likeIds);

    /**
     * 取消点赞
     * @param growtechLike
     * @return
     */
    public int deleteGrowtechLike(GrowtechLike growtechLike);


    /**
     * 根据种植技术ID查询点赞列表
     *
     * @param growTechId
     * @return
     */
    public List<Map<String, Object>> selectGrowtechLikeListByGrowTechIdForMap(Long growTechId);

    List<Map<String, Object>> selectGrowtechLikeListByGrowTechIdAndMemberIdForMap(@Param("growTechId") Long growTechId, @Param("memberId") Long memberId);
}
