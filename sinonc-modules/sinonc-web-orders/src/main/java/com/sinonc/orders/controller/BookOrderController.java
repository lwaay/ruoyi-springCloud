package com.sinonc.orders.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.domain.BookOrder;
import com.sinonc.orders.service.BookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预订单 信息操作处理
 *
 * @author sinonc
 * @date 2019-11-01
 */
@Controller
@RequestMapping("/orders/bookOrder")
public class BookOrderController extends BaseController {
    private String prefix = "orders/bookOrder";

	@Autowired
	private BookOrderService bookOrderService;

	@PreAuthorize(hasPermi = "orders:bookOrder:view")
	@GetMapping()
	public String bookOrder() {
	    return prefix + "/bookOrder";
	}

	/**
	 * 查询预订单列表
	 */
	@PreAuthorize(hasPermi = "orders:bookOrder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BookOrder bookOrder) {
		startPage();
        List<BookOrder> list = bookOrderService.listBookOrder(bookOrder);
		return getDataTable(list);
	}


	/**
	 * 导出预订单列表
	 */
	@PreAuthorize(hasPermi = "orders:bookOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BookOrder bookOrder) {
    	List<BookOrder> list = bookOrderService.listBookOrder(bookOrder);
        ExcelUtil<BookOrder> util = new ExcelUtil<BookOrder>(BookOrder.class);
        return util.exportExcel(list, "bookOrder");
    }

	/**
	 * 新增预订单
	 */
	@GetMapping("/add")
	public String add() {
	    return prefix + "/add";
	}

	/**
	 * 新增保存预订单
	 */
	@PreAuthorize(hasPermi = "orders:bookOrder:add")
	@Log(title = "预订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BookOrder bookOrder) {
		return toAjax(bookOrderService.addBookOrder(bookOrder));
	}

	/**
	 * 修改预订单
	 */
	@GetMapping("/edit/{boId}")
	public String edit(@PathVariable("boId") Long boId, ModelMap mmap) {
		BookOrder bookOrder = bookOrderService.getBookOrderById(boId);
		mmap.put("bookOrder", bookOrder);
	    return prefix + "/edit";
	}

	/**
	 * 修改保存预订单
	 */
	@PreAuthorize(hasPermi = "orders:bookOrder:edit")
	@Log(title = "预订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BookOrder bookOrder) {
		return toAjax(bookOrderService.updateBookOrder(bookOrder));
	}

	/**
	 * 删除预订单
	 */
	@PreAuthorize(hasPermi = "orders:bookOrder:remove")
	@Log(title = "预订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(bookOrderService.deleteBookOrderByIds(ids));
	}

}
