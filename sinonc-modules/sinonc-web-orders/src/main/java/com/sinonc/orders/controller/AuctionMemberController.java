package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.domain.Auctionmember;
import com.sinonc.orders.service.AuctionmemberService;
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
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 竞拍记录Controller
 *
 * @author ruoyi
 * @date 2022-03-21
 */
@RestController
@RequestMapping("/auctionmember")
public class AuctionMemberController extends BaseController {
    @Autowired
    private AuctionmemberService auctionmemberService;

    /**
     * 查询竞拍记录列表
     */
    @PreAuthorize(hasPermi = "order:auctionmember:list")
    @GetMapping("/list")
    public TableDataInfo list(Auctionmember auctionmember) {
        startPage();
        List<Auctionmember> list = auctionmemberService.listAuctionmember(auctionmember);
        return getDataTable(list);
    }

    /**
     * 导出竞拍记录列表
     */
    @PreAuthorize(hasPermi = "order:auctionmember:export")
    @Log(title = "竞拍记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Auctionmember auctionmember) throws IOException {
        List<Auctionmember> list = auctionmemberService.listAuctionmember(auctionmember);
        ExcelUtil<Auctionmember> util = new ExcelUtil<Auctionmember>(Auctionmember.class);
        util.exportExcel(response, list, "auctionmember");
    }

    /**
     * 获取竞拍记录详细信息
     */
    @PreAuthorize(hasPermi = "order:auctionmember:query")
    @GetMapping(value = "/{auctionmemberId}")
    public AjaxResult getInfo(@PathVariable("auctionmemberId") Long auctionmemberId) {
        return AjaxResult.success(auctionmemberService.getAuctionmemberById(auctionmemberId));
    }

    /**
     * 新增竞拍记录
     */
    @PreAuthorize(hasPermi = "order:auctionmember:add")
    @Log(title = "竞拍记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Auctionmember auctionmember) {
        return toAjax(auctionmemberService.addAuctionmember(auctionmember));
    }

    /**
     * 修改竞拍记录
     */
    @PreAuthorize(hasPermi = "order:auctionmember:edit")
    @Log(title = "竞拍记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Auctionmember auctionmember) {
        return toAjax(auctionmemberService.updateAuctionmember(auctionmember));
    }

    /**
     * 删除竞拍记录
     */
    @PreAuthorize(hasPermi = "order:auctionmember:remove")
    @Log(title = "竞拍记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{auctionmemberIds}")
    public AjaxResult remove(@PathVariable Long[] auctionmemberIds) {
        return toAjax(auctionmemberService.deleteAuctionmemberByIds(auctionmemberIds));
    }
}
