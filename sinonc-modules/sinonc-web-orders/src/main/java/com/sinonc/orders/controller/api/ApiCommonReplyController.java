package com.sinonc.orders.controller.api;

import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.orders.domain.AdoptLike;
import com.sinonc.orders.domain.CommentReply;
import com.sinonc.orders.service.AdoptLikeService;
import com.sinonc.orders.service.CommentReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Api评论回复模块
 */
@Api
@RestController
@RequestMapping("api/commonreply")
public class ApiCommonReplyController extends BaseController {

    @Autowired
    private CommentReplyService commentReplyService;
    @Autowired
    AdoptLikeService adoptLikeService;


    @ApiOperation(value = "新增评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsIdP", value = "商品或动态Id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "replyId", value = "回复的评论回复id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "addcommonreply" )
    public AjaxResult addCommonReply(Long goodsIdP, Long userId, Long replyId, String content){
        if(content.isEmpty()){
            AjaxResult.error("评论/回复内容不能为空!");
        }
        CommentReply commentReply = new CommentReply();
        commentReply.setContent(content);
        commentReply.setGoodsIdP(goodsIdP);
        commentReply.setUserId(userId);
        commentReply.setCreateTime(new Date());
        commentReply.setReplyId(replyId);
        int rows = commentReplyService.addCommentReply(commentReply);
        return AjaxResult.success(rows);
    }



    @ApiOperation(value = "删除评论")
    @ApiImplicitParam(name = "commentreplyId", value = "评论Id", dataType = "Long", required = true, paramType = "query")
    @RequestMapping(value = "deletecommonreply" )
    public AjaxResult deleteCommentReply(Long commentreplyId){
       int rows =  commentReplyService.deleteCommentReplyByIds(commentreplyId.toString());
        return AjaxResult.success(rows);
    }


    @ApiOperation(value = "新增点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adoptionIdP", value = "认养圈Id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "addLike" )
    public AjaxResult addLike(Long adoptionIdP,Long userId){

        AdoptLike adoptLike=new AdoptLike();

        adoptLike.setAdoptionIdP(adoptionIdP);
        adoptLike.setUserIdP(userId);

        int rows = adoptLikeService.addAdoptLike(adoptLike);

        if(rows==-1){
            return AjaxResult.error("一个人对一个认养圈只能点赞一次");
        }else
        {
            return AjaxResult.success(rows);
        }

    }




    @ApiOperation(value = "取消点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adoptionIdP", value = "认养圈Id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = "deleteLike" )
    public AjaxResult deleteLike(Long adoptionIdP,Long userId){

        AdoptLike adoptLike=new AdoptLike();
        adoptLike.setAdoptionIdP(adoptionIdP);
        adoptLike.setUserIdP(userId);

        int rows =  adoptLikeService.deleteAdoptLike(adoptLike);
        return AjaxResult.success(rows);
    }

}
