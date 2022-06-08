package com.sinonc.orders.ec.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.ec.domain.EshopProduct;
import com.sinonc.orders.ec.constants.Constants;
import com.sinonc.orders.ec.domain.EshopProductRealtime;
import com.sinonc.orders.ec.domain.EshopProductReptile;
import com.sinonc.orders.ec.mapper.CronMapper;
import com.sinonc.orders.ec.schedule.Cron;
import com.sinonc.orders.ec.service.IEshopProductRealtimeService;
import com.sinonc.orders.ec.service.IEshopProductReptileService;
import com.sinonc.orders.ec.service.IEshopProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 电商数据Controller
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@RestController
@RequestMapping("/ec/product")
public class EshopProductController extends BaseController {
    @Autowired
    private IEshopProductService eshopProductService;
    @Autowired
    private IEshopProductRealtimeService realtimeService;
    @Autowired
    private CronMapper cronMapper;
    @Autowired
    private IEshopProductReptileService reptileService;

    /**
     * 查询电商数据列表
     */
    @PreAuthorize(hasPermi = "system:product:list")
    @GetMapping("/list")
    public TableDataInfo list(EshopProduct eshopProduct) {
        startPage();
        List<EshopProduct> list = eshopProductService.selectEshopProductList(eshopProduct);
        return getDataTable(list);
    }

    @Log(title = "电商管理", businessType = BusinessType.IMPORT)
    @PreAuthorize(hasPermi = "system:product:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<EshopProduct> util = new ExcelUtil<EshopProduct>(EshopProduct.class);
        try(InputStream is = file.getInputStream()){
            List<EshopProduct> eshopProductList = util.importExcel(is);
            //给电商平台类型赋值
            for (EshopProduct eshopProduct : eshopProductList) {
                if(eshopProduct == null || StringUtils.isEmpty(eshopProduct.getShopType())){
                    continue;
                }
                if(Constants.CSHOP.equals(eshopProduct.getShopType())){
                    eshopProduct.setPlatform(Constants.TB);
                }else if(Constants.TMALL.equals(eshopProduct.getShopType())){
                    eshopProduct.setPlatform(Constants.TM);
                }else {
                    eshopProduct.setPlatform(Constants.JD);
                }
            }
            String operName = SecurityUtils.getUsername();
            String message = eshopProductService.importEshop(eshopProductList, operName);
            return AjaxResult.success(message);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return AjaxResult.error(e.getMessage());
        }
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException {
        ExcelUtil<EshopProduct> util = new ExcelUtil<EshopProduct>(EshopProduct.class);
        util.importTemplateExcel(response, "电商数据");
    }

    /**
     * 导出电商数据列表
     */
    @PreAuthorize(hasPermi = "system:product:export")
    @Log(title = "电商数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EshopProduct eshopProduct) throws IOException {
        List<EshopProduct> list = eshopProductService.selectProductList(eshopProduct);
        ExcelUtil<EshopProduct> util = new ExcelUtil<EshopProduct>(EshopProduct.class);
        util.exportExcel(response, list, "product");
    }

    /**
     * 获取电商数据详细信息
     */
    @PreAuthorize(hasPermi = "system:product:query")
    @GetMapping(value = "/{eshopId}")
    public AjaxResult getInfo(@PathVariable("eshopId") Long eshopId) {
        return AjaxResult.success(eshopProductService.selectEshopProductById(eshopId));
    }

    /**
     * 新增电商数据
     */
    @Log(title = "电商数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody EshopProduct eshopProduct) {
        return toAjax(eshopProductService.insertEshopProduct(eshopProduct));
    }

    /**
     * 修改电商数据
     */
    @PreAuthorize(hasPermi = "system:product:edit")
    @Log(title = "电商数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EshopProduct eshopProduct) {
        return toAjax(eshopProductService.updateEshopProduct(eshopProduct));
    }

    /**
     * 删除电商数据
     */
    @PreAuthorize(hasPermi = "system:product:remove")
    @Log(title = "电商数据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{eshopIds}")
    public AjaxResult remove(@PathVariable Long[] eshopIds) {
        return toAjax(eshopProductService.deleteEshopProductByIds(eshopIds));
    }

    @GetMapping("/reptile/{goodsId}")
    public AjaxResult changeReptile(@PathVariable(value = "goodsId") String goodsId) {
        if (StringUtils.isEmpty(goodsId)) {
            throw new BusinessException("goods id 不能为空");
        }
        return toAjax(eshopProductService.changeReptile(goodsId));
    }

    @PostMapping("/reptile/global/set")
    @SuppressWarnings("all")
    public AjaxResult changeGlobalSetting(Integer status, Integer type, String productTypes, Long cronId) {
        List<EshopProductRealtime> needUpdate;
        QueryWrapper<EshopProductRealtime> queryWrapper = new QueryWrapper<EshopProductRealtime>().isNotNull("goods_id");
        // 周期查询
        if (type == 1) {
            if (cronId == null) {
                throw new BusinessException("定时任务为空");
            }
            // 关
            if (status == 0) {
                List<EshopProductReptile> list = reptileService.list(new QueryWrapper<EshopProductReptile>().eq("status", status == 0 ? 1 : 0).eq("cron_type", type));
                if (!CollectionUtils.isEmpty(list)) {
                    reptileService.updateBatchById(list.stream().peek(e -> e.setStatus(status)).collect(Collectors.toList()));
                }
                return AjaxResult.success();
            }
            // 选中打开状态才能被全局筛选
            queryWrapper.eq("reptile_status", 1);
            needUpdate = realtimeService.list(queryWrapper);
        }
        // 实时查询
        else if (type == 2) {
            // 关
            if (status == 0) {
                List<EshopProductReptile> list = reptileService.list(new QueryWrapper<EshopProductReptile>().eq("status", status == 0 ? 1 : 0).eq("cron_type", type));
                if (!CollectionUtils.isEmpty(list)) {
                    reptileService.updateBatchById(list.stream().peek(e -> e.setStatus(status)).collect(Collectors.toList()));
                }
                return AjaxResult.success();
            } else {
                // 开 需要指定打开的商品类型
                if (StringUtils.isEmpty(productTypes)) {
                    throw new BusinessException("类型为空");
                }
                if (cronId == null) {
                    throw new BusinessException("定时任务为空");
                }
                queryWrapper.eq("reptile_status", 1);
                needUpdate = realtimeService.list(queryWrapper.in("product_type", productTypes.split(",")));
            }
        } else {
            throw new BusinessException("未知状态");
        }
        if (needUpdate.isEmpty()) {
            throw new BusinessException("未找到需要更新的任务,保存失败!");
        }
        Boolean res =  reptileService.saveOrUpdateBatch(eshopProductReptilesHandle(needUpdate, cronId, status, type));
        return res? AjaxResult.success(): AjaxResult.error("执行更新失败,未找到需更新任务");
    }

    private List<EshopProductReptile> eshopProductReptilesHandle(List<EshopProductRealtime> needUpdate, Long cronId, Integer status, Integer type) {

        List<EshopProductReptile> eshopProductReptiles;
        eshopProductReptiles = needUpdate.stream().map(e ->
                EshopProductReptile.builder().cronId(cronId)
                        .cronType(type)
                        .goodsOrigin(e.getPlatform())
                        .goodsId(e.getGoodsId())
                        .goodsStore(e.getName())
                        .goodsType(e.getProductType())
                        .status(status)
                        .build()
        ).collect(Collectors.toList());
        List<EshopProductReptile> existingList = reptileService.list(new QueryWrapper<EshopProductReptile>().eq("cron_type", type).in("goods_id", eshopProductReptiles.stream().map(EshopProductReptile::getGoodsId).collect(Collectors.toList())));
        if (!CollectionUtils.isEmpty(existingList)) {
            existingList.forEach(e -> {
                eshopProductReptiles.stream().filter(es -> es.getGoodsId().equals(e.getGoodsId()) && es.getCronType().equals(e.getCronType())).findFirst().ifPresent(es -> {
                    es.setId(e.getId());
                });
            });
        }
        return eshopProductReptiles;
    }

    @GetMapping("/cronList/{type}")
    public AjaxResult cronList(@PathVariable("type") Integer type) {
        return AjaxResult.success(cronMapper.selectList(new QueryWrapper<Cron>().eq("type", type)));
    }

    @GetMapping("/change/num")
    public AjaxResult statisticNum(String productTypes) {
        // 统计所有数量
        if (StringUtils.isEmpty(productTypes)) {
            return AjaxResult.success(realtimeService.count(new QueryWrapper<EshopProductRealtime>().isNotNull("goods_id")));
        }
        return AjaxResult.success(realtimeService.count(new QueryWrapper<EshopProductRealtime>().isNotNull("goods_id").in("product_type", Arrays.asList(productTypes.split(",")))));
    }

    /**
     * 根据品牌名称模糊查询
     * @param name
     * @return
     */
    @GetMapping("/getBrandListByName")
    public AjaxResult getBrandListByName(String name){
        return AjaxResult.success(eshopProductService.selectBrandListByName(name));
    }

}
