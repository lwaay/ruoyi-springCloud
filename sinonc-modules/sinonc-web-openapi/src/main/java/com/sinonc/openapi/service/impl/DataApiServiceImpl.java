package com.sinonc.openapi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.exceptions.ApiException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.openapi.domain.DataApi;
import com.sinonc.openapi.domain.DataApiConfig;
import com.sinonc.openapi.domain.DataApiItem;
import com.sinonc.openapi.dto.DataApiConfigDto;
import com.sinonc.openapi.mapper.DataApiConfigMapper;
import com.sinonc.openapi.mapper.DataApiItemMapper;
import com.sinonc.openapi.mapper.DataApiMapper;
import com.sinonc.openapi.service.IDataApiService;
import com.sinonc.openapi.vo.DataApiVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 第三方数据接入管理Service业务层处理
 *
 * @author huanghao
 * @date 2020-10-21
 */
@Service
public class DataApiServiceImpl implements IDataApiService {
    @Autowired
    private DataApiMapper dataApiMapper;
    @Autowired
    private DataApiItemMapper dataApiItemMapper;

    @Autowired
    private DataApiConfigMapper apiConfigMapper;

    /**
     * 查询第三方数据接入管理
     *
     * @param id 第三方数据接入管理ID
     * @return 第三方数据接入管理
     */
    @Override
    public DataApi selectDataApiById(Long id) {
        DataApi dataApi = dataApiMapper.selectById(id);
        String[] openUrlArray = dataApi.getOpenApi().split("[,，]");
        dataApi.setApiNum(openUrlArray.length);
        List<DataApiItem> apiItems = dataApiItemMapper.selectDataApiItemList(new DataApiItem());
        List<String> openUrlList = Arrays.asList(openUrlArray);
        apiItems = apiItems.stream().peek(e -> {
            e.setIsUse(0);
            if (openUrlList.contains(e.getId().toString())) {
                e.setIsUse(1);
            }
        }).collect(Collectors.toList());
        dataApi.setApiItems(apiItems);
        List<DataApiConfig> dataApiConfigs = apiConfigMapper.selectList(new QueryWrapper<DataApiConfig>().eq("data_api_id", id));
        List<DataApiVo> configList = new ArrayList<>();
        dataApiConfigs.forEach(e -> {
            DataApiVo dataApiVo = new DataApiVo();
            dataApiVo.setDataApiItemId(e.getDataApiItemId());
            dataApiVo.setConfigList(JSONArray.parseArray(e.getConfig(), DataApiConfigDto.class));
            dataApiVo.setId(e.getId());
            configList.add(dataApiVo);
        });
        dataApi.setConfigList(configList);
        return dataApi;
    }

    /**
     * 查询第三方数据接入管理列表
     *
     * @param dataApi 第三方数据接入管理
     * @return 第三方数据接入管理
     */
    @Override
    public List<DataApi> selectDataApiList(DataApi dataApi) {
        return dataApiMapper.selectDataApiList(dataApi);
    }

    /**
     * 新增第三方数据接入管理
     *
     * @param dataApi 第三方数据接入管理
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDataApi(DataApi dataApi, HttpServletRequest request) {
        String appLabel = request.getHeader("appLabel");
        dataApi.setCreateTime(DateUtils.getNowDate());
        DataApi existData = dataApiMapper.selectDataApiByAppLabel(dataApi.getSystemLabel());
        if(ObjectUtils.isEmpty(existData)){
            dataApi.setInterfaceNum(StringUtils.isNotEmpty(dataApi.getOpenApi()) ?dataApi.getOpenApi().split(",",-1).length:0);
            dataApiMapper.insert(dataApi);
        }else{
            throw new ApiException("已存在对应的系统key，请重新输入");
        }
        if (CollectionUtils.isNotEmpty(dataApi.getConfigList())) {
            List<DataApiVo> configList = dataApi.getConfigList();
            configList.forEach(e -> {
                DataApiConfig existConfig = apiConfigMapper.selectOne(new QueryWrapper<DataApiConfig>().eq("app_label", appLabel).eq("data_api_id", dataApi.getId()));
                if (existConfig != null) {
                    existConfig.setConfig(JSONArray.toJSONString(e.getConfigList()));
                    existConfig.setDataApiItemId(e.getDataApiItemId());
                    existConfig.setDataApiId(dataApi.getId());
                    existConfig.setAppLabel(appLabel);
                    apiConfigMapper.updateById(existConfig);
                } else {
                    DataApiConfig apiConfig = new DataApiConfig();
                    apiConfig.setConfig(JSONArray.toJSONString(e.getConfigList()));
                    apiConfig.setDataApiItemId(e.getDataApiItemId());
                    apiConfig.setDataApiId(dataApi.getId());
                    apiConfig.setAppLabel(appLabel);

                    apiConfigMapper.insert(apiConfig);
                }

            });
        }
        return 1;
    }

    /**
     * 修改第三方数据接入管理
     *
     * @param dataApi 第三方数据接入管理
     * @return 结果
     */
    @Override
    public int updateDataApi(DataApi dataApi) {
        if (CollectionUtils.isNotEmpty(dataApi.getConfigList())) {
            dataApi.getConfigList().forEach(e -> {
                DataApiConfig apiConfig = apiConfigMapper.selectById(e.getId());
                apiConfig.setConfig(JSONArray.toJSONString(e.getConfigList()));
                apiConfigMapper.updateById(apiConfig);
            });
        }
        dataApi.setInterfaceNum(StringUtils.isNotEmpty(dataApi.getOpenApi()) ?dataApi.getOpenApi().split(",",-1).length:0);
        return dataApiMapper.updateById(dataApi);
    }

    /**
     * 批量删除第三方数据接入管理
     *
     * @param ids 需要删除的第三方数据接入管理ID
     * @return 结果
     */
    @Override
    public int deleteDataApiByIds(Long[] ids) {
        return dataApiMapper.deleteDataApiByIds(ids);
    }

    /**
     * 删除第三方数据接入管理信息
     *
     * @param id 第三方数据接入管理ID
     * @return 结果
     */
    @Override
    public int deleteDataApiById(Long id) {
        return dataApiMapper.deleteDataApiById(id);
    }

//    public static void main(String[] args) {
//        String json =  "[{\"configList\":[{\"check\":1,\"key\":\"ossName\",\"name\":\"图片名称\",\"require\":0},{\"check\":1,\"key\":\"uploadIp\",\"name\":\"上传ip\",\"require\":0},{\"check\":1,\"key\":\"uploadClient\",\"name\":\"上传客户端\",\"require\":0},{\"check\":1,\"key\":\"fileSize\",\"name\":\"文件大小\",\"require\":0},{\"check\":1,\"key\":\"ossPath\",\"name\":\"访问路径\",\"require\":1}],\"dataApiItemId\":3,\"id\":4},{\"configList\":[{\"check\":0,\"key\":\"fileId\",\"name\":\"文件资源主键\",\"require\":0},{\"check\":0,\"key\":\"fileName\",\"name\":\"文件名称\",\"require\":0},{\"check\":1,\"key\":\"ossPath\",\"name\":\"文件oss地址\",\"require\":1},{\"check\":0,\"key\":\"ossName\",\"name\":\"文件oss名称\",\"require\":0},{\"check\":0,\"key\":\"fileSuffix\",\"name\":\"文件后缀\",\"require\":0},{\"check\":0,\"key\":\"fileSize\",\"name\":\"文件大小\",\"require\":0},{\"check\":1,\"key\":\"uploadDate\",\"name\":\"上传时间\",\"require\":0},{\"check\":1,\"key\":\"uploadIp\",\"name\":\"上传地址\",\"require\":0},{\"check\":0,\"key\":\"uploadClient\",\"name\":\"上传客户端名称\",\"require\":0}],\"dataApiItemId\":4,\"id\":5}]\n";
//        System.out.println(JSON.parse(json));
//    }
}
