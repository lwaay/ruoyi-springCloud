package com.sinonc.base.controller.api;

import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.service.IAreaCodeService;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/2/23 14:41
 */
@Api(tags = "地区代码")
@RestController
@RequestMapping("api/base/area/")
public class ApiAreaCodeController {

    @Autowired
    private IAreaCodeService areaCodeService;

    @PostMapping("/list")
    public R<List<AreaCode>> list(@RequestBody AreaCode areaCode) {
        List<AreaCode> list = areaCodeService.selectAreaCodeList(areaCode);
        return R.ok(list);
    }

    @PostMapping("/getaddressname")
    public R<String> changeAddressName(String addressCode){
        return R.ok(areaCodeService.changeAddressName(addressCode));
    }

    /**
     * 获取根据父区域代码获取行政区域数据
     */
    @GetMapping("/queryByParent")
    public AjaxResult queryByParent(Long code){
        List<AreaCode> list = areaCodeService.listAreaCodeByParentCode(code);
        return CollectionUtils.isEmpty(list)?AjaxResult.error():AjaxResult.success(list);
    }

    /**
     * 获取指定等级的行政区划
     */
    @GetMapping("/parentBaseArea")
    public  AjaxResult parentBaseArea(Integer level,String code){
        List<AreaCode> list = areaCodeService.parentBaseArea(level,code);
        return AjaxResult.success(list);
    }

}
