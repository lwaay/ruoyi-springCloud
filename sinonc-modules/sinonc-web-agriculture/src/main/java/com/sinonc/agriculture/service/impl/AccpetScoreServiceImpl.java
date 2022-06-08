package com.sinonc.agriculture.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.sinonc.agriculture.constants.AccpetScoreConstants;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.AccpetScoreMapper;
import com.sinonc.agriculture.domain.AccpetScore;
import com.sinonc.agriculture.service.AccpetScoreService;

/**
 * 专家评分Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-15
 */
@Service
public class AccpetScoreServiceImpl implements AccpetScoreService
{
    @Autowired
    private AccpetScoreMapper accpetScoreMapper;

    /**
     * 查询专家评分
     *
     * @param id 专家评分ID
     * @return 专家评分
     */
    @Override
    public AccpetScore getAccpetScoreById(Long id)
    {
        return accpetScoreMapper.selectAccpetScoreById(id);
    }

    /**
     * 查询专家评分列表
     *
     * @param accpetScore 专家评分
     * @return 专家评分
     */
    @Override
    public List<AccpetScore> getAccpetScoreList(AccpetScore accpetScore)
    {
        return accpetScoreMapper.selectAccpetScoreList(accpetScore);
    }

    /**
     * 新增专家评分
     *
     * @param accpetScore 专家评分
     * @return 结果
     */
    @Override
    public int addAccpetScore(AccpetScore accpetScore)
    {
        AccpetScore paraAccpetScore=new AccpetScore();

        paraAccpetScore.setCreateTime(DateUtils.getNowDate());
        paraAccpetScore.setMemberId(accpetScore.getMemberId());
        paraAccpetScore.setExpertId(accpetScore.getExpertId());
        List list=accpetScoreMapper.selectAccpetScoreList(paraAccpetScore);
        //没有查到历史评分就应该可以打分
        if (list!=null&&list.size()<=0){

            accpetScore.setCreateTime(DateUtils.getNowDate());

            BigDecimal bigDecimalScoreValue=new BigDecimal("0.00");
            BigDecimal mannerScoreBigDecimal=new BigDecimal(accpetScore.getMannerScore()).multiply(AccpetScoreConstants.MANNER_SCORE_ALLOCATION);
            BigDecimal speedScoreBigDecimal=new BigDecimal(accpetScore.getSpeedScore()).multiply(AccpetScoreConstants.SPEED_SCORE_ALLOCATION);
            BigDecimal abilityScoreBigDecimal=new BigDecimal(accpetScore.getAbilityScore()).multiply(AccpetScoreConstants.ABILITY_SCORE_ALLOCATION);

            bigDecimalScoreValue=bigDecimalScoreValue.add(mannerScoreBigDecimal).add(speedScoreBigDecimal).add(abilityScoreBigDecimal);

            accpetScore.setScoreValue(bigDecimalScoreValue.toString());

            return accpetScoreMapper.insertAccpetScore(accpetScore);
        }
        return -1;


    }

    /**
     * 修改专家评分
     *
     * @param accpetScore 专家评分
     * @return 结果
     */
    @Override
    public int updateAccpetScore(AccpetScore accpetScore)
    {
        return accpetScoreMapper.updateAccpetScore(accpetScore);
    }

    /**
     * 删除专家评分对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAccpetScoreByIds(String ids)
    {
        return accpetScoreMapper.deleteAccpetScoreByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专家评分信息
     *
     * @param id 专家评分ID
     * @return 结果
     */
    @Override
    public int deleteAccpetScoreById(Long id)
    {
        return accpetScoreMapper.deleteAccpetScoreById(id);
    }
}
