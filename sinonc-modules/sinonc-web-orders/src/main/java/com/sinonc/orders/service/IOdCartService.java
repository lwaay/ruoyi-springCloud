package com.sinonc.orders.service;

import java.util.List;

import com.sinonc.orders.dto.CartDto;
import com.sinonc.orders.domain.OdCart;
import com.sinonc.orders.vo.OdCartVo;
import com.sinonc.orders.vo.ShopVo;

/**
 * 购物车Service接口
 *
 * @author ruoyi
 * @date 2022-03-28
 */
public interface IOdCartService {
    /**
     * 查询购物车
     *
     * @param id 购物车ID
     * @return 购物车
     */
    public OdCart selectOdCartById(Long id);

    /**
     * 查询购物车列表
     *
     * @param odCart 购物车
     * @return 购物车集合
     */
    public List<ShopVo> selectOdCartList(OdCart odCart);

    /**
     * 新增购物车
     *
     * @param cart 购物车
     * @return 结果
     */
    public int insertOdCart(CartDto cart);

    /**
     * 修改购物车
     *
     * @param cart 购物车
     * @return 结果
     */
    public int updateOdCart(CartDto cart);

    /**
     * 批量删除购物车
     *
     * @param ids 需要删除的购物车ID
     * @return 结果
     */
    public int deleteOdCartByIds(Long[] ids);

    /**
     * 删除购物车信息
     *
     * @param id 购物车ID
     * @return 结果
     */
    public int deleteOdCartById(Long id);

    /**
     * 修改商品后失效所有购物车
     */
    public boolean invalidCartByGoodsId(Long goodsId);
}
