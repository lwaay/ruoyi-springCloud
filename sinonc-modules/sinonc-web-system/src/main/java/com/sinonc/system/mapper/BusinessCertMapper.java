package com.sinonc.system.mapper;

import java.util.List;

import com.sinonc.system.domain.BusinessCert;

/**
 * 农业经营主体证件信息 Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-16
 */
public interface BusinessCertMapper {
    /**
     * 查询农业经营主体证件信息
     *
     * @param certId 农业经营主体证件信息 ID
     * @return 农业经营主体证件信息
     */
    public BusinessCert selectBusinessCertById(Long certId);

    /**
     * 查询农业经营主体证件信息 列表
     *
     * @param businessCert 农业经营主体证件信息
     * @return 农业经营主体证件信息 集合
     */
    public List<BusinessCert> selectBusinessCertList(BusinessCert businessCert);

    /**
     * 新增农业经营主体证件信息
     *
     * @param businessCert 农业经营主体证件信息
     * @return 结果
     */
    public int insertBusinessCert(BusinessCert businessCert);

    /**
     * 修改农业经营主体证件信息
     *
     * @param businessCert 农业经营主体证件信息
     * @return 结果
     */
    public int updateBusinessCert(BusinessCert businessCert);

    /**
     * 删除农业经营主体证件信息
     *
     * @param certId 农业经营主体证件信息 ID
     * @return 结果
     */
    public int deleteBusinessCertById(Long certId);

    /**
     * 批量删除农业经营主体证件信息
     *
     * @param certIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusinessCertByIds(Long[] certIds);
}
