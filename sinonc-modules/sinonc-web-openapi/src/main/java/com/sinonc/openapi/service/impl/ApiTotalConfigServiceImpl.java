package com.sinonc.openapi.service.impl;

import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.openapi.domain.ApiTotalConfig;
import com.sinonc.openapi.dto.ApiTotalConfigDto;
import com.sinonc.openapi.mapper.ApiTotalConfigMapper;
import com.sinonc.openapi.service.IApiTotalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 接口配置Service业务层处理
 *
 * @author ruoyi
 * @date 2021-10-09
 */
@Service
public class ApiTotalConfigServiceImpl implements IApiTotalConfigService {
    @Autowired
    private ApiTotalConfigMapper apiTotalConfigMapper;

    /**
     * 查询接口配置
     *
     * @param configId 接口配置ID
     * @return 接口配置
     */
    @Override
    public ApiTotalConfig selectApiTotalConfigById(Long configId) {
        return apiTotalConfigMapper.selectApiTotalConfigById(configId);
    }

    /**
     * 查询接口配置列表
     *
     * @param apiTotalConfig 接口配置
     * @return 接口配置
     */
    @Override
    public List<ApiTotalConfig> selectApiTotalConfigList(ApiTotalConfig apiTotalConfig) {
        return apiTotalConfigMapper.selectApiTotalConfigList(apiTotalConfig);
    }

    /**
     * 新增接口配置
     *
     * @param apiTotalConfig 接口配置
     * @return 结果
     */
    @Override
    public int insertApiTotalConfig(ApiTotalConfig apiTotalConfig) {
        if (StringUtils.isEmpty(apiTotalConfig.getUrlSuffix())){
            throw new BusinessException("请输入接口地址后保存数据。");
        }
        //验证接口是否重复
        if(repeatUrlSuffix(apiTotalConfig)){
            throw new BusinessException("接口地址重复，请修改接口地址后保存。");
        }

        apiTotalConfig.setCreateTime(DateUtils.getNowDate());
        apiTotalConfig.setCreateName(SecurityUtils.getUsername());
        return apiTotalConfigMapper.insertApiTotalConfig(apiTotalConfig);
    }

    private boolean repeatUrlSuffix(ApiTotalConfig apiTotalConfig){
        boolean result = false;
        String suffix = apiTotalConfig.getUrlSuffix();
        if (StringUtils.isEmpty(suffix)){
            return result;
        }
        ApiTotalConfig old = apiTotalConfigMapper.getConfigBySuffix(suffix);
        if (Optional.ofNullable(old).isPresent()){
            if (apiTotalConfig.getConfigId() == null){
                result = true;
            }else {
                if (!apiTotalConfig.getConfigId().equals(old.getConfigId())){
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * 修改接口配置
     *
     * @param apiTotalConfig 接口配置
     * @return 结果
     */
    @Override
    public int updateApiTotalConfig(ApiTotalConfig apiTotalConfig) {
        if (StringUtils.isEmpty(apiTotalConfig.getUrlSuffix())){
            throw new BusinessException("请输入接口地址后保存数据。");
        }
        //验证接口是否重复
        if(repeatUrlSuffix(apiTotalConfig)){
            throw new BusinessException("接口地址重复，请修改接口地址后保存。");
        }
        return apiTotalConfigMapper.updateApiTotalConfig(apiTotalConfig);
    }

    /**
     * 批量删除接口配置
     *
     * @param configIds 需要删除的接口配置ID
     * @return 结果
     */
    @Override
    public int deleteApiTotalConfigByIds(Long[] configIds) {
        return apiTotalConfigMapper.deleteApiTotalConfigByIds(configIds);
    }

    /**
     * 删除接口配置信息
     *
     * @param configId 接口配置ID
     * @return 结果
     */
    @Override
    public int deleteApiTotalConfigById(Long configId) {
        return apiTotalConfigMapper.deleteApiTotalConfigById(configId);
    }

    /**
     * 执行查询list方法
     */
    private List<Map<String,String>> executeDateQueryList(ApiTotalConfig config,ApiTotalConfigDto params){
        //组装查询参数
        String table = config.getTableName();
        String columns = config.getColumnName();
        if (StringUtils.isEmpty(table) || StringUtils.isEmpty(columns)){
            throw new BusinessException("接口配置表，字段错误，执行查询失败。");
        }

        params.setTables(table);
        params.setColumns(columns);
        //执行分页
        Long pageNum = params.getPageNum() == null || params.getPageNum()<1L?1L:params.getPageNum();
        Long pageSize = params.getPageSize() == null || params.getPageSize()<1L?100L:params.getPageSize();
        Long start = (pageNum-1)*pageSize;
        Long end = pageNum*pageSize;
        params.setStart(start);
        params.setEnd(end);
        return apiTotalConfigMapper.queryList(params);
    }

}
