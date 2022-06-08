package com.sinonc.origins.mapper;

import com.sinonc.origins.api.domain.ProProductInfo;
import com.sinonc.origins.dto.ProProductInfoDto;

import java.util.List;

/**
 * 产品信息Mapper接口
 *
 * @author zhangxl
 * @date 2020-10-21
 */
public interface ProProductInfoMapper{

    /**
     * 产品数量统计
     * @param baseArea
     * @return
     */
    public long selectCount(String baseArea);

    /**
     * 查询产品信息
     *
     * @param productId 产品信息ID
     * @return 产品信息
     */
    public ProProductInfo selectProProductInfoById(Long productId);

    /**
     * 查询产品信息列表
     *
     * @param proProductInfo 产品信息
     * @return 产品信息集合
     */
    public List<ProProductInfo> selectProProductInfoList(ProProductInfo proProductInfo);

    /**
     * 查询产品信息列表
     *
     * @param productInfo 产品信息
     * @return 产品信息集合
     */
    public List<ProProductInfo> selectProductInfoByLike(ProProductInfo productInfo);
    /**
     * 查询产品信息列表
     *
     * @param proProductInfoDto 产品信息
     * @return 产品信息集合
     */
    public List<ProProductInfoDto> listProProductInfoDto(ProProductInfoDto proProductInfoDto);
    /**
     * 查询所有产品信息
     *
     * @return 产品信息集合
     */
    public List<ProProductInfo> selectProductInfos();

    /**
     * 新增产品信息
     *
     * @param proProductInfo 产品信息
     * @return 结果
     */
    public int insertProProductInfo(ProProductInfo proProductInfo);

    /**
     * 修改产品信息
     *
     * @param proProductInfo 产品信息
     * @return 结果
     */
    public int updateProProductInfo(ProProductInfo proProductInfo);

    /**
     * 删除产品信息
     *
     * @param productId 产品信息ID
     * @return 结果
     */
    public int deleteProProductInfoById(Long productId);

    /**
     * 批量删除产品信息
     *
     * @param productIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProProductInfoByIds(Long[] productIds);

    ProProductInfo selectProductByCode(String code);
}
