package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.SectionDict;
import com.sinonc.common.core.web.domain.Ztree;

import java.util.List;

/**
 * 板块字典Service接口
 *
 * @author ruoyi
 * @date 2020-03-06
 */
public interface SectionDictService
{
    /**
     * 查询板块字典
     *
     * @param sectionId 板块字典ID
     * @return 板块字典
     */
    public SectionDict selectSectionDictById(Long sectionId);

    /**
     * 查询板块字典列表
     *
     * @param sectionDict 板块字典
     * @return 板块字典集合
     */
    public List<SectionDict> selectSectionDictList(SectionDict sectionDict);

    /**
     * 新增板块字典
     *
     * @param sectionDict 板块字典
     * @return 结果
     */
    public int insertSectionDict(SectionDict sectionDict);

    /**
     * 修改板块字典
     *
     * @param sectionDict 板块字典
     * @return 结果
     */
    public int updateSectionDict(SectionDict sectionDict);

    /**
     * 批量删除板块字典
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSectionDictByIds(String ids);

    /**
     * 删除板块字典信息
     *
     * @param sectionId 板块字典ID
     * @return 结果
     */
    public int deleteSectionDictById(Long sectionId);

    /**
     * 查询板块字典树列表
     *
     * @return 所有板块字典信息
     */
    public List<Ztree> selectSectionDictTree();

    /**
     * 查询板块列表
     *
     * @param memberId
     * @return
     */
    List<SectionDict> selectAllSectionDictList(Long memberId);
}
