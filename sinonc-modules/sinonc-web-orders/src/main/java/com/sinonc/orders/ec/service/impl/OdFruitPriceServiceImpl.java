package com.sinonc.orders.ec.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.ec.domain.OdFruitPrice;
import com.sinonc.orders.ec.mapper.OdFruitPriceMapper;
import com.sinonc.orders.ec.service.IOdFruitPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 水果价格Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@Service
public class OdFruitPriceServiceImpl implements IOdFruitPriceService {
    @Autowired
    private OdFruitPriceMapper odFruitPriceMapper;

    /**
     * 查询水果价格
     *
     * @param id 水果价格ID
     * @return 水果价格
     */
    @Override
    public OdFruitPrice selectOdFruitPriceById(Long id) {
        return odFruitPriceMapper.selectOdFruitPriceById(id);
    }

    /**
     * 查询水果价格列表
     *
     * @param odFruitPrice 水果价格
     * @return 水果价格
     */
    @Override
    public List<OdFruitPrice> selectOdFruitPriceList(OdFruitPrice odFruitPrice) {
        return odFruitPriceMapper.selectOdFruitPriceList(odFruitPrice);
    }

    /**
     * 新增水果价格
     *
     * @param odFruitPrice 水果价格
     * @return 结果
     */
    @Override
    public int insertOdFruitPrice(OdFruitPrice odFruitPrice) {
        odFruitPrice.setCreateTime(DateUtils.getNowDate());
        return odFruitPriceMapper.insertOdFruitPrice(odFruitPrice);
    }

    /**
     * 修改水果价格
     *
     * @param odFruitPrice 水果价格
     * @return 结果
     */
    @Override
    public int updateOdFruitPrice(OdFruitPrice odFruitPrice) {
        return odFruitPriceMapper.updateOdFruitPrice(odFruitPrice);
    }

    /**
     * 批量删除水果价格
     *
     * @param ids 需要删除的水果价格ID
     * @return 结果
     */
    @Override
    public int deleteOdFruitPriceByIds(Long[] ids) {
        return odFruitPriceMapper.deleteOdFruitPriceByIds(ids);
    }

    /**
     * 删除水果价格信息
     *
     * @param id 水果价格ID
     * @return 结果
     */
    @Override
    public int deleteOdFruitPriceById(Long id) {
        return odFruitPriceMapper.deleteOdFruitPriceById(id);
    }
}
