package com.sinonc.base.controller.api;

import com.sinonc.base.domain.CheckItem;
import com.sinonc.base.domain.Checkup;
import com.sinonc.base.service.CheckupService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sinonc
 * @date 2019-09-18
 */
@Api(tags = "检验单及检验项目")
@Controller
@RequestMapping("api/base/checkup")
public class ApiCheckupController extends BaseController {

	@Autowired
	private CheckupService checkupService;


	/**
	 * 根据基地编号查询该基地的检测单及检测项目
	 */
	@ApiOperation(value = "根据基地编号查询该基地的检测单及检测项目")
	@GetMapping("/queryCheckupAndItem")
	@ResponseBody
	public AjaxResult queryCheckupAndItem(Long farmId, String checkTitle) {
        try {
            Checkup checkup=new Checkup();
            checkup.setFarmId(farmId);

            Map rsMap=new HashMap<>();
            List<CheckItem>  checkItemList=checkupService.queryCheckupAndItemTwo(checkup,checkTitle,rsMap);

            rsMap.put("checkItemList",checkItemList);
            rsMap.put("soil",checkTitle);

            return AjaxResult.success(rsMap);
        } catch (Exception e) {
            Map rsMap=new HashMap<>();
            rsMap.put("checkItemList",new ArrayList<>());
            rsMap.put("soil",checkTitle);
            rsMap.put("sumUp","");
            return AjaxResult.success(rsMap);
        }

	}
}
