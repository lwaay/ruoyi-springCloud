package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.GrowTech;
import com.sinonc.agriculture.vo.GrowTechVo;

import java.util.List;
import java.util.Map;

/**
 * 种养殖技术Mapper接口
 *
 * @author zhang.xl
 * @date 2020-03-06
 */
public interface GrowTechMapper
{
    /**
     * 查询种养殖技术
     *
     * @param growId 种养殖技术ID
     * @return 种养殖技术
     */
    public GrowTech selectGrowTechById(Long growId);

    /**
     * 查询种养殖技术列表
     *
     * @param growTech 种养殖技术
     * @return 种养殖技术集合
     */
    public List<GrowTech> selectGrowTechList(GrowTech growTech);

    /**
     * 查询Map类型的列表
     * @param growTech
     * @return
     */
    public List<Map<String, Object>> selectGrowTechListForMap(GrowTech growTech);

    /**
     * 新增种养殖技术
     *
     * @param growTech 种养殖技术
     * @return 结果
     */
    public int insertGrowTech(GrowTech growTech);

    /**
     * 修改种养殖技术
     *
     * @param growTech 种养殖技术
     * @return 结果
     */
    public int updateGrowTech(GrowTech growTech);

    /**
     * 删除种养殖技术
     *
     * @param growId 种养殖技术ID
     * @return 结果
     */
    public int deleteGrowTechById(Long growId);

    /**
     * 批量删除种养殖技术
     *
     * @param growIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrowTechByIds(Long[] growIds);

    GrowTech selectGrowTechByIdForUpdate(Long growId);

    List<Map<String, Object>> selectOwnCommentGrowTechListForMap(Long memberId);

    List<GrowTech> selectGrowtechListByGrowtechVo(GrowTechVo growTechVo);

    Integer count();
}
