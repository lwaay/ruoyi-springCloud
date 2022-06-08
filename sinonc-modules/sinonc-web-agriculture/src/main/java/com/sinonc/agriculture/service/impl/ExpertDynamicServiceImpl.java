package com.sinonc.agriculture.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.sinonc.agriculture.service.MemberInfoService;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.MemberNoticeConstants;
import com.sinonc.agriculture.constants.SysConstants;
import com.sinonc.agriculture.domain.*;
import com.sinonc.agriculture.mapper.*;
import com.sinonc.agriculture.service.ExpertDynamicService;
import com.sinonc.system.api.RemoteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 专家动态Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-31
 */
@Service
public class ExpertDynamicServiceImpl implements ExpertDynamicService {

    @Autowired
    private ExpertDynamicMapper expertDynamicMapper;

    @Autowired
    private ExpertDynamicLikeMapper expertDynamicLikeMapper;

    @Autowired
    private ExpertDynamicCommentMapper expertDynamicCommentMapper;

    @Autowired
    private ExpertInfoMapper expertInfoMapper;

    @Autowired
    private ConcernInfoMapper concernInfoMapper;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private MemberNoticeMapper memberNoticeMapper;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    /**
     * 查询专家动态
     *
     * @param dynamicId 专家动态ID
     * @return 专家动态
     */
    @Override
    public ExpertDynamic getExpertDynamicById(Long dynamicId) {
        return expertDynamicMapper.selectExpertDynamicById(dynamicId);
    }

    /**
     * 查询专家动态列表
     *
     * @param expertDynamic 专家动态
     * @return 专家动态
     */
    @Override
    public List<ExpertDynamic> getExpertDynamicList(ExpertDynamic expertDynamic) {
        return expertDynamicMapper.selectExpertDynamicList(expertDynamic);
    }

    /**
     * 新增专家动态
     *
     * @param expertDynamic 专家动态
     * @return 结果
     */
    @Override
    @Transactional
    public int addExpertDynamic(ExpertDynamic expertDynamic) {
        Date nowDate = DateUtils.getNowDate();

        expertDynamic.setSysName(SysConstants.SYS_NAME);
        expertDynamic.setCreateTime(nowDate);
        expertDynamic.setUpdateTime(nowDate);

        int i = expertDynamicMapper.insertExpertDynamic(expertDynamic);

        //添加用户通知
        executor.execute(new TimerTask() {
            @Override
            public void run() {

                ExpertInfo expertInfo = expertInfoMapper.selectExpertInfoByMemberId(expertDynamic.getMemberId());

                if (expertInfo != null) {

                    MemberInfo memberInfo = memberInfoService.getMemberInfoById(expertInfo.getMemberId());

                    List<ConcernInfo> concernInfos = concernInfoMapper.selectByTypeAndTargetId(ConcernInfoConstants.CONCERN_INFO_EXPERT, expertInfo.getExpertId());

                    if (concernInfos.size() == 0) {
                        return;
                    }

                    List<MemberNotice> memberNotices = new ArrayList<>(concernInfos.size());

                    concernInfos.forEach(o -> {

                        Map<String, Object> content = new HashMap<>();

                        content.put("headImg", memberInfo.getHeadImg());
                        content.put("nikeName", memberInfo.getNikeName());
                        content.put("role", memberInfo.getRole());
                        content.put("dynamicId", expertDynamic.getDynamicId());
                        content.put("createTime", nowDate);

                        MemberNotice memberNotice = new MemberNotice();
                        memberNotice.setMemberId(o.getMemberId());
                        memberNotice.setUpdateTime(nowDate);
                        memberNotice.setCreateTime(nowDate);
                        memberNotice.setType(MemberNoticeConstants.TYPE_MEMBER_DYNAMIC);
                        memberNotice.setNoticeContent(JSONUtils.toJSONString(content));

                        memberNotices.add(memberNotice);
                    });

                    memberNoticeMapper.batchInsert(memberNotices);

                }

            }
        });

        return i;
    }

    /**
     * 添加点赞消息
     *
     * @param memberId  会员ID
     * @param dynamicId 动态ID
     * @return
     */
    @Override
    @Transactional
    public int addLike(Long memberId, Long dynamicId) {

        ExpertDynamicLike expertDynamicLike = expertDynamicLikeMapper.selectByMemberIdAndDynamicId(memberId, dynamicId);

        if (expertDynamicLike != null) {
            throw new RuntimeException("你已经点过赞了");
        }

        //增加累计数
        expertDynamicMapper.addCount("like_count", dynamicId);

        expertDynamicLike = new ExpertDynamicLike();
        expertDynamicLike.setMemberId(memberId);
        expertDynamicLike.setDynamicId(dynamicId);
        expertDynamicLike.setCreateTime(new Date());

        return expertDynamicLikeMapper.insertExpertDynamicLike(expertDynamicLike);

    }

    /**
     * 添加动态评论
     *
     * @param comment 评论
     * @return 影响的行数
     */
    @Override
    @Transactional
    public int addComment(ExpertDynamicComment comment) {

        comment.setCreateTime(new Date());

        executor.execute(new TimerTask() {
            @Override
            public void run() {
                expertDynamicMapper.addCount("comment_count", comment.getDynamicId());
            }
        });

        return expertDynamicCommentMapper.insertExpertDynamicComment(comment);
    }

    /**
     * 修改专家动态
     *
     * @param expertDynamic 专家动态
     * @return 结果
     */
    @Override
    public int updateExpertDynamic(ExpertDynamic expertDynamic) {
        expertDynamic.setUpdateTime(DateUtils.getNowDate());
        return expertDynamicMapper.updateExpertDynamic(expertDynamic);
    }

    /**
     * 删除专家动态对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpertDynamicByIds(String ids) {
        return expertDynamicMapper.deleteExpertDynamicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专家动态信息
     *
     * @param dynamicId 专家动态ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteExpertDynamicById(Long dynamicId) {
        expertDynamicLikeMapper.deleteByDynamicId(dynamicId);
        return expertDynamicMapper.deleteExpertDynamicById(dynamicId);
    }
}
