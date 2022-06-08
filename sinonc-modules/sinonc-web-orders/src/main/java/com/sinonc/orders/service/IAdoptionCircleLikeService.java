package com.sinonc.orders.service;

import java.util.List;

import com.sinonc.orders.domain.AdoptionCircleLike;

/**
 * 朋友圈点赞Service接口
 *
 * @author ruoyi
 * @date 2022-04-23
 */
public interface IAdoptionCircleLikeService {
    /**
     * 查询朋友圈点赞
     *
     * @param id 朋友圈点赞ID
     * @return 朋友圈点赞
     */
    public AdoptionCircleLike selectAdoptionCircleLikeById(Long id);

    /**
     * 查询朋友圈点赞列表
     *
     * @param adoptionCircleLike 朋友圈点赞
     * @return 朋友圈点赞集合
     */
    public List<AdoptionCircleLike> selectAdoptionCircleLikeList(AdoptionCircleLike adoptionCircleLike);

    /**
     * 新增朋友圈点赞
     *
     * @param adoptionCircleLike 朋友圈点赞
     * @return 结果
     */
    public int insertAdoptionCircleLike(AdoptionCircleLike adoptionCircleLike);

    /**
     * 修改朋友圈点赞
     *
     * @param adoptionCircleLike 朋友圈点赞
     * @return 结果
     */
    public int updateAdoptionCircleLike(AdoptionCircleLike adoptionCircleLike);

    /**
     * 批量删除朋友圈点赞
     *
     * @param ids 需要删除的朋友圈点赞ID
     * @return 结果
     */
    public int deleteAdoptionCircleLikeByIds(Long[] ids);

    /**
     * 删除朋友圈点赞信息
     *
     * @param id 朋友圈点赞ID
     * @return 结果
     */
    public int deleteAdoptionCircleLikeById(Long id);

    /**
     *  取消点赞
     * @param adoptionCircleLike
     * @return
     */
    public int removeAdoptionCircleLike(AdoptionCircleLike adoptionCircleLike);

    /**
     * 动态点赞数
     * @param adoptionCircleLike
     * @return
     */
    public int selectAdoptionCircleLikeCount(AdoptionCircleLike adoptionCircleLike);
}
