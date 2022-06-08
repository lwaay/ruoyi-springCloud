package com.sinonc.orders.ec.mapper;

import com.sinonc.orders.ec.domain.EshopType;

import java.util.List;

/**
 * 自定义折线展示Mapper接口
 *
 * @author ruoyi
 * @date 2020-11-23
 */
public interface EshopTypeMapper {
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
     * 删除自定义折线展示
     *
     * @param id 自定义折线展示ID
     * @return 结果
     */
    public int deleteEshopTypeById(Long id);

    /**
     * 批量删除自定义折线展示
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEshopTypeByIds(Long[] ids);

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

    /**
     * 修改所有品类为不展示
     * @return
     */
    int updateAll();

    /**
     * 批量修改品类展示
     * @param ids
     * @return
     */
    int updateEshopTypeByIds(Long[] ids);
}
