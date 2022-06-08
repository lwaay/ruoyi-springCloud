package com.sinonc.iot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.config.property.IotConfig;
import com.sinonc.iot.domain.IotWarn;
import com.sinonc.iot.dto.IotWarnDto;
import com.sinonc.iot.mapper.IotWarnMapper;
import com.sinonc.iot.service.IDeviceInfoService;
import com.sinonc.iot.service.IotWarnService;
import com.sinonc.iot.util.WarnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * 设备告警信息Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-09
 */
@Service
@Slf4j
public class IotWarnServiceImpl implements IotWarnService {
    @Autowired
    private IotWarnMapper iotWarnMapper;

    @Autowired
    private IotConfig appInfoConfing;

    @Autowired
    private IDeviceInfoService deviceInfoService;

    /**
     * 查询设备告警信息
     *
     * @param warnId 设备告警信息ID
     * @return 设备告警信息
     */
    @Override
    public IotWarn getIotWarnById(Long warnId) {
        return iotWarnMapper.selectIotWarnById(warnId);
    }

    /**
     * 查询设备告警信息列表
     *
     * @param iotWarn 设备告警信息
     * @return 设备告警信息
     */
    @Override
    public List<IotWarn> getIotWarnList(IotWarn iotWarn) {
        return iotWarnMapper.selectIotWarnList(iotWarn);
    }

    /**
     * 查询设备告警信息列表
     *
     * @param iotWarn 设备告警信息
     * @return 设备告警信息
     */
    @Override
    public List<IotWarn> listIotWarm(IotWarn iotWarn) {
        return iotWarnMapper.listIotWarm(iotWarn);
    }

    /**
     * 新增设备告警信息
     *
     * @param iotWarn 设备告警信息
     * @return 结果
     */
    @Override
    public int addIotWarn(IotWarn iotWarn) {
        return iotWarnMapper.insertIotWarn(iotWarn);
    }

    /**
     * 修改设备告警信息
     *
     * @param iotWarn 设备告警信息
     * @return 结果
     */
    @Override
    public int updateIotWarn(IotWarn iotWarn) {
        return iotWarnMapper.updateIotWarn(iotWarn);
    }

    /**
     * 删除设备告警信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIotWarnByIds(String ids) {
        return iotWarnMapper.deleteIotWarnByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备告警信息信息
     *
     * @param warnId 设备告警信息ID
     * @return 结果
     */
    @Override
    public int deleteIotWarnById(Long warnId) {
        return iotWarnMapper.deleteIotWarnById(warnId);
    }

    /**
     * 根据id获取预警明细
     */
    @Override
    public IotWarnDto getIotWarnDetailById(Long warnId){
        IotWarnDto res = new IotWarnDto();
        if (warnId ==null || warnId <1L){
            return null;
        }
        IotWarn warn = iotWarnMapper.selectIotWarnById(warnId);
        if (!Optional.ofNullable(warn).isPresent()){
            return null;
        }
        BeanUtils.copyProperties(warn,res);
        //获取设备名称
        if (StringUtils.isNotBlank(res.getDeviceId())){
            DeviceInfo deviceInfo = deviceInfoService.getDeviceInfo(res.getDeviceId());
            if (Optional.ofNullable(deviceInfo).isPresent()){
                res.setDeviceName(deviceInfo.getDeviceName());
            }
        }
        //非虫情预警
        if (!warn.getWarnType().equals(4)){
            return res;
        }
        //如果是虫情数据，获取虫情字典
        String result = warn.getWarnInfo();
        if (StringUtils.isEmpty(result) || "[]".equals(result)){
            return res;
        }
        JSONArray results = JSON.parseArray(result);
        JSONArray dicts = new JSONArray();
        if (!Optional.ofNullable(warn).isPresent() || results.isEmpty()){
            return res;
        }
        results.stream().forEach(item->{
            JSONObject object = (JSONObject) item;
            if (!Optional.ofNullable(object).isPresent() || object.isEmpty()){
                return;
            }
            Long dictId = 0L;
            try {
                dictId = Long.parseLong(object.getString("id"));
            }catch (Exception e){
                log.error(e.getMessage(),e);
                return;
            }
            if (dictId == null || dictId<1L){
                return;
            }
            //根据id获取害虫字典
            String uri = appInfoConfing.getHost()+"/api/iot/app/getInsectDict/" +dictId;
            AjaxResult ajaxResult = WarnUtils.getBodyAjaxResult(appInfoConfing.getAppKey(),appInfoConfing.getAppSecret(),uri);
            if (CollectionUtils.isEmpty(ajaxResult) || ajaxResult.get("code") == null || (int)ajaxResult.get("code") != 0){
                return;
            }
            JSONObject dict = (JSONObject) JSONObject.toJSON(ajaxResult.get("data"));
            if (!Optional.ofNullable(dict).isPresent() || dict.isEmpty()){
                return;
            }
            dict.put("number",object.get("num"));
            dicts.add(dict);
        });
        res.setInsectDicts(dicts);
        return res;
    }

    @Override
    public int warnCount() {
        return iotWarnMapper.warnCount();
    }
}
