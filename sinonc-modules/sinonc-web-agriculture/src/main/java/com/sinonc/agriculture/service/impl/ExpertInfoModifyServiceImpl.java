package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.constants.ExpertInfoConstants;
import com.sinonc.agriculture.domain.ExpertCertified;
import com.sinonc.agriculture.domain.ExpertCertifiedModify;
import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.domain.ExpertInfoModify;
import com.sinonc.agriculture.mapper.ExpertCertifiedMapper;
import com.sinonc.agriculture.mapper.ExpertCertifiedModifyMapper;
import com.sinonc.agriculture.mapper.ExpertInfoMapper;
import com.sinonc.agriculture.mapper.ExpertInfoModifyMapper;
import com.sinonc.agriculture.service.ExpertInfoModifyService;
import com.sinonc.agriculture.vo.ExpertInfoModifyVo;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 专家信息暂存Service业务层处理
 *
 * @author ruoyi
 * @date 2020-04-07
 */
@Service
public class ExpertInfoModifyServiceImpl implements ExpertInfoModifyService {
    @Autowired
    private ExpertInfoModifyMapper expertInfoModifyMapper;

    @Autowired
    private ExpertCertifiedModifyMapper expertCertifiedModifyMapper;

    @Autowired
    private ExpertInfoMapper expertInfoMapper;

    @Autowired
    private ExpertCertifiedMapper expertCertifiedMapper;

    /**
     * 查询专家信息暂存
     *
     * @param expertmodId 专家信息暂存ID
     * @return 专家信息暂存
     */
    @Override
    public ExpertInfoModify getExpertInfoModifyById(Long expertmodId) {
        return expertInfoModifyMapper.selectExpertInfoModifyById(expertmodId);
    }

    /**
     * 查询专家信息暂存列表
     *
     * @param expertInfoModify 专家信息暂存
     * @return 专家信息暂存
     */
    @Override
    public List<ExpertInfoModify> getExpertInfoModifyList(ExpertInfoModify expertInfoModify) {
        return expertInfoModifyMapper.selectExpertInfoModifyList(expertInfoModify);
    }

    /**
     * 新增专家信息暂存
     *
     * @param expertInfoModify 专家信息暂存
     * @return 结果
     */
    @Override
    public int addExpertInfoModify(ExpertInfoModify expertInfoModify) {
        expertInfoModify.setCreateTime(DateUtils.getNowDate());
        return expertInfoModifyMapper.insertExpertInfoModify(expertInfoModify);
    }

    /**
     * 修改专家信息暂存
     *
     * @param expertInfoModify 专家信息暂存
     * @return 结果
     */
    @Override
    public int updateExpertInfoModify(ExpertInfoModify expertInfoModify) {
        expertInfoModify.setUpdateTime(DateUtils.getNowDate());
        return expertInfoModifyMapper.updateExpertInfoModify(expertInfoModify);
    }

    /**
     * 删除专家信息暂存对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpertInfoModifyByIds(String ids) {
        return expertInfoModifyMapper.deleteExpertInfoModifyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专家信息暂存信息
     *
     * @param expertmodId 专家信息暂存ID
     * @return 结果
     */
    @Override
    public int deleteExpertInfoModifyById(Long expertmodId) {
        return expertInfoModifyMapper.deleteExpertInfoModifyById(expertmodId);
    }

    @Override
    @Transactional
    public void modifyExpertInfoModify(ExpertInfoModifyVo expertInfoModifyVo) throws Exception {
        boolean isUpdate = isUpdateExpertInfoModify(expertInfoModifyVo.getExpertIdP());

        if (isUpdate) {
            throw new Exception("你提交的修改资料正在审核。");
        }

        expertInfoModifyVo.setVerifyStatus(ExpertInfoConstants.VERIFY_STATUS_AUDITING);
        expertInfoModifyMapper.insertExpertInfoModify(expertInfoModifyVo);
        List<ExpertCertifiedModify> expertCertifiedModifyList = expertInfoModifyVo.getExpertCertifiedModifyList();

        if (expertCertifiedModifyList != null && expertCertifiedModifyList.size() > 0) {
            for (int i = 0; i < expertCertifiedModifyList.size(); i++) {
                ExpertCertifiedModify expertCertifiedModify = expertCertifiedModifyList.get(i);
                expertCertifiedModify.setExpertId(expertInfoModifyVo.getExpertmodId());
                expertCertifiedModify.setMemberId(expertInfoModifyVo.getMemberId());
                expertCertifiedModify.setCreateTime(DateUtils.getNowDate());
                expertCertifiedModifyMapper.insertExpertCertifiedModify(expertCertifiedModify);
            }
        }
    }

    public boolean isUpdateExpertInfoModify(Long ExpertId) {
        ExpertInfoModify expertInfoModify = new ExpertInfoModify();
        expertInfoModify.setExpertIdP(ExpertId);
        List expertInfoModifyList = expertInfoModifyMapper.selectExpertInfoModifyList(expertInfoModify);

        if (expertInfoModifyList != null && expertInfoModifyList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public int auditExpertInfoModify(ExpertInfoModify expertInfoModify) {

        ExpertInfoModify tempExpertInfoModify = expertInfoModifyMapper.selectExpertInfoModifyById(expertInfoModify.getExpertmodId());

        if (expertInfoModify.getVerifyStatus() != null && expertInfoModify.getVerifyStatus() == ExpertInfoConstants.VERIFY_STATUS_ADOPT) {
            //审核通过，将暂存信息覆盖到专家表
            ExpertInfo tempExpertInfo = expertInfoMapper.selectExpertInfoById(expertInfoModify.getExpertIdP());
            changeExpertInfoModifyToExpertInfo(tempExpertInfoModify, tempExpertInfo);
            expertInfoMapper.updateExpertInfo(tempExpertInfo);

            //更新专家资格证明资料
            ExpertCertifiedModify expertCertifiedModify = new ExpertCertifiedModify();
            expertCertifiedModify.setExpertId(tempExpertInfoModify.getExpertmodId());
            List<ExpertCertifiedModify> expertCertifiedModifyList = expertCertifiedModifyMapper.selectExpertCertifiedModifyList(expertCertifiedModify);

            List<ExpertCertified> expertCertifiedList = new ArrayList<ExpertCertified>();
            changeExpertCertifiedModifyListToExpertCertifiedList(expertCertifiedModifyList, expertCertifiedList, tempExpertInfo);
            expertCertifiedMapper.deleteExpertCertifiedByExpertInfoId(tempExpertInfo.getExpertId());

            for (int i = 0; i < expertCertifiedList.size(); i++) {
                ExpertCertified expertCertified = expertCertifiedList.get(i);
                expertCertifiedMapper.insertExpertCertified(expertCertified);
            }
        }
        //删除暂存专家信息及资格证明
        expertCertifiedModifyMapper.deleteExpertCertifiedModifyByExpertCertifiedId(tempExpertInfoModify.getExpertmodId());
        expertInfoModifyMapper.deleteExpertInfoModifyById(tempExpertInfoModify.getExpertmodId());
        return 1;
    }

    private void changeExpertInfoModifyToExpertInfo(ExpertInfoModify tempExpertInfoModify, ExpertInfo tempExpertInfo) {
        tempExpertInfo.setCropCode(tempExpertInfoModify.getCropCode());
        tempExpertInfo.setAreaCode(tempExpertInfoModify.getAreaCode());
        if(!tempExpertInfoModify.getPersonalPhoto().equals("-")){
            tempExpertInfo.setPersonalPhoto(tempExpertInfoModify.getPersonalPhoto());
        }

        tempExpertInfo.setPhone(tempExpertInfoModify.getPhone());
        tempExpertInfo.setRegionCode(tempExpertInfoModify.getRegionCode());

        if(!tempExpertInfoModify.getEmail().equals("-")){
            tempExpertInfo.setEmail(tempExpertInfoModify.getEmail());
        }

        tempExpertInfo.setIdenCard(tempExpertInfoModify.getIdenCard());
        tempExpertInfo.setPersonalProfile(tempExpertInfoModify.getPersonalProfile());
        tempExpertInfo.setRealName(tempExpertInfoModify.getRealName());
    }

    private void changeExpertCertifiedModifyListToExpertCertifiedList(List<ExpertCertifiedModify> expertCertifiedModifyList, List<ExpertCertified> expertCertifiedList, ExpertInfo tempExpertInfo) {
        if (expertCertifiedModifyList != null) {
            for (int i = 0; i < expertCertifiedModifyList.size(); i++) {
                ExpertCertifiedModify tempExpertCertifiedModify = expertCertifiedModifyList.get(i);

                ExpertCertified expertCertified = new ExpertCertified();

                expertCertified.setCertifiedId(tempExpertCertifiedModify.getCertifiedmodId());
                expertCertified.setCertifiedImg(tempExpertCertifiedModify.getCertifiedImg());
                expertCertified.setExpertId(tempExpertInfo.getExpertId());
                expertCertified.setMemberId(tempExpertInfo.getMemberId());

                expertCertifiedList.add(expertCertified);
            }
        }
    }
}
