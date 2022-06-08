package com.sinonc.orders.service;

import java.util.List;

import com.sinonc.orders.domain.OdGoods;
import com.sinonc.orders.dto.BookGoodsDto;
import com.sinonc.orders.dto.OdGoodsDto;
import com.sinonc.orders.vo.OdGoodsVo;

/**
 * 商品Service接口
 *
 * @author ruoyi
 * @date 2022-04-01
 */
public interface IOdGoodsService {
    /**
     * 查询商品
     *
     * @param goodsId 商品ID
     * @return 商品
     */
    public OdGoods selectOdGoodsById(Long goodsId);

    /**
     * 查询商品列表
     *
     * @param odGoods 商品
     * @return 商品集合
     */
    public List<OdGoods> selectOdGoodsList(OdGoods odGoods);

    /**
     * 新增商品
     *
     * @param odGoods 商品
     * @return 结果
     */
    public int insertOdGoods(OdGoods odGoods);

    /**
     * 修改商品
     *
     * @param odGoods 商品
     * @return 结果
     */
    public int updateOdGoods(OdGoods odGoods);

    /**
     * 批量删除商品
     *
     * @param goodsIds 需要删除的商品ID
     * @return 结果
     */
    public int deleteOdGoodsByIds(Long[] goodsIds);

    /**
     * 删除商品信息
     *
     * @param goodsId 商品ID
     * @return 结果
     */
    public int deleteOdGoodsById(Long goodsId);

    /**
     * 添加商品
     * @param odGoodsVo
     * @return
     */
    int addOdGoodsVo(OdGoodsVo odGoodsVo) throws Exception;

    /**
     * 查询直供商品及规格列表
     * @param goodsId
     * @return
     */
    OdGoodsVo selectOdGoodsVoById(Long goodsId) throws Exception;

    /**
     * 更新商品
     * @param odGoodsVo
     * @return
     */
    int updateOdGoodsVo(OdGoodsVo odGoodsVo) throws Exception;

    /**
     * 添加预订商品
     * @param bookGoodsDto
     * @return
     */
    int addBookGoods(BookGoodsDto bookGoodsDto);

    /**
     * 修改预订商品
     * @param bookGoodsDto
     * @return
     */
    int updateBookGoods(BookGoodsDto bookGoodsDto);

    /**
     * 查询预订商品及规格列表
     * @param goodsId
     * @return
     */
    BookGoodsDto selectOdGoodsDtoById(Long goodsId) throws Exception;

    /**
     * 根据商品id查询商品详情
     * @param goodsId
     * @return
     */
    OdGoodsDto selectOdGoodsDetailById(Long goodsId);

    /**
     * 查询指定数量的商品
     * @param odGoods
     * @return
     */
    List<OdGoods> selectOdGoodsListLimit(OdGoods odGoods);

    /**
     * 查询热销
     * @param odGoods
     * @return
     */
    List<OdGoods> selectRxOdGoodsByShopId(OdGoods odGoods);
}
