package com.sinonc.origins.controller.api;

import com.sinonc.base.api.RemoteAreaCodeService;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.origins.api.domain.ProOriginsInfo;
import com.sinonc.origins.api.domain.ProProductInfo;
import com.sinonc.origins.service.*;
import com.sinonc.origins.vo.BigScreenVo;
import com.sinonc.origins.vo.QueryParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lw
 * @anthor wang
 */
@RestController
@Api(tags = "溯源信息接口")
@RequestMapping("/api/bigScreen")
public class ApiBigScreenController extends BaseController {
    @Autowired
    private IPmBusinessService iBusinessService;
    @Autowired
    private IProProductInfoService iProductInfoService;
    @Autowired
    private IProVisitService iProVisitService;
    @Autowired
    private RemoteAreaCodeService areaCodeService;
    @Autowired
    private IProOriginsInfoService originsInfoService;
    @Autowired
    private IProInputService proInputService;

    /**
     * 大屏朔源信息通用接口
     *
     * @param productInfo
     * @return
     */
    @ApiOperation("大屏朔源信息通用接口")
    @PostMapping("/getProductInfo")
    public TableDataInfo getProductInfo(@RequestBody ProProductInfo productInfo) {
        startPage();
        List<ProProductInfo> list = iProductInfoService.selectProductByLike(productInfo);
        for (ProProductInfo info : list) {
            if(!StringUtils.isEmpty(info.getMainImages())){
                info.setImgs( info.getMainImages().split(","));
            }
        }
        return getDataTable(list);
    }



    /**
     * 根据地区查询朔源信息
     *
     * @param pAreaCode
     * @return
     */
    @PostMapping("/getProductInfoByCode")
    @ApiOperation("根据地区查询朔源信息")
    @ApiImplicitParam(name = "父类编码", value = "pAreaCode", dataType = "long", required = true)
    public TableDataInfo getProductInfoByCode(@RequestParam(required = true) Long pAreaCode) {
        startPage();
        try {
            List<ProProductInfo> productInfoBycode = iProductInfoService.getProductInfoBycode(pAreaCode);
            return getDataTable(productInfoBycode);
        } catch (Exception e) {
            return getDataTable(new ArrayList<>());
        }

    }

    /**
     * 获取所有修水县镇
     *
     * @return
     */
    @ApiOperation("获取所有修水县镇")
    @ApiImplicitParam(name = "父类编码", value = "pCode", dataType = "long", required = true)
    @GetMapping("/getArea/{pCode}")
    public AjaxResult getAreaByCode(@PathVariable("pCode") Long pCode) {
        try {
            AreaCode areaCode = new AreaCode();
            areaCode.setPcode(pCode);
            List<AreaCode> areaList = areaCodeService.list(areaCode).getData();
            return AjaxResult.success(areaList);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

    }


    /**
     * 农事活动管理动态
     *
     * @return
     */
    @ApiOperation("农事活动管理动态")
    @GetMapping("/getOriginsList")
    public TableDataInfo getOriginsList(ProOriginsInfo originsInfo) {
        startPage();
        try {
            List<ProOriginsInfo> proOriginsInfoList = originsInfoService.selectProOriginsInfoList(originsInfo);
            return getDataTable(proOriginsInfoList);
        } catch (Exception e) {
            return getDataTable(new ArrayList<>());
        }
    }

    /**
     * 农事投入品占比分析
     *
     * @return
     */
    @ApiOperation("农事投入品占比分析")
    @GetMapping("/getProInput")
    public AjaxResult getProInput(BigScreenVo bigScreenVo) {
        return AjaxResult.success(proInputService.getInputRate(bigScreenVo));
    }

    /**
     * 农事用工数据分析
     *
     * @return
     */
    @ApiOperation("农事用工数据分析")
    @GetMapping("/getProAnalysis")
    public AjaxResult getProAnalysis(BigScreenVo bigScreenVo) {
        return AjaxResult.success(originsInfoService.getProAnalysis(bigScreenVo));
    }

    /**
     * 大屏数据统计接口
     *
     * @return
     */
    @GetMapping("/getStatistics")
    public AjaxResult getStatistics(String baseArea) {
        Map<String,Long> map = new HashMap<String, Long>();
        //产品数
        long productStatistics = 0L;
        //企业数
        long businessStatistics = 0L;
        //朔源查看数
        long viewStatistics = 0L;
        try {
            productStatistics = iProductInfoService.getCount(baseArea);
            businessStatistics = iBusinessService.getAreaCount(baseArea);
            viewStatistics = iProVisitService.getVisitCountByCode(baseArea);
        } catch (Exception e) {

        }
        map.put("productStatistics", productStatistics);
        map.put("businessStatistics", businessStatistics);
        map.put("viewStatistics", viewStatistics);
        return AjaxResult.success(map);
    }


}
