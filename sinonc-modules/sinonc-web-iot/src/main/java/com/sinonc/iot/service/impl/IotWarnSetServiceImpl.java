package com.sinonc.iot.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.iot.constant.EnumConstant;
import com.sinonc.iot.domain.IotWarnSet;
import com.sinonc.iot.mapper.IotWarnSetMapper;
import com.sinonc.iot.service.IotWarnSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 设备告警条件设置Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-09
 */
@Service
public class IotWarnSetServiceImpl implements IotWarnSetService {
    @Autowired
    private IotWarnSetMapper iotWarnSetMapper;

    /**
     * 查询设备告警条件设置
     *
     * @param warnsetId 设备告警条件设置ID
     * @return 设备告警条件设置
     */
    @Override
    public IotWarnSet getIotWarnSetById(Long warnsetId) {
        return iotWarnSetMapper.selectIotWarnSetById(warnsetId);
    }

    /**
     * 查询设备告警条件设置列表
     *
     * @param iotWarnSet 设备告警条件设置
     * @return 设备告警条件设置
     */
    @Override
    public List<IotWarnSet> getIotWarnSetList(IotWarnSet iotWarnSet) {
        return iotWarnSetMapper.selectIotWarnSetList(iotWarnSet);
    }

    /**
     * 新增设备告警条件设置
     *
     * @param iotWarnSet 设备告警条件设置
     * @return 结果
     */
    @Override
    public int addIotWarnSet(IotWarnSet iotWarnSet) {
        iotWarnSet.setIsEnble(EnumConstant.IS_ENBLE.YES.getKey());
        iotWarnSet.setCreateTime(new Date());
        iotWarnSet.setCreateBy(SecurityUtils.getUserId()+"");
        return iotWarnSetMapper.insertIotWarnSet(iotWarnSet);
    }

    /**
     * 修改设备告警条件设置
     *
     * @param iotWarnSet 设备告警条件设置
     * @return 结果
     */
    @Override
    public int updateIotWarnSet(IotWarnSet iotWarnSet) {
        return iotWarnSetMapper.updateIotWarnSet(iotWarnSet);
    }

    /**
     * 删除设备告警条件设置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIotWarnSetByIds(String ids) {
        return iotWarnSetMapper.deleteIotWarnSetByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备告警条件设置信息
     *
     * @param warnsetId 设备告警条件设置ID
     * @return 结果
     */
    @Override
    public int deleteIotWarnSetById(Long warnsetId) {
        return iotWarnSetMapper.deleteIotWarnSetById(warnsetId);
    }
}
