package com.sinonc.orders.ec.mapper;

import com.sinonc.orders.ec.domain.EshopSelect;

import java.util.List;

/**
 * 品牌展示Mapper接口
 *
 * @author ruoyi
 * @date 2020-11-10
 */
public interface EshopSelectMapper {
    /**
     * 查询品牌展示
     *
     * @param id 品牌展示ID
     * @return 品牌展示
     */
    public EshopSelect selectEshopSelectById(Long id);

    /**
     * 查询品牌展示列表
     *
     * @param eshopSelect 品牌展示
     * @return 品牌展示集合
     */
    public List<EshopSelect> selectEshopSelectList(EshopSelect eshopSelect);

    /**
     * 新增品牌展示
     *
     * @param eshopSelect 品牌展示
     * @return 结果
     */
    public int insertEshopSelect(EshopSelect eshopSelect);

    /**
     * 修改品牌展示
     *
     * @param eshopSelect 品牌展示
     * @return 结果
     */
    public int updateEshopSelect(EshopSelect eshopSelect);

    /**
     * 删除品牌展示
     *
     * @param id 品牌展示ID
     * @return 结果
     */
    public int deleteEshopSelectById(Long id);

    /**
     * 批量删除品牌展示
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEshopSelectByIds(Long[] ids);
}
