package com.sinonc.orders.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.dto.CartDto;
import com.sinonc.orders.service.*;
import com.sinonc.orders.vo.OdCartVo;
import com.sinonc.orders.vo.ShopVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.OdCartMapper;

/**
 * 购物车Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@Service
@Slf4j
public class OdCartServiceImpl implements IOdCartService {
    @Autowired
    private OdCartMapper odCartMapper;
    @Autowired
    private IShopService shopService;
    @Autowired
    private SpecsService specsService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 查询购物车
     *
     * @param id 购物车ID
     * @return 购物车
     */
    @Override
    public OdCart selectOdCartById(Long id) {
        return odCartMapper.selectOdCartById(id);
    }

    /**
     * 查询购物车列表
     *
     * @param odCart 购物车
     * @return 购物车
     */
    @Override
    public List<ShopVo> selectOdCartList(OdCart odCart) {
        List<ShopVo> shopVoList = odCartMapper.listShopCart(odCart);
        shopVoList.forEach(shopVo -> {
            List<OdCartVo> cartVoList = shopVo.getCarts();
            cartVoList.forEach(odCartVo -> {
                Specs specs = specsService.getSpecsById(odCartVo.getGoodsSpecsIdP());
                if(specs != null){
                    odCartVo.setSpecs(specs);
                }
            });
        });
        return shopVoList;
    }

    /**
     * 新增购物车
     *
     * @param cart 购物车
     * @return 结果
     */
    @Override
    public int insertOdCart(CartDto cart) {
        if (!Optional.ofNullable(cart).isPresent()){
            throw new BusinessException("未获取添加购物车信息,新增购物车失败");
        }
        if (cart.getCartNum()==null || cart.getCartNum()<1){
            throw new BusinessException("未获取商品数量信息,新增购物车失败");
        }
        if (cart.getMemberId()==null || cart.getMemberId()<1L){
            throw new BusinessException("未获取用户信息,新增购物车失败");
        }
        Goods good = goodsService.getGoodsById(cart.getGoodsIdP());
        if (cart.getGoodsIdP() == null || cart.getGoodsIdP() <1L || !Optional.ofNullable(good).isPresent()){
            throw new BusinessException("未获取商品信息,新增购物车失败");
        }
        Shop shop = shopService.selectShopById(cart.getShopIdP());
        if (cart.getShopIdP() == null || cart.getShopIdP() <1L  || !Optional.ofNullable(shop).isPresent()){
            throw new BusinessException("未获取店铺信息,新增购物车失败");
        }
        Specs specs = specsService.getSpecsById(cart.getGoodsSpecsIdP());
        if (cart.getGoodsSpecsIdP() ==null || cart.getGoodsSpecsIdP() <1L || !Optional.ofNullable(specs).isPresent()){
            throw new BusinessException("未获取规格信息,新增购物车失败");
        }
        OdCart old = odCartMapper.getHistoryCart(cart.getMemberId(),cart.getGoodsSpecsIdP());
        if (Optional.ofNullable(old).isPresent()){
            old.setCartNum(old.getCartNum()+cart.getCartNum());
            CartDto cartDto = new CartDto();
            BeanUtils.copyProperties(old,cartDto);
            return odCartMapper.updateOdCart(cartDto);
        }else {
            OdCart odCart = OdCart.builder().goodsIdP(cart.getGoodsIdP())
                    .goodsName(good.getName())
                    .cartNum(cart.getCartNum())
                    .goodsImg(good.getImage().split(",",-1)[0])
                    .goodsPrice(specs.getSpecsPrice())
                    .goodsSpecsIdP(cart.getGoodsSpecsIdP())
                    .memberId(cart.getMemberId())
                    .goodsSpecsName(specs.getSpecsName())
                    .shopIdP(cart.getShopIdP())
                    .shopNameP(shop.getShopName())
                    .isDel(0).isInvalid(0)
                    .isPay(0).goodsType(cart.getGoodsType())
                    .build();
            odCart.setCreateTime(new Date());
            return odCartMapper.insertOdCart(odCart);
        }
    }

    /**
     * 修改购物车
     *
     * @param cart 购物车
     * @return 结果
     */
    @Override
    public int updateOdCart(CartDto cart) {
        return odCartMapper.updateOdCart(cart);
    }

    /**
     * 批量删除购物车
     *
     * @param ids 需要删除的购物车ID
     * @return 结果
     */
    @Override
    public int deleteOdCartByIds(Long[] ids) {
        return odCartMapper.deleteCart(ids);
    }

    /**
     * 删除购物车信息
     *
     * @param id 购物车ID
     * @return 结果
     */
    @Override
    public int deleteOdCartById(Long id) {
        return odCartMapper.deleteOdCartById(id);
    }

    @Override
    public boolean invalidCartByGoodsId(Long goodsId) {
        if (goodsId == null || goodsId<1L){
            return false;
        }
        try {
           return odCartMapper.invalidCartByGoodsId(goodsId)>0;
        }catch (Exception e){
           log.error(e.getMessage());
        }
        return false;
    }
}
