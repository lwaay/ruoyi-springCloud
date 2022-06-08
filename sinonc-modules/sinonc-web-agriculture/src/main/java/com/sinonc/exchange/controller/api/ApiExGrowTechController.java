package com.sinonc.exchange.controller.api;

import com.github.pagehelper.PageHelper;
import com.sinonc.agriculture.domain.GrowTech;
import com.sinonc.agriculture.service.CropDictService;
import com.sinonc.agriculture.service.GrowTechService;
import com.sinonc.agriculture.vo.CropDictVo;
import com.sinonc.agriculture.vo.GrowTechVo;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.domain.Ztree;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.exchange.utils.SysSignUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(tags = "外部-种养殖技术接口")
@RequestMapping("api/agriculture/exchange/growtech")
@RestController
@Slf4j
public class ApiExGrowTechController extends BaseController {

    @Autowired
    private GrowTechService growTechService;

    @Autowired
    private CropDictService cropDictService;

    @ApiOperation("查询种养殖技术列表")
    @PostMapping(value = "list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public TableDataInfo list(@RequestBody GrowTechVo growTechVo) {
        //验证签名
        growTechVo.setParams(new HashMap<>());
        String sign = SysSignUtil.getSign(growTechVo);

        if (!sign.equals(growTechVo.getSign())) {
            TableDataInfo rspData = new TableDataInfo();
            return rspData;
        }

        PageHelper.startPage(growTechVo.getPageNum(), growTechVo.getPageSize());
        List<GrowTech> list = growTechService.selectGrowtechListByGrowtechVo(growTechVo);
        return getDataTable(list);
    }

    @ApiOperation("新增种养殖信息")
    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult addSave(@RequestBody GrowTechVo growTech) {
        //验证签名
        String sign = SysSignUtil.getSign(growTech);

        if (!sign.equals(growTech.getSign())) {
            return AjaxResult.error("签名验证失败。");
        }

        return toAjax(growTechService.insertGrowTech(growTech));
    }

    @ApiOperation("查询种养殖信息")
    @PostMapping(value = "selectGrowTechById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult selectGrowTechById(@RequestBody GrowTechVo growTech) {
        //验证签名
        String sign = SysSignUtil.getSign(growTech);

        if (!sign.equals(growTech.getSign())) {
            return AjaxResult.error("签名验证失败。");
        }

        AjaxResult ajaxResult = new AjaxResult();
        GrowTechVo growTechVo = growTechService.selectGrowTechById(growTech.getGrowId());
        ajaxResult.put("GrowTechVo", growTechVo);

        return ajaxResult;
    }

    @ApiOperation("更新种养殖信息")
    @PostMapping(value = "updateGrowTech", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult updateGrowTech(@RequestBody GrowTechVo growTech) {
        //验证签名
        String sign = SysSignUtil.getSign(growTech);

        if (!sign.equals(growTech.getSign())) {
            return AjaxResult.error("签名验证失败。");
        }

        AjaxResult ajaxResult = new AjaxResult();
        int rs = growTechService.updateGrowTech(growTech);
        ajaxResult.put("updateStatus", rs);

        return ajaxResult;
    }


//    @ApiOperation("删除种养殖技术")
//    @PostMapping(value = "remove", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    public AjaxResult remove(@RequestBody GrowTechVo growTech) {
//        //验证签名
//        String sign = SysSignUtil.getSign(growTech);
//
//        if (!sign.equals(growTech.getSign())) {
//            return AjaxResult.error("签名验证失败。");
//        }
//
//        AjaxResult ajaxResult = new AjaxResult();
//        int rs = growTechService.deleteGrowTechByIds();
//        ajaxResult.put("deleteStatus", rs);
//
//        return ajaxResult;
//    }

    @ApiOperation("查询作物树")
    @PostMapping(value = "selectCropDictVoById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public AjaxResult selectCropDictVoById(@RequestBody CropDictVo cropDictVo) {
        //验证签名
        String sign = SysSignUtil.getSign(cropDictVo);

        if (!sign.equals(cropDictVo.getSign())) {
            return AjaxResult.error("签名验证失败。");
        }

        AjaxResult ajaxResult = new AjaxResult();

        CropDict cropDict = cropDictService.selectCropDictById(cropDictVo.getCropId());

        ajaxResult.put("cropDictVo", cropDict);

        return ajaxResult;
    }

    @ApiOperation("查询作物树列表")
    @PostMapping(value = "treeData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Ztree> treeData(@RequestBody CropDictVo cropDict) {
        //验证签名
        String sign = SysSignUtil.getSign(cropDict);

        if (!sign.equals(cropDict.getSign())) {
            return null;
        }

        List<Ztree> ztrees = cropDictService.selectCropDictTree();
        return ztrees;
    }

}
