package com.sinonc.agriculture.controller.api;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.domain.ConcernInfo;
import com.sinonc.agriculture.service.ConcernInfoService;
import com.sinonc.agriculture.service.CropDictService;
import com.sinonc.agriculture.vo.CropChildVo;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api
@RestController
@RequestMapping("api/agriculture/cropDict")
public class ApiCropDictController extends BaseController {

    @Autowired
    private CropDictService cropDictService;

    @Autowired
    private ConcernInfoService concernInfoService;

    @Autowired
    private TokenService tokenService;



//    @ApiOperation("根据作物id查询该作物信息和其子类信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(value = "id", name = "id", paramType = "query"),
//            @ApiImplicitParam(value = "pageNum", name = "pageNum", paramType = "query"),
//            @ApiImplicitParam(value = "pageSize", name = "pageSize", paramType = "query")
//    })
//    @GetMapping("getCrops")
//    public AjaxResult getCropChildList(@RequestParam(defaultValue = "0") Long id) {
//
//        startPage();
//        List<CropChildVo> cropChildList = cropDictService.getCropChildList(id);
//
//        PageInfo<CropChildVo> pageInfo = new PageInfo<>(cropChildList);
//        AjaxResult success = AjaxResult.success(pageInfo);
//
//        success.put("isHasNextPage", pageInfo.isHasNextPage());
//        return success;
//    }

    /**
     * 首次进入，没有关注记录才能使用
     * @param cropIds
     * @return
     */
    @ApiOperation("批量添加关注作物")
    @PostMapping("batchConcern/{cropIds}")
    @ApiImplicitParam(name = "token", value = "token", paramType = "head", required = true)
    public AjaxResult batchConcern(@PathVariable String cropIds) {

        String[] ids = cropIds.split(",");

        Long memberId = tokenService.getLoginUser().getUserid();

        List<ConcernInfo> concernInfos = concernInfoService.getByMemberIdAndType(memberId, ConcernInfoConstants.CONCERN_INFO_CROP);

        if (concernInfos.size() != 0) {
            return AjaxResult.error("已存在关注作物信息，不允许使用批量添加接口");
        }

        List<ConcernInfo> concernInfoList = new ArrayList<>(ids.length);

        for (String id : ids) {

            Date date = new Date();
            ConcernInfo concernInfo = new ConcernInfo();

            concernInfo.setMemberId(memberId);
            concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_CROP);
            concernInfo.setTargetId(Long.valueOf(id));

            concernInfo.setCreateTime(date);

            concernInfoList.add(concernInfo);
        }

        return toAjax(concernInfoService.batchAdd(concernInfoList));
    }


//    /**
//     * 查询作物分页列表
//     *
//     * @param pageNum
//     * @return
//     */
//    @ApiOperation("查询作物分页列表")
//    @GetMapping(value = "/selectCropDictList")
//    public AjaxResult selectCropDictList(Integer pageNum,Integer pageSize) {
//        if (pageNum == null) {
//            pageNum = 1;
//        }
//
//        if (pageSize == null) {
//            pageSize = 5;
//        }
//
//        PageHelper.startPage(pageNum, pageSize);
//        Long memberId=tokenService.getLoginUser().getUserid();
//        PageInfo pageInfo = new PageInfo<>(cropDictService.selectAllCropDictList(memberId));
//
//        AjaxResult success = AjaxResult.success();
//        success.put("data", pageInfo.getList());
//        success.put("totalPage", pageInfo.getPages());
//        success.put("hasNext", pageInfo.isHasNextPage());
//        success.put("hasPre", pageInfo.isHasPreviousPage());
//
//        return success;
//    }



    @ApiOperation("添加关注作物")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    @GetMapping("concern/{cropId}")
    public AjaxResult concern(@PathVariable Long cropId) {

        ConcernInfo concernInfo = new ConcernInfo();

        concernInfo.setCreateTime(new Date());
        concernInfo.setMemberId(tokenService.getLoginUser().getUserid());
        concernInfo.setTargetId(cropId);
        concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_CROP);

        return toAjax(concernInfoService.insertConcernInfo(concernInfo, concernInfo.getMemberId()));
    }

    @ApiOperation("取消关注作物")
    @ApiImplicitParam(name = "token", value = "token", paramType = "query")
    @GetMapping("concern/cancel/{cropId}")
    public AjaxResult cancelConcern(@PathVariable Long cropId) {
        try {
            ConcernInfo concernInfo = new ConcernInfo();
            concernInfo.setMemberId(tokenService.getLoginUser().getUserid());
            concernInfo.setTargetId(cropId);
            concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_CROP);

            int rows = concernInfoService.deleteConcernInfo(concernInfo);
            return toAjax(rows);
        } catch (Exception e) {
            return toAjax(1);
        }
    }
}
