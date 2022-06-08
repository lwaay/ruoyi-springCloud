package com.sinonc.system.service.impl;

import java.util.List;

import com.sinonc.system.api.domain.SysImportLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.SysImportLogMapper;
import com.sinonc.system.service.ISysImportLogService;

/**
 * 数据导入日志Service业务层处理
 *
 * @author ruoyi
 * @date 2021-08-11
 */
@Service
public class SysImportLogServiceImpl implements ISysImportLogService {
    @Autowired
    private SysImportLogMapper sysImportLogMapper;

    /**
     * 查询数据导入日志
     *
     * @param logId 数据导入日志ID
     * @return 数据导入日志
     */
    @Override
    public SysImportLog selectSysImportLogById(Long logId) {
        return sysImportLogMapper.selectSysImportLogById(logId);
    }

    /**
     * 查询数据导入日志列表
     *
     * @param sysImportLog 数据导入日志
     * @return 数据导入日志
     */
    @Override
    public List<SysImportLog> selectSysImportLogList(SysImportLog sysImportLog) {
        return sysImportLogMapper.selectSysImportLogList(sysImportLog);
    }

    /**
     * 新增数据导入日志
     *
     * @param sysImportLog 数据导入日志
     * @return 结果
     */
    @Override
    public int insertSysImportLog(SysImportLog sysImportLog) {
                                                                                                                                                                                                                        return sysImportLogMapper.insertSysImportLog(sysImportLog);
    }

    /**
     * 修改数据导入日志
     *
     * @param sysImportLog 数据导入日志
     * @return 结果
     */
    @Override
    public int updateSysImportLog(SysImportLog sysImportLog) {
                                                                                                                                                                                                                        return sysImportLogMapper.updateSysImportLog(sysImportLog);
    }

    /**
     * 批量删除数据导入日志
     *
     * @param logIds 需要删除的数据导入日志ID
     * @return 结果
     */
    @Override
    public int deleteSysImportLogByIds(Long[] logIds) {
        return sysImportLogMapper.deleteSysImportLogByIds(logIds);
    }

    /**
     * 删除数据导入日志信息
     *
     * @param logId 数据导入日志ID
     * @return 结果
     */
    @Override
    public int deleteSysImportLogById(Long logId) {
        return sysImportLogMapper.deleteSysImportLogById(logId);
    }
}
