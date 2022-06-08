package com.sinonc.agriculture.service.impl;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.domain.*;
import com.sinonc.agriculture.dto.PolicyNewsDto;
import com.sinonc.agriculture.mapper.PolicyNewsMapper;
import com.sinonc.agriculture.mapper.PolicynewsCommentMapper;
import com.sinonc.agriculture.mapper.PolicynewsLikeMapper;
import com.sinonc.agriculture.service.PolicyNewsService;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.exchange.vo.PolicyNewsVo;
import com.sinonc.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 政策新闻Service业务层处理
 *
 * @author zhang.xl
 * @date 2020-03-05
 */
@Service
public class PolicyNewsServiceImpl implements PolicyNewsService
{
    @Autowired
    private PolicyNewsMapper policyNewsMapper;

    @Autowired
    private PolicynewsLikeMapper PolicynewsLikeMapper;

    @Autowired
    private PolicynewsCommentMapper policynewsCommentMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询政策新闻
     *
     * @param newsId 政策新闻ID
     * @return 政策新闻
     */
    @Override
    public PolicyNewsDto selectPolicyNewsDtoById(Long newsId)
    {
        PolicyNewsDto policyNewsDto= policyNewsMapper.selectPolicyNewsDtoById(newsId);

        PolicynewsLike policynewsLike=new PolicynewsLike();
        policynewsLike.setNewsIdP(newsId);
        List<PolicynewsLike> likeList=PolicynewsLikeMapper.selectPolicynewsLikeList(policynewsLike);

        if(likeList!=null){
            policyNewsDto.setLikes(likeList);
            policyNewsDto.setLikeSize(likeList.size());
        }else {
            policyNewsDto.setLikes(null);
            policyNewsDto.setLikeSize(0);
        }

        return policyNewsDto;
    }

    /**
     * 查询政策新闻列表
     *
     * @param policyNews 政策新闻
     * @return 政策新闻
     */
    @Override
    public List<PolicyNews> selectPolicyNewsList(PolicyNews policyNews) {
        List<PolicyNews> policyNewsList = policyNewsMapper.selectPolicyNewsList(policyNews);
        if (policyNewsList != null && policyNewsList.size() > 0) {
            for (int i = 0; i < policyNewsList.size(); i++) {
                PolicyNews tempPolicyNews = policyNewsList.get(i);
                List tempList = new ArrayList();
                tempPolicyNews.setCreateTime(tempPolicyNews.getIssueTime());
                if(tempPolicyNews.getMainImg()==null){
                    tempPolicyNews.setMainImg(MemberDictConstants.SHARE_MAIN_IMAGE);
                }

                tempPolicyNews.setPictures(tempList);
            }
        }

        return policyNewsList;
    }

    /**
     * 新增政策新闻
     *
     * @param policyNews 政策新闻
     * @return 结果
     */
    @Override
    public int insertPolicyNews(PolicyNews policyNews) {
        LoginUser loginUser = tokenService.getLoginUser();

        if (loginUser != null) {
            policyNews.setUserId(loginUser.getUserid());
            policyNews.setUserName(loginUser.getUsername());
            policyNews.setCreateBy(String.valueOf(loginUser.getUserid()));
        }
        policyNews.setCreateTime(DateUtils.getNowDate());

        return policyNewsMapper.insertPolicyNews(policyNews);
    }

    /**
     * 修改政策新闻
     *
     * @param policyNews 政策新闻
     * @return 结果
     */
    @Override
    public int updatePolicyNews(PolicyNews policyNews)
    {

        policyNews.setUpdateTime(DateUtils.getNowDate());

        return policyNewsMapper.updatePolicyNews(policyNews);
    }



    /**
     * 删除政策新闻对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePolicyNewsByIds(Long[] ids)
    {
        return policyNewsMapper.deletePolicyNewsByIds(ids);
    }

    /**
     * 删除政策新闻信息
     *
     * @param newsId 政策新闻ID
     * @return 结果
     */
    @Override
    public int deletePolicyNewsById(Long newsId)
    {
        return policyNewsMapper.deletePolicyNewsById(newsId);
    }

    @Override
    @Transactional
    public PolicyNews summPolicyNewsReadCount(Long newsId) {
        PolicyNews policyNews=policyNewsMapper.selectPolicyNewsByIdForUpdate(newsId);

        if(policyNews.getReadCount()!=null)
        {
            long readCount = (long) policyNews.getReadCount();
            readCount = readCount + 1;
            policyNews.setReadCount(readCount);
        } else {
            long readCount = Long.valueOf(1);
            policyNews.setReadCount(readCount);
        }
        policyNewsMapper.updatePolicyNews(policyNews);
        return policyNews;
    }

    @Override
    public List<PolicyNews> selectPolicyNewsListByPolicyNewsVo(PolicyNewsVo policyNewsVo) {
        List<PolicyNews> policyNewsList = policyNewsMapper.selectPolicyNewsListByPolicyNewsVo(policyNewsVo);
        for (int i = 0; i < policyNewsList.size(); i++) {
            PolicyNews tempPolicyNews = policyNewsList.get(i);
            List tempList = new ArrayList();
            tempList.add(tempPolicyNews.getMainImg());
            tempPolicyNews.setPictures(tempList);
        }

        return policyNewsList;
    }

    @Override
    @Transactional
    public int addPolicyNewsLike(Long newsId, Long memberIdP) {

        PolicynewsLike policynewsLike=new PolicynewsLike();
        policynewsLike.setNewsIdP(newsId);
        policynewsLike.setMemberIdP(memberIdP);
        List list=PolicynewsLikeMapper.selectPolicynewsLikeList(policynewsLike);

        if(list!=null&&list.size()>0){
            return -1;//新增失败，一个人对同一个政策新闻只能点赞一次
        }else {
            return PolicynewsLikeMapper.insertPolicynewsLike(policynewsLike);
        }

    }

    @Override
    @Transactional
    public int addPolicynewsComment(PolicynewsComment policynewsComment) {
        policynewsComment.setCreateTime(new Date());
        int row = policynewsCommentMapper.insertPolicynewsComment(policynewsComment);

        return row;
    }

}
