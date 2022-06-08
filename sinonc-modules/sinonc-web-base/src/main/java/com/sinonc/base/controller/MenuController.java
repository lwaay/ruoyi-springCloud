package com.sinonc.base.controller;

import com.sinonc.base.domain.Menu;
import com.sinonc.base.service.IMenuService;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 大屏菜单Controller
 *
 * @author hhao
 * @date 2020-11-26
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Autowired
    private IMenuService menuService;

    /**
     * 查询大屏菜单列表
     */
    @PreAuthorize(hasPermi = "base:menu:list")
    @GetMapping("/list")
    public TableDataInfo list(Menu menu) {
        startPage();
        List<Menu> list = menuService.selectMenuList(menu);
        return getDataTable(list);
    }

    /**
     * 导出大屏菜单列表
     */
    @PreAuthorize(hasPermi = "base:menu:export")
    @Log(title = "大屏菜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Menu menu) throws IOException {
        List<Menu> list = menuService.selectMenuList(menu);
        ExcelUtil<Menu> util = new ExcelUtil<Menu>(Menu.class);
        util.exportExcel(response, list, "menu");
    }

    /**
     * 获取大屏菜单详细信息
     */
    @PreAuthorize(hasPermi = "base:menu:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(menuService.selectMenuById(id));
    }

    /**
     * 新增大屏菜单
     */
    @PreAuthorize(hasPermi = "base:menu:add")
    @Log(title = "大屏菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Menu menu) {
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改大屏菜单
     */
    @PreAuthorize(hasPermi = "base:menu:edit")
    @Log(title = "大屏菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Menu menu) {
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除大屏菜单
     */
    @PreAuthorize(hasPermi = "base:menu:remove")
    @Log(title = "大屏菜单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(menuService.deleteMenuByIds(ids));
    }

    /**
     * 加载父菜单
     */
    @GetMapping("/parent")
    public AjaxResult parentList() {
        return AjaxResult.success(menuService.selectMenuList(new Menu(0L)));
    }

    /**
     * 修改状态
     * @param menu
     * @return
     */
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody Menu menu){
        return toAjax(menuService.updateMenu(menu));
    }
}
