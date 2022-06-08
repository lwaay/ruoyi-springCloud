package com.sinonc.agriculture.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.ListQuestionAnswerConstants;
import com.sinonc.agriculture.domain.CommentReply;
import com.sinonc.agriculture.domain.GrowTech;
import com.sinonc.agriculture.domain.GrowtechComment;
import com.sinonc.agriculture.domain.OwnDynamic;
import com.sinonc.agriculture.mapper.CommentReplyMapper;
import com.sinonc.agriculture.mapper.GrowTechMapper;
import com.sinonc.agriculture.mapper.GrowtechCommentMapper;
import com.sinonc.agriculture.service.GrowtechCommentService;
import com.sinonc.agriculture.service.OwnDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 养殖技术评论Service业务层处理
 *
 * @author zhang.xl
 * @date 2020-03-07
 */
@Service
public class GrowtechCommentServiceImpl implements GrowtechCommentService {
    @Autowired
    private GrowtechCommentMapper growtechCommentMapper;

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Autowired
    private OwnDynamicService ownDynamicService;

    @Autowired
    private GrowTechMapper growTechMapper;

    /**
     * 查询养殖技术评论
     *
     * @param commentId 养殖技术评论ID
     * @return 养殖技术评论
     */
    @Override
    public GrowtechComment selectGrowtechCommentById(Long commentId) {
        return growtechCommentMapper.selectGrowtechCommentById(commentId);
    }

    /**
     * 查询养殖技术评论列表
     *
     * @param growtechComment 养殖技术评论
     * @return 养殖技术评论
     */
    @Override
    public List<GrowtechComment> selectGrowtechCommentList(GrowtechComment growtechComment)
    {
        return growtechCommentMapper.selectGrowtechCommentList(growtechComment);
    }

    /**
     * 新增养殖技术评论
     *
     * @param growtechComment 养殖技术评论
     * @return 结果
     */
    @Override
    public int insertGrowtechComment(GrowtechComment growtechComment)
    {
        growtechComment.setCreateTime(DateUtils.getNowDate());
        return growtechCommentMapper.insertGrowtechComment(growtechComment);
    }

    /**
     * 修改养殖技术评论
     *
     * @param growtechComment 养殖技术评论
     * @return 结果
     */
    @Override
    public int updateGrowtechComment(GrowtechComment growtechComment)
    {
        return growtechCommentMapper.updateGrowtechComment(growtechComment);
    }

    /**
     * 删除养殖技术评论对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGrowtechCommentByIds(String ids)
    {
        return growtechCommentMapper.deleteGrowtechCommentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除养殖技术评论信息
     *
     * @param commentId 养殖技术评论ID
     * @return 结果
     */
    @Override
    public int deleteGrowtechCommentById(Long commentId)
    {
        return growtechCommentMapper.deleteGrowtechCommentById(commentId);
    }

    @Override
    @Transactional
    public int addGrowtechComment(Long growtechIdP, Long memberId, Long replyId, String content) {
        GrowtechComment growtechComment = new GrowtechComment();

        growtechComment.setGrowtechIdP(growtechIdP);
        growtechComment.setMemberIdP(memberId);
        growtechComment.setReplyId(replyId);
        growtechComment.setContent(content);
        growtechComment.setCreateTime(DateUtils.getNowDate());

        int row = growtechCommentMapper.insertGrowtechComment(growtechComment);
        return row;
    }

    @Override
    @Transactional
    public int addCommentReply(CommentReply commentReply) {
        commentReply.setCreateTime(new Date());
        int row = commentReplyMapper.insertCommentReply(commentReply);


        Long growtechId = commentReply.getGrowtechIdP();

        GrowTech growTech = growTechMapper.selectGrowTechById(growtechId);

        Map<String, Object> content = new HashMap<>();
        content.put("growId", growTech.getGrowId());
        content.put("title", growTech.getTitle());
        content.put("introduction", growTech.getIntroduction());
        content.put("shapeType", growTech.getShapeType());
        content.put("userId", growTech.getUserId());
        content.put("userName", growTech.getUserName());
        content.put("mainImg", growTech.getMainImg());
        content.put("readCount", growTech.getReadCount());
        content.put("videoUrl", growTech.getVideoUrl());
        content.put("content", growTech.getContent());
        content.put("issueTime", growTech.getIssueTime());
        content.put("cropsDictvalue", growTech.getCropsDictvalue());
        content.put("columnDictvalue", growTech.getColumnDictvalue());
        content.put("createTime", growTech.getCreateTime());

        //插入动态表
        OwnDynamic ownDynamic = new OwnDynamic();
        ownDynamic.setMemberIdP(commentReply.getMemberIdP());
        ownDynamic.setTargetId(commentReply.getReplyId());
        ownDynamic.setType(ListQuestionAnswerConstants.OwnGrowTechMomment);

        String growtechCommentContent = JSONUtils.toJSONString(content);
        ownDynamic.setDynaContent(growtechCommentContent);

        row = row + ownDynamicService.addOwnDynamic(ownDynamic);
        return row;
    }

}
