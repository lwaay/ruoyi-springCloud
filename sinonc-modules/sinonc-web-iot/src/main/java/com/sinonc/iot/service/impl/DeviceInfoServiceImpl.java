package com.sinonc.iot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.vo.BaseFarmVo;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.iot.api.domain.DataPoint;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.api.domain.DeviceProperty;
import com.sinonc.iot.dto.ExtDeviceInfoDto;
import com.sinonc.iot.mapper.DeviceInfoMapper;
import com.sinonc.iot.mapper.DevicePropertyMapper;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.service.IDevicePropertyService;
import com.sinonc.iot.service.IDeviceTypeService;
import com.sinonc.iot.vo.DataPointVo;
import com.sinonc.iot.vo.DeviceInfoVo;
import com.sinonc.iot.vo.InfoVo;
import com.sinonc.system.api.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 设备Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-25
 */
@Service
@Slf4j
public class DeviceInfoServiceImpl implements IDeviceInfoService {
    @Resource
    private DeviceInfoMapper deviceInfoMapper;
    @Autowired
    private IDeviceTypeService deviceTypeService;
    @Autowired
    private IDevicePropertyService devicePropertyService;
    @Resource
    private RemoteBaseFarmService baseFarmService;

    @Resource
    private DevicePropertyMapper devicePropertyMapper;

    /**
     * 查询设备
     *
     * @param id 设备ID
     * @return 设备
     */
    @Override
    public DeviceInfo selectDeviceInfoById(Long id) {
        return deviceInfoMapper.selectDeviceInfoById(id);
    }

    /**
     * 查询设备列表
     *
     * @param deviceInfo 设备
     * @return 设备
     */
    @Override
    public List<DeviceInfo> selectDeviceInfoList(DeviceInfo deviceInfo) {
        return deviceInfoMapper.selectDeviceInfoList(deviceInfo);
    }

    /**
     * 新增设备
     *
     * @param deviceInfo 设备
     * @return 结果
     */
    @Override
    public int insertDeviceInfo(DeviceInfo deviceInfo) {
        deviceInfo.setCreateTime(DateUtils.getNowDate());
        return deviceInfoMapper.insertDeviceInfo(deviceInfo);
    }

    /**
     * 修改设备
     *
     * @param deviceInfo 设备
     * @return 结果
     */
    @Override
    public int updateDeviceInfo(DeviceInfo deviceInfo) {
        deviceInfo.setUpdateTime(DateUtils.getNowDate());
        return deviceInfoMapper.updateDeviceInfo(deviceInfo);
    }

    /**
     * 批量删除设备
     *
     * @param ids 需要删除的设备ID
     * @return 结果
     */
    @Override
    public int deleteDeviceInfoByIds(Long[] ids) {
        return deviceInfoMapper.deleteDeviceInfoByIds(ids);
    }

    /**
     * 删除设备信息
     *
     * @param id 设备ID
     * @return 结果
     */
    @Override
    public int deleteDeviceInfoById(Long id) {
        return deviceInfoMapper.deleteDeviceInfoById(id);
    }

    @Override
    public List<DeviceInfo> getDeviceListByIds(String[] ids) {
        return deviceInfoMapper.getDeviceListByIds(ids);
    }

    @Override
    public Integer getCount() {
        return deviceInfoMapper.getCount();
    }

    @Override
    public List<DeviceInfo> getDeviceListByBaseId(Long id) {
        return deviceInfoMapper.getDeviceListByBaseId(id);
    }

    @Override
    public DeviceInfo getDeviceInfoByDeviceId(String id) {
        return deviceInfoMapper.getDeviceInfoByDeviceId(id);
    }

    @Override
    public List<DeviceInfo> getDeviceByType(String id) {
        return deviceInfoMapper.getDeviceByType(id);
    }

    @Override
    public List<DeviceInfoVo> getDeviceInfoList(DeviceInfo deviceInfo) {
        return deviceInfoMapper.getDeviceInfoList(deviceInfo);
    }

    @Override
    public DeviceInfoVo getDeviceInfo(String deviceId) {
        return deviceInfoMapper.getDeviceInfo(deviceId);
    }

    @Override
    public List<ExtDeviceInfoDto> getExtDeviceInfoDtoList() throws IOException {
        Connection.Response response = org.jsoup.Jsoup.connect("http://218.201.61.89:65008/index6.htm").method(Connection.Method.GET)
                .timeout(10 * 1000)
                .userAgent("Mozilla/5.0")
                .execute();
        if (response == null) {
            return new ArrayList<>();
        }
        Document document1 = response.parse();
        Element trs = document1.select(".supersive_bottom_content").get(0);
        Elements clearfixList = trs.select(".clearfix");
        List<ExtDeviceInfoDto> list = new ArrayList<>();
        for (int i = 0; i < clearfixList.size(); i++) {
            Element clearfixElement = clearfixList.get(i);

            ExtDeviceInfoDto extDeviceInfoDto = buildDeviceInfo(clearfixElement);
            list.add(extDeviceInfoDto);
        }
        return list;
    }

    private static ExtDeviceInfoDto buildDeviceInfo(Element clearfixElement) {
        ExtDeviceInfoDto extDeviceInfoDto = new ExtDeviceInfoDto();
        Element suprviseTitle1 = clearfixElement.select(".suprvise_title1").get(1);
        extDeviceInfoDto.setBaseFarmName(suprviseTitle1.text());
        Element suprviseTitle2 = clearfixElement.select(".suprvise_title2").get(0);
        extDeviceInfoDto.setTownsName(suprviseTitle2.text());
        Element suprviseTitle3 = clearfixElement.select(".suprvise_title3").get(0);
        extDeviceInfoDto.setDeviceName(suprviseTitle3.text());
        Element suprviseTitle4 = clearfixElement.select(".suprvise_title4").get(0);
        extDeviceInfoDto.setSource(suprviseTitle4.text());
        Element suprviseTitle5 = clearfixElement.select(".suprvise_title5").get(0);
        extDeviceInfoDto.setStatus(suprviseTitle5.text());
        return extDeviceInfoDto;
    }

    /**
     * 获取物联网设备运行情况
     * @return
     */
    @Override
    public List<InfoVo> selectDeviceList(){
        return deviceInfoMapper.selectDeviceList();
    }

    @Override
    public String[] getUserAllDeviceIds(Long entityId) {
        BaseFarm baseFarm = new BaseFarm();
        baseFarm.setEntityId(entityId);
        List<BaseFarm> list = baseFarmService.list(baseFarm).getData();
        List<String> idsList = new ArrayList<>();
        for (BaseFarm farm : list) {
            String deviceIdsString = farm.getDeviceIds();
            if (StringUtils.isNotEmpty(deviceIdsString)) {
                String[] ids = Convert.toStrArray(deviceIdsString);
                List<String> tempList = Arrays.asList(ids);
                idsList.addAll(tempList);
            }
        }
        return idsList.toArray(new String[idsList.size()]);
    }
}
