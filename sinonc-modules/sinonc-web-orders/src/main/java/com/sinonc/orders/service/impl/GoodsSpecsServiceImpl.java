package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.GoodsSpecs;
import com.sinonc.orders.mapper.GoodsSpecsMapper;
import com.sinonc.orders.service.GoodsSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
public class GoodsSpecsServiceImpl implements GoodsSpecsService {
	@Autowired
	private GoodsSpecsMapper goodsSpecsMapper;

	/**
     * 查询商品规格信息
     *
     * @param goodsSpecId 商品规格ID
     * @return 商品规格信息
     */
    @Override
	public GoodsSpecs getGoodsSpecsById(Long goodsSpecId) {
	    return goodsSpecsMapper.selectGoodsSpecsById(goodsSpecId);
	}

	/**
     * 查询商品规格列表
     *
     * @param goodsSpecs 商品规格信息
     * @return 商品规格集合
     */
	@Override
	public List<GoodsSpecs> listGoodsSpecs(GoodsSpecs goodsSpecs) {
	    return goodsSpecsMapper.selectGoodsSpecsList(goodsSpecs);
	}

    /**
     * 新增商品规格
     *
     * @param goodsSpecs 商品规格信息
     * @return 结果
     */
	@Override
	public int addGoodsSpecs(GoodsSpecs goodsSpecs) {
	    return goodsSpecsMapper.insertGoodsSpecs(goodsSpecs);
	}

	/**
	 * 批量新增商品规格
	 * @param goodsSpecs 商品规格列表
	 * @return 结果
	 */
    @Override
    public int batchAddGoodsSpecs(List<GoodsSpecs> goodsSpecs) {
		return goodsSpecsMapper.batchInsert(goodsSpecs);
    }

    /**
     * 修改商品规格
     *
     * @param goodsSpecs 商品规格信息
     * @return 结果
     */
	@Override
	public int updateGoodsSpecs(GoodsSpecs goodsSpecs) {
	    return goodsSpecsMapper.updateGoodsSpecs(goodsSpecs);
	}

	/**
     * 删除商品规格对象
     *
     * @param goodsSpecIds 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGoodsSpecsByIds(String ids) {
		return goodsSpecsMapper.deleteGoodsSpecsByIds(Convert.toStrArray(ids));
	}

	/**
	 * 根据商品ID删除对应商品的属性关联关系
	 * @param goodsId 商品ID
	 * @return
	 */
    @Override
    public int deleteGoodsSpecsByGoodsId(Long goodsId) {
        return goodsSpecsMapper.deleteGoodsSpecsByGoodsId(goodsId);
    }

}
