package com.sinonc.origins.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.origins.api.domain.ProOriginsInfo;
import com.sinonc.origins.dto.ProOriginsInfoDto;
import com.sinonc.origins.mapper.ProOriginsInfoMapper;
import com.sinonc.origins.service.IProOriginsInfoService;
import com.sinonc.origins.vo.BigScreenVo;
import com.sinonc.system.api.RemoteDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 溯源信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-10-23
 */
@Service
public class ProOriginsInfoServiceImpl implements IProOriginsInfoService {
    @Autowired
    private ProOriginsInfoMapper proOriginsInfoMapper;
    @Autowired
    private RemoteDictService remoteDictService;

    /**
     * 查询溯源信息
     *
     * @param originsId 溯源信息ID
     * @return 溯源信息
     */
    @Override
    public ProOriginsInfo selectProOriginsInfoById(Long originsId) {
        return proOriginsInfoMapper.selectProOriginsInfoById(originsId);
    }

    /**
     * 查询溯源信息
     *
     * @param originsId 溯源信息ID
     * @return 溯源信息
     */
    @Override
    public ProOriginsInfoDto selectProOriginsInfoDtoById(Long originsId) {
        return proOriginsInfoMapper.selectProOriginsInfoDtoById(originsId);
    }

    /**
     * 查询溯源信息列表
     *
     * @param proOriginsInfoDto 溯源信息
     * @return 溯源信息
     */
    @Override
    public List<ProOriginsInfoDto> selectProOriginsInfoDtoList(ProOriginsInfoDto proOriginsInfoDto) {
        return proOriginsInfoMapper.selectProOriginsInfoDtoList(proOriginsInfoDto);
    }

    /**
     * 查询溯源信息列表
     *
     * @param proOriginsInfo 溯源信息
     * @return 溯源信息
     */
    @Override
    public List<ProOriginsInfo> selectProOriginsInfoList(ProOriginsInfo proOriginsInfo) {
        return proOriginsInfoMapper.selectProOriginsInfoList(proOriginsInfo);
    }

    /**
     * 新增溯源信息
     *
     * @param proOriginsInfo 溯源信息
     * @return 结果
     */
    @Override
    public int insertProOriginsInfo(ProOriginsInfo proOriginsInfo) {
        proOriginsInfo.setCreateTime(DateUtils.getNowDate());
        return proOriginsInfoMapper.insertProOriginsInfo(proOriginsInfo);
    }

    /**
     * 修改溯源信息
     *
     * @param proOriginsInfo 溯源信息
     * @return 结果
     */
    @Override
    public int updateProOriginsInfo(ProOriginsInfo proOriginsInfo) {
        proOriginsInfo.setUpdateTime(DateUtils.getNowDate());
        return proOriginsInfoMapper.updateProOriginsInfo(proOriginsInfo);
    }

    /**
     * 批量删除溯源信息
     *
     * @param originsIds 需要删除的溯源信息ID
     * @return 结果
     */
    @Override
    public int deleteProOriginsInfoByIds(Long[] originsIds) {
        return proOriginsInfoMapper.deleteProOriginsInfoByIds(originsIds);
    }

    /**
     * 删除溯源信息信息
     *
     * @param originsId 溯源信息ID
     * @return 结果
     */
    @Override
    public int deleteProOriginsInfoById(Long originsId) {
        return proOriginsInfoMapper.deleteProOriginsInfoById(originsId);
    }

    /**
     * 根据产品id和溯源类型查询产品溯源信息
     *
     * @param productId
     * @param type
     * @return
     */
    @Override
    public List<Map<String, Object>> selectOriginsInfoByIdForTypeAndIsOpe(Long productId, int type) {
        List<Map<String, Object>> list = proOriginsInfoMapper.selectOriginsInfoByIdForTypeAndIsOpe(productId, type);
        if (list.size() > 0) {
            for (Map<String, Object> sc : list) {
                if (sc != null && sc.get("orImages") != null) {
                    String orImages = sc.get("orImages").toString();
                    if (StringUtils.isNotEmpty(orImages)) {
                        String[] scImagesList = sc.get("orImages").toString().split(",");
                        sc.put("imagesList", scImagesList);
                    } else {
                        sc.put("imagesList", null);
                    }

                }
                if (sc != null && sc.get("orVideo") != null) {
                    String[] videosList = sc.get("orVideo").toString().split(",");
                    sc.put("videoList", videosList);
                }

                if (sc.get("operaType") == null) {
                    sc.put("operaType", "");
                }

                if (sc.get("operaMode") == null) {
                    sc.put("operaMode", "");
                }
            }
        }
        return list;
    }

    /**
     * 根据产品id获取产品溯源记录次数
     */
    @Override
    public Long countOriginsByProductId(Long productId) {
        return proOriginsInfoMapper.countOriginsByProId(productId);
    }

    /**
     * 农事用工数据分析
     * @return
     */
    @Override
    public List<Map<String,Integer>> getProAnalysis(BigScreenVo bigScreenVo){
        return proOriginsInfoMapper.selectProAnalysis(bigScreenVo);
    }

}
