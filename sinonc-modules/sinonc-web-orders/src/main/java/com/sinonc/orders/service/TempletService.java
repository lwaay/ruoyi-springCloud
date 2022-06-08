package com.sinonc.orders.service;

import com.sinonc.orders.domain.Templet;

import java.util.List;

/**
 * 认养证书模版 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface TempletService {

	/**
     * 查询认养证书模版信息
     *
     * @param templetId 认养证书模版ID
     * @return 认养证书模版信息
     */
	public Templet getTempletById(Long templetId);

	/**
     * 查询认养证书模版列表
     *
     * @param templet 认养证书模版信息
     * @return 认养证书模版集合
     */
	public List<Templet> listTemplet(Templet templet);

	/**
     * 新增认养证书模版
     *
     * @param templet 认养证书模版信息
     * @return 结果
     */
	public int addTemplet(Templet templet);

	/**
     * 修改认养证书模版
     *
     * @param templet 认养证书模版信息
     * @return 结果
     */
	public int updateTemplet(Templet templet);

	/**
     * 删除认养证书模版信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTempletByIds(String ids);

}
