package com.sinonc.origins.mapper;

import com.sinonc.origins.api.domain.ProOriginsInfo;
import com.sinonc.origins.dto.ProOriginsInfoDto;
import com.sinonc.origins.vo.BigScreenVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 溯源信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-23
 */
public interface ProOriginsInfoMapper {
    /**
     * 查询溯源信息
     *
     * @param originsId 溯源信息ID
     * @return 溯源信息
     */
    public ProOriginsInfo selectProOriginsInfoById(Long originsId);

    /**
     * 查询溯源信息(包括产品信息)
     * @param originsId 溯源信息ID
     * @return ProOriginsInfoDto
     */
    public ProOriginsInfoDto selectProOriginsInfoDtoById(Long originsId);

    /**
     * 查询溯源信息列表
     *
     * @param proOriginsInfoDto 溯源信息
     * @return 溯源信息集合
     */
    public List<ProOriginsInfoDto> selectProOriginsInfoDtoList(ProOriginsInfoDto proOriginsInfoDto);

    /**
     * 查询溯源信息列表
     *
     * @param proOriginsInfo 溯源信息
     * @return 溯源信息集合
     */
    public List<ProOriginsInfo> selectProOriginsInfoList(ProOriginsInfo proOriginsInfo);


    /**
     * 新增溯源信息
     *
     * @param proOriginsInfo 溯源信息
     * @return 结果
     */
    public int insertProOriginsInfo(ProOriginsInfo proOriginsInfo);

    /**
     * 修改溯源信息
     *
     * @param proOriginsInfo 溯源信息
     * @return 结果
     */
    public int updateProOriginsInfo(ProOriginsInfo proOriginsInfo);

    /**
     * 删除溯源信息
     *
     * @param originsId 溯源信息ID
     * @return 结果
     */
    public int deleteProOriginsInfoById(Long originsId);

    /**
     * 批量删除溯源信息
     *
     * @param originsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProOriginsInfoByIds(Long[] originsIds);

    /**
     * 根据产品id和溯源类型查询产品溯源信息
     * @param productId
     * @param type
     * @return
     */
    List<Map<String,Object>> selectOriginsInfoByIdForTypeAndIsOpe(@Param("productId") Long productId, @Param("type") int type);

    /**
     * 根据产品id获取产品溯源记录次数
     */
    Long countOriginsByProductId(Long productId);

    /**
     * 根据产品id获取产品溯源记录次数
     */
    Long countOriginsByProId(Long productId);

    /**
     * 农事用工数据分析
     * @param bigScreenVo
     * @return
     */
    List<Map<String,Integer>> selectProAnalysis(BigScreenVo bigScreenVo);
}
