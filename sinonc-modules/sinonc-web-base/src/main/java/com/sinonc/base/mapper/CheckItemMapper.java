package com.sinonc.base.mapper;


import com.sinonc.base.domain.CheckItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 检验项目 数据层
 *
 * @author sinonc
 * @date 2019-11-14
 */
@Mapper
public interface CheckItemMapper {
	/**
     * 查询检验项目信息
     *
     * @param checkItemId 检验项目ID
     * @return 检验项目信息
     */
	public CheckItem selectCheckItemById(Long checkItemId);

	/**
     * 查询检验项目列表
     *
     * @param checkItem 检验项目信息
     * @return 检验项目集合
     */
	public List<CheckItem> selectCheckItemList(CheckItem checkItem);

	/**
     * 新增检验项目
     *
     * @param checkItem 检验项目信息
     * @return 结果
     */
	public int insertCheckItem(CheckItem checkItem);

	/**
     * 修改检验项目
     *
     * @param checkItem 检验项目信息
     * @return 结果
     */
	public int updateCheckItem(CheckItem checkItem);

	/**
     * 删除检验项目
     *
     * @param checkItemId 检验项目ID
     * @return 结果
     */
	public int deleteCheckItemById(Long checkItemId);


	public int deleteCheckItemByCheckIdP(Long checkIdP);

	/**
     * 批量删除检验项目
     *
     * @param checkItemIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCheckItemByIds(Long[] checkItemIds);

	public List<CheckItem> selectCheckItemListByCheckId(String checkId);

}
