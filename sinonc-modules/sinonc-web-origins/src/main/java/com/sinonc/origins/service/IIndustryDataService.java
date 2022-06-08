package com.sinonc.origins.service;

import java.util.List;
import java.util.Map;

import com.sinonc.origins.domain.IndustryData;

/**
 * 产业数据(大屏)Service接口
 *
 * @author ruoyi
 * @date 2022-04-18
 */
public interface IIndustryDataService {
    /**
     * 查询产业数据(大屏)
     *
     * @param id 产业数据(大屏)ID
     * @return 产业数据(大屏)
     */
    public IndustryData selectIndustryDataById(Long id);

    /**
     * 查询产业数据(大屏)列表
     *
     * @param industryData 产业数据(大屏)
     * @return 产业数据(大屏)集合
     */
    public List<IndustryData> selectIndustryDataList(IndustryData industryData);

    /**
     * 新增产业数据(大屏)
     *
     * @param industryData 产业数据(大屏)
     * @return 结果
     */
    public int insertIndustryData(IndustryData industryData);

    /**
     * 修改产业数据(大屏)
     *
     * @param industryData 产业数据(大屏)
     * @return 结果
     */
    public int updateIndustryData(IndustryData industryData);

    /**
     * 批量删除产业数据(大屏)
     *
     * @param ids 需要删除的产业数据(大屏)ID
     * @return 结果
     */
    public int deleteIndustryDataByIds(Long[] ids);

    /**
     * 删除产业数据(大屏)信息
     *
     * @param id 产业数据(大屏)ID
     * @return 结果
     */
    public int deleteIndustryDataById(Long id);

    public Map selectIndustryDataByAreaCode(String year);

    public Map selectIndustryDataByBreed(String year);

    public Map selectIndustryDataByBreedFive(String year);
}
