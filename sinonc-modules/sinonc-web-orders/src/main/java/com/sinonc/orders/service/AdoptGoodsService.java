package com.sinonc.orders.service;

import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.domain.Specs;

import java.io.IOException;

/**
 * 认养商品服务层
 */
public interface AdoptGoodsService {

    /**
     * 新增认养商品
     *
     * @return 结果
     */
    int addAdoptGoods(Goods goods) throws IOException;

    /**
     * 根据商品ID查询规格
     * @param goodsId
     * @return
     */
    Specs selectSpecsByGoodId(Long goodsId);

    /**
     * 获取规格对应的规格属性JSON格式
     * @param specsId
     * @return
     */
    String selectSpecsValueListBySpecsId(Long specsId)throws Exception;

    /**
     * 更新认养商品
     * @param goods
     * @return
     */
    int updateAdoptGoods(Goods goods) throws Exception;

    /**
     * 获取规格对应的规格属性的主键
     * @param specsId
     * @return
     */
    String selectSpecsValueIds(Long specsId);

    /**
     * 删除认养商品
     * @param ids
     * @return
     */
    int deleteAdoptGoodsByIds(String ids);

}
