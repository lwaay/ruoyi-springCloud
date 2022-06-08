package com.sinonc.system.mapper;

import java.util.List;

import com.sinonc.system.domain.BuserviceCompany;

/**
 * 服务商 Mapper接口
 *
 * @author ruoyi
 * @date 2022-02-24
 */
public interface BuserviceCompanyMapper {
    /**
     * 查询服务商
     *
     * @param serviceId 服务商 ID
     * @return 服务商
     */
    public BuserviceCompany selectBuserviceCompanyById(Long serviceId);

    /**
     * 查询服务商 列表
     *
     * @param buserviceCompany 服务商
     * @return 服务商 集合
     */
    public List<BuserviceCompany> selectBuserviceCompanyList(BuserviceCompany buserviceCompany);

    /**
     * 新增服务商
     *
     * @param buserviceCompany 服务商
     * @return 结果
     */
    public int insertBuserviceCompany(BuserviceCompany buserviceCompany);

    /**
     * 修改服务商
     *
     * @param buserviceCompany 服务商
     * @return 结果
     */
    public int updateBuserviceCompany(BuserviceCompany buserviceCompany);

    /**
     * 删除服务商
     *
     * @param serviceId 服务商 ID
     * @return 结果
     */
    public int deleteBuserviceCompanyById(Long serviceId);

    /**
     * 批量删除服务商
     *
     * @param serviceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBuserviceCompanyByIds(Long[] serviceIds);
}
