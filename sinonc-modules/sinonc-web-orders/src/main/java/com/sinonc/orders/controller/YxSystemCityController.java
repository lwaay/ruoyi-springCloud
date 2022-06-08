package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.service.IYxSystemCityService;
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
import com.sinonc.orders.domain.YxSystemCity;
import com.sinonc.orders.service.IYxSystemCityService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 城市Controller
 *
 * @author ruoyi
 * @date 2022-03-29
 */
@RestController
@RequestMapping("/city")
public class YxSystemCityController extends BaseController {

    @Autowired
    private IYxSystemCityService yxSystemCityService;

    /**
     * 查询城市列表
     */
    @PreAuthorize(hasPermi = "shop:city:list")
    @GetMapping("/list")
    public TableDataInfo list(YxSystemCity yxSystemCity) {
        startPage();
        List<YxSystemCity> list = yxSystemCityService.selectYxSystemCityList(yxSystemCity);
        return getDataTable(list);
    }

    /**
     * 导出城市列表
     */
    @Log(title = "城市", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YxSystemCity yxSystemCity) throws IOException {
        List<YxSystemCity> list = yxSystemCityService.selectYxSystemCityList(yxSystemCity);
        ExcelUtil<YxSystemCity> util = new ExcelUtil<YxSystemCity>(YxSystemCity.class);
        util.exportExcel(response, list, "city");
    }

    /**
     * 获取城市详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(yxSystemCityService.selectYxSystemCityById(id));
    }

    /**
     * 新增城市
     */
    @PreAuthorize(hasPermi = "shop:city:add")
    @Log(title = "城市", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YxSystemCity yxSystemCity) {
        return toAjax(yxSystemCityService.insertYxSystemCity(yxSystemCity));
    }

    /**
     * 修改城市
     */
    @PreAuthorize(hasPermi = "shop:city:edit")
    @Log(title = "城市", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YxSystemCity yxSystemCity) {
        return toAjax(yxSystemCityService.updateYxSystemCity(yxSystemCity));
    }

    /**
     * 删除城市
     */
    @PreAuthorize(hasPermi = "shop:city:remove")
    @Log(title = "城市", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(yxSystemCityService.deleteYxSystemCityByIds(ids));
    }
}
