package com.sinonc.system.service.impl;

import java.util.List;

import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.BuserviceCompanyMapper;
import com.sinonc.system.domain.BuserviceCompany;
import com.sinonc.system.service.IBuserviceCompanyService;

/**
 * 服务商 Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-24
 */
@Service
public class BuserviceCompanyServiceImpl implements IBuserviceCompanyService {
    @Autowired
    private BuserviceCompanyMapper buserviceCompanyMapper;

    /**
     * 查询服务商
     *
     * @param serviceId 服务商 ID
     * @return 服务商
     */
    @Override
    public BuserviceCompany selectBuserviceCompanyById(Long serviceId) {
        return buserviceCompanyMapper.selectBuserviceCompanyById(serviceId);
    }

    /**
     * 查询服务商 列表
     *
     * @param buserviceCompany 服务商
     * @return 服务商
     */
    @Override
    public List<BuserviceCompany> selectBuserviceCompanyList(BuserviceCompany buserviceCompany) {
        return buserviceCompanyMapper.selectBuserviceCompanyList(buserviceCompany);
    }

    /**
     * 新增服务商
     *
     * @param buserviceCompany 服务商
     * @return 结果
     */
    @Override
    public int insertBuserviceCompany(BuserviceCompany buserviceCompany) {
        buserviceCompany.setCreateTime(DateUtils.getNowDate());
        return buserviceCompanyMapper.insertBuserviceCompany(buserviceCompany);
    }

    /**
     * 修改服务商
     *
     * @param buserviceCompany 服务商
     * @return 结果
     */
    @Override
    public int updateBuserviceCompany(BuserviceCompany buserviceCompany) {
        buserviceCompany.setUpdateTime(DateUtils.getNowDate());
        return buserviceCompanyMapper.updateBuserviceCompany(buserviceCompany);
    }

    /**
     * 批量删除服务商
     *
     * @param serviceIds 需要删除的服务商 ID
     * @return 结果
     */
    @Override
    public int deleteBuserviceCompanyByIds(Long[] serviceIds) {
        return buserviceCompanyMapper.deleteBuserviceCompanyByIds(serviceIds);
    }

    /**
     * 删除服务商 信息
     *
     * @param serviceId 服务商 ID
     * @return 结果
     */
    @Override
    public int deleteBuserviceCompanyById(Long serviceId) {
        return buserviceCompanyMapper.deleteBuserviceCompanyById(serviceId);
    }
}
