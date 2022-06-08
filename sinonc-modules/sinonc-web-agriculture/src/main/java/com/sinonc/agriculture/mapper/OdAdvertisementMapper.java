package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.OdAdvertisement;

import java.util.List;

/**
 * 轮播图Mapper接口
 *
 * @author ruoyi
 * @date 2020-04-15
 */
public interface OdAdvertisementMapper {
    /**
     * 查询轮播图
     *
     * @param adverId 轮播图ID
     * @return 轮播图
     */
    public OdAdvertisement selectOdAdvertisementById(Long adverId);

    /**
     * 查询轮播图列表
     *
     * @param odAdvertisement 轮播图
     * @return 轮播图集合
     */
    public List<OdAdvertisement> selectOdAdvertisementList(OdAdvertisement odAdvertisement);

    /**
     * 新增轮播图
     *
     * @param odAdvertisement 轮播图
     * @return 结果
     */
    public int insertOdAdvertisement(OdAdvertisement odAdvertisement);

    /**
     * 修改轮播图
     *
     * @param odAdvertisement 轮播图
     * @return 结果
     */
    public int updateOdAdvertisement(OdAdvertisement odAdvertisement);

    /**
     * 删除轮播图
     *
     * @param adverId 轮播图ID
     * @return 结果
     */
    public int deleteOdAdvertisementById(Long adverId);

    /**
     * 批量删除轮播图
     *
     * @param adverIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdAdvertisementByIds(String[] adverIds);
}
