package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Cert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 认养证书 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface CertMapper {
	/**
     * 查询认养证书信息
     *
     * @param certId 认养证书ID
     * @return 认养证书信息
     */
	public Cert selectCertById(Long certId);

	/**
     * 查询认养证书列表
     *
     * @param cert 认养证书信息
     * @return 认养证书集合
     */
	public List<Cert> selectCertList(Cert cert);

	/**
     * 新增认养证书
     *
     * @param cert 认养证书信息
     * @return 结果
     */
	public int insertCert(Cert cert);

	/**
     * 修改认养证书
     *
     * @param cert 认养证书信息
     * @return 结果
     */
	public int updateCert(Cert cert);

	/**
     * 删除认养证书
     *
     * @param certId 认养证书ID
     * @return 结果
     */
	public int deleteCertById(Long certId);

	/**
     * 批量删除认养证书
     *
     * @param certIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteCertByIds(String[] certIds);

    public String selectLastNo();
}
