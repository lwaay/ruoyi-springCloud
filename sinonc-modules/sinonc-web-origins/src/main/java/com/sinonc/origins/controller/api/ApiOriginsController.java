package com.sinonc.origins.controller.api;


import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.IpUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.iot.api.RemoteIotService;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.api.vo.ProStaticVo;
import com.sinonc.origins.api.domain.PmBusiness;
import com.sinonc.origins.api.domain.ProOriginsInfo;
import com.sinonc.origins.api.domain.ProProductInfo;
import com.sinonc.origins.api.domain.ProVisit;
import com.sinonc.origins.constants.PriceConstants;
import com.sinonc.origins.domain.ProductLike;
import com.sinonc.iot.api.dto.ProdEnviStatisticsDto;
import com.sinonc.origins.dto.ProProductInfoDto;
import com.sinonc.origins.service.*;
import com.sinonc.system.api.RemoteEntityService;
import com.sinonc.system.api.domain.BusinessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @anthor wang
 */
@RestController
@RequestMapping("/api/origins")
public class ApiOriginsController {
    @Autowired
    private IProVisitService iProVisitService;
    @Autowired
    private IProProductInfoService iProductInfoService;
    @Autowired
    private IProductLikeService iProductLikeService;
    @Autowired
    private IProOriginsInfoService iOriginsInfoService;
    @Autowired
    private RemoteIotService remoteIotServices;
    @Autowired
    private RemoteEntityService remoteEntityService;
    @Autowired
    private RemoteBaseFarmService remoteBaseFarmService;
    @Autowired
    private RedisService redisService;


    @GetMapping("/getProductByProductId")
    public AjaxResult getProductByProductId(@RequestParam(required = true) Long productId, HttpServletRequest httpServletRequest) {
        ProProductInfoDto proProductInfoDto=iProductInfoService.selectProProductInfoById(productId);
        AjaxResult ajaxResult=getAjaxResultProductByCode(proProductInfoDto.getProductCode(),httpServletRequest);
        return ajaxResult;
    }

    /**
     * ?????????????????????????????????????????????????????????
     *
     * @param code
     * @return
     */
    @GetMapping("/getProductByCode")
    public AjaxResult getProductByCode(@RequestParam(required = true) String code, HttpServletRequest httpServletRequest) {
        AjaxResult ajaxResult=getAjaxResultProductByCode(code,httpServletRequest);
        return ajaxResult;
    }


    private AjaxResult   getAjaxResultProductByCode(String code,HttpServletRequest httpServletRequest){
        Map<String, Object> result = new HashMap<>();
        ProProductInfo productInfo = iProductInfoService.selectProductByCode(code);
        if (!Optional.ofNullable(productInfo).isPresent()) {
            return AjaxResult.error("??????????????????");
        }
        productInfo.setImgs(productInfo.getMainImages().split(","));
        productInfo.setMainImages("");
        ProVisit proVisit = new ProVisit();
        proVisit.setProductId(productInfo.getProductId());
        proVisit.setVisitShopname(productInfo.getBrandName());
        proVisit.setVisitTime(DateUtils.getNowDate());
        proVisit.setVisitCity(IpUtils.getIpAddr(httpServletRequest));

        BusinessEntity business = new BusinessEntity();
        if (productInfo.getTillArea() != null) {
            //?????????
            business = remoteEntityService.getEntityById(productInfo.getTillmainIdP()).getData();
            //?????????
            BusinessEntity produce = remoteEntityService.getEntityById(productInfo.getManuIdP()).getData();

            productInfo.setProVisit(iProVisitService.getVisitCount(productInfo.getProductId()));
            proVisit.setVisitProduct(business.getEntityName());
            productInfo.setMainIntroduce(business.getBusinessScope());
            productInfo.setBusinessName(business.getEntityName());
            productInfo.setProduceName(produce.getEntityName());

            ProductLike productLike = iProductLikeService.selectProductLikeById(productInfo.getProductId());
            if (productLike != null) {
                productInfo.setProLike(productLike.getLikeNum());
            } else {
                productInfo.setProLike(0L);
            }
        }

        iProVisitService.insertProVisit(proVisit);

        //??????????????????
        List<Map<String, Object>> listsc = iOriginsInfoService.selectOriginsInfoByIdForTypeAndIsOpe(productInfo.getProductId(), 5);
        //??????????????????
        List<Map<String, Object>> listjs = iOriginsInfoService.selectOriginsInfoByIdForTypeAndIsOpe(productInfo.getProductId(), 2);
        //??????????????????
        List<Map<String, Object>> listrz = iOriginsInfoService.selectOriginsInfoByIdForTypeAndIsOpe(productInfo.getProductId(), 3);
        //??????????????????
        List<Map<String, Object>> listgc = iOriginsInfoService.selectOriginsInfoByIdForTypeAndIsOpe(productInfo.getProductId(), 1);
        //??????????????????
        List<Map<String, Object>> listjl = iOriginsInfoService.selectOriginsInfoByIdForTypeAndIsOpe(productInfo.getProductId(), 4);
        for (Map<String, Object> map : listgc) {
            if (map != null && map.get("videoList") != null) {
                //????????????
                String[] videoList = (String[]) map.get("videoList");
                productInfo.setVideoUrl(videoList[0]);
                break;
            }
        }

        //??????????????????id??????????????????
        List<BaseFarm> baseFarmList = remoteBaseFarmService.list(new BaseFarm(productInfo.getTillmainIdP())).getData();
        List<DeviceMonitor> listDeviceMonitor = new ArrayList();
        baseFarmList.forEach(entity -> {
            String deviceIds = entity.getDeviceIds();
            String deviceId = "";
            if (StringUtils.isNotEmpty(deviceIds)) {
                String[] deviceIdArray = deviceIds.split(",");
                if (deviceIdArray != null && deviceIdArray.length > 0) {
                    deviceId = deviceIdArray[0];
                }
            }

            if (listgc != null && listgc.size() > 0 && StringUtils.isNotEmpty(deviceId)) {
                Map<String, Object> firstSc = listgc.get(0);
                Date originsDate = (Date) firstSc.get("origins_date");
                Date endDate = new Date();
                ProStaticVo proStaticVo = new ProStaticVo(originsDate, endDate, deviceId);
                ProdEnviStatisticsDto prodEnviStatisticsDto = remoteIotServices.getProStatic(proStaticVo).getData();
                result.put("prodEnviStatisticsDto", prodEnviStatisticsDto);
            } else {
                result.put("prodEnviStatisticsDto", new ProdEnviStatisticsDto());
            }

            //?????????????????????
            R<List<DeviceMonitor>> deviceMonitorsResult = remoteIotServices.getMonitorListByFarm(entity.getFarmId());
            if(Constants.SUCCESS.equals(deviceMonitorsResult.getCode())){
                listDeviceMonitor.addAll(deviceMonitorsResult.getData());
            }
        });


        result.put("productInfo", productInfo); //????????????
        result.put("listsc", listsc);
        result.put("listjs", listjs);
        result.put("listrz", listrz);
        result.put("listgc", listgc);
        result.put("listjl", listjl);
        result.put("busi", business);
        result.put("listDeviceMonitor", listDeviceMonitor);


        return AjaxResult.success(result);
    }

    /**
     * ????????????????????????
     *
     * @param id
     * @return
     */
    @PostMapping("/getLike")
    public AjaxResult getProductByCode(@RequestParam(required = true) Long id) {
        ProProductInfo productInfo = iProductInfoService.selectProProductInfoById(id);
        if (productInfo != null) {
            ProductLike productLike = iProductLikeService.selectProductLikeById(id);
            if (productLike != null) {
                productLike.setProductIdP(productInfo.getProductId());
                productLike.setProductName(productInfo.getProductName());
                productLike.setLikeNum(productLike.getLikeNum() + 1);
                productLike.setCreateTime(DateUtils.getNowDate());
                iProductLikeService.updateProductLike(productLike);
                return AjaxResult.success("??????");
            } else {
                ProductLike like = new ProductLike();
                like.setProductIdP(productInfo.getProductId());
                like.setProductName(productInfo.getProductName());
                like.setLikeNum(1L);
                like.setCreateTime(DateUtils.getNowDate());
                iProductLikeService.insertProductLike(like);
                return AjaxResult.success("??????");
            }

        }
        return AjaxResult.error("?????????????????????");
    }

    /**
     * ??????????????????
     *
     * @param originsInfo
     * @return
     */
    @PostMapping("/putOrigins")
    public AjaxResult putOrigins(@RequestBody ProOriginsInfo originsInfo) {
        if (!Optional.ofNullable(originsInfo).isPresent()) {
            return AjaxResult.error();
        }
        if (originsInfo.getProductIdP() == null || StringUtils.isEmpty(originsInfo.getOriginsType()) || StringUtils.isEmpty(originsInfo.getOriginsProType())) {
            return AjaxResult.error();
        }
        int res = 0;
        originsInfo.setIsOpen("1");
        if (originsInfo.getOriginsId() == null) {
            res = iOriginsInfoService.insertProOriginsInfo(originsInfo);
        } else {
            res = iOriginsInfoService.updateProOriginsInfo(originsInfo);
        }
        return res > 0 ? AjaxResult.success() : AjaxResult.error();
    }


    /**
     * ????????????Id??????????????????
     */
    @GetMapping("/getOrigins/{productId}")
    public AjaxResult getOrigins(@PathVariable("productId") Long productId) {
        ProOriginsInfo query = new ProOriginsInfo();
        query.setProductIdP(productId);
        query.setOriginsType(PriceConstants.PRODUCT);
        List<ProOriginsInfo> list = iOriginsInfoService.selectProOriginsInfoList(query);
        return AjaxResult.success(list);
    }

    /**
     * ????????????????????????
     */
    @GetMapping("/getProduct/{entityIdP}")
    public AjaxResult getProduct(@PathVariable("entityIdP") Long entityIdP) {
        ProProductInfo query = new ProProductInfo();
        query.setTillmainIdP(entityIdP);
        List<ProProductInfo> list = iProductInfoService.selectProProductInfoList(query);
        return AjaxResult.success(list);
    }

    @PostMapping("/delOrigins/{originsId}")
    public AjaxResult delOrigins(@PathVariable("originsId") Long originsId) {
        return iOriginsInfoService.deleteProOriginsInfoById(originsId) > 0 ? AjaxResult.success() : AjaxResult.error();
    }


}



