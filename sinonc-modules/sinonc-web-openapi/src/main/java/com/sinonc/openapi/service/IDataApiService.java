package com.sinonc.openapi.service;

import com.sinonc.openapi.domain.DataApi;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 第三方数据接入管理Service接口
 *
 * @author huanghao
 * @date 2020-10-21
 */
public interface IDataApiService {
    /**
     * 查询第三方数据接入管理
     *
     * @param id 第三方数据接入管理ID
     * @return 第三方数据接入管理
     */
    public DataApi selectDataApiById(Long id);

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
    public int insertDataApi(DataApi dataApi, HttpServletRequest request);

    /**
     * 修改第三方数据接入管理
     *
     * @param dataApi 第三方数据接入管理
     * @return 结果
     */
    public int updateDataApi(DataApi dataApi);

    /**
     * 批量删除第三方数据接入管理
     *
     * @param ids 需要删除的第三方数据接入管理ID
     * @return 结果
     */
    public int deleteDataApiByIds(Long[] ids);

    /**
     * 删除第三方数据接入管理信息
     *
     * @param id 第三方数据接入管理ID
     * @return 结果
     */
    public int deleteDataApiById(Long id);
}
