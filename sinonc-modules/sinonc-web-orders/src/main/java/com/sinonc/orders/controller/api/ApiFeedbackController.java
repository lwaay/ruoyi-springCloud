package com.sinonc.orders.controller.api;

import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.AppFeedback;
import com.sinonc.orders.service.impl.AppFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈Controller
 */
@RestController
public class ApiFeedbackController {

    @Autowired
    private AppFeedbackService appFeedbackService;

    /**
     * 新增意见反馈
     * @return
     */
    @RequestMapping(value = "api/feedbackId/add")
    public AjaxResult addSuggest(String suggestType, String suggestCotent, String imageUrl){
        if(StringUtils.isEmpty(suggestType)){
            return AjaxResult.error("参数不能为空");
        }
        if(StringUtils.isEmpty(suggestCotent)){
            return AjaxResult.error("参数不能为空");
        }
        if(StringUtils.isEmpty(imageUrl)){
            return AjaxResult.error("参数不能为空");
        }
        //获取用户memberId
        Long memberId = SecurityUtils.getUserId();
        AppFeedback feedback = new AppFeedback();
        feedback.setMemberId(memberId);
        feedback.setCreateTime(DateUtils.getNowDate());
        feedback.setSuggestCotent(suggestCotent);
        feedback.setSuggestType(suggestType);
        feedback.setImageUrl(imageUrl);
        feedback.setSuggestStatus("1"); //未处理
        int row = appFeedbackService.addSuggest(feedback);
        return AjaxResult.success();
    }


    /**
     * 查询意见反馈列表
     * @return
     */
    @RequestMapping(value = "api/feedbackId/list")
    public AjaxResult selectSuggestList(){
        Long memberId = SecurityUtils.getUserId();
        List<Map<String,Object>> result = appFeedbackService.selectSuggestList(memberId);
        if(result.size() > 0){
            for (Map<String,Object> map:result) {
                if(map.get("suggestType") != null){
                    String type = map.get("suggestType").toString(); //类型
                    if("1".equals(type)){
                        map.put("suggestType","性能体验");
                    }else if("2".equals(type)){
                        map.put("suggestType","功能异常");
                    }else if("3".equals(type)){
                        map.put("suggestType","产品建议");
                    }else if("4".equals(type)){
                        map.put("suggestType","其他");
                    }
                }
                if(map.get("suggestStatus") != null){
                    String status = map.get("suggestStatus").toString(); //状态
                    if("1".equals(status)){
                        map.put("suggestStatus","未处理");
                    }else if("2".equals(status)){
                        map.put("suggestStatus","已处理");
                    }
                }
            }
        }
        return AjaxResult.success(result);
    }

    /**
     * 根据id查询意见反馈
     * @return
     */
    @RequestMapping(value = "api/feedbackId/selectsuggestbyid")
    public AjaxResult selectSuggestById(Long feedbackId){
        if(feedbackId == null){
            AjaxResult.error("参数不能为空！");
        }
        Map<String,Object> map = appFeedbackService.selectSuggestById(feedbackId);
        if(map != null){
            if(map.get("suggestType") != null){
                String type = map.get("suggestType").toString(); //类型
                if("1".equals(type)){
                    map.put("suggestType","性能体验");
                }else if("2".equals(type)){
                    map.put("suggestType","功能异常");
                }else if("3".equals(type)){
                    map.put("suggestType","产品建议");
                }else if("4".equals(type)){
                    map.put("suggestType","其他");
                }
            }
            if(map.get("suggestStatus") != null){
                String status = map.get("suggestStatus").toString(); //状态
                if("1".equals(status)){
                    map.put("suggestStatus","未处理");
                }else if("2".equals(status)){
                    map.put("suggestStatus","已处理");
                }
            }
            if(map.get("imageUrl") != null){
                String [] images = map.get("imageUrl").toString().split(",");
                map.put("imageArr",images);
            }
        }
        return AjaxResult.success(map);
    }




}
