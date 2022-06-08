package com.sinonc.origins.controller.api;

import com.github.pagehelper.PageHelper;
import com.sinonc.agriculture.RemoteExpertInfoService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.RemoteIotService;
import com.sinonc.order.api.RemoteShopService;
import com.sinonc.origins.domain.IndustryData;
import com.sinonc.origins.domain.vo.ServiceDataVo;
import com.sinonc.origins.service.IIndustryDataService;
import com.sinonc.origins.service.IProProductInfoService;
import com.sinonc.origins.service.IProVisitService;
import com.sinonc.system.api.RemoteEntityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/4/19 11:24
 */
@RestController
@Api(tags = "大屏主页接口")
@RequestMapping("/api/index")
public class ApiIndexController extends BaseController {

    @Autowired
    private RemoteExpertInfoService remoteExpertInfoService;

    @Autowired
    private RemoteShopService shopService;

    @Autowired
    private RemoteIotService iotService;

//    @Autowired
//    private RemoteEntityService remoteEntityService;

    @Autowired
    private IProProductInfoService productInfoService;

    @Autowired
    private IIndustryDataService industryDataService;

    @Autowired
    private IProVisitService visitService;

    @ApiOperation("平台服务与监测数据")
    @GetMapping("servicedata")
    @Cacheable(cacheNames = "index" , key = "'servicedata'")
    public AjaxResult serviceAndMonitorData(){
        List<ServiceDataVo> dataVoList = new ArrayList<>(6);
        // 专家数量
        int expertCount = remoteExpertInfoService.expertCount().getData();
        ServiceDataVo p1 = new ServiceDataVo("专家数量",expertCount,"人");
        dataVoList.add(p1);
        // 溯源访问数
        Long visitCount = visitService.getVisitCountByCode(null);
        ServiceDataVo p2 = new ServiceDataVo("溯源访问数", Math.toIntExact(visitCount),"次");
        dataVoList.add(p2);
        // 知识库数量
        int articleCount = remoteExpertInfoService.articleCount().getData();
        ServiceDataVo p3 = new ServiceDataVo("知识库数量", articleCount,"篇");
        dataVoList.add(p3);
        // 溯源产品数
        long originCount = productInfoService.getCount(null);
        ServiceDataVo p4 = new ServiceDataVo("溯源产品数", Math.toIntExact(originCount),"个");
        dataVoList.add(p4);
        // 商品数
        int goodsCount = shopService.goodsCount().getData();
        ServiceDataVo p5 = new ServiceDataVo("电商商品数", goodsCount,"个");
        dataVoList.add(p5);
        // 预警数
        int warnCount = iotService.warnCount().getData();
        ServiceDataVo p6 = new ServiceDataVo("数据预警", warnCount,"条");
        dataVoList.add(p6);
        return AjaxResult.success(dataVoList);
    }

    @ApiOperation("百色芒果历年种植规模与产值分析 地区分类")
    @GetMapping("analysis/area")
    @Cacheable(cacheNames = "index", key = "'area_year_' + #year")
    public AjaxResult analysisA(@RequestParam(value = "year", defaultValue = "2020") String year){
        return AjaxResult.success(industryDataService.selectIndustryDataByAreaCode(year));
    }

    @ApiOperation("百色芒果历年种植规模与产值分析 品种分类")
    @GetMapping("analysis/breed")
    @Cacheable(cacheNames = "index", key = "'breed_year_' + #year")
    public AjaxResult analysisB(@RequestParam(value = "year", defaultValue = "2020") String year){
        return AjaxResult.success(industryDataService.selectIndustryDataByBreed(year));
    }

    @ApiOperation("百色芒果历年种植规模与产值分析 饼图 top5")
    @GetMapping("analysis/breedfive")
    @Cacheable(cacheNames = "index", key = "'breedfive_year_' + #year")
    public AjaxResult analysisBF(@RequestParam(value = "year", defaultValue = "2020") String year){
        return AjaxResult.success(industryDataService.selectIndustryDataByBreedFive(year));
    }

    @ApiOperation("清除首页缓存")
    @GetMapping("clear")
    @CacheEvict(cacheNames = "index", allEntries = true)
    public AjaxResult clearCache(){
        return AjaxResult.success("清除缓存首页缓存成功");
    }

//    @ApiOperation("获取百色芒果新型生产经营主体")
//    @GetMapping("businessEntity")
//    public AjaxResult getBusinessEntityList(){
//        return AjaxResult.success(remoteEntityService.selectBusinessEntityList().getData());
//    }
}
