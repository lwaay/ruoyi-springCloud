package com.sinonc.orders.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.sinonc.orders.domain.AuctionBond;
import com.sinonc.orders.service.AuctionBondService;
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
 * 竞拍活动押金Controller
 *
 * @author ruoyi
 * @date 2022-03-22
 */
@RestController
@RequestMapping("/bond")
public class AuctionBondController extends BaseController {
    @Autowired
    private AuctionBondService auctionBondService;

    /**
     * 查询竞拍活动押金列表
     */
    @PreAuthorize(hasPermi = "order:bond:list")
    @GetMapping("/list")
    public TableDataInfo list(AuctionBond odAuctionBond) {
        startPage();
        List<AuctionBond> list = auctionBondService.listAuctionBond(odAuctionBond);
        return getDataTable(list);
    }

    /**
     * 导出竞拍活动押金列表
     */
    @PreAuthorize(hasPermi = "order:bond:export")
    @Log(title = "竞拍活动押金", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AuctionBond odAuctionBond) throws IOException {
        List<AuctionBond> list = auctionBondService.listAuctionBond(odAuctionBond);
        ExcelUtil<AuctionBond> util = new ExcelUtil<AuctionBond>(AuctionBond.class);
        util.exportExcel(response, list, "bond");
    }

    /**
     * 获取竞拍活动押金详细信息
     */
    @PreAuthorize(hasPermi = "order:bond:query")
    @GetMapping(value = "/{auctionbondId}")
    public AjaxResult getInfo(@PathVariable("auctionbondId") Long auctionbondId) {
        return AjaxResult.success(auctionBondService.getAuctionBondById(auctionbondId));
    }

    /**
     * 新增竞拍活动押金
     */
    @PreAuthorize(hasPermi = "order:bond:add")
    @Log(title = "竞拍活动押金", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AuctionBond odAuctionBond) {
        return toAjax(auctionBondService.addAuctionBond(odAuctionBond));
    }

    /**
     * 修改竞拍活动押金
     */
    @PreAuthorize(hasPermi = "order:bond:edit")
    @Log(title = "竞拍活动押金", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AuctionBond odAuctionBond) {
        return toAjax(auctionBondService.updateAuctionBond(odAuctionBond));
    }

    /**
     * 删除竞拍活动押金
     */
    @PreAuthorize(hasPermi = "order:bond:remove")
    @Log(title = "竞拍活动押金", businessType = BusinessType.DELETE)
    @DeleteMapping("/{auctionbondIds}")
    public AjaxResult remove(@PathVariable String auctionbondIds) {
        return toAjax(auctionBondService.deleteAuctionBondByIds(auctionbondIds));
    }
}
