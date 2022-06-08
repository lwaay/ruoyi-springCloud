package com.sinonc.agriculture.service;

import com.alibaba.fastjson.JSONArray;
import com.sinonc.agriculture.domain.GrowTech;
import com.sinonc.agriculture.vo.GrowTechVo;

import java.util.List;
import java.util.Map;

/**
 * 种养殖技术Service接口
 *
 * @author zhang.xl
 * @date 2020-03-06
 */
public interface GrowTechService {
    /**
     * 查询种养殖技术
     *
     * @param growId 种养殖技术ID
     * @return 种养殖技术
     */
    public GrowTechVo selectGrowTechById(Long growId);

    /**
     * 查询种养殖技术列表
     *
     * @param growTech 种养殖技术
     * @return 种养殖技术集合
     */
    public List<GrowTech> selectGrowTechList(GrowTech growTech);

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
     * 批量删除种养殖技术
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGrowTechByIds(Long[] ids);

    /**
     * 删除种养殖技术信息
     *
     * @param growId 种养殖技术ID
     * @return 结果
     */
    public int deleteGrowTechById(Long growId);

    /**
     * 累加养种植技术阅读量
     * @param growId
     * @return
     */
    public GrowTech summGrowTechReadCount(Long growId);

    /**
     * 查询种植技术
     *
     * @param growTech
     * @return
     */
    public List<Map<String, Object>> selectGrowTechListForMap(GrowTech growTech, Long memberId,Long parentCropId);

    public JSONArray getCropDictTree();

    /**
     * 查询我评论过的种养殖技术
     *
     * @param memberId
     * @return
     */
    public List<Map<String, Object>> selectOwnCommentGrowTechList(Long memberId);

    List<GrowTech> selectGrowtechListByGrowtechVo(GrowTechVo growTechVo);

    /**
     * 增加分享次数
     * @param growId
     * @return
     */
    GrowTech summGrowTechShareCount(Long growId);

    Integer count();
}
