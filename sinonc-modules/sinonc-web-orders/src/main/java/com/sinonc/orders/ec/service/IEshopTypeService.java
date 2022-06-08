package com.sinonc.orders.ec.service;

import com.sinonc.orders.ec.domain.EshopType;

import java.util.List;

/**
 * 自定义折线展示Service接口
 *
 * @author ruoyi
 * @date 2020-11-23
 */
public interface IEshopTypeService {
    /**
     * 查询自定义折线展示
     *
     * @param id 自定义折线展示ID
     * @return 自定义折线展示
     */
    public EshopType selectEshopTypeById(Long id);

    /**
     * 查询自定义折线展示列表
     *
     * @param eshopType 自定义折线展示
     * @return 自定义折线展示集合
     */
    public List<EshopType> selectEshopTypeList(EshopType eshopType);

    /**
     * 新增自定义折线展示
     *
     * @param eshopType 自定义折线展示
     * @return 结果
     */
    public int insertEshopType(EshopType eshopType);

    /**
     * 修改自定义折线展示
     *
     * @param eshopType 自定义折线展示
     * @return 结果
     */
    public int updateEshopType(EshopType eshopType);

    /**
     * 批量删除自定义折线展示
     *
     * @param ids 需要删除的自定义折线展示ID
     * @return 结果
     */
    public int deleteEshopTypeByIds(Long[] ids);

    /**
     * 删除自定义折线展示信息
     *
     * @param id 自定义折线展示ID
     * @return 结果
     */
    public int deleteEshopTypeById(Long id);

    /**
     * 查询所有父id为零的类别
     * @return 结果
     */
    List<EshopType> selectTypeList();

    /**
     *根据id查询所有子类
     * @param id
     * @return
     */
    List<EshopType> selectChildrenListById(Long id);
}
