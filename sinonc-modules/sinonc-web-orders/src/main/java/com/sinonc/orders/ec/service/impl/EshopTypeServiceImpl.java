package com.sinonc.orders.ec.service.impl;

import com.sinonc.orders.ec.domain.EshopType;
import com.sinonc.orders.ec.mapper.EshopTypeMapper;
import com.sinonc.orders.ec.service.IEshopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义折线展示Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-23
 */
@Service
public class EshopTypeServiceImpl implements IEshopTypeService {
    @Autowired
    private EshopTypeMapper eshopTypeMapper;

    /**
     * 查询自定义折线展示
     *
     * @param id 自定义折线展示ID
     * @return 自定义折线展示
     */
    @Override
    public EshopType selectEshopTypeById(Long id) {
        return eshopTypeMapper.selectEshopTypeById(id);
    }

    /**
     * 查询自定义折线展示列表
     *
     * @param eshopType 自定义折线展示
     * @return 自定义折线展示
     */
    @Override
    public List<EshopType> selectEshopTypeList(EshopType eshopType) {
        return eshopTypeMapper.selectEshopTypeList(eshopType);
    }

    /**
     * 新增自定义折线展示
     *
     * @param eshopType 自定义折线展示
     * @return 结果
     */
    @Override
    public int insertEshopType(EshopType eshopType) {
        return eshopTypeMapper.insertEshopType(eshopType);
    }

    /**
     * 修改自定义折线展示
     *
     * @param eshopType 自定义折线展示
     * @return 结果
     */
    @Override
    public int updateEshopType(EshopType eshopType) {
        return eshopTypeMapper.updateEshopType(eshopType);
    }

    /**
     * 批量删除自定义折线展示
     *
     * @param ids 需要删除的自定义折线展示ID
     * @return 结果
     */
    @Override
    public int deleteEshopTypeByIds(Long[] ids) {
        return eshopTypeMapper.deleteEshopTypeByIds(ids);
    }

    /**
     * 删除自定义折线展示信息
     *
     * @param id 自定义折线展示ID
     * @return 结果
     */
    @Override
    public int deleteEshopTypeById(Long id) {
        return eshopTypeMapper.deleteEshopTypeById(id);
    }

    /**
     * 查询所有父id为零的类别
     *
     * @return 结果
     */
    @Override
    public List<EshopType> selectTypeList() {
        return eshopTypeMapper.selectTypeList();
    }

    /**
     * 根据id查询所有子类
     *
     * @param id
     * @return
     */
    @Override
    public List<EshopType> selectChildrenListById(Long id) {
        return eshopTypeMapper.selectChildrenListById(id);
    }
}
