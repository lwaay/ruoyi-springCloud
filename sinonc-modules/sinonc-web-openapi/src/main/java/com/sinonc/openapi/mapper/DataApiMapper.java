package com.sinonc.openapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinonc.openapi.domain.DataApi;

import java.util.List;

/**
 * 第三方数据接入管理Mapper接口
 *
 * @author huanghao
 * @date 2020-10-21
 */
public interface DataApiMapper extends BaseMapper<DataApi> {
    /**
     * 查询第三方数据接入管理
     *
     * @param id 第三方数据接入管理ID
     * @return 第三方数据接入管理
     */
    public DataApi selectDataApiById(Long id);

    /**
     * 查询第三方数据接入管理
     *
     * @param appLabel 第三方数据接入app标签
     * @return 第三方数据接入管理
     */
    public DataApi selectDataApiByAppLabel(String appLabel);

    /**
     * 查询第三方数据接入管理列表
     *
     * @param dataApi 第三方数据接入管理
     * @return 第三方数据接入管理集合
     */
    public List<DataApi> selectDataApiList(DataApi dataApi);

    /**
     * 新增第三方数据接入管理
     *
     * @param dataApi 第三方数据接入管理
     * @return 结果
     */
    public int insertDataApi(DataApi dataApi);

    /**
     * 修改第三方数据接入管理
     *
     * @param dataApi 第三方数据接入管理
     * @return 结果
     */
    public int updateDataApi(DataApi dataApi);

    /**
     * 删除第三方数据接入管理
     *
     * @param id 第三方数据接入管理ID
     * @return 结果
     */
    public int deleteDataApiById(Long id);

    /**
     * 批量删除第三方数据接入管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDataApiByIds(Long[] ids);
}
