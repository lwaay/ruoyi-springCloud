package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.PageDataInfo;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.dto.CartDto;
import com.sinonc.orders.domain.OdCart;
import com.sinonc.orders.service.IOdCartService;
import com.sinonc.orders.vo.OdCartVo;
import com.sinonc.orders.vo.ShopVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * 购物车Controller
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@Api(tags = "购物车API")
@RestController
@RequestMapping("/api/cart")
public class ApiCartController extends BaseController {

    @Autowired
    private IOdCartService odCartService;

    /**
     * 查询购物车列表
     */
    @ApiOperation("获取我的购物车列表")
    @PostMapping("/list")
    public PageDataInfo list(@RequestBody OdCart odCart) {
        if (!Optional.ofNullable(odCart).isPresent() || odCart.getMemberId() == null){
            throw new BusinessException("查询购物车失败,请确认查询参数");
        }
        int pageNum = odCart.getPageNum() == null || odCart.getPageNum()<1?1:odCart.getPageNum();
        int pageSize = odCart.getPageSize() == null || odCart.getPageSize()<1?10:odCart.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<ShopVo> list = odCartService.selectOdCartList(odCart);
        return getPageTable(list);
    }

    /**
     * 获取购物车详细信息
     */
    @ApiOperation("获取购物车详情")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(odCartService.selectOdCartById(id));
    }

    /**
     * 新增购物车
     */
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @ApiOperation("新增购物车")
    @PostMapping
    public AjaxResult add(@RequestBody CartDto cart) {
        return toAjax(odCartService.insertOdCart(cart));
    }

    /**
     * 修改购物车
     */
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @ApiOperation("修改购物车")
    @PutMapping
    public AjaxResult edit(@RequestBody CartDto cart) {
        return toAjax(odCartService.updateOdCart(cart));
    }

    /**
     * 删除购物车
     */
    @Log(title = "购物车", businessType = BusinessType.DELETE)
    @ApiOperation("删除购物车")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(odCartService.deleteOdCartByIds(ids));
    }
}
