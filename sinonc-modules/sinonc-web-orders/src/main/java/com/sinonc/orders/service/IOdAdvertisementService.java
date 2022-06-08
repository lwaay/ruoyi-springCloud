package com.sinonc.orders.service;

import java.util.List;

import com.sinonc.orders.domain.OdAdvertisement;

/**
 * 轮播图Service接口
 *
 * @author ruoyi
 * @date 2022-03-28
 */
public interface IOdAdvertisementService {
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
     * 批量删除轮播图
     *
     * @param adverIds 需要删除的轮播图ID
     * @return 结果
     */
    public int deleteOdAdvertisementByIds(Long[] adverIds);

    /**
     * 删除轮播图信息
     *
     * @param adverId 轮播图ID
     * @return 结果
     */
    public int deleteOdAdvertisementById(Long adverId);
}
