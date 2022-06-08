package com.sinonc.origins.service.impl;

import java.util.List;
import java.util.Map;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.origins.vo.BigScreenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.origins.mapper.ProInputMapper;
import com.sinonc.origins.domain.ProInput;
import com.sinonc.origins.service.IProInputService;

/**
 * 农产品投入品信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-04-15
 */
@Service
public class ProInputServiceImpl implements IProInputService {
    @Autowired
    private ProInputMapper proInputMapper;

    /**
     * 查询农产品投入品信息
     *
     * @param inputId 农产品投入品信息ID
     * @return 农产品投入品信息
     */
    @Override
    public ProInput selectProInputById(Long inputId) {
        return proInputMapper.selectProInputById(inputId);
    }

    /**
     * 查询农产品投入品信息列表
     *
     * @param proInput 农产品投入品信息
     * @return 农产品投入品信息
     */
    @Override
    public List<ProInput> selectProInputList(ProInput proInput) {
        return proInputMapper.selectProInputList(proInput);
    }

    /**
     * 新增农产品投入品信息
     *
     * @param proInput 农产品投入品信息
     * @return 结果
     */
    @Override
    public int insertProInput(ProInput proInput) {
        proInput.setCreateTime(DateUtils.getNowDate());
        return proInputMapper.insertProInput(proInput);
    }

    /**
     * 修改农产品投入品信息
     *
     * @param proInput 农产品投入品信息
     * @return 结果
     */
    @Override
    public int updateProInput(ProInput proInput) {
        proInput.setUpdateTime(DateUtils.getNowDate());
        return proInputMapper.updateProInput(proInput);
    }

    /**
     * 批量删除农产品投入品信息
     *
     * @param inputIds 需要删除的农产品投入品信息ID
     * @return 结果
     */
    @Override
    public int deleteProInputByIds(Long[] inputIds) {
        return proInputMapper.deleteProInputByIds(inputIds);
    }

    /**
     * 删除农产品投入品信息信息
     *
     * @param inputId 农产品投入品信息ID
     * @return 结果
     */
    @Override
    public int deleteProInputById(Long inputId) {
        return proInputMapper.deleteProInputById(inputId);
    }

    @Override
    public List<Map<String,Integer>> getInputRate(BigScreenVo bigScreenVo){
        return proInputMapper.selectInputRate(bigScreenVo);
    }

}
