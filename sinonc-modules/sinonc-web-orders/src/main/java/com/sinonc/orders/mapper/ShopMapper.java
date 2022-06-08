package com.sinonc.orders.mapper;

import com.sinonc.order.api.domain.Shop;

import java.util.List;


/**
 * 店铺信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-30
 */
public interface ShopMapper {

    /**
     * 根据主体id查询店铺
     * @param entityId
     * @return
     */
    List<Shop> selectShopByEntity(Long entityId);
    /**
     * 查询店铺信息
     *
     * @param shopId 店铺信息ID
     * @return 店铺信息
     */
    public Shop selectShopById(Long shopId);

    /**
     * 查询店铺信息列表
     *
     * @param shop 店铺信息
     * @return 店铺信息集合
     */
    public List<Shop> selectShopList(Shop shop);

    /**
     * 新增店铺信息
     *
     * @param shop 店铺信息
     * @return 结果
     */
    public int insertShop(Shop shop);

    /**
     * 修改店铺信息
     *
     * @param shop 店铺信息
     * @return 结果
     */
    public int updateShop(Shop shop);

    /**
     * 删除店铺信息
     *
     * @param shopId 店铺信息ID
     * @return 结果
     */
    public int deleteShopById(Long shopId);

    /**
     * 批量删除店铺信息
     *
     * @param shopIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteShopByIds(Long[] shopIds);
}
