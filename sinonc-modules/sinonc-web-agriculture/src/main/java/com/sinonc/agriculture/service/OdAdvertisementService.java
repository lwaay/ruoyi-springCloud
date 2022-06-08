package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.OdAdvertisement;

import java.util.List;

/**
 * 轮播图Service接口
 *
 * @author ruoyi
 * @date 2020-04-15
 */
public interface OdAdvertisementService {
    /**
     * 查询轮播图
     *
     * @param adverId 轮播图ID
     * @return 轮播图
     */
    public OdAdvertisement getOdAdvertisementById(Long adverId);

    /**
     * 查询轮播图列表
     *
     * @param odAdvertisement 轮播图
     * @return 轮播图集合
     */
    public List<OdAdvertisement> getOdAdvertisementList(OdAdvertisement odAdvertisement);

    /**
     * 新增轮播图
     *
     * @param odAdvertisement 轮播图
     * @return 结果
     */
    public int addOdAdvertisement(OdAdvertisement odAdvertisement);

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
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdAdvertisementByIds(String ids);

    /**
     * 删除轮播图信息
     *
     * @param adverId 轮播图ID
     * @return 结果
     */
    public int deleteOdAdvertisementById(Long adverId);
}
