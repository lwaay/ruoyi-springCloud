package com.sinonc.agriculture.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.aliyuncs.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.domain.CommentReply;
import com.sinonc.agriculture.domain.GrowTech;
import com.sinonc.agriculture.domain.MemberDict;
import com.sinonc.agriculture.service.*;
import com.sinonc.agriculture.vo.GrowTechVo;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;
import java.util.TimerTask;

/**
 * 种植技术接口
 */
@Api(tags = "种植技术接口")
@RestController
@RequestMapping("/agriculture/api")
public class ApiGrowTechController extends BaseController {

    @Autowired
    private GrowTechService growTechService;

    @Autowired
    private GrowtechLikeService growtechLikeService;

    @Autowired
    private GrowtechCommentService growtechCommentService;

    @Autowired
    private MemberDictService memberDictService;

    @Autowired
    private CropDictService cropDictService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor executor;

    /**
     * 查询种植技术列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("查询种植技术列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query"),
            @ApiImplicitParam(name = "cropsDictvalue", value = "作物编码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sunCropId", value = "子类作物编码", required = true, paramType = "query")
    })
    @GetMapping(value = "/selectGrowTechList")
    public AjaxResult selectGrowTechList(Integer pageNum, Integer pageSize, String cropsDictvalue, String title, Long sunCropId, String techType) {

        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        Long parentCropId=null;

        GrowTech growTech = new GrowTech();
        if(sunCropId!=null){
            CropDict cropDict=cropDictService.selectCropDictById(sunCropId);
            if(cropDict!=null){
                 parentCropId=cropDict.getParentId();
                growTech.setParentCropId(parentCropId);
                growTech.setCropsDictvalue(null);
            }else {
                return AjaxResult.error("无cropId为:"+sunCropId+"的作物信息");
            }
        }else {
            growTech.setCropsDictvalue(cropsDictvalue);
        }

        growTech.setTitle(title);
        if(!StringUtils.isEmpty(techType)){
            growTech.setTechType(techType);
        }

        PageInfo pageInfo;
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("issue_time desc");
        if (Optional.ofNullable(tokenService.getLoginUser()).isPresent()) {
            pageInfo = new PageInfo<>(growTechService.selectGrowTechListForMap(growTech, tokenService.getLoginUser().getUserid(),parentCropId));
        } else {
            pageInfo = new PageInfo<>(growTechService.selectGrowTechListForMap(growTech, null,parentCropId));
        }


        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("total", pageInfo.getTotal());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }


    /**
     * 查询我评论过的种养殖技术
     *
     * @param pageNum
     * @param pageSize
     * @param memberId
     * @return
     */
    @ApiOperation("查询我评论过的种养殖技术")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "分页页码", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小", paramType = "query"),
            @ApiImplicitParam(name = "memberId", value = "会员ID", required = true, paramType = "query")
    })

    @GetMapping(value = "/selectOwnCommentGrowTechList")
    public AjaxResult selectOwnCommentGrowTechList(Integer pageNum, Integer pageSize, Long memberId) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);

        if (memberId == null || memberId == 0) {
            //传0则查询自己的
            memberId = tokenService.getLoginUser().getUserid();
        }

        PageInfo pageInfo = new PageInfo<>(growTechService.selectOwnCommentGrowTechList(memberId));

        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }


    /**
     * 根据种植技术ID查询详情
     *
     * @param growId
     * @return
     */
    @ApiOperation("根据种植技术ID查询详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "growId", value = "种植技术ID", required = true, paramType = "query")
    })

    @GetMapping(value = "/selectGrowTechById/{growId}")
    public AjaxResult selectGrowTechById(@PathVariable("growId") Long growId) {

        if (growId == null) {
            return AjaxResult.error("growId不能为空");
        }

        //查看文章时阅读数+1
        growTechService.summGrowTechReadCount(growId);
        GrowTechVo growTechVo = growTechService.selectGrowTechById(growId);
        return AjaxResult.success(growTechVo);
    }

    /**
     * 增加阅读次数
     *
     * @param growId
     * @return
     */
    @ApiOperation("增加阅读次数")
    @GetMapping(value = "/summGrowTechReadCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "growId", value = "种植技术ID", required = true, paramType = "query")
    })
    public AjaxResult summGrowTechReadCount(Long growId) {
        if (growId == null) {
            return AjaxResult.error("growId不能为空");
        }
        GrowTech growTech = growTechService.summGrowTechReadCount(growId);
        return AjaxResult.success(growTech);
    }


    /**
     * 养殖技术点赞
     *
     * @param growId
     * @return
     */
    @ApiOperation("养殖技术点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "growId", value = "种植技术ID", required = true, paramType = "query")
    })
    @GetMapping(value = "/addLike")
    public AjaxResult addLike(Long growId) {

        if (growId == null) {
            return AjaxResult.error("growId不能为空");
        }

        Long memberId = SecurityUtils.getUserId();
        int rows = growtechLikeService.addGrowtechLike(growId, memberId);

        if (rows == -1) {
            return AjaxResult.error("一个人对同一个种植技术只能点赞一次");
        } else {
            return AjaxResult.success(rows);
        }
    }


    /**
     * 取消点赞
     *
     * @param growId
     * @return
     */
    @ApiOperation("取消点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "growId", value = "种植技术ID", required = true, paramType = "query")
    })
    @GetMapping(value = "/deleteLike")
    public AjaxResult deleteLike(Long growId) {

        if (growId == null) {
            return AjaxResult.error("growId不能为空");
        }

        Long memberId = tokenService.getLoginUser().getUserid();
        int rows = growtechLikeService.deleteGrowtechLike(growId, memberId);

        if (rows == -1) {
            return AjaxResult.error("取消点赞失败。");
        } else {
            return AjaxResult.success(rows);
        }
    }

    /**
     * 删除评论
     *
     * @param commentId
     * @return
     */
    @ApiOperation("删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "commentId", value = "评论ID", required = true, paramType = "query")
    })
    @GetMapping(value = "/deleteCommontReply")
    public AjaxResult deleteCommontReply(Long commentId) {

        if (commentId == null) {
            return AjaxResult.error("growId不能为空");
        }

        int rows = growtechCommentService.deleteGrowtechCommentById(commentId);

        return AjaxResult.success(rows);
    }


    /**
     * 获取作物树
     *
     * @return
     */
    @ApiOperation("获取作物树")
    @GetMapping(value = "/getCropDictTree")
    public AjaxResult getCropDictTree() {
        JSONArray rs = growTechService.getCropDictTree();

        return AjaxResult.success(rs);
    }


    @ApiOperation("添加评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", type = "String", required = true, paramType = "head"),
            @ApiImplicitParam(name = "growtechIdP", value = "种植技术ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "replyContent", value = "评论内容", required = true, paramType = "query"),
            @ApiImplicitParam(name = "replyMemberIdP", value = "上级评论ID", required = true, paramType = "query")
    })
    @GetMapping(value = "/addCommontReplyTwo")
    public AjaxResult addCommontReplyTwo(CommentReply commentReply) {
        Long memberId = SecurityUtils.getUserId();
        commentReply.setMemberIdP(memberId);

        int rows = growtechCommentService.addCommentReply(commentReply);

        if (rows > 0) {
            addMemberDictCount(SecurityUtils.getUserId(), MemberDictConstants.TYPE_COMMENT_TOTAL);
        }
        return AjaxResult.success(rows);
    }


    /**
     * 字典值 +1 操作
     *
     * @param memberId 会员ID
     * @param dictType 字典类型
     */
    private void addMemberDictCount(Long memberId, String dictType) {
        executor.execute(new TimerTask() {
            @Override
            public void run() {

                MemberDict memberDict = memberDictService.getDictByMemberIdAndType(memberId, dictType);

                if (memberDict == null) {

                    memberDict = new MemberDict();
                    memberDict.setDictType(dictType);
                    memberDict.setMemberId(memberId);
                    memberDict.setDictValue("1");
                    memberDict.setCreateTime(new Date());

                    memberDictService.addMemberDict(memberDict);

                } else {

                    memberDict.setDictValue(String.valueOf(Integer.parseInt(memberDict.getDictValue()) + 1));
                    memberDict.setUpdateTime(new Date());
                    memberDictService.updateMemberDict(memberDict);
                }

            }
        });
    }


    /**
     * 增加分享次数
     *
     * @param growId
     * @return
     */
    @ApiOperation("增加分享次数")
    @GetMapping(value = "/summGrowTechShareCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "growId", value = "种植技术ID", required = true, paramType = "query")
    })
    public AjaxResult summGrowTechShareCount(Long growId) {
        if (growId == null) {
            return AjaxResult.error("growId不能为空");
        }
        GrowTech growTech = growTechService.summGrowTechShareCount(growId);
        return AjaxResult.success(growTech);
    }

    /**
     * 知识库数量
     * @return
     */
    @GetMapping("/article/count")
    public AjaxResult articlesCount(){
        return AjaxResult.success(growTechService.count());
    }

}
