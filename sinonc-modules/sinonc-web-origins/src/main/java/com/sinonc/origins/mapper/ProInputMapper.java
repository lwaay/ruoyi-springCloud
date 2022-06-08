package com.sinonc.origins.mapper;

import java.util.List;
import java.util.Map;

import com.sinonc.origins.domain.ProInput;
import com.sinonc.origins.vo.BigScreenVo;

/**
 * 农产品投入品信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-15
 */
public interface ProInputMapper {
    /**
     * 查询农产品投入品信息
     *
     * @param inputId 农产品投入品信息ID
     * @return 农产品投入品信息
     */
    public ProInput selectProInputById(Long inputId);

    /**
     * 查询农产品投入品信息列表
     *
     * @param proInput 农产品投入品信息
     * @return 农产品投入品信息集合
     */
    public List<ProInput> selectProInputList(ProInput proInput);

    /**
     * 新增农产品投入品信息
     *
     * @param proInput 农产品投入品信息
     * @return 结果
     */
    public int insertProInput(ProInput proInput);

    /**
     * 修改农产品投入品信息
     *
     * @param proInput 农产品投入品信息
     * @return 结果
     */
    public int updateProInput(ProInput proInput);

    /**
     * 删除农产品投入品信息
     *
     * @param inputId 农产品投入品信息ID
     * @return 结果
     */
    public int deleteProInputById(Long inputId);

    /**
     * 批量删除农产品投入品信息
     *
     * @param inputIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProInputByIds(Long[] inputIds);

    /**
     * 农事投入品占比分析
     * @param bigScreenVo
     * @return
     */
    public List<Map<String,Integer>> selectInputRate(BigScreenVo bigScreenVo);

}
