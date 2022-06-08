package com.sinonc.orders.service;

import com.sinonc.orders.domain.AppVersion;

import java.util.List;

/**
 * 信丰脐橙APP版本 服务层
 *
 * @author sinonc
 * @date 2019-11-22
 */
public interface AppVersionService {

	/**
     * 查询信丰脐橙APP版本信息
     *
     * @param versionId 信丰脐橙APP版本ID
     * @return 信丰脐橙APP版本信息
     */
	public AppVersion getAppVersionById(Long versionId);


	public AppVersion getLastVersion(Integer type);
	/**
     * 查询信丰脐橙APP版本列表
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 信丰脐橙APP版本集合
     */
	public List<AppVersion> listAppVersion(AppVersion appVersion);

	/**
     * 新增信丰脐橙APP版本
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 结果
     */
	public int addAppVersion(AppVersion appVersion);

	/**
     * 修改信丰脐橙APP版本
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 结果
     */
	public int updateAppVersion(AppVersion appVersion);

	/**
     * 删除信丰脐橙APP版本信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAppVersionByIds(String ids);

}
