package com.sinonc.agriculture.service;

import com.sinonc.agriculture.domain.ExpertInfoModify;
import com.sinonc.agriculture.vo.ExpertInfoModifyVo;

import java.util.List;

/**
 * 专家信息暂存Service接口
 *
 * @author ruoyi
 * @date 2020-04-07
 */
public interface ExpertInfoModifyService {
    /**
     * 查询专家信息暂存
     *
     * @param expertmodId 专家信息暂存ID
     * @return 专家信息暂存
     */
    public ExpertInfoModify getExpertInfoModifyById(Long expertmodId);

    /**
     * 查询专家信息暂存列表
     *
     * @param expertInfoModify 专家信息暂存
     * @return 专家信息暂存集合
     */
    public List<ExpertInfoModify> getExpertInfoModifyList(ExpertInfoModify expertInfoModify);

    /**
     * 新增专家信息暂存
     *
     * @param expertInfoModify 专家信息暂存
     * @return 结果
     */
    public int addExpertInfoModify(ExpertInfoModify expertInfoModify);

    /**
     * 修改专家信息暂存
     *
     * @param expertInfoModify 专家信息暂存
     * @return 结果
     */
    public int updateExpertInfoModify(ExpertInfoModify expertInfoModify);

    /**
     * 批量删除专家信息暂存
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpertInfoModifyByIds(String ids);

    /**
     * 删除专家信息暂存信息
     *
     * @param expertmodId 专家信息暂存ID
     * @return 结果
     */
    public int deleteExpertInfoModifyById(Long expertmodId);


    /**
     * 提交专家暂存资料
     *
     * @param expertInfoModifyVo
     */
    public void modifyExpertInfoModify(ExpertInfoModifyVo expertInfoModifyVo) throws Exception;

    /**
     * 审核专家暂存信息
     *
     * @param expertInfoModify
     * @return
     */
    public int auditExpertInfoModify(ExpertInfoModify expertInfoModify);

    /**
     * 查询是否已经提交专家暂存信息
     *
     * @param ExpertId
     * @return
     */
    public boolean isUpdateExpertInfoModify(Long ExpertId);
}
