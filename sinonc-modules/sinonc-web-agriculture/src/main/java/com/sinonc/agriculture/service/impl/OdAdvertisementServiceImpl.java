package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.domain.OdAdvertisement;
import com.sinonc.agriculture.mapper.OdAdvertisementMapper;
import com.sinonc.agriculture.service.OdAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图Service业务层处理
 *
 * @author ruoyi
 * @date 2020-04-15
 */
@Service
public class OdAdvertisementServiceImpl implements OdAdvertisementService {
    @Autowired
    private OdAdvertisementMapper odAdvertisementMapper;

    /**
     * 查询轮播图
     *
     * @param adverId 轮播图ID
     * @return 轮播图
     */
    @Override
    public OdAdvertisement getOdAdvertisementById(Long adverId) {
        return odAdvertisementMapper.selectOdAdvertisementById(adverId);
    }

    /**
     * 查询轮播图列表
     *
     * @param odAdvertisement 轮播图
     * @return 轮播图
     */
    @Override
    public List<OdAdvertisement> getOdAdvertisementList(OdAdvertisement odAdvertisement) {
        return odAdvertisementMapper.selectOdAdvertisementList(odAdvertisement);
    }

    /**
     * 新增轮播图
     *
     * @param odAdvertisement 轮播图
     * @return 结果
     */
    @Override
    public int addOdAdvertisement(OdAdvertisement odAdvertisement) {
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
     * 删除轮播图对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOdAdvertisementByIds(String ids) {
        return odAdvertisementMapper.deleteOdAdvertisementByIds(Convert.toStrArray(ids));
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
