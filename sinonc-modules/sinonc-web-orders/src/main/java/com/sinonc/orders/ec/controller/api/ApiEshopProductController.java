package com.sinonc.orders.ec.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.RemoteFruitService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.ec.bo.ProductSellBo;
import com.sinonc.orders.ec.constants.Constants;
import com.sinonc.orders.ec.domain.EshopBrand;
import com.sinonc.orders.ec.domain.EshopProduct;
import com.sinonc.orders.ec.domain.ProductType;
import com.sinonc.orders.ec.service.IEshopBrandService;
import com.sinonc.orders.ec.service.IEshopProductService;
import com.sinonc.orders.ec.service.IProductTypeService;
import com.sinonc.orders.ec.vo.*;
import com.sinonc.system.api.RemoteDictService;
import com.sinonc.system.api.domain.BusinessEntity;
import com.sinonc.system.api.domain.SysDictData;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author huanghao
 * @apiNote 图表数据
 * @date 2020/8/15 11:28
 */
@RestController
@RequestMapping("/api/eshop")
@Api(tags = "电商数据")
public class ApiEshopProductController {
    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private IEshopProductService eshopProductService;
    @Autowired
    private IProductTypeService productTypeService;
    @Autowired
    private IEshopBrandService brandService;

    @Autowired
    private RemoteBaseFarmService remoteBaseFarmService;
    @Autowired
    private RemoteFruitService remoteFruitService;
    @Autowired
    private RemoteDictService remoteDictService;

    @Autowired
    private TokenService tokenService;

    /**
     * 智能推荐
     *
     * @return
     */
    @PostMapping("/intellectPrice")
    public AjaxResult intellectPrice() {
        RestTemplate restTemplate = new RestTemplate();
        Set<String> typeSet = getTypeBy();
        String typeNames = String.join(",", typeSet);

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
        if(!StringUtils.isEmpty(wxUser.getEntityId())) {
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

    /**
     * 查询大屏默认品类
     *
     * @return
     */
    @GetMapping("/selectDefaultTypeList")
    @ApiOperation("查询大屏默认品类")
    public AjaxResult selectDefaultTypeList() {
        List<ProductType> productTypeList = productTypeService.selectListDefault();
        return AjaxResult.success(productTypeList);
    }

    /**
     * 查询大屏默认品牌
     *
     * @return
     */
    @GetMapping("/selectDefaultBrandList")
    @ApiOperation("查询大屏默认品牌")
    public AjaxResult selectDefaultBrandList() {
        List<EshopBrand> brandList = brandService.selectListDefault();
        return AjaxResult.success(brandList);
    }


    /**
     * 查询电商销售数据的默认日期参数
     *
     * @return
     */
    @GetMapping("/selectDateForParam")
    @ApiOperation("查询电商销售数据的默认日期参数")
    public AjaxResult selectDateForParam() {
        Object dataParam = eshopProductService.selectDateForParam();
        return AjaxResult.success(dataParam);
    }

    /**
     * 查询大屏展示的品类
     *
     * @return
     */
    @GetMapping("/selectTypeList")
    @ApiOperation("查询大屏展示的品类")
    public AjaxResult selectTypeList() {
        List<ProductType> productTypeList = productTypeService.selectProductTypeList(new ProductType(Constants.STATUS));
        return AjaxResult.success(productTypeList);
    }

    /**
     * 查询大屏展示的品牌
     *
     * @return
     */
    @GetMapping("/selectBrandListShow")
    @ApiOperation("查询大屏展示的品牌")
    public AjaxResult selectBrandListShow() {
        List<EshopBrand> brandList = brandService.selectEshopBrandList(new EshopBrand(Constants.STATUS));
        return AjaxResult.success(brandList);
    }

    /**
     * 获取销售排行榜
     *
     * @param productSortVo 查询条件
     * @return 结果
     */
    @PostMapping("/rank")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "查询条件 1 销售量 2 单价 ", name = "key", defaultValue = "0", required = true),
            @ApiImplicitParam(value = "开始日期", name = "startTime", required = true),
            @ApiImplicitParam(value = "结束日期", name = "endTime", required = true),
            @ApiImplicitParam(value = "品类id集", name = "typeIds", required = false)
    })
    @ApiOperation("获取销售排行榜")
    public AjaxResult rankList(@RequestBody ProductSortVo productSortVo) {
        productSortVo.setDimension("4");
        List<EshopPorductVo> result = eshopProductService.getRankList(productSortVo);
        return AjaxResult.success(result);
    }

    /**
     * 获取折现图
     *
     * @return 结果
     */
    @PostMapping("/line")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "开始日期", name = "startTime", required = true),
            @ApiImplicitParam(value = "结束日期", name = "endTime", required = true),
            @ApiImplicitParam(value = "品类id集", name = "typeIds", required = false)
    })
    @ApiOperation("获取折现图")
    public AjaxResult brandTypeList(@Valid @RequestBody ProductSortVo productSortVo) {
        ProductBrandTypeVo result = eshopProductService.getBrandTypeList(productSortVo);
        return AjaxResult.success(result);
    }

    /**
     * 电商月销售统计
     *
     * @return
     */
    @PostMapping("/getListByMonth")
    @ApiImplicitParam(value = "日期", name = "date", required = true)
    @ApiOperation("电商月销售统计")
    public AjaxResult getListByMonth(@RequestBody @Valid ProductSortVo productSortVo) {
        List<EshopMonthVo> result = eshopProductService.getListByMonth(productSortVo);
        return AjaxResult.success(result);
    }

    /**
     * 电商品类销售对比分析
     *
     * @return
     */
    @PostMapping("/getSellProportion")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "开始日期", name = "startTime", required = true),
            @ApiImplicitParam(value = "结束日期", name = "endTime", required = true),
            @ApiImplicitParam(value = "品类id集", name = "typeIds", required = false)
    })
    @ApiOperation("电商品类销售对比分析")
    public AjaxResult getSellProportion(@RequestBody @Valid ProductSortVo productSortVo) {
        ProductSellBo result = eshopProductService.getSellProportion(productSortVo);
        return AjaxResult.success(result);
    }

    /**
     * 百色市电商品牌销售对比分析
     *
     * @return
     */
    @PostMapping("/getBrandList")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "开始日期", name = "startTime", required = true),
            @ApiImplicitParam(value = "结束日期", name = "endTime", required = true),
            @ApiImplicitParam(value = "品类id集", name = "typeIds", required = false)
    })
    @ApiOperation("百色市电商品牌销售对比分析")
    public AjaxResult getBrandList(@RequestBody @Valid ProductSortVo productSortVo) {
        List<ProductSellVo> result = eshopProductService.getBrandPriceList(productSortVo);
        return AjaxResult.success(result);
    }

    /**
     * 百色市农产品特产店铺销售对比分析
     *
     * @return
     */
    @PostMapping("/getShopList")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "开始日期", name = "startTime", required = true),
            @ApiImplicitParam(value = "结束日期", name = "endTime", required = true),
            @ApiImplicitParam(value = "品类id集", name = "typeIds", required = false)
    })
    @ApiOperation("百色市农产品特产店铺销售对比分析")
    public AjaxResult getShopList(@RequestBody @Valid ProductSortVo productSortVo) {
        List<ProductSellVo> result = eshopProductService.getShopList(productSortVo);
        return AjaxResult.success(result);
    }


    @GetMapping("/getRealTimeCategory")
    public AjaxResult getRealTimeCategory() {
        List<EshopProduct> reptileRealtimeProduct = eshopProductService.list(new QueryWrapper<EshopProduct>().eq("reptile_status_realtime", 1));
        List<RealTimeProductCategoryVo> collect = reptileRealtimeProduct.stream().map(e ->
                RealTimeProductCategoryVo.builder().goodsId(e.getGoodsId()).productType(e.getProductType()).build()
        ).collect(Collectors.toList());
        return AjaxResult.success(collect);
    }

    /**
     * 获取电商销售实时数据
     *
     * @param productType
     * @return
     */
    @GetMapping("/shopRealTimeStatisticData")
    public AjaxResult shopRealTimeStatisticData(String productType) {
        return AjaxResult.success(eshopProductService.getShopRealTimeStatisticData(productType));
    }

    /**
     * 获取店铺商品实时数据
     *
     * @param goodsStore
     * @return
     */
    @GetMapping("/storeRealTimeStatisticData")
    public AjaxResult storeRealTimeStatisticData(String goodsStore) {
        return AjaxResult.success(eshopProductService.getStoreRealTimeStatisticData(goodsStore));
    }

    /**
     * @return 获取商品品类下拉
     */
    @GetMapping("/reptile/product/type")
    public AjaxResult reptileProductType() {
        return AjaxResult.success(eshopProductService.getReptileProductType());
    }

    /**
     * @return 获取店铺下拉
     */
    @GetMapping("/reptile/product/store")
    public AjaxResult reptileProductStore() {
        return AjaxResult.success(eshopProductService.getReptileProductStore());
    }

    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, String appKey) throws Exception {
        if (!Constants.APPKEY.equals(appKey)) {
            return AjaxResult.error("密钥错误！");
        }
        try (InputStream is = file.getInputStream()) {
            ExcelUtil<EshopProduct> util = new ExcelUtil<EshopProduct>(EshopProduct.class);
            List<EshopProduct> eshopProductList = util.importExcel(is);
            //给电商平台类型赋值
            for (EshopProduct eshopProduct : eshopProductList) {
                if (eshopProduct == null || StringUtils.isEmpty(eshopProduct.getShopType())) {
                    continue;
                }
                if (Constants.CSHOP.equals(eshopProduct.getShopType())) {
                    eshopProduct.setPlatform(Constants.TB);
                } else if (Constants.TMALL.equals(eshopProduct.getShopType())) {
                    eshopProduct.setPlatform(Constants.TM);
                } else {
                    eshopProduct.setPlatform(Constants.JD);
                }
            }
            String operName = SecurityUtils.getUsername();
            String message = eshopProductService.importEshop(eshopProductList, operName);
            return AjaxResult.success(message);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<EshopProduct> util = new ExcelUtil<EshopProduct>(EshopProduct.class);
        util.importTemplateExcel(response, "电商数据");
    }

}
