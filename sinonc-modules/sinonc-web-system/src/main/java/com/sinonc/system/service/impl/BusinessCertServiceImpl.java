package com.sinonc.system.service.impl;

import java.util.List;

import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.mapper.BusinessEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.system.mapper.BusinessCertMapper;
import com.sinonc.system.domain.BusinessCert;
import com.sinonc.system.service.IBusinessCertService;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 农业经营主体证件信息 Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class BusinessCertServiceImpl implements IBusinessCertService {
    @Autowired
    private BusinessCertMapper businessCertMapper;

    @Resource
    private BusinessEntityMapper businessEntityMapper;

    /**
     * 查询农业经营主体证件信息
     *
     * @param certId 农业经营主体证件信息 ID
     * @return 农业经营主体证件信息
     */
    @Override
    public BusinessCert selectBusinessCertById(Long certId) {
        return businessCertMapper.selectBusinessCertById(certId);
    }

    /**
     * 查询农业经营主体证件信息 列表
     *
     * @param businessCert 农业经营主体证件信息
     * @return 农业经营主体证件信息
     */
    @Override
    public List<BusinessCert> selectBusinessCertList(BusinessCert businessCert) {
        List<BusinessCert> businessCertList = businessCertMapper.selectBusinessCertList(businessCert);
        businessCertList.forEach(x->{
            BusinessEntity businessEntity = businessEntityMapper.selectBusinessEntityById(x.getEntityId());
            if(!ObjectUtils.isEmpty(businessEntity)){
                x.setEntityName(businessEntity.getEntityName());
            }
        });
        return businessCertList;
    }

    /**
     * 新增农业经营主体证件信息
     *
     * @param businessCert 农业经营主体证件信息
     * @return 结果
     */
    @Override
    public int insertBusinessCert(BusinessCert businessCert) {
        return businessCertMapper.insertBusinessCert(businessCert);
    }

    /**
     * 修改农业经营主体证件信息
     *
     * @param businessCert 农业经营主体证件信息
     * @return 结果
     */
    @Override
    public int updateBusinessCert(BusinessCert businessCert) {
        return businessCertMapper.updateBusinessCert(businessCert);
    }

    /**
     * 批量删除农业经营主体证件信息
     *
     * @param certIds 需要删除的农业经营主体证件信息 ID
     * @return 结果
     */
    @Override
    public int deleteBusinessCertByIds(Long[] certIds) {
        return businessCertMapper.deleteBusinessCertByIds(certIds);
    }

    /**
     * 删除农业经营主体证件信息 信息
     *
     * @param certId 农业经营主体证件信息 ID
     * @return 结果
     */
    @Override
    public int deleteBusinessCertById(Long certId) {
        return businessCertMapper.deleteBusinessCertById(certId);
    }
}
