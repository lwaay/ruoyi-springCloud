package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

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
import com.sinonc.orders.domain.AdoptionCircleLike;
import com.sinonc.orders.service.IAdoptionCircleLikeService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 朋友圈点赞Controller
 *
 * @author ruoyi
 * @date 2022-04-23
 */
@RestController
@RequestMapping("/circlelike")
public class AdoptionCircleLikeController extends BaseController {
    @Autowired
    private IAdoptionCircleLikeService adoptionCircleLikeService;

    /**
     * 查询朋友圈点赞列表
     */
    @PreAuthorize(hasPermi = "orders:circlelike:list")
    @GetMapping("/list")
    public TableDataInfo list(AdoptionCircleLike adoptionCircleLike) {
        startPage();
        List<AdoptionCircleLike> list = adoptionCircleLikeService.selectAdoptionCircleLikeList(adoptionCircleLike);
        return getDataTable(list);
    }

    /**
     * 导出朋友圈点赞列表
     */
    @PreAuthorize(hasPermi = "orders:circlelike:export")
    @Log(title = "朋友圈点赞", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdoptionCircleLike adoptionCircleLike) throws IOException {
        List<AdoptionCircleLike> list = adoptionCircleLikeService.selectAdoptionCircleLikeList(adoptionCircleLike);
        ExcelUtil<AdoptionCircleLike> util = new ExcelUtil<AdoptionCircleLike>(AdoptionCircleLike. class);
        util.exportExcel(response, list, "circlelike");
    }

    /**
     * 获取朋友圈点赞详细信息
     */
    @PreAuthorize(hasPermi = "orders:circlelike:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(adoptionCircleLikeService.selectAdoptionCircleLikeById(id));
    }

    /**
     * 新增朋友圈点赞
     */
    @PreAuthorize(hasPermi = "orders:circlelike:add")
    @Log(title = "朋友圈点赞", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdoptionCircleLike adoptionCircleLike) {
        return toAjax(adoptionCircleLikeService.insertAdoptionCircleLike(adoptionCircleLike));
    }

    /**
     * 修改朋友圈点赞
     */
    @PreAuthorize(hasPermi = "orders:circlelike:edit")
    @Log(title = "朋友圈点赞", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdoptionCircleLike adoptionCircleLike) {
        return toAjax(adoptionCircleLikeService.updateAdoptionCircleLike(adoptionCircleLike));
    }

    /**
     * 删除朋友圈点赞
     */
    @PreAuthorize(hasPermi = "orders:circlelike:remove")
    @Log(title = "朋友圈点赞", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(adoptionCircleLikeService.deleteAdoptionCircleLikeByIds(ids));
    }
}
