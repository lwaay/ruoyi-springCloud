package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.common.redis.service.RedisService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.YxSystemCity;
import com.sinonc.orders.dto.ShippingTemplatesDto;
import com.sinonc.orders.service.IYxSystemCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import com.sinonc.orders.domain.YxShippingTemplates;
import com.sinonc.orders.service.IYxShippingTemplatesService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 运费模板Controller
 *
 * @author ruoyi
 * @date 2022-03-23
 */
@RestController
@RequestMapping("/templates")
public class YxShippingTemplatesController extends BaseController {
    @Autowired
    private IYxShippingTemplatesService yxShippingTemplatesService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private IYxSystemCityService cityService;

    private final static String YSHOP_REDIS_SYS_CITY_KEY = "baise:city_list";

    /**
     * 查询运费模板列表
     */
    @PreAuthorize(hasPermi = "shop:templates:list")
    @GetMapping("/list")
    public TableDataInfo list(YxShippingTemplates yxShippingTemplates) {
        if (SecurityUtils.isAdmin()){
            yxShippingTemplates.setEntityId(SecurityUtils.getEntity());
        }
        startPage();
        List<YxShippingTemplates> list = yxShippingTemplatesService.selectYxShippingTemplatesList(yxShippingTemplates);
        return getDataTable(list);
    }

    /**
     * 导出运费模板列表
     */
    @PreAuthorize(hasPermi = "shop:templates:export")
    @Log(title = "运费模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YxShippingTemplates yxShippingTemplates) throws IOException {
        List<YxShippingTemplates> list = yxShippingTemplatesService.selectYxShippingTemplatesList(yxShippingTemplates);
        ExcelUtil<YxShippingTemplates> util = new ExcelUtil<YxShippingTemplates>(YxShippingTemplates.class);
        util.exportExcel(response, list, "templates");
    }

    /**
     * 获取运费模板详细信息
     */
    @PreAuthorize(hasPermi = "shop:templates:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id) {
        return AjaxResult.success(yxShippingTemplatesService.selectYxShippingTemplatesById(id));
    }

    /**
     * 修改运费模板
     */
    @PreAuthorize(hasPermi = "shop:templates:save")
    @Log(title = "运费模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult save(@RequestBody ShippingTemplatesDto yxShippingTemplates) {
        return toAjax(yxShippingTemplatesService.saveYxShippingTemplates(yxShippingTemplates));
    }

    /**
     * 删除运费模板
     */
    @PreAuthorize(hasPermi = "shop:templates:remove")
    @Log(title = "运费模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(yxShippingTemplatesService.deleteYxShippingTemplatesByIds(ids));
    }

    /**
     * 查询城市列表
     * @return
     */
    @GetMapping("/citys")
    public AjaxResult citys(){
        List<YxSystemCity> cities = redisService.getCacheList(YSHOP_REDIS_SYS_CITY_KEY);
        if (CollectionUtils.isEmpty(cities)){
            cities = cityService.treeCity();
            if (CollectionUtils.isEmpty(cities)){
                return AjaxResult.success(cities);
            }
            redisService.setCacheList(YSHOP_REDIS_SYS_CITY_KEY,cities);
        }
        return AjaxResult.success(cities);
    }

    /**
     * 查询我的运费模板
     */
    @GetMapping("/choseTemplates")
    public AjaxResult choseTemplates(YxShippingTemplates query){
       if (SecurityUtils.isAdmin()){
           query.setEntityId(SecurityUtils.getEntity());
       }
       List<YxShippingTemplates> list = yxShippingTemplatesService.selectYxShippingTemplatesList(query);
       return AjaxResult.success(list);
    }
}
