package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.domain.ConcernInfo;
import com.sinonc.agriculture.mapper.ConcernInfoMapper;
import com.sinonc.agriculture.service.CropDictService;
import com.sinonc.agriculture.vo.CropChildVo;
import com.sinonc.base.api.RemoteCorpDictService;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.web.domain.Ztree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 作物字典Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-06
 */
@Service("cropdict")
public class CropDictServiceImpl implements CropDictService
{

    @Autowired
    private ConcernInfoMapper concernInfoMapper;

    @Autowired
    private RemoteCorpDictService remoteCorpDictService;

    /**
     * 查询作物字典
     *
     * @param cropId 作物字典ID
     * @return 作物字典
     */
    @Override
    public CropDict selectCropDictById(Long cropId)
    {
        return (CropDict) remoteCorpDictService.getInfo(cropId).getData();
    }

    /**
     * 查询作物字典树列表
     *
     * @return 所有作物字典信息
     */
    @Override
    public List<Ztree> selectCropDictTree()
    {
        List<CropDict> cropDictList = (List<CropDict>) remoteCorpDictService.listWithoutSplit(new CropDict()).getRows();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (CropDict cropDict : cropDictList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(cropDict.getCropId());
            ztree.setpId(cropDict.getParentId());
            ztree.setName(cropDict.getCropName());
            ztree.setTitle(cropDict.getCropName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    /**
     * 根据作物id查询该作物信息和其子类信息
     * @param cropDictId
     * @return
     */
    @Override
    public List<CropChildVo> getCropChildList(Long cropDictId) {

        CropDict cropDict = new CropDict();
        cropDict.setParentId(cropDictId);
        List<CropDict> cropDicts = (List<CropDict>) remoteCorpDictService.listWithoutSplit(cropDict).getRows();

        List<CropChildVo> cropChildVos = new LinkedList<>();

        for (CropDict dict : cropDicts) {

            CropDict params = new CropDict();
            params.setParentId(dict.getCropId());

            List<CropDict> childList = (List<CropDict>) remoteCorpDictService.listWithoutSplit(params).getRows();

            CropChildVo cropChildVo = new CropChildVo();
            BeanUtils.copyProperties(dict, cropChildVo);
            cropChildVo.setList(childList);
            cropChildVo.setChildren(childList);

            cropChildVos.add(cropChildVo);
        }

        return cropChildVos;
    }

    @Override
    public List<CropDict> selectAllCropDictList(Long memberId) {
        CropDict cropDict=new CropDict();
        List<CropDict> cropDictList=(List<CropDict>) remoteCorpDictService.listWithoutSplit(cropDict).getRows();

        for (int i = 0; i <cropDictList.size() ; i++) {
            CropDict tempCropDict=cropDictList.get(i);

            ConcernInfo concernInfo=new ConcernInfo();
            concernInfo.setTargetId(tempCropDict.getCropId());
            concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_CROP);
            //关注数
            List<ConcernInfo> concernInfoNumList = concernInfoMapper.selectConcernInfoList(concernInfo);
            //是否被关注
            concernInfo.setMemberId(memberId);
            List<ConcernInfo> concernInfoCheckList = concernInfoMapper.selectConcernInfoList(concernInfo);
            //查询列表，列表为零则没关注
            if (concernInfoCheckList != null && concernInfoCheckList.size() > 0) {
                tempCropDict.setChecked(true);
            } else {
                tempCropDict.setChecked(false);
            }

            if (concernInfoNumList != null && concernInfoNumList.size() > 0) {
                tempCropDict.setConcernNum(Long.valueOf(concernInfoNumList.size()));
            } else {
                tempCropDict.setConcernNum(Long.valueOf(0));
            }
        }

        return cropDictList;
    }

}
