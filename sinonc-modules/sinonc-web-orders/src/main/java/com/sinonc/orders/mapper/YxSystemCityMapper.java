package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.YxSystemCity;

/**
 * 城市Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface YxSystemCityMapper {
    /**
     * 查询城市
     *
     * @param id 城市ID
     * @return 城市
     */
    public YxSystemCity selectYxSystemCityById(Long id);

    /**
     * 查询城市列表
     *
     * @param yxSystemCity 城市
     * @return 城市集合
     */
    public List<YxSystemCity> selectYxSystemCityList(YxSystemCity yxSystemCity);

    /**
     * 新增城市
     *
     * @param yxSystemCity 城市
     * @return 结果
     */
    public int insertYxSystemCity(YxSystemCity yxSystemCity);

    /**
     * 修改城市
     *
     * @param yxSystemCity 城市
     * @return 结果
     */
    public int updateYxSystemCity(YxSystemCity yxSystemCity);

    /**
     * 删除城市
     *
     * @param id 城市ID
     * @return 结果
     */
    public int deleteYxSystemCityById(Long id);

    /**
     * 批量删除城市
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxSystemCityByIds(Long[] ids);
}
