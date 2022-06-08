package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.dto.CartDto;
import com.sinonc.orders.domain.OdCart;
import com.sinonc.orders.vo.OdCartVo;
import com.sinonc.orders.vo.ShopVo;
import org.apache.ibatis.annotations.Param;

/**
 * 购物车Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-28
 */
public interface OdCartMapper {
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
    public List<OdCart> selectOdCartList(OdCart odCart);

    /**
     * 查询购物车列表
     */
    public List<ShopVo> listShopCart(OdCart odCart);

    /**
     * 新增购物车
     *
     * @param odCart 购物车
     * @return 结果
     */
    public int insertOdCart(OdCart odCart);

    /**
     * 修改购物车
     *
     * @param cart 购物车
     * @return 结果
     */
    public int updateOdCart(CartDto cart);

    /**
     * 删除购物车
     *
     * @param id 购物车ID
     * @return 结果
     */
    public int deleteOdCartById(Long id);

    /**
     * 批量删除购物车
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOdCartByIds(Long[] ids);

    /**
     * 逻辑删除购物车
     */
    public int deleteCart(Long[] ids);

    /**
     * 根据商品Id失效所有购物车
     */
    public int invalidCartByGoodsId(@Param("goodsId")Long goodsId);

    /**
     * 根据用户id，规格id
     */
    public OdCart getHistoryCart(@Param("memberId")Long memberId,@Param("specId")Long specId);

    /**
     * 根据规格id修改购买状态
     */
    public int payCartByGoodsId(@Param("specId") Long specId,@Param("memberId") Long memberId);
}
