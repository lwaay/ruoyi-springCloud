package com.sinonc.openapi.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.openapi.domain.ApiLog;
import com.sinonc.openapi.mapper.ApiLogMapper;
import com.sinonc.openapi.service.IApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 调用日志Service业务层处理
 *
 * @author hhao
 * @date 2020-11-23
 */
@Service
public class ApiLogServiceImpl implements IApiLogService {
    @Autowired
    private ApiLogMapper apiLogMapper;

    /**
     * 查询调用日志
     *
     * @param id 调用日志ID
     * @return 调用日志
     */
    @Override
    public ApiLog selectApiLogById(Long id) {
        return apiLogMapper.selectApiLogById(id);
    }

    /**
     * 查询调用日志列表
     *
     * @param apiLog 调用日志
     * @return 调用日志
     */
    @Override
    public List<ApiLog> selectApiLogList(ApiLog apiLog) {
        return apiLogMapper.selectApiLogList(apiLog);
    }

    /**
     * 新增调用日志
     *
     * @param apiLog 调用日志
     * @return 结果
     */
    @Override
    public int insertApiLog(ApiLog apiLog) {
        apiLog.setCreateTime(DateUtils.getNowDate());
        return apiLogMapper.insertApiLog(apiLog);
    }

    /**
     * 修改调用日志
     *
     * @param apiLog 调用日志
     * @return 结果
     */
    @Override
    public int updateApiLog(ApiLog apiLog) {
        return apiLogMapper.updateApiLog(apiLog);
    }

    /**
     * 批量删除调用日志
     *
     * @param ids 需要删除的调用日志ID
     * @return 结果
     */
    @Override
    public int deleteApiLogByIds(Long[] ids) {
        return apiLogMapper.deleteApiLogByIds(ids);
    }

    /**
     * 删除调用日志信息
     *
     * @param id 调用日志ID
     * @return 结果
     */
    @Override
    public int deleteApiLogById(Long id) {
        return apiLogMapper.deleteApiLogById(id);
    }
}
