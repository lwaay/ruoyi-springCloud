package com.sinonc.orders.ec.service;

import com.sinonc.orders.ec.domain.OdFruitPrice;

import java.util.List;

/**
 * 水果价格Service接口
 *
 * @author ruoyi
 * @date 2020-09-27
 */
public interface IOdFruitPriceService {
    /**
     * 查询水果价格
     *
     * @param id 水果价格ID
     * @return 水果价格
     */
    public OdFruitPrice selectOdFruitPriceById(Long id);

    /**
     * 查询水果价格列表
     *
     * @param odFruitPrice 水果价格
     * @return 水果价格集合
     */
    public List<OdFruitPrice> selectOdFruitPriceList(OdFruitPrice odFruitPrice);

    /**
     * 新增水果价格
     *
     * @param odFruitPrice 水果价格
     * @return 结果
     */
    public int insertOdFruitPrice(OdFruitPrice odFruitPrice);

    /**
     * 修改水果价格
     *
     * @param odFruitPrice 水果价格
     * @return 结果
     */
    public int updateOdFruitPrice(OdFruitPrice odFruitPrice);

    /**
     * 批量删除水果价格
     *
     * @param ids 需要删除的水果价格ID
     * @return 结果
     */
    public int deleteOdFruitPriceByIds(Long[] ids);

    /**
     * 删除水果价格信息
     *
     * @param id 水果价格ID
     * @return 结果
     */
    public int deleteOdFruitPriceById(Long id);
}
