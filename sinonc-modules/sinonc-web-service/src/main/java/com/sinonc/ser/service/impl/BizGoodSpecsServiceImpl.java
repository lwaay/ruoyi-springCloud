package com.sinonc.ser.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.service.domain.BizGoodSpecs;
import com.sinonc.ser.mapper.BizGoodSpecsMapper;
import com.sinonc.ser.service.IBizGoodSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品规格Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-30
 */
@Service
public class BizGoodSpecsServiceImpl implements IBizGoodSpecsService {
    @Autowired
    private BizGoodSpecsMapper bizGoodSpecsMapper;

    /**
     * 查询商品规格
     *
     * @param specsId 商品规格ID
     * @return 商品规格
     */
    @Override
    public BizGoodSpecs selectBizGoodSpecsById(Long specsId) {
        return bizGoodSpecsMapper.selectBizGoodSpecsById(specsId);
    }

    /**
     * 查询商品规格列表
     *
     * @param bizGoodSpecs 商品规格
     * @return 商品规格
     */
    @Override
    public List<BizGoodSpecs> selectBizGoodSpecsList(BizGoodSpecs bizGoodSpecs) {
        return bizGoodSpecsMapper.selectBizGoodSpecsList(bizGoodSpecs);
    }

    /**
     * 新增商品规格
     *
     * @param bizGoodSpecs 商品规格
     * @return 结果
     */
    @Override
    public int insertBizGoodSpecs(BizGoodSpecs bizGoodSpecs) {
        bizGoodSpecs.setCreateTime(DateUtils.getNowDate());
        return bizGoodSpecsMapper.insertBizGoodSpecs(bizGoodSpecs);
    }

    /**
     * 修改商品规格
     *
     * @param bizGoodSpecs 商品规格
     * @return 结果
     */
    @Override
    public int updateBizGoodSpecs(BizGoodSpecs bizGoodSpecs) {
        bizGoodSpecs.setUpdateTime(DateUtils.getNowDate());
        return bizGoodSpecsMapper.updateBizGoodSpecs(bizGoodSpecs);
    }

    /**
     * 删除商品规格对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizGoodSpecsByIds(String ids) {
        return bizGoodSpecsMapper.deleteBizGoodSpecsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品规格信息
     *
     * @param specsId 商品规格ID
     * @return 结果
     */
    @Override
    public int deleteBizGoodSpecsById(Long specsId) {
        return bizGoodSpecsMapper.deleteBizGoodSpecsById(specsId);
    }

}
