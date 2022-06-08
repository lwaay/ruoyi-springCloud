package com.sinonc.orders.controller;

import java.math.BigDecimal;
import java.util.List;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.Express;
import com.sinonc.orders.dto.OrderDto;
import com.sinonc.orders.dto.RefundDto;
import com.sinonc.orders.dto.SendDto;
import com.sinonc.orders.service.ExpressService;
import com.sinonc.orders.service.RefundService;
import com.sinonc.orders.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.service.OrderService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 订单Controller
 *
 * @author ruoyi
 * @date 2022-03-18
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OdOrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RefundService refundService;

    @Autowired
    private ExpressService expressService;

    /**
     * 查询订单列表
     */
    @PreAuthorize(hasPermi = "orders:order:list")
    @GetMapping("/list")
    public TableDataInfo list(OrderDto odOrder) {
        if (!SecurityUtils.isAdmin()){
            odOrder.setEntityId(SecurityUtils.getEntity());
        }
        startPage();
        List<OrderVO> list = orderService.listDataScopeOrderVO(odOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize(hasPermi = "orders:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Order order) throws IOException {
        List<Order> list = orderService.listOrder(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        util.exportExcel(response, list, "order");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize(hasPermi = "orders:order:query")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId) {
        OrderVO vo = orderService.getOrderVoById(orderId);
        return AjaxResult.success(vo);
    }

    /**
     * 新增订单
     */
    @PreAuthorize(hasPermi = "orders:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Order odOrder) {
        return toAjax(orderService.addOrder(odOrder));
    }

    /**
     * 新增订单
     */
    @PreAuthorize(hasPermi = "orders:order:refund")
    @PostMapping("/refund")
    @ResponseBody
    public AjaxResult saveRefund(@RequestBody RefundDto refund) {

        if (!Optional.ofNullable(refund).isPresent()) {
            return AjaxResult.error("参数错误");
        }
        try {
            refundService.refund(refund.getOrderNo(), refund.getRfAmount(), refund.getReason(), SecurityUtils.getUsername());
            return AjaxResult.success();
        } catch (Exception e) {
            log.error("订单 " + refund.getOrderNo() + " 退款失败", e);
            return AjaxResult.error("退款失败：" + e.getMessage());
        }
    }

    /**
     * 订单发货
     */
    @PreAuthorize(hasPermi = "orders:order:send")
    @PostMapping("/send")
    @ResponseBody
    public AjaxResult saveSend(@RequestBody SendDto send) {

        if (!Optional.ofNullable(send).isPresent()) {
            return AjaxResult.error("参数错误");
        }
        try {
            Express express = new Express();
            BeanUtils.copyProperties(send,express);
            return toAjax(expressService.addExpress(express));
        } catch (Exception e) {
            log.error("新增物流信息失败,"+e.getMessage());
            return AjaxResult.error("新增物流信息失败：" + e.getMessage());
        }
    }


    /**
     * 修改订单
     */
    @PreAuthorize(hasPermi = "orders:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Order order) {
        return toAjax(orderService.updateOrder(order));
    }

    /**
     * 删除订单
     */
    @PreAuthorize(hasPermi = "orders:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String orderIds) {
        return toAjax(orderService.deleteOrderByIds(orderIds));
    }
}
