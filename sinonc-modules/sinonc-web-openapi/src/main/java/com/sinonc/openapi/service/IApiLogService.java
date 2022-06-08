package com.sinonc.openapi.service;

import com.sinonc.openapi.domain.ApiLog;

import java.util.List;

/**
 * 调用日志Service接口
 *
 * @author hhao
 * @date 2020-11-23
 */
public interface IApiLogService {
    /**
     * 查询调用日志
     *
     * @param id 调用日志ID
     * @return 调用日志
     */
    public ApiLog selectApiLogById(Long id);

    /**
     * 查询调用日志列表
     *
     * @param apiLog 调用日志
     * @return 调用日志集合
     */
    public List<ApiLog> selectApiLogList(ApiLog apiLog);

    /**
     * 新增调用日志
     *
     * @param apiLog 调用日志
     * @return 结果
     */
    public int insertApiLog(ApiLog apiLog);

    /**
     * 修改调用日志
     *
     * @param apiLog 调用日志
     * @return 结果
     */
    public int updateApiLog(ApiLog apiLog);

    /**
     * 批量删除调用日志
     *
     * @param ids 需要删除的调用日志ID
     * @return 结果
     */
    public int deleteApiLogByIds(Long[] ids);

    /**
     * 删除调用日志信息
     *
     * @param id 调用日志ID
     * @return 结果
     */
    public int deleteApiLogById(Long id);
}
