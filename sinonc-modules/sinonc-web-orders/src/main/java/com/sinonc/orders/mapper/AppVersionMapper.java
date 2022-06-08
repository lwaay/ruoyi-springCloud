package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AppVersion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 信丰脐橙APP版本 数据层
 *
 * @author sinonc
 * @date 2019-11-22
 */
@Mapper
public interface AppVersionMapper {
	/**
     * 查询信丰脐橙APP版本信息
     *
     * @param versionId 信丰脐橙APP版本ID
     * @return 信丰脐橙APP版本信息
     */
	public AppVersion selectAppVersionById(Long versionId);

	/**
     * 查询信丰脐橙APP版本列表
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 信丰脐橙APP版本集合
     */
	public List<AppVersion> selectAppVersionList(AppVersion appVersion);

	public AppVersion selectLastVersion(Integer type);

	/**
     * 新增信丰脐橙APP版本
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 结果
     */
	public int insertAppVersion(AppVersion appVersion);

	/**
     * 修改信丰脐橙APP版本
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 结果
     */
	public int updateAppVersion(AppVersion appVersion);

	/**
     * 删除信丰脐橙APP版本
     *
     * @param versionId 信丰脐橙APP版本ID
     * @return 结果
     */
	public int deleteAppVersionById(Long versionId);

	/**
     * 批量删除信丰脐橙APP版本
     *
     * @param versionIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAppVersionByIds(String[] versionIds);

}
