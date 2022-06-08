package com.sinonc.orders.service.impl;

import java.util.List;
                                                                                            import com.sinonc.common.core.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.OdAdvertisementMapper;
import com.sinonc.orders.domain.OdAdvertisement;
import com.sinonc.orders.service.IOdAdvertisementService;

/**
 * 轮播图Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@Service
public class OdAdvertisementServiceImpl implements IOdAdvertisementService {
    @Autowired
    private OdAdvertisementMapper odAdvertisementMapper;

    /**
     * 查询轮播图
     *
     * @param adverId 轮播图ID
     * @return 轮播图
     */
    @Override
    public OdAdvertisement selectOdAdvertisementById(Long adverId) {
        return odAdvertisementMapper.selectOdAdvertisementById(adverId);
    }

    /**
     * 查询轮播图列表
     *
     * @param odAdvertisement 轮播图
     * @return 轮播图
     */
    @Override
    public List<OdAdvertisement> selectOdAdvertisementList(OdAdvertisement odAdvertisement) {
        return odAdvertisementMapper.selectOdAdvertisementList(odAdvertisement);
    }

    /**
     * 新增轮播图
     *
     * @param odAdvertisement 轮播图
     * @return 结果
     */
    @Override
    public int insertOdAdvertisement(OdAdvertisement odAdvertisement) {
                                                                                                                                                            odAdvertisement.setCreateTime(DateUtils.getNowDate());
                                                return odAdvertisementMapper.insertOdAdvertisement(odAdvertisement);
    }

    /**
     * 修改轮播图
     *
     * @param odAdvertisement 轮播图
     * @return 结果
     */
    @Override
    public int updateOdAdvertisement(OdAdvertisement odAdvertisement) {
                                                                                                                                                                                return odAdvertisementMapper.updateOdAdvertisement(odAdvertisement);
    }

    /**
     * 批量删除轮播图
     *
     * @param adverIds 需要删除的轮播图ID
     * @return 结果
     */
    @Override
    public int deleteOdAdvertisementByIds(Long[] adverIds) {
        return odAdvertisementMapper.deleteOdAdvertisementByIds(adverIds);
    }

    /**
     * 删除轮播图信息
     *
     * @param adverId 轮播图ID
     * @return 结果
     */
    @Override
    public int deleteOdAdvertisementById(Long adverId) {
        return odAdvertisementMapper.deleteOdAdvertisementById(adverId);
    }
}
