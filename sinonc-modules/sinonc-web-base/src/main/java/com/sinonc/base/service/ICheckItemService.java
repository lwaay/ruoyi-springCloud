package com.sinonc.base.service;

import java.util.List;

import com.sinonc.base.domain.CheckItem;

/**
 * 检验项目Service接口
 *
 * @author ruoyi
 * @date 2022-04-24
 */
public interface ICheckItemService {
    /**
     * 查询检验项目
     *
     * @param checkItemId 检验项目ID
     * @return 检验项目
     */
    public CheckItem selectCheckItemById(Long checkItemId);

    /**
     * 查询检验项目列表
     *
     * @param checkItem 检验项目
     * @return 检验项目集合
     */
    public List<CheckItem> selectCheckItemList(CheckItem checkItem);

    /**
     * 新增检验项目
     *
     * @param checkItem 检验项目
     * @return 结果
     */
    public int insertCheckItem(CheckItem checkItem);

    /**
     * 修改检验项目
     *
     * @param checkItem 检验项目
     * @return 结果
     */
    public int updateCheckItem(CheckItem checkItem);

    /**
     * 批量删除检验项目
     *
     * @param checkItemIds 需要删除的检验项目ID
     * @return 结果
     */
    public int deleteCheckItemByIds(Long[] checkItemIds);

    /**
     * 删除检验项目信息
     *
     * @param checkItemId 检验项目ID
     * @return 结果
     */
    public int deleteCheckItemById(Long checkItemId);
}
