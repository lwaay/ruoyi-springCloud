package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.service.AuctionService;
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
 * 竞拍活动Controller
 *
 * @author ruoyi
 * @date 2022-03-18
 */
@RestController
@RequestMapping("/auction")
public class AuctionController extends BaseController {
    @Autowired
    private AuctionService auctionService;

    /**
     * 查询竞拍活动列表
     */
    @PreAuthorize(hasPermi = "order:auction:list")
    @GetMapping("/list")
    public TableDataInfo list(Auction odAuction) {
        startPage();
        List<Auction> list = auctionService.listAuction(odAuction);
        return getDataTable(list);
    }

    /**
     * 导出竞拍活动列表
     */
    @PreAuthorize(hasPermi = "order:auction:export")
    @Log(title = "竞拍活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Auction odAuction) throws IOException {
        List<Auction> list = auctionService.listAuction(odAuction);
        ExcelUtil<Auction> util = new ExcelUtil<Auction>(Auction.class);
        util.exportExcel(response, list, "auction");
    }

    /**
     * 获取竞拍活动详细信息
     */
    @PreAuthorize(hasPermi = "order:auction:query")
    @GetMapping(value = "/{auctionId}")
    public AjaxResult getInfo(@PathVariable("auctionId") Long auctionId) {
        return AjaxResult.success(auctionService.getAuctionById(auctionId));
    }

    /**
     * 新增竞拍活动
     */
    @PreAuthorize(hasPermi = "order:auction:add")
    @Log(title = "竞拍活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Auction odAuction) {
        return toAjax(auctionService.addAuction(odAuction));
    }

    /**
     * 修改竞拍活动
     */
    @PreAuthorize(hasPermi = "order:auction:edit")
    @Log(title = "竞拍活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Auction odAuction) {
        return toAjax(auctionService.updateAuction(odAuction));
    }

    /**
     * 删除竞拍活动
     */
    @PreAuthorize(hasPermi = "order:auction:remove")
    @Log(title = "竞拍活动", businessType = BusinessType.DELETE)
    @DeleteMapping("/{auctionIds}")
    public AjaxResult remove(@PathVariable String auctionIds) {
        return toAjax(auctionService.deleteAuctionByIds(auctionIds));
    }
}
