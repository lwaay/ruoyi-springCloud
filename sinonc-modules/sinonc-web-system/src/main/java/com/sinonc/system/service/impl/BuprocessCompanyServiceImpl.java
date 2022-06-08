package com.sinonc.system.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.BuprocessCompanyMapper;
import com.sinonc.system.domain.BuprocessCompany;
import com.sinonc.system.service.IBuprocessCompanyService;

/**
 * 加工农企Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@Service
public class BuprocessCompanyServiceImpl implements IBuprocessCompanyService {
    @Autowired
    private BuprocessCompanyMapper buprocessCompanyMapper;

    /**
     * 查询加工农企
     *
     * @param proceId 加工农企ID
     * @return 加工农企
     */
    @Override
    public BuprocessCompany selectBuprocessCompanyById(Long proceId) {
        return buprocessCompanyMapper.selectBuprocessCompanyById(proceId);
    }

    /**
     * 查询加工农企列表
     *
     * @param buprocessCompany 加工农企
     * @return 加工农企
     */
    @Override
    public List<BuprocessCompany> selectBuprocessCompanyList(BuprocessCompany buprocessCompany) {
        return buprocessCompanyMapper.selectBuprocessCompanyList(buprocessCompany);
    }

    /**
     * 新增加工农企
     *
     * @param buprocessCompany 加工农企
     * @return 结果
     */
    @Override
    public int insertBuprocessCompany(BuprocessCompany buprocessCompany) {
        buprocessCompany.setCreateTime(DateUtils.getNowDate());
        return buprocessCompanyMapper.insertBuprocessCompany(buprocessCompany);
    }

    /**
     * 修改加工农企
     *
     * @param buprocessCompany 加工农企
     * @return 结果
     */
    @Override
    public int updateBuprocessCompany(BuprocessCompany buprocessCompany) {
        buprocessCompany.setUpdateTime(DateUtils.getNowDate());
        return buprocessCompanyMapper.updateBuprocessCompany(buprocessCompany);
    }

    /**
     * 批量删除加工农企
     *
     * @param proceIds 需要删除的加工农企ID
     * @return 结果
     */
    @Override
    public int deleteBuprocessCompanyByIds(Long[] proceIds) {
        return buprocessCompanyMapper.deleteBuprocessCompanyByIds(proceIds);
    }

    /**
     * 删除加工农企信息
     *
     * @param proceId 加工农企ID
     * @return 结果
     */
    @Override
    public int deleteBuprocessCompanyById(Long proceId) {
        return buprocessCompanyMapper.deleteBuprocessCompanyById(proceId);
    }
}
