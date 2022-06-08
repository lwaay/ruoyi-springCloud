package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.AppVersion;
import com.sinonc.orders.mapper.AppVersionMapper;
import com.sinonc.orders.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 信丰脐橙APP版本 服务层实现
 *
 * @author sinonc
 * @date 2019-11-22
 */
@Service
public class AppVersionServiceImpl implements AppVersionService {
	@Autowired
	private AppVersionMapper appVersionMapper;

	/**
     * 查询信丰脐橙APP版本信息
     *
     * @param versionId 信丰脐橙APP版本ID
     * @return 信丰脐橙APP版本信息
     */
    @Override
	public AppVersion getAppVersionById(Long versionId) {
	    return appVersionMapper.selectAppVersionById(versionId);
	}

	@Override
	public AppVersion getLastVersion(Integer type) {
		return appVersionMapper.selectLastVersion(type);
	}

	/**
     * 查询信丰脐橙APP版本列表
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 信丰脐橙APP版本集合
     */
	@Override
	public List<AppVersion> listAppVersion(AppVersion appVersion) {
	    return appVersionMapper.selectAppVersionList(appVersion);
	}

    /**
     * 新增信丰脐橙APP版本
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 结果
     */
	@Override
	public int addAppVersion(AppVersion appVersion) {
		appVersion.setCreateTime(new Date());

	    return appVersionMapper.insertAppVersion(appVersion);
	}

	/**
     * 修改信丰脐橙APP版本
     *
     * @param appVersion 信丰脐橙APP版本信息
     * @return 结果
     */
	@Override
	public int updateAppVersion(AppVersion appVersion) {
		appVersion.setUpdateTime(new Date());
	    return appVersionMapper.updateAppVersion(appVersion);
	}

	/**
     * 删除信丰脐橙APP版本对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAppVersionByIds(String ids) {
		return appVersionMapper.deleteAppVersionByIds(Convert.toStrArray(ids));
	}

}
