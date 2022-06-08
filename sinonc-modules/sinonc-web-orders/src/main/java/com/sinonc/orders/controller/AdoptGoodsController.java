package com.sinonc.orders.controller;

import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.domain.Specs;
import com.sinonc.orders.mapper.ShopMapper;
import com.sinonc.orders.service.AdoptGoodsService;
import com.sinonc.orders.service.BrandService;
import com.sinonc.orders.service.GoodsService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 认养商品发布
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Slf4j
@RestController
@RequestMapping("/adoptGoods")
public class AdoptGoodsController extends BaseController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AdoptGoodsService adoptGoodsService;
//    @Autowired
//    private FarmAllMapper farmAllMapper;
//    @Autowired
//    private FarminfoMapper farminfoMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private BrandService brandService;

    /**
     * 查询认养商品列表
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Goods goods) {
        startPage();
        //认养商品
        goods.setType(0);
        List<Goods> list = goodsService.listGoods(goods);
        return getDataTable(list);
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody Goods goods) {
        try {
            return toAjax(adoptGoodsService.addAdoptGoods(goods));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改商品
     */
    @GetMapping("/edit/{goodsId}")
    @ResponseBody
    public AjaxResult edit(@PathVariable("goodsId") Long goodsId) {
        Goods goods = goodsService.getGoodsById(goodsId);
        Specs specs = adoptGoodsService.selectSpecsByGoodId(goodsId);
        goods.setSpecsName(specs.getSpecsName());
        goods.setSpecsPrice(specs.getSpecsPrice());
        goods.setStock(specs.getStock());
        goods.setPerWeight(specs.getPerWeight());
        try {
            String specsValueJson = adoptGoodsService.selectSpecsValueListBySpecsId(specs.getSpecsId());
            goods.setSpecsJson(specsValueJson);
            String specsValueids = adoptGoodsService.selectSpecsValueIds(specs.getSpecsId());
            goods.setSpecsValueids(specsValueids);
        } catch (Exception e) {
            log.error("error", e);
        }
        return AjaxResult.success(goods);
    }

    /**
     * 修改保存商品
     */
    @PutMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody Goods goods) {
        try {
            return toAjax(adoptGoodsService.updateAdoptGoods(goods));
        } catch (Exception e) {
            return error(e.getMessage());
        }

    }

    /**
     * 删除商品及其对应的其他表数据
     * @param ids
     * @return
     */
    @DeleteMapping("/remove/{ids}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("ids") String ids) {
        return toAjax(adoptGoodsService.deleteAdoptGoodsByIds(ids));
    }

}
