package com.sinonc.origins.controller.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.RemoteFruitService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.common.core.constant.Constants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.origins.constants.PriceConstants;
import com.sinonc.origins.vo.QueryParamVo;
import com.sinonc.system.api.RemoteDictService;
import com.sinonc.system.api.RemoteEntityService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.api.domain.SysDictData;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @anthor wang
 */
@Slf4j
@RestController
@RequestMapping("/api/price")
public class ApiPriceController {

    @Autowired
    private RemoteEntityService remoteEntityService;
    @Autowired
    private RemoteBaseFarmService remoteBaseFarmService;
    @Autowired
    private RemoteFruitService remoteFruitService;
    @Autowired
    private RemoteDictService remoteDictService;

    @Autowired
    private TokenService tokenService;

    @Value("${price.url}")
    private String priceUrl;


    /**
     * 智能推荐
     *
     * @return
     */
    @PostMapping("/intellectPrice")
    public AjaxResult intellectPrice(String type) {
        RestTemplate restTemplate = new RestTemplate();
        String typeNames = "";
        if(StringUtils.isNotEmpty(type)){
            typeNames = type;
        }else{
            Set<String> typeSet = getTypeBy();
            typeNames = String.join(",", typeSet);
        }
        QueryParamVo paramVo = new QueryParamVo();
        paramVo.setCategorys(typeNames);
        paramVo.setOrderBy("desc");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<QueryParamVo> httpEntity = new HttpEntity<>(paramVo, headers);
        ResponseEntity<String> exchage = restTemplate.exchange(priceUrl + "/price/intellectPrice/1/10",
                HttpMethod.POST, httpEntity, String.class);
        JSONObject res = JSONObject.parseObject(exchage.getBody());
        if (res.getIntValue("code") == 200) {
            return AjaxResult.success(res.getJSONObject("data"));
        }

        return AjaxResult.error();
    }

    private Set<String> getTypeBy() {
        Set<String> types = new HashSet<>();
        LoginUser loginUser = tokenService.getLoginUser();
        if (!Optional.ofNullable(loginUser).isPresent()) {
            throw new BusinessException("用户未登录或用户信息错误");
        }
        WxUser wxUser = loginUser.getWxUser();
        if (!Optional.ofNullable(wxUser).isPresent()) {
            throw new BusinessException("用户未登录或用户信息错误");
        }
        if (!StringUtils.isEmpty(wxUser.getEntityId())) {
            BaseFarm baseFarm = new BaseFarm();
            baseFarm.setEntityId(Long.valueOf(wxUser.getEntityId()));
            List<BaseFarm> baseFarmList = remoteBaseFarmService.list(baseFarm).getData();
            baseFarmList.forEach(entity -> {
                FruiterInfo fruit = new FruiterInfo();
                fruit.setOrchId(baseFarm.getFarmId());
                List<FruiterInfo> fruitInfoList = remoteFruitService.listFruit(fruit).getData();
                if (fruitInfoList.size() > 0) {
                    //将类别放入set
                    fruitInfoList.forEach(info -> {
                        List<SysDictData> dictDataList = remoteDictService.getLabelBy("mango_type").getData();
                        dictDataList.forEach(dict -> {
                            if (dict.getDictValue().equals(info.getFruType())) {
                                types.add(dict.getDictLabel());
                            }
                        });
                    });
                }
            });
        }
        return types;
    }

    @PostMapping("/rank")
    public AjaxResult rank(@RequestBody QueryParamVo paramVo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<QueryParamVo> httpEntity = new HttpEntity<>(paramVo, headers);
        paramVo.setOrderBy("desc");
        ResponseEntity<String> exchage = restTemplate.exchange(priceUrl + "/price/" + paramVo.getCurrent() + "/" + paramVo.getSize(),
                HttpMethod.POST, httpEntity, String.class);
        JSONObject res = JSONObject.parseObject(exchage.getBody());
        if (res.getIntValue("code") == 200) {
            return AjaxResult.success(res.getJSONObject("data"));
        }
        return AjaxResult.error();
    }

    @GetMapping("/getAvgPriceByMarket")
    public AjaxResult getAvgPriceByMarket(QueryParamVo queryParamVo) {
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(priceUrl + "/statistic/getAvgPriceByMarket?groupBy=" + queryParamVo.getGroupBy() + "&category=" + queryParamVo.getCategory(), String.class);
        JSONObject jsonObject = JSON.parseObject(res);
        if (jsonObject.getIntValue("code") == 200) {
            return AjaxResult.success(jsonObject.get("data"));
        }
        return AjaxResult.error();
    }

    @GetMapping("/ringGrowthPrice")
    public AjaxResult ringGrowthPrice(String categoryName) {
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(priceUrl + "/market/api/ringGrowthPrice.do?categoryName=" + categoryName, String.class);
        JSONObject jsonObject = JSON.parseObject(res);
        if (jsonObject.getIntValue("code") == 200) {
            return AjaxResult.success(jsonObject.get("data"));
        }
        return AjaxResult.error();
    }

    @GetMapping("/getCategoryByParent")
    public AjaxResult getCategoryByParent(String parentCategory) {
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(priceUrl + "/price/getCategory?parentCategory=" + parentCategory, String.class);
        JSONObject jsonObject = JSON.parseObject(res);
        if (jsonObject.getIntValue("code") == 200) {
            return AjaxResult.success(jsonObject.get("data"));
        }
        return AjaxResult.error();
    }

    @GetMapping("/categoryPriceTrendBy")
    public AjaxResult monthCategoryPriceTrend(String type, String categoryName) {
        RestTemplate restTemplate = new RestTemplate();
        String url;
        if (PriceConstants.WEEK.equals(type)) {
            url = priceUrl + "/market/api/weekCategoryPriceTrend.do?categoryName=" + categoryName;
        } else if (PriceConstants.MONTH.equals(type)) {
            url = priceUrl + "/market/api/monthCategoryPriceTrend.do?categoryName=" + categoryName;
        } else {
            url = priceUrl + "/market/api/dayCategoryPriceTrend.do?categoryName=" + categoryName;
        }
        String res = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSON.parseObject(res);
        if (jsonObject.getIntValue("code") == 200) {
            return AjaxResult.success(jsonObject.get("data"));
        }
        return AjaxResult.error();
    }


}



