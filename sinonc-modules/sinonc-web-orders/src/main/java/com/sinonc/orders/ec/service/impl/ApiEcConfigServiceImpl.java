package com.sinonc.orders.ec.service.impl;

import com.sinonc.orders.ec.mapper.ApiEcConfigMapper;
import com.sinonc.orders.ec.service.IApiEcConfigService;
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
public class ApiEcConfigServiceImpl implements IApiEcConfigService {

    @Autowired
    private ApiEcConfigMapper ecConfigMapper;

    @Override
    public List<Map<String, String>> getTables() {
        return ecConfigMapper.getTables();
    }

    @Override
    public List<Map<String, String>> getColumn(String tableName) {
        return ecConfigMapper.getColumns(tableName);
    }

    /**
     * 统一查询数据
     */
    @Override
    public List<Map<String, String>> queryList(Map<String, String> params){
        return ecConfigMapper.queryList(params);
    }
}
