package com.sinonc.orders.ec.controller;

import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.orders.ec.domain.FarmProduceSale;
import com.sinonc.orders.ec.dto.ProduceSaleDto;
import com.sinonc.orders.ec.dto.ProduceSalePieDto;
import com.sinonc.orders.ec.service.IFarmProduceSaleService;
import com.sinonc.orders.ec.vo.FarmProduceSaleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 农产品销售信息Controller
 *
 * @author ruoyi
 * @date 2021-08-11
 */
@RestController
@RequestMapping("/ec/farmsale")
public class FarmProduceSaleController extends BaseController {
    @Autowired
    private IFarmProduceSaleService farmProduceSaleService;

    /**
     * 查询农产品销售信息列表
     */
    @PreAuthorize(hasPermi = "ec:farmsale:list")
    @GetMapping("/list")
    public TableDataInfo list(FarmProduceSale farmProduceSale) {
        startPage();
        List<FarmProduceSale> list = farmProduceSaleService.selectFarmProduceSaleList(farmProduceSale);
        return getDataTable(list);
    }

    /**
     * 导出农产品销售信息列表
     */
    @PreAuthorize(hasPermi = "ec:farmsale:export")
    @Log(title = "农产品销售信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FarmProduceSale farmProduceSale) throws IOException {
        List<FarmProduceSale> list = farmProduceSaleService.selectFarmProduceSaleList(farmProduceSale);
        ExcelUtil<FarmProduceSale> util = new ExcelUtil<FarmProduceSale>(FarmProduceSale.class);
        util.exportExcel(response, list, "farmsale");
    }

    /**
     * 获取农产品销售信息详细信息
     */
    @PreAuthorize(hasPermi = "ec:farmsale:query")
    @GetMapping(value = "/{saleId}")
    public AjaxResult getInfo(@PathVariable("saleId") Long saleId) {
        return AjaxResult.success(farmProduceSaleService.selectFarmProduceSaleById(saleId));
    }

    /**
     * 新增农产品销售信息
     */
    @PreAuthorize(hasPermi = "ec:farmsale:add")
    @Log(title = "农产品销售信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FarmProduceSale farmProduceSale) {
        return toAjax(farmProduceSaleService.insertFarmProduceSale(farmProduceSale));
    }

    /**
     * 修改农产品销售信息
     */
    @PreAuthorize(hasPermi = "ec:farmsale:edit")
    @Log(title = "农产品销售信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FarmProduceSale farmProduceSale) {
        return toAjax(farmProduceSaleService.updateFarmProduceSale(farmProduceSale));
    }

    /**
     * 删除农产品销售信息
     */
    @PreAuthorize(hasPermi = "ec:farmsale:remove")
    @Log(title = "农产品销售信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{saleIds}")
    public AjaxResult remove(@PathVariable Long[] saleIds) {
        return toAjax(farmProduceSaleService.deleteFarmProduceSaleByIds(saleIds));
    }


    @GetMapping("/statisticFarmProduceSale")
    public AjaxResult statisticFarmProduceSale(FarmProduceSaleVo farmProduceSaleVo) {
        String queryType = farmProduceSaleVo.getQueryType();
        if ("1".equals(queryType)) {
            //单价
            List<ProduceSaleDto> ProduceSaleDtoList = farmProduceSaleService.statisticFarmProduceSaleByUnitPrice(farmProduceSaleVo);
            return AjaxResult.success(ProduceSaleDtoList);
        }
        if ("2".equals(queryType)) {
            //销量
            List<ProduceSaleDto> ProduceSaleDtoList = farmProduceSaleService.statisticFarmProduceSaleBySaleVol(farmProduceSaleVo);
            return AjaxResult.success(ProduceSaleDtoList);
        }
        if ("3".equals(queryType)) {
            //收入
            List<ProduceSaleDto> ProduceSaleDtoList = farmProduceSaleService.statisticFarmProduceSaleByIncome(farmProduceSaleVo);
            return AjaxResult.success(ProduceSaleDtoList);
        }

        return AjaxResult.success(new ArrayList<ProduceSaleDto>());
    }


    @GetMapping("/statisticPieFarmProduceSale")
    public AjaxResult statisticPieFarmProduceSale(String pieType) {
        if ("1".equals(pieType)) {
            //收入
            List<ProduceSalePieDto> ProduceSaleDtoList = farmProduceSaleService.statisticPieFarmProduceSaleByIncome();
            return AjaxResult.success(ProduceSaleDtoList);
        }
        if ("2".equals(pieType)) {
            //销量
            List<ProduceSalePieDto> ProduceSaleDtoList = farmProduceSaleService.statisticPieFarmProduceSaleBySaleVol();
            return AjaxResult.success(ProduceSaleDtoList);
        }


        return AjaxResult.success(new ArrayList<ProduceSalePieDto>());
    }


    @GetMapping("/getProduceTypeList")
    public AjaxResult getProduceTypeList() {
        List<String> produceTypeList = farmProduceSaleService.getProduceTypeList();
        return AjaxResult.success(produceTypeList);
    }

}
