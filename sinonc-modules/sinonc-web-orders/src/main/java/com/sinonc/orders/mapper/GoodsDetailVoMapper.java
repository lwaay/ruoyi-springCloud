package com.sinonc.orders.mapper;

import com.sinonc.orders.vo.GoodsDetailVo;

import java.util.List;
import java.util.Map;

/**
 * 查询商品和规格的详情信息
 */
public interface GoodsDetailVoMapper {

    /**
     * 根据商品id查询商品详情
     * @param goodsId 商品ID
     * @return 结果
     */
    GoodsDetailVo selectByGoodsId(Long goodsId);

    /**
     * 查询商品简要详情（商品列表显示）
     * @param goodsDetailVo 商品简要信息
     * @return 结果
     */
    List<Map<String, Object>> selectGoodsBriefInfo(GoodsDetailVo goodsDetailVo);

    List<Map<String, Object>> selectGoodsBriefInfoIsRecommend(GoodsDetailVo goodsDetailVo);
}
