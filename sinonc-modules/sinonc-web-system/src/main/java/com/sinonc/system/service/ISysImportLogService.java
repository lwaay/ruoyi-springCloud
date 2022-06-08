package com.sinonc.system.service;

import com.sinonc.system.api.domain.SysImportLog;

import java.util.List;

/**
 * 数据导入日志Service接口
 *
 * @author ruoyi
 * @date 2021-08-11
 */
public interface ISysImportLogService {
    /**
     * 查询数据导入日志
     *
     * @param logId 数据导入日志ID
     * @return 数据导入日志
     */
    public SysImportLog selectSysImportLogById(Long logId);

    /**
     * 查询数据导入日志列表
     *
     * @param sysImportLog 数据导入日志
     * @return 数据导入日志集合
     */
    public List<SysImportLog> selectSysImportLogList(SysImportLog sysImportLog);

    /**
     * 新增数据导入日志
     *
     * @param sysImportLog 数据导入日志
     * @return 结果
     */
    public int insertSysImportLog(SysImportLog sysImportLog);

    /**
     * 修改数据导入日志
     *
     * @param sysImportLog 数据导入日志
     * @return 结果
     */
    public int updateSysImportLog(SysImportLog sysImportLog);

    /**
     * 批量删除数据导入日志
     *
     * @param logIds 需要删除的数据导入日志ID
     * @return 结果
     */
    public int deleteSysImportLogByIds(Long[] logIds);

    /**
     * 删除数据导入日志信息
     *
     * @param logId 数据导入日志ID
     * @return 结果
     */
    public int deleteSysImportLogById(Long logId);
}
