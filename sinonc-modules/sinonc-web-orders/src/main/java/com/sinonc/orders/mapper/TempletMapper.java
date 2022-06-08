package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Templet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 认养证书模版 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface TempletMapper {
	/**
     * 查询认养证书模版信息
     *
     * @param templetId 认养证书模版ID
     * @return 认养证书模版信息
     */
	public Templet selectTempletById(Long templetId);

	/**
     * 查询认养证书模版列表
     *
     * @param templet 认养证书模版信息
     * @return 认养证书模版集合
     */
	public List<Templet> selectTempletList(Templet templet);

	/**
	 * 查询上一个模版的编号
	 * @return 模版编号25
	 */
	public String selectLastNo();

	/**
     * 新增认养证书模版
     *
     * @param templet 认养证书模版信息
     * @return 结果
     */
	public int insertTemplet(Templet templet);

	/**
     * 修改认养证书模版
     *
     * @param templet 认养证书模版信息
     * @return 结果
     */
	public int updateTemplet(Templet templet);

	/**
     * 删除认养证书模版
     *
     * @param templetId 认养证书模版ID
     * @return 结果
     */
	public int deleteTempletById(Long templetId);

	/**
     * 批量删除认养证书模版
     *
     * @param templetIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteTempletByIds(String[] templetIds);

}
