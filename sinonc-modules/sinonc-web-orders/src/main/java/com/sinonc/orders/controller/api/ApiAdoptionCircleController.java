package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.sql.SqlUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.PageDomain;
import com.sinonc.common.core.web.page.TableSupport;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.orders.domain.AdoptionCircle;
import com.sinonc.orders.domain.AdoptionCircleLike;
import com.sinonc.orders.domain.AdoptionCircleReply;
import com.sinonc.orders.service.AdoptionCircleService;
import com.sinonc.orders.service.CommentReplyService;
import com.sinonc.orders.service.IAdoptionCircleLikeService;
import com.sinonc.orders.service.IAdoptionCircleReplyService;
import com.sinonc.system.api.domain.WxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 朋友圈Api接口
 */
@Api(tags = "橙友圈")
@RestController
@RequestMapping("api/adoptioncircle")
public class ApiAdoptionCircleController extends BaseController {

    @Autowired
    private AdoptionCircleService adoptionCircleService;
    @Autowired
    private IAdoptionCircleReplyService circleReplyService;
    @Autowired
    private IAdoptionCircleLikeService circleLikeService;

    @Autowired
    private TokenService tokenService;

//    /**
//     * @return
//     * @deprecated 此方法已经过期
//     */
//    @ApiOperation(value = "查询朋友圈")
//    @RequestMapping(value = "selectadoptioncircle")
//    public AjaxResult selectAdoptionCircle() {
//        AdoptionCircle adoptionCircle = new AdoptionCircle();
//        List<Map<String, Object>> list = adoptionCircleService.selectAdoptionCircleListForApi();
//        for (int i = 0; i < list.size(); i++) {
//            list.get(i).put("images", list.get(i).get("images").toString().split(","));
//            Long adoptionId = Long.parseLong(list.get(i).get("adoption_id").toString());
//            List<Map<String, Object>> map = commentReplyService.selectCommontReplyByTypeAndGoodIdP(adoptionId);
//            list.get(i).put("comments", map);
//        }
//        return AjaxResult.success(list);
//    }


    @ApiOperation(value = "查询朋友圈two")
    @RequestMapping(value = "selectAdoptionCircleListForApiTwo")
    public AjaxResult selectAdoptionCircleListForApiTwo() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = "create_time desc";
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
        PageInfo pageInfo = new PageInfo<>(adoptionCircleService.selectAdoptionCircleListForApiTwo());

        AjaxResult success = AjaxResult.success();
        success.put("data", pageInfo.getList());
        success.put("totalPage", pageInfo.getPages());
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("hasPre", pageInfo.isHasPreviousPage());

        return success;
    }

    /**
     * 橙友圈发布接口
     *
     * @return 结果
     */
    @ApiOperation(value = "添加芒友圈")
    @PostMapping("addAdoptionCircle")
    public AjaxResult insertAdoptionCircle(@RequestBody AdoptionCircle adoptionCircle) {
        String content = adoptionCircle.getContent();
        if (StringUtils.isEmpty(content)) {
            return AjaxResult.error("content 参数不能为空");
        }
        WxUserConsume wxUser = tokenService.getLoginUser().getWxUserConsume();
        if(wxUser == null){
            return AjaxResult.error("请先登录");
        }
        adoptionCircle.setMemberId(wxUser.getId());
        adoptionCircle.setCreateTime(DateUtils.getNowDate());
        try {
            return AjaxResult.success(adoptionCircleService.addAdoptionCircle(adoptionCircle));
        } catch (Exception e) {
            return AjaxResult.error("异常");
        }
    }

    @ApiOperation(value = "删除芒友圈动态")
    @GetMapping("delete/{cId}")
    public AjaxResult delete(@PathVariable("cId") long cId) {
        AdoptionCircle adoptionCircle = new AdoptionCircle();
        WxUserConsume wxUser = tokenService.getLoginUser().getWxUserConsume();
        if(wxUser == null){
            return AjaxResult.error("请先登录");
        }
        adoptionCircle.setAdoptionId(cId);
        adoptionCircle.setMemberId(wxUser.getId());
        // 标记删除
        adoptionCircle.setIsDeleted("1");
        adoptionCircleService.updateAdoptionCircleWithMemberId(adoptionCircle);
        return AjaxResult.success();
    }

    @ApiOperation(value = "芒友圈动态点赞")
    @PostMapping("like")
    public AjaxResult like(Long adoptionId){
        AdoptionCircleLike like = new AdoptionCircleLike();
        WxUserConsume wxUser = tokenService.getLoginUser().getWxUserConsume();
        if(wxUser == null){
            return AjaxResult.error("请先登录");
        }
        like.setAdoptionId(adoptionId);
        like.setMemberId(wxUser.getId());
        return toAjax(circleLikeService.insertAdoptionCircleLike(like));
    }

    @ApiOperation(value = "芒友圈动态取消点赞")
    @PostMapping("dislike")
    public AjaxResult dislike(Long adoptionId){
        AdoptionCircleLike like = new AdoptionCircleLike();
        WxUserConsume wxUser = tokenService.getLoginUser().getWxUserConsume();
        if(wxUser == null){
            return AjaxResult.error("请先登录");
        }
        like.setAdoptionId(adoptionId);
        like.setMemberId(wxUser.getId());
        return toAjax(circleLikeService.removeAdoptionCircleLike(like));
    }

    @ApiOperation(value = "芒友圈动态评论")
    @PutMapping("comment")
    public AjaxResult comment(@RequestBody @Validated AdoptionCircleReply reply){
        WxUserConsume wxUser = tokenService.getLoginUser().getWxUserConsume();
        if(wxUser == null){
            return AjaxResult.error("请先登录");
        }
        reply.setMemberId(wxUser.getId());
        return toAjax(circleReplyService.insertAdoptionCircleReply(reply));
    }

    @ApiOperation(value = "芒友圈动态评论删除")
    @DeleteMapping("comment")
    public AjaxResult delComment(Long replyId){
        WxUserConsume wxUser = tokenService.getLoginUser().getWxUserConsume();
        if(wxUser == null){
            return AjaxResult.error("请先登录");
        }
        return toAjax(circleReplyService.deleteAdoptionCircleReplyById(replyId,wxUser.getId()));
    }
}
