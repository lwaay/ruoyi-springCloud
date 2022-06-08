package com.sinonc.orders.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.Brand;
import com.sinonc.orders.dto.OdGoodsDto;
import com.sinonc.orders.service.BrandService;
import com.sinonc.orders.service.IShopService;
import com.sinonc.orders.vo.OdGoodsVo;
import org.springframework.beans.BeanUtils;
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
import com.sinonc.orders.domain.OdGoods;
import com.sinonc.orders.service.IOdGoodsService;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.page.TableDataInfo;

/**
 * 商品Controller
 *
 * @author ruoyi
 * @date 2022-04-01
 */
@RestController
@RequestMapping("/goods")
public class OdGoodsController extends BaseController {
    @Autowired
    private IOdGoodsService odGoodsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private IShopService shopService;


    /**
     * 查询商品列表
     */
    @PreAuthorize(hasPermi = "orders:goods:list")
    @GetMapping("/list")
    public TableDataInfo list(OdGoods odGoods) {
        startPage();
        odGoods.setType(1);
        List<OdGoods> list = odGoodsService.selectOdGoodsList(odGoods);
        TableDataInfo tableDataInfo= getDataTable(list);

        List<OdGoodsDto> odGoodsDtoList=new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            OdGoodsDto tempOdGoodsDto=new OdGoodsDto();
            odGoodsDtoList.add(tempOdGoodsDto);
            OdGoods tempOdGoods=list.get(i);
            BeanUtils.copyProperties(tempOdGoods,tempOdGoodsDto);

            Brand brand=brandService.getBrandById(tempOdGoods.getBrandId());
            if(brand!=null){
                tempOdGoodsDto.setBrandName(brand.getBrandName());
            }else {
                tempOdGoodsDto.setBrandName("");
            }

            Shop tempShop=shopService.selectShopById(tempOdGoods.getShopId());
            if(tempShop!=null){
                tempOdGoodsDto.setShopName(tempShop.getShopName());
            }else {
                tempOdGoodsDto.setShopName("");
            }

        }
        tableDataInfo.setRows(odGoodsDtoList);

        return tableDataInfo;
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize(hasPermi = "orders:goods:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OdGoods odGoods) throws IOException {
        List<OdGoods> list = odGoodsService.selectOdGoodsList(odGoods);
        ExcelUtil<OdGoods> util = new ExcelUtil<OdGoods>(OdGoods.class);
        util.exportExcel(response, list, "goods");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize(hasPermi = "orders:goods:query")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId) throws Exception {
        return AjaxResult.success(odGoodsService.selectOdGoodsVoById(goodsId));
    }

    /**
     * 新增商品
     */
    @PreAuthorize(hasPermi = "orders:goods:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OdGoodsVo odGoodsVo) throws Exception {
        int rs=odGoodsService.addOdGoodsVo(odGoodsVo);
        return toAjax(rs);
    }

    /**
     * 修改商品
     */
    @PreAuthorize(hasPermi = "orders:goods:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OdGoodsVo odGoodsVo) throws Exception{
        int rs=odGoodsService.updateOdGoodsVo(odGoodsVo);
        return toAjax(rs);
    }

    /**
     * 删除商品
     */
    @PreAuthorize(hasPermi = "orders:goods:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds) {
        return toAjax(odGoodsService.deleteOdGoodsByIds(goodsIds));
    }
}
