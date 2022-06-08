package com.sinonc.base.mapper;

import com.sinonc.base.domain.Checkup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 土壤、果蔬检测 数据层
 *
 * @author sinonc
 * @date 2019-11-14
 */
@Mapper
public interface CheckupMapper {
	/**
     * 查询土壤、果蔬检测信息
     *
     * @param checkId 土壤、果蔬检测ID
     * @return 土壤、果蔬检测信息
     */
	public Checkup selectCheckupById(Long checkId);

	/**
     * 查询土壤、果蔬检测列表
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 土壤、果蔬检测集合
     */
	public List<Checkup> selectCheckupList(Checkup checkup);

	/**
     * 新增土壤、果蔬检测
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 结果
     */
	public int insertCheckup(Checkup checkup);

	/**
     * 修改土壤、果蔬检测
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 结果
     */
	public int updateCheckup(Checkup checkup);

	/**
     * 删除土壤、果蔬检测
     *
     * @param checkId 土壤、果蔬检测ID
     * @return 结果
     */
	public int deleteCheckupById(Long checkId);

	/**
     * 批量删除土壤、果蔬检测
     *
     * @param checkIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCheckupByIds(Long[] checkIds);

	public String selectLatelyYear(Long farmId);

	public Checkup selectCheckupListByYear(Checkup checkup);

	public String selectAreaCodeByShopId(String shopId);

}
