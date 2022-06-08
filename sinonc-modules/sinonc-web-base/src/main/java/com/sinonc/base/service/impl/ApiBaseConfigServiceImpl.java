package com.sinonc.base.service.impl;

import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.mapper.ApiBaseConfigMapper;
import com.sinonc.base.mapper.AreaCodeMapper;
import com.sinonc.base.service.IApiBaseConfigService;
import com.sinonc.base.service.IAreaCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 行政区域Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-23
 */
@Service
public class ApiBaseConfigServiceImpl implements IApiBaseConfigService {

    @Autowired
    private ApiBaseConfigMapper baseConfigMapper;

    @Override
    public List<Map<String, String>> getTables() {
        return baseConfigMapper.getTables();
    }

    @Override
    public List<Map<String, String>> getColumn(String tableName) {
        return baseConfigMapper.getColumns(tableName);
    }

    /**
     * 统一查询数据
     */
    @Override
    public List<Map<String,String>> queryList(Map<String,String> params){
        return baseConfigMapper.queryList(params);
    }
}
