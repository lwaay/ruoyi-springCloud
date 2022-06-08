package com.sinonc.base.service.impl;

import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.mapper.BaseFarmMapper;
import com.sinonc.base.service.IBaseFarmService;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.SysUser;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 基地信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-25
 */
@Slf4j
@Service
public class BaseFarmServiceImpl implements IBaseFarmService {
    @Autowired
    private BaseFarmMapper baseFarmMapper;

    @Autowired
    private RemoteWxUserService wxUserService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询基地信息
     *
     * @param farmId 基地信息ID
     * @return 基地信息
     */
    @Override
    public BaseFarm selectBaseFarmById(Long farmId) {
        return baseFarmMapper.selectBaseFarmById(farmId);
    }

    /**
     * 查询基地信息列表
     *
     * @param baseFarm 基地信息
     * @return 基地信息
     */
    @Override
    public List<BaseFarm> selectBaseFarmList(BaseFarm baseFarm) {
        return baseFarmMapper.selectBaseFarmList(baseFarm);
    }

    /**
     * 新增基地信息
     *
     * @param baseFarm 基地信息
     * @return 结果
     */
    @Override
    public int insertBaseFarm(BaseFarm baseFarm) {
        baseFarm.setCreateTime(DateUtils.getNowDate());
        Long areaCode = baseFarmMapper.getMaxAreaCode(baseFarm.getAreaCode());
        if (areaCode == null) {
            //例如500105102000  取 500105102
            String iniAreaCode = baseFarm.getAreaCode().substring(0, 9);
            iniAreaCode = iniAreaCode + "0001";
            baseFarm.setFarmCode(Long.valueOf(iniAreaCode));
        } else {
            baseFarm.setFarmCode(areaCode);
        }
        return baseFarmMapper.insertBaseFarm(baseFarm);
    }

    /**
     * 修改基地信息
     *
     * @param baseFarm 基地信息
     * @return 结果
     */
    @Override
    public int updateBaseFarm(BaseFarm baseFarm) {
        baseFarm.setUpdateTime(DateUtils.getNowDate());
        return baseFarmMapper.updateBaseFarm(baseFarm);
    }

    /**
     * 批量删除基地信息
     *
     * @param farmIds 需要删除的基地信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseFarmByIds(Long[] farmIds) {
        return baseFarmMapper.deleteBaseFarmByIds(farmIds);
    }

    /**
     * 删除基地信息信息
     *
     * @param farmId 基地信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseFarmById(Long farmId) {
        return baseFarmMapper.deleteBaseFarmById(farmId);
    }

    @Override
    public Integer getFarmCount() {
        return baseFarmMapper.getFarmCount();
    }

    @Override
    public Double getFarmAreaCount() {
        return baseFarmMapper.getFarmAreaCount();
    }

    @Override
    public List<String> getFarmNameList() {
        return baseFarmMapper.getFarmNameList();
    }

    @Override
    public List<BaseFarm> getFarmListByMemberId(Long memberId) {
        List<BaseFarm> baseFarmList = new ArrayList<>();

        R<WxUser> wxUserOK = wxUserService.getWxUserByMemberId(memberId);
        if (wxUserOK == null) {
            return baseFarmList;
        }
        WxUser wxUser = wxUserOK.getData();
        if (wxUser == null) {
            return baseFarmList;
        }
        String entityIdString = wxUser.getEntityId();
        if (entityIdString == null) {
            return baseFarmList;
        }

        Long entityId = Long.valueOf(entityIdString);

        BaseFarm baseFarm = new BaseFarm();
        baseFarm.setEntityId(entityId);
        List<BaseFarm> rsList = baseFarmMapper.selectBaseFarmListByEntityId(baseFarm);
        return rsList;
    }

    /**
     * 根据系统用户ID查询基地列表
     *
     * @return
     */
    @Override
    public List<BaseFarm> getFarmListBySys(){
        List<BaseFarm> baseFarmList = new ArrayList<>();
        LoginUser loginUser = tokenService.getLoginUser();
        if (!Optional.ofNullable(loginUser).isPresent()){
            return baseFarmList;
        }
        Long entityId = loginUser.getEntityId();
        if (entityId == null || entityId == 0){
            return baseFarmList;
        }
        BaseFarm baseFarm = new BaseFarm();
        baseFarm.setEntityId(entityId);
        log.error("主体id：{}",entityId);
        return baseFarmMapper.selectBaseFarmListByEntityId(baseFarm);

    }

    @Override
    public int addBaseFarm(BaseFarm baseFarm) {
        baseFarm.setCreateTime(DateUtils.getNowDate());
        baseFarm.setBaseCreateDate(DateUtils.getNowDate());
        baseFarm.setHasMonitor(0L);
        baseFarm.setHasIot(0L);
        String areaCode = "5001551";
        baseFarm.setAreaCode(areaCode);
        Long farmCode = baseFarmMapper.getMaxAreaCode(areaCode);
        Long memberId = baseFarm.getMemberId();
        R<WxUser> wxUserOK = wxUserService.getWxUserByMemberId(memberId);
        WxUser wxUser = wxUserOK.getData();
        Long entityId = Long.valueOf(wxUser.getEntityId());
        baseFarm.setEntityId(entityId);
        if (farmCode == null) {
            //例如500105102000  取 500105102
            String iniAreaCode = "500105102" + "0001";
            baseFarm.setFarmCode(Long.valueOf(iniAreaCode));
        } else {
            baseFarm.setFarmCode(farmCode);
        }
        return baseFarmMapper.insertBaseFarm(baseFarm);
    }
}
