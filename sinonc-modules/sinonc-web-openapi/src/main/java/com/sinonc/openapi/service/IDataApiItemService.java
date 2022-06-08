package com.sinonc.openapi.service;


import com.sinonc.openapi.domain.DataApiItem;

import java.util.List;

/**
 * 系统接口明细Service接口
 *
 * @author huanghao
 * @date 2020-11-05
 */
public interface IDataApiItemService {
    /**
     * 查询系统接口明细
     *
     * @param id 系统接口明细ID
     * @return 系统接口明细
     */
    public DataApiItem selectDataApiItemById(Long id);

    /**
     * 查询系统接口明细列表
     *
     * @param dataApiItem 系统接口明细
     * @return 系统接口明细集合
     */
    public List<DataApiItem> selectDataApiItemList(DataApiItem dataApiItem);

    /**
     * 新增系统接口明细
     *
     * @param dataApiItem 系统接口明细
     * @return 结果
     */
    public int insertDataApiItem(DataApiItem dataApiItem);

    /**
     * 修改系统接口明细
     *
     * @param dataApiItem 系统接口明细
     * @return 结果
     */
    public int updateDataApiItem(DataApiItem dataApiItem);

    /**
     * 批量删除系统接口明细
     *
     * @param ids 需要删除的系统接口明细ID
     * @return 结果
     */
    public int deleteDataApiItemByIds(Long[] ids);

    /**
     * 删除系统接口明细信息
     *
     * @param id 系统接口明细ID
     * @return 结果
     */
    public int deleteDataApiItemById(Long id);
}
