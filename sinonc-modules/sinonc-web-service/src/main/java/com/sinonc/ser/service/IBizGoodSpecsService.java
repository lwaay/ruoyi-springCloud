package com.sinonc.ser.service;

import com.sinonc.service.domain.BizGoodSpecs;

import java.util.List;


/**
 * 商品规格Service接口
 *
 * @author ruoyi
 * @date 2020-07-30
 */
public interface IBizGoodSpecsService {
    /**
     * 查询商品规格
     *
     * @param specsId 商品规格ID
     * @return 商品规格
     */
    BizGoodSpecs selectBizGoodSpecsById(Long specsId);

    /**
     * 查询商品规格列表
     *
     * @param bizGoodSpecs 商品规格
     * @return 商品规格集合
     */
    List<BizGoodSpecs> selectBizGoodSpecsList(BizGoodSpecs bizGoodSpecs);

    /**
     * 新增商品规格
     *
     * @param bizGoodSpecs 商品规格
     * @return 结果
     */
    int insertBizGoodSpecs(BizGoodSpecs bizGoodSpecs);

    /**
     * 修改商品规格
     *
     * @param bizGoodSpecs 商品规格
     * @return 结果
     */
    int updateBizGoodSpecs(BizGoodSpecs bizGoodSpecs);

    /**
     * 批量删除商品规格
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteBizGoodSpecsByIds(String ids);

    /**
     * 删除商品规格信息
     *
     * @param specsId 商品规格ID
     * @return 结果
     */
    int deleteBizGoodSpecsById(Long specsId);

}
