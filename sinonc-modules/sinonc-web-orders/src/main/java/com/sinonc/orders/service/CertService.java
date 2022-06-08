package com.sinonc.orders.service;

import com.sinonc.orders.domain.Cert;

import java.util.List;

/**
 * 认养证书 服务层
 *
 * @author sinonc
 * @date 2019-07-25
 */
public interface CertService {

	/**
     * 查询认养证书信息
     *
     * @param certId 认养证书ID
     * @return 认养证书信息
     */
	public Cert getCertById(Long certId);

	/**
     * 查询认养证书列表
     *
     * @param cert 认养证书信息
     * @return 认养证书集合
     */
	public List<Cert> listCert(Cert cert);

	/**
     * 新增认养证书
     *
     * @param cert 认养证书信息
     * @return 结果
     */
	public int addCert(Cert cert);

	/**
     * 修改认养证书
     *
     * @param cert 认养证书信息
     * @return 结果
     */
	public int updateCert(Cert cert);

	/**
     * 删除认养证书信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCertByIds(String ids);

}
