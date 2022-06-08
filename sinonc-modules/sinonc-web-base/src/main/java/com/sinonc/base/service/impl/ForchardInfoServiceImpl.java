package com.sinonc.base.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.base.mapper.ForchardInfoMapper;
import com.sinonc.base.domain.ForchardInfo;
import com.sinonc.base.service.IForchardInfoService;

/**
 * 果园信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-21
 */
@Service
public class ForchardInfoServiceImpl implements IForchardInfoService {
    @Autowired
    private ForchardInfoMapper forchardInfoMapper;

    /**
     * 查询果园信息
     *
     * @param orchId 果园信息ID
     * @return 果园信息
     */
    @Override
    public ForchardInfo selectForchardInfoById(Long orchId) {
        return forchardInfoMapper.selectForchardInfoById(orchId);
    }

    /**
     * 查询果园信息列表
     *
     * @param forchardInfo 果园信息
     * @return 果园信息
     */
    @Override
    public List<ForchardInfo> selectForchardInfoList(ForchardInfo forchardInfo) {
        return forchardInfoMapper.selectForchardInfoList(forchardInfo);
    }

    /**
     * 新增果园信息
     *
     * @param forchardInfo 果园信息
     * @return 结果
     */
    @Override
    public int insertForchardInfo(ForchardInfo forchardInfo) {
        forchardInfo.setCreateTime(DateUtils.getNowDate());
        return forchardInfoMapper.insertForchardInfo(forchardInfo);
    }

    /**
     * 修改果园信息
     *
     * @param forchardInfo 果园信息
     * @return 结果
     */
    @Override
    public int updateForchardInfo(ForchardInfo forchardInfo) {
        forchardInfo.setUpdateTime(DateUtils.getNowDate());
        return forchardInfoMapper.updateForchardInfo(forchardInfo);
    }

    /**
     * 批量删除果园信息
     *
     * @param orchIds 需要删除的果园信息ID
     * @return 结果
     */
    @Override
    public int deleteForchardInfoByIds(Long[] orchIds) {
        return forchardInfoMapper.deleteForchardInfoByIds(orchIds);
    }

    /**
     * 删除果园信息信息
     *
     * @param orchId 果园信息ID
     * @return 结果
     */
    @Override
    public int deleteForchardInfoById(Long orchId) {
        return forchardInfoMapper.deleteForchardInfoById(orchId);
    }
}
