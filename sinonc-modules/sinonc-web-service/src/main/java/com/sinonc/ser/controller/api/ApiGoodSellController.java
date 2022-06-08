package com.sinonc.ser.controller.api;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.domain.PageResult;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.ser.dto.BizGoodSellDto;
import com.sinonc.ser.service.IBizGoodSellService;
import com.sinonc.ser.vo.BizGoodSellParaVo;
import com.sinonc.ser.vo.BizGoodSellVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 商品供应API
 *
 * @author zhangxinlong
 */
@Api(tags = "商品供应API")
@RestController
@RequestMapping("api/biz/goodsell")
public class ApiGoodSellController extends BaseController {

    @Autowired
    private IBizGoodSellService bizGoodSellService;

    @Autowired
    private TokenService tokenService;

    /**
     * 供应商品发布接口
     *
     * @return 结果
     */
    @ApiOperation(value = "发布供应商品")
    @PostMapping("addBizGoodSell")
    public AjaxResult addBizGoodSell(@Valid @RequestBody BizGoodSellVo bizGoodSellVo) {
        try {
            Long memberId = tokenService.getLoginUser().getUserid();
            bizGoodSellVo.setMemberIdP(memberId);

            int rs = bizGoodSellService.addBizGoodSell(bizGoodSellVo);
            return AjaxResult.success(rs);
        } catch (Exception e) {
            return AjaxResult.error("发布供应商品异常.", e);
        }
    }


    /**
     * 供应商品修改接口
     *
     * @return 结果
     */
    @ApiOperation(value = "修改供应商品")
    @PostMapping("modifyBizGoodSell")
    public AjaxResult modifyBizGoodSell(@Valid @RequestBody BizGoodSellVo bizGoodSellVo) {
        try {
            int rs = bizGoodSellService.modifyBizGoodSell(bizGoodSellVo);
            return AjaxResult.success(rs);
        } catch (Exception e) {
            return AjaxResult.error("修改供应商品异常.", e);
        }
    }


    @ApiOperation(value = "查询我要卖列表")
    @PostMapping(value = "selectBizGoodSellForPage")
    public R<PageResult<BizGoodSellDto>> selectBizGoodSellForPage(@Valid @RequestBody BizGoodSellParaVo bizGoodSellParaVo) {
        PageHelper.startPage(bizGoodSellParaVo.getPageNum(), bizGoodSellParaVo.getPageSize(),"top_flag" + (StringUtils.isBlank(bizGoodSellParaVo.getOrderBy())?"": "," + bizGoodSellParaVo.getOrderBy()));

        List<BizGoodSellDto> bizGoodSellDtoList = bizGoodSellService.selectBizGoodSellForPage(bizGoodSellParaVo);
        PageInfo<BizGoodSellDto> pageInfo = new PageInfo<>(bizGoodSellDtoList);

        // 大小额
        List<BizGoodSellDto> bizGoodSellDtoListListResult = bizGoodSellService.changeBizGoodSellDtoList(bizGoodSellDtoList,bizGoodSellParaVo);

        PageResult<BizGoodSellDto> pageResult = new PageResult<BizGoodSellDto>();
        pageResult.setRows(bizGoodSellDtoListListResult);
//        pageResult.setRows(bizGoodSellDtoList);
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setHasNext(pageInfo.isHasNextPage());
        pageResult.setHasPre(pageInfo.isHasPreviousPage());

        return R.ok(pageResult);
    }

    @ApiOperation(value = "查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "infoId", value = "商品信息ID", required = true)
    })
    @GetMapping(value = "selectBizGoodSellByInfoId")
    public R<BizGoodSellDto> selectBizGoodSellByInfoId(Long infoId) {
        BizGoodSellDto bizGoodSellDto = bizGoodSellService.selectBizGoodSellByInfoId(infoId);
        return R.ok(bizGoodSellDto);
    }


}
