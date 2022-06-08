package com.sinonc.origins.mapper;

import java.util.List;

import com.sinonc.origins.domain.IndustryData;
import com.sinonc.origins.dto.IndustryDataWithArea;

/**
 * 产业数据(大屏)Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-18
 */
public interface IndustryDataMapper {
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
     * 删除产业数据(大屏)
     *
     * @param id 产业数据(大屏)ID
     * @return 结果
     */
    public int deleteIndustryDataById(Long id);

    /**
     * 批量删除产业数据(大屏)
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteIndustryDataByIds(Long[] ids);

    public List<IndustryDataWithArea> selectIndustryDataGroupByAreaCode(String year);

    public List<IndustryDataWithArea> selectIndustryDataGroupByBreed(String year);

    public List<IndustryDataWithArea> selectIndustryDataGroupByBreedLimitFive(String year);
}
