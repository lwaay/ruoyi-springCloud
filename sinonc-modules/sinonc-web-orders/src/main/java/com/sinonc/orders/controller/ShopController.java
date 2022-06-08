package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.order.api.domain.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.service.IShopService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 店铺信息Controller
 *
 * @author ruoyi
 * @date 2022-03-30
 */
@RestController
@RequestMapping("/shopinfo")
public class ShopController extends BaseController {
    @Autowired
    private IShopService shopService;

    /**
     * 获取店铺信息详细信息
     */
    @GetMapping(value = "/getShopListByEntityId/{entityId}")
    public AjaxResult getShopListByEntityId(@PathVariable("entityId") Long entityId) {
        return AjaxResult.success(shopService.selectShopByEntity(entityId));
    }

    /**
     * 查询店铺信息列表
     */
    @PreAuthorize(hasPermi = "orders:shopinfo:list")
    @GetMapping("/list")
    public TableDataInfo list(Shop shop) {
        startPage();
        List<Shop> list = shopService.selectShopList(shop);
        return getDataTable(list);
    }

    /**
     * 导出店铺信息列表
     */
    @PreAuthorize(hasPermi = "orders:shopinfo:export")
    @Log(title = "店铺信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Shop shop) throws IOException {
        List<Shop> list = shopService.selectShopList(shop);
        ExcelUtil<Shop> util = new ExcelUtil<Shop>(Shop.class);
        util.exportExcel(response, list, "shopinfo");
    }

    /**
     * 获取店铺信息详细信息
     */
    @PreAuthorize(hasPermi = "orders:shopinfo:query")
    @GetMapping(value = "/{shopId}")
    public AjaxResult getInfo(@PathVariable("shopId") Long shopId) {
        return AjaxResult.success(shopService.selectShopById(shopId));
    }

    /**
     * 新增店铺信息
     */
    @PreAuthorize(hasPermi = "orders:shopinfo:add")
    @Log(title = "店铺信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Shop shop) {
        return toAjax(shopService.insertShop(shop));
    }

    /**
     * 修改店铺信息
     */
    @PreAuthorize(hasPermi = "orders:shopinfo:edit")
    @Log(title = "店铺信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Shop shop) {
        return toAjax(shopService.updateShop(shop));
    }

    /**
     * 删除店铺信息
     */
    @PreAuthorize(hasPermi = "orders:shopinfo:remove")
    @Log(title = "店铺信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{shopIds}")
    public AjaxResult remove(@PathVariable Long[] shopIds) {
        return toAjax(shopService.deleteShopByIds(shopIds));
    }
}
