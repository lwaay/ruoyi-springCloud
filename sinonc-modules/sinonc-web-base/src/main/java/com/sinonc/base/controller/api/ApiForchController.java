package com.sinonc.base.controller.api;


import com.sinonc.base.domain.ForchardInfo;
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.base.service.IForchardInfoService;
import com.sinonc.base.service.IFruiterInfoService;
import com.sinonc.common.core.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *
 */
@RestController
@RequestMapping("api/forch")
@Slf4j
public class ApiForchController {

    @Autowired
    private IForchardInfoService forchardInfoService;

    @Autowired
    private IFruiterInfoService fruiterInfoService;


    @GetMapping("getForchListByMemberId/{id}")
    public AjaxResult getForchListByMemberId(@PathVariable("id") Long id) {
        ForchardInfo forchardInfo = new ForchardInfo();
        //从会员获取经营主体ID，设置经营主体forchardInfo.setEntityId("1");
        List<ForchardInfo> forchardInfoList = forchardInfoService.selectForchardInfoList(forchardInfo);
        return AjaxResult.success("success", forchardInfoList);
    }


    @GetMapping("getFruiterInfoListByForchId/{id}")
    public AjaxResult getFruiterInfoListByForchId(@PathVariable("id") Long id) {
        FruiterInfo fruiterInfo = new FruiterInfo();
        fruiterInfo.setOrchId(id);
        List<FruiterInfo> fruiterInfoList = fruiterInfoService.selectFruiterInfoList(fruiterInfo);
        return AjaxResult.success("success", fruiterInfoList);
    }


}
