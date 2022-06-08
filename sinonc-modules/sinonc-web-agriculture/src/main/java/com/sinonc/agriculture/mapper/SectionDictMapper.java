package com.sinonc.agriculture.mapper;

import com.sinonc.agriculture.domain.SectionDict;
import com.sinonc.agriculture.vo.SectionDictVo;

import java.util.List;

/**
 * 板块字典Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-06
 */
public interface SectionDictMapper 
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
     * 删除板块字典
     * 
     * @param sectionId 板块字典ID
     * @return 结果
     */
    public int deleteSectionDictById(Long sectionId);

    /**
     * 批量删除板块字典
     *
     * @param sectionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSectionDictByIds(String[] sectionIds);

    /**
     * 查询所有板块Vo字典
     *
     * @return
     */
    List<SectionDictVo> selectAllSectionVo();

}
