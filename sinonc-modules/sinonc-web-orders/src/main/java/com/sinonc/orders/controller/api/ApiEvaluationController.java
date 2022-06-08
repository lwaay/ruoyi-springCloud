package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.consume.api.RemoteWxUserConsumeService;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.orders.domain.Evaluation;
import com.sinonc.orders.service.EvaluationService;
import com.sinonc.orders.vo.EvaluationVo;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import com.vdurmont.emoji.EmojiParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "订单评论API接口")
@RestController
@RequestMapping("api/evaluation")
public class ApiEvaluationController extends BaseController {

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private RemoteWxUserConsumeService consumeService;

    @ApiOperation("根据商品ID查询商品评价")
    @GetMapping("list")
    public AjaxResult getEvaluationList(Long goodsId, String star) {

        if (goodsId == null) {
            return AjaxResult.error("goodsId不能为空");
        }

        startPage();
        List<Evaluation> evaluations = evaluationService.getListBetweenStar(goodsId, star);
        PageInfo<Evaluation> pageInfo = new PageInfo<>(evaluations);

        List<Evaluation> list = pageInfo.getList();

        Set<Long> memberIds = new HashSet<>();

        for (Evaluation ev : list) {
            //查询用户信息
            ev.setWxUserConsume(consumeService.getUserById(ev.getMemberId()).getData());
            ev.setEvaluationContent(EmojiParser.parseToUnicode(ev.getEvaluationContent()));
            memberIds.add(ev.getMemberId());
        }


        Map<Long, String> memberNames = new HashMap<>();

        if (memberIds.size() > 0) {

            for (Long memberId : memberIds) {
                WxUserConsume consume = consumeService.getUserById(memberId).getData();
                String name = consume.getName();
                String first = name.substring(0, 1);
                String last = name.substring(name.length() - 1);
                memberNames.put(consume.getId(), first + "***" + last);
            }
        }

        AjaxResult success = AjaxResult.success(list);
        success.put("hasNext", pageInfo.isHasNextPage());
        success.put("names", memberNames);

        return success;
    }


    @ApiOperation("查询好中差评数量")
    @GetMapping("starTypeCount")
    public AjaxResult getStarTypeCount(Long goodsId) {
        return AjaxResult.success(evaluationService.getStarTypeCount(goodsId));
    }


    @ApiOperation("会员添加商品评论")
    @PostMapping("addEvaluation")
    public AjaxResult addEvaluation(@RequestBody EvaluationVo evaluationVo) {
        Long orderId = evaluationVo.getOrderId();
        String content = evaluationVo.getContent();
        String images = evaluationVo.getImages();
        Integer goodsStar = evaluationVo.getGoodsStar();
        Integer expressStar = evaluationVo.getExpressStar();
        Integer serviceStar = evaluationVo.getServiceStar();

        if (orderId == null || StringUtils.isEmpty(content) || goodsStar == null || expressStar == null || serviceStar == null) {
            return AjaxResult.error("参数错误");
        }

        Long memberId = SecurityUtils.getUserId();

        Evaluation evaluation = new Evaluation();
        evaluation.setMemberId(memberId);
        evaluation.setGoodsStar(goodsStar);
        evaluation.setExpressStar(expressStar);
        evaluation.setServiceStar(serviceStar);
        evaluation.setImages(images);
        evaluation.setEvaluationContent(content);
        evaluation.setOrderId(orderId);

        return toAjax(evaluationService.addEvaluation(evaluation));
    }

}
