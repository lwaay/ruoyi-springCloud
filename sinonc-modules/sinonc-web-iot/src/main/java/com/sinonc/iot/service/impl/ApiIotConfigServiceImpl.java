package com.sinonc.iot.service.impl;

import com.sinonc.iot.mapper.ApiIotConfigMapper;
import com.sinonc.iot.service.IApiIotConfigService;
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
public class ApiIotConfigServiceImpl implements IApiIotConfigService {

    @Autowired
    private ApiIotConfigMapper iotConfigMapper;

    @Override
    public List<Map<String, String>> getTables() {
        return iotConfigMapper.getTables();
    }

    @Override
    public List<Map<String, String>> getColumn(String tableName) {
        return iotConfigMapper.getColumns(tableName);
    }

    /**
     * 统一查询数据
     */
    @Override
    public List<Map<String,String>> queryList(Map<String,String> params){
        return iotConfigMapper.queryList(params);
    }
}
