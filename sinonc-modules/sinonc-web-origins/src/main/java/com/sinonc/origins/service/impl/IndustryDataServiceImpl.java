package com.sinonc.origins.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.origins.dto.IndustryDataWithArea;
import com.sinonc.system.api.RemoteConfigService;
import com.sinonc.system.api.domain.SysDictData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.origins.mapper.IndustryDataMapper;
import com.sinonc.origins.domain.IndustryData;
import com.sinonc.origins.service.IIndustryDataService;

/**
 * 产业数据(大屏)Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-18
 */
@Service
public class IndustryDataServiceImpl implements IIndustryDataService {
    @Autowired
    private IndustryDataMapper industryDataMapper;

    @Autowired
    private RemoteConfigService configService;

    /**
     * 查询产业数据(大屏)
     *
     * @param id 产业数据(大屏)ID
     * @return 产业数据(大屏)
     */
    @Override
    public IndustryData selectIndustryDataById(Long id) {
        return industryDataMapper.selectIndustryDataById(id);
    }

    /**
     * 查询产业数据(大屏)列表
     *
     * @param industryData 产业数据(大屏)
     * @return 产业数据(大屏)
     */
    @Override
    public List<IndustryData> selectIndustryDataList(IndustryData industryData) {
        return industryDataMapper.selectIndustryDataList(industryData);
    }

    /**
     * 新增产业数据(大屏)
     *
     * @param industryData 产业数据(大屏)
     * @return 结果
     */
    @Override
    public int insertIndustryData(IndustryData industryData) {
                                                                                                                                                                                                                        industryData.setCreateTime(DateUtils.getNowDate());
                                                                    return industryDataMapper.insertIndustryData(industryData);
    }

    /**
     * 修改产业数据(大屏)
     *
     * @param industryData 产业数据(大屏)
     * @return 结果
     */
    @Override
    public int updateIndustryData(IndustryData industryData) {
                                                                                                                                                                                                                                                                industryData.setUpdateTime(DateUtils.getNowDate());
                            return industryDataMapper.updateIndustryData(industryData);
    }

    /**
     * 批量删除产业数据(大屏)
     *
     * @param ids 需要删除的产业数据(大屏)ID
     * @return 结果
     */
    @Override
    public int deleteIndustryDataByIds(Long[] ids) {
        return industryDataMapper.deleteIndustryDataByIds(ids);
    }

    /**
     * 删除产业数据(大屏)信息
     *
     * @param id 产业数据(大屏)ID
     * @return 结果
     */
    @Override
    public int deleteIndustryDataById(Long id) {
        return industryDataMapper.deleteIndustryDataById(id);
    }

    @Override
    public Map selectIndustryDataByAreaCode(String year) {
        Map<String, String> areaCodeMap = new HashMap<>();
        List<String> areaNames = new ArrayList<>();
        List<String> plantScales = new ArrayList<>();
        List<String> values = new ArrayList<>();
        List<IndustryDataWithArea> dataWithAreaList = industryDataMapper.selectIndustryDataGroupByAreaCode(year);
        List<SysDictData> dictDataList = configService.dictType("baise_area").getData();
        dictDataList.forEach(x->{
            areaCodeMap.put(x.getDictValue(), x.getDictLabel());
        });
        dataWithAreaList.forEach(x->{
            areaNames.add(areaCodeMap.get(x.getAreaCode()));
            plantScales.add(x.getPlantScale());
            values.add(x.getValue());
        });
        List<List> data = new ArrayList<>();
        data.add(plantScales);
        data.add(values);
        Map<String, List> ret = new HashMap<>();
        ret.put("areaName", areaNames);
        ret.put("datas", data);
        return ret;
    }

    @Override
    public Map selectIndustryDataByBreed(String year) {
        Map<String, String> breedMap = new HashMap<>();
        List<String> breeds = new ArrayList<>();
        List<String> yields = new ArrayList<>();
        List<String> values = new ArrayList<>();
        List<IndustryDataWithArea> dataWithAreaList = industryDataMapper.selectIndustryDataGroupByBreed(year);
        List<SysDictData> dictDataList = configService.dictType("mango_type").getData();
        dictDataList.forEach(x->{
            breedMap.put(x.getDictValue(), x.getDictLabel());
        });
        dataWithAreaList.forEach(x->{
            breeds.add(breedMap.get(x.getBreed()));
            yields.add(x.getYield());
            values.add(x.getValue());
        });
        List<List> data = new ArrayList<>();
        data.add(yields);
        data.add(values);
        Map<String, List> ret = new HashMap<>();
        ret.put("breeds", breeds);
        ret.put("datas", data);
        return ret;
    }

    @Override
    public Map selectIndustryDataByBreedFive(String year) {
        Map<String, String> breedMap = new HashMap<>();
        List<String> breeds = new ArrayList<>();
        List<String> plantScales = new ArrayList<>();
        List<IndustryDataWithArea> dataWithAreaList = industryDataMapper.selectIndustryDataGroupByBreedLimitFive(year);
        List<SysDictData> dictDataList = configService.dictType("mango_type").getData();
        dictDataList.forEach(x->{
            breedMap.put(x.getDictValue(), x.getDictLabel());
        });
        dataWithAreaList.forEach(x->{
            breeds.add(breedMap.get(x.getBreed()));
            plantScales.add(x.getPlantScale());
        });
        Map<String, List> ret = new HashMap<>(2);
        ret.put("breeds", breeds);
        ret.put("plantScales", plantScales);
        return ret;
    }
}
