package com.sinonc.orders.ec.service.impl;

import com.sinonc.orders.ec.domain.EshopSelect;
import com.sinonc.orders.ec.mapper.EshopSelectMapper;
import com.sinonc.orders.ec.service.IEshopSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 品牌展示Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-10
 */
@Service
public class EshopSelectServiceImpl implements IEshopSelectService {
    @Autowired
    private EshopSelectMapper eshopSelectMapper;

    /**
     * 查询品牌展示
     *
     * @param id 品牌展示ID
     * @return 品牌展示
     */
    @Override
    public EshopSelect selectEshopSelectById(Long id) {
        return eshopSelectMapper.selectEshopSelectById(id);
    }

    /**
     * 查询品牌展示列表
     *
     * @param eshopSelect 品牌展示
     * @return 品牌展示
     */
    @Override
    public List<EshopSelect> selectEshopSelectList(EshopSelect eshopSelect) {
        return eshopSelectMapper.selectEshopSelectList(eshopSelect);
    }

    /**
     * 新增品牌展示
     *
     * @param eshopSelect 品牌展示
     * @return 结果
     */
    @Override
    public int insertEshopSelect(EshopSelect eshopSelect) {
                                                                            return eshopSelectMapper.insertEshopSelect(eshopSelect);
    }

    /**
     * 修改品牌展示
     *
     * @param eshopSelect 品牌展示
     * @return 结果
     */
    @Override
    public int updateEshopSelect(EshopSelect eshopSelect) {
                                                                            return eshopSelectMapper.updateEshopSelect(eshopSelect);
    }

    /**
     * 批量删除品牌展示
     *
     * @param ids 需要删除的品牌展示ID
     * @return 结果
     */
    @Override
    public int deleteEshopSelectByIds(Long[] ids) {
        return eshopSelectMapper.deleteEshopSelectByIds(ids);
    }

    /**
     * 删除品牌展示信息
     *
     * @param id 品牌展示ID
     * @return 结果
     */
    @Override
    public int deleteEshopSelectById(Long id) {
        return eshopSelectMapper.deleteEshopSelectById(id);
    }
}
