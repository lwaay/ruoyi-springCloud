package com.sinonc.orders.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.orders.mapper.YxSystemCityMapper;
import com.sinonc.orders.domain.YxSystemCity;
import com.sinonc.orders.service.IYxSystemCityService;
import org.springframework.util.CollectionUtils;

/**
 * 城市Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@Service
public class YxSystemCityServiceImpl implements IYxSystemCityService {
    @Autowired
    private YxSystemCityMapper yxSystemCityMapper;

    /**
     * 查询城市
     *
     * @param id 城市ID
     * @return 城市
     */
    @Override
    public YxSystemCity selectYxSystemCityById(Long id) {
        return yxSystemCityMapper.selectYxSystemCityById(id);
    }

    /**
     * 查询城市列表
     *
     * @param yxSystemCity 城市
     * @return 城市
     */
    @Override
    public List<YxSystemCity> selectYxSystemCityList(YxSystemCity yxSystemCity) {
        return yxSystemCityMapper.selectYxSystemCityList(yxSystemCity);
    }

    /**
     * 新增城市
     *
     * @param yxSystemCity 城市
     * @return 结果
     */
    @Override
    public int insertYxSystemCity(YxSystemCity yxSystemCity) {
        return yxSystemCityMapper.insertYxSystemCity(yxSystemCity);
    }

    /**
     * 修改城市
     *
     * @param yxSystemCity 城市
     * @return 结果
     */
    @Override
    public int updateYxSystemCity(YxSystemCity yxSystemCity) {
        return yxSystemCityMapper.updateYxSystemCity(yxSystemCity);
    }

    /**
     * 批量删除城市
     *
     * @param ids 需要删除的城市ID
     * @return 结果
     */
    @Override
    public int deleteYxSystemCityByIds(Long[] ids) {
        return yxSystemCityMapper.deleteYxSystemCityByIds(ids);
    }

    /**
     * 删除城市信息
     *
     * @param id 城市ID
     * @return 结果
     */
    @Override
    public int deleteYxSystemCityById(Long id) {
        return yxSystemCityMapper.deleteYxSystemCityById(id);
    }

    /**
     * 获取城市树
     */
    public List<YxSystemCity> treeCity(){
        List<YxSystemCity> cities = yxSystemCityMapper.selectYxSystemCityList(YxSystemCity.builder().parentId(0).build());
        if (CollectionUtils.isEmpty(cities)){
            return cities;
        }
        cities.forEach(item->{
            item.setChildren(yxSystemCityMapper.selectYxSystemCityList(YxSystemCity.builder().parentId(item.getCityId()).build()));
        });
        return cities;
    }
}
