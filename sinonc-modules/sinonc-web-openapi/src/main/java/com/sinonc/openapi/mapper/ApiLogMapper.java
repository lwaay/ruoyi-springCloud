package com.sinonc.openapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinonc.openapi.domain.ApiLog;

import java.util.List;

/**
 * @author huanghao
 * @apiNote 日志接口
 * @date 2020/11/6 14:24
 */
public interface ApiLogMapper extends BaseMapper<ApiLog> {
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
     * 删除调用日志
     *
     * @param id 调用日志ID
     * @return 结果
     */
    public int deleteApiLogById(Long id);

    /**
     * 批量删除调用日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteApiLogByIds(Long[] ids);

}
