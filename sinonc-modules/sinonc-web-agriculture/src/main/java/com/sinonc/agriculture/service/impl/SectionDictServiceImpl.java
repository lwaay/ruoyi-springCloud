package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.domain.ConcernInfo;
import com.sinonc.agriculture.domain.SectionDict;
import com.sinonc.agriculture.mapper.ConcernInfoMapper;
import com.sinonc.agriculture.mapper.SectionDictMapper;
import com.sinonc.agriculture.service.SectionDictService;
import com.sinonc.agriculture.vo.SectionDictVo;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.web.domain.Ztree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 板块字典Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-06
 */
@Service("sectionDictServiceImpl")
public class SectionDictServiceImpl implements SectionDictService {
    @Autowired
    private SectionDictMapper sectionDictMapper;

    @Autowired
    private ConcernInfoMapper concernInfoMapper;

    /**
     * 查询板块字典
     *
     * @param sectionId 板块字典ID
     * @return 板块字典
     */
    @Override
    public SectionDict selectSectionDictById(Long sectionId) {
        return sectionDictMapper.selectSectionDictById(sectionId);
    }

    /**
     * 查询板块字典列表
     *
     * @param sectionDict 板块字典
     * @return 板块字典
     */
    @Override
    public List<SectionDict> selectSectionDictList(SectionDict sectionDict)
    {
        return sectionDictMapper.selectSectionDictList(sectionDict);



    }

    /**
     * 新增板块字典
     *
     * @param sectionDict 板块字典
     * @return 结果
     */
    @Override
    public int insertSectionDict(SectionDict sectionDict)
    {
        sectionDict.setCreateTime(DateUtils.getNowDate());
        return sectionDictMapper.insertSectionDict(sectionDict);
    }

    /**
     * 修改板块字典
     *
     * @param sectionDict 板块字典
     * @return 结果
     */
    @Override
    public int updateSectionDict(SectionDict sectionDict)
    {
        sectionDict.setUpdateTime(DateUtils.getNowDate());
        return sectionDictMapper.updateSectionDict(sectionDict);
    }

    /**
     * 删除板块字典对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSectionDictByIds(String ids)
    {
        return sectionDictMapper.deleteSectionDictByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除板块字典信息
     *
     * @param sectionId 板块字典ID
     * @return 结果
     */
    @Override
    public int deleteSectionDictById(Long sectionId)
    {
        return sectionDictMapper.deleteSectionDictById(sectionId);
    }

    /**
     * 查询板块字典树列表
     *
     * @return 所有板块字典信息
     */
    @Override
    public List<Ztree> selectSectionDictTree()
    {
        List<SectionDict> sectionDictList = sectionDictMapper.selectSectionDictList(new SectionDict());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (SectionDict sectionDict : sectionDictList) {
            Ztree ztree = new Ztree();
            ztree.setId(sectionDict.getSectionId());
            ztree.setpId(sectionDict.getParentId());
            ztree.setName(sectionDict.getSectionName());
            ztree.setTitle(sectionDict.getSectionName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public List<SectionDict> selectAllSectionDictList(Long memberId) {
        SectionDict sectionDict = new SectionDict();
        List<SectionDict> sectionDictList = sectionDictMapper.selectSectionDictList(sectionDict);

        for (int i = 0; i < sectionDictList.size(); i++) {
            SectionDict tempSectionDict = sectionDictList.get(i);

            ConcernInfo concernInfo = new ConcernInfo();
            concernInfo.setTargetId(tempSectionDict.getSectionId());
            concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_SECTION);
            //关注数
            List<ConcernInfo> concernInfoNumList = concernInfoMapper.selectConcernInfoList(concernInfo);
            //是否被关注
            concernInfo.setMemberId(memberId);
            List<ConcernInfo> concernInfoCheckList = concernInfoMapper.selectConcernInfoList(concernInfo);
            //查询列表，列表为零则没关注
            if (concernInfoCheckList.size() > 0) {
                tempSectionDict.setChecked(true);
            }
            if (concernInfoNumList.size() > 0) {
                tempSectionDict.setConcernNum(Long.valueOf(concernInfoNumList.size()));
            } else {
                tempSectionDict.setConcernNum(Long.valueOf(0));
            }
        }

        return sectionDictList;
    }

    public List<SectionDictVo> getAllSectionVo() {
        return sectionDictMapper.selectAllSectionVo();
    }
}
