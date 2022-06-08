package com.sinonc.origins.service;

import com.sinonc.origins.api.domain.ProProductInfo;
import com.sinonc.origins.dto.ProProductInfoDto;
import com.sinonc.origins.vo.ProProductInfoVo;

import java.util.List;

/**
 * 产品信息Service接口
 *
 * @author zhangxl
 * @date 2020-10-21
 */
public interface IProProductInfoService {

    /**
     * 根据地区查询地区朔源信息
     * @param pAreaCode
     * @return
     */
    List<ProProductInfo> getProductInfoBycode(Long pAreaCode);

    /**
     * 产品数量统计
     * @param baseArea
     * @return
     */
    public long getCount(String baseArea);

    /**
     * 查询产品信息
     *
     * @param productId 产品信息ID
     * @return 产品信息
     */
    public ProProductInfoDto selectProProductInfoById(Long productId);

    /**
     * 查询产品信息列表
     *
     * @param proProductInfo 产品信息
     * @return 产品信息集合
     */
    public List<ProProductInfo> selectProProductInfoList(ProProductInfo proProductInfo);

    /**
     * 大屏查询产品信息列表
     *
     * @param productInfo 产品信息
     * @return 产品信息集合
     */
    public List<ProProductInfo> selectProductByLike(ProProductInfo productInfo);

    /**
     * 根据部门id获取部门关联的溯源产品
     */
    public List<ProProductInfo> listProProductByDeptId(Long deptId);

    /**
     * 查询产品信息列表
     *
     * @param proProductInfoDto 产品信息
     * @return 产品信息集合
     */
    public List<ProProductInfoDto> listProProductInfo(ProProductInfoDto proProductInfoDto);

    /**
     * 查询所有产品信息
     *
     * @return 产品信息集合
     */
    public List<ProProductInfo> selectProductInfos();

    /**
     * 新增产品信息
     *
     * @param proProductInfoVo 产品信息
     * @return 结果
     */
    public int insertProProductInfo(ProProductInfoVo proProductInfoVo);

    /**
     * 修改产品信息
     *
     * @param proProductInfoVo 产品信息
     * @return 结果
     */
    public int updateProProductInfo(ProProductInfoVo proProductInfoVo);

    /**
     * 批量删除产品信息
     *
     * @param productIds 需要删除的产品信息ID
     * @return 结果
     */
    public int deleteProProductInfoByIds(Long[] productIds);

    /**
     * 删除产品信息信息
     *
     * @param productId 产品信息ID
     * @return 结果
     */
    public int deleteProProductInfoById(Long productId);

    /**
     * 查询产品编码信息列表
     *
     * @param code 产品信息
     * @return 产品信息集合
     */
    public ProProductInfo selectProductByCode(String  code);
}
