package com.sinonc.ser.controller.api;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.base.api.RemoteAreaCodeService;
import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.common.core.domain.PageResult;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.ser.dto.BizGoodBuyDto;
import com.sinonc.ser.service.IBizGoodBuyService;
import com.sinonc.ser.service.IBizGoodInfoService;
import com.sinonc.ser.vo.BizGoodBuyParaVo;
import com.sinonc.ser.vo.BizGoodBuyVo;
import com.sinonc.system.api.domain.WxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhangxinlong
 * @apiNote 商品采购接口
 * @date 2020/8/1  15:37
 */
@Api(tags = "商品采购API")
@RestController
@RequestMapping("api/biz/goodbuy")
public class ApiGoodBuyController extends BaseController {

    @Autowired
    private IBizGoodBuyService bizGoodBuyService;

    @Autowired
    private RemoteAreaCodeService areaCodeService;

    @Autowired
    private IBizGoodInfoService bizGoodInfoService;

    @Autowired
    private TokenService tokenService;

    /**
     * 采购商品发布接口
     *
     * @return 结果
     */
    @ApiOperation(value = "发布采购商品")
    @ApiImplicitParam(name = "token", value = "身份令牌", dataType = "string", paramType = "header", required = true)
    @PostMapping("addBizGoodBuy")
    public AjaxResult addBizGoodBuy(@Valid @RequestBody BizGoodBuyVo bizGoodBuyVo) {
        try {
//            Long memberId = tokenService.getLoginUser().getUserid();
            WxUser wxUser = tokenService.getLoginUser().getWxUser();
            if(!ObjectUtils.isEmpty(wxUser)){
                bizGoodBuyVo.setMemberIdP(wxUser.getId());
                bizGoodBuyVo.setPhone(wxUser.getPhone());
            }else{
                return AjaxResult.error("用户信息异常");
            }
            int rs = bizGoodBuyService.addBizGoodBuy(bizGoodBuyVo);
            return AjaxResult.success(rs);
        } catch (NullPointerException e) {
            return AjaxResult.error("请先登录.");
        } catch (Exception e) {
            return AjaxResult.error("发布采购商品异常.", e);
        }
    }


    /**
     * 采购商品修改接口
     *
     * @return 结果
     */
    @ApiOperation(value = "修改采购商品信息")
    @ApiImplicitParam(name = "token", value = "身份令牌", dataType = "string", paramType = "header", required = true)
    @PostMapping("modifyBizGoodBuy")
    public AjaxResult modifyBizGoodBuy(@Valid @RequestBody BizGoodBuyVo bizGoodBuyVo) {
        try {
            int rs = bizGoodBuyService.modifyBizGoodBuy(bizGoodBuyVo);
            return AjaxResult.success(rs);
        } catch (Exception e) {
            return AjaxResult.error("修改采购商品信息异常.", e);
        }
    }


    @ApiOperation(value = "查询我要买列表")
    @ApiImplicitParam(name = "token", value = "身份令牌", dataType = "string", paramType = "header", required = true)
    @PostMapping(value = "selectBizGoodBuyForPage")
    public R<PageResult<BizGoodBuyDto>> selectBizGoodBuyForPage(@Valid @RequestBody BizGoodBuyParaVo bizGoodBuyParaVo) {
        PageHelper.startPage(bizGoodBuyParaVo.getPageNum(), bizGoodBuyParaVo.getPageSize(),"top_flag" + (StringUtils.isBlank(bizGoodBuyParaVo.getOrderBy())?"": "," + bizGoodBuyParaVo.getOrderBy()));
        List<BizGoodBuyDto> bizGoodBuyDtoList = bizGoodBuyService.selectBizGoodBuyForPage(bizGoodBuyParaVo);
        PageInfo<BizGoodBuyDto> pageInfo = new PageInfo<>(bizGoodBuyDtoList);
        // 置顶
        List<BizGoodBuyDto> bizGoodBuyDtoListResult = bizGoodBuyService.changeBizGoodBuyDtoList(bizGoodBuyDtoList);
        PageResult<BizGoodBuyDto> pageResult = new PageResult<BizGoodBuyDto>();
        pageResult.setRows(bizGoodBuyDtoListResult);
//        pageResult.setRows(bizGoodBuyDtoList);
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setHasNext(pageInfo.isHasNextPage());
        pageResult.setHasPre(pageInfo.isHasPreviousPage());

        return R.ok(pageResult);
    }

    @ApiOperation(value = "查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "infoId", value = "商品信息ID", required = true)
    })
    @GetMapping(value = "selectBizGoodBuyByInfoId")
    public R<BizGoodBuyDto> selectBizGoodBuyByInfoId(Long infoId) {
        BizGoodBuyDto bizGoodBuyDto = bizGoodBuyService.selectBizGoodBuyByInfoId(infoId);
        if (bizGoodBuyDto != null) {
            return R.ok(bizGoodBuyDto);
        } else {
            return R.fail(null, "采购商品不存在，infoId:" + infoId);
        }

    }

    @ApiOperation(value = "根据县区名称查询新行政区划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaName", value = "区县名称", required = true)
    })
    @GetMapping(value = "selectAreaCodeByName")
    public R<AreaCode> selectAreaCodeByName(String areaName) {
        AreaCode paraAreaCode = new AreaCode();
        paraAreaCode.setName(areaName);
        List<AreaCode> areaCode = areaCodeService.list(paraAreaCode).getData();
        AreaCode rsAreaCode = null;
        if (areaCode != null && areaCode.size() > 0) {
            rsAreaCode = areaCode.get(0);
            rsAreaCode.setCode(rsAreaCode.getCode() / 1000000);
            rsAreaCode.setPcode(rsAreaCode.getPcode() / 1000000);
        }

        if (rsAreaCode != null) {
            return R.ok(rsAreaCode);
        } else {
            return R.fail(null, "县区不存在，areaName:" + areaName);
        }
    }

    /**
     * 删除商品信息接口
     *
     * @return 结果
     */
    @ApiOperation(value = "删除商品信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", dataType = "string", paramType = "header", required = true),
            @ApiImplicitParam(name = "goodInfoId", value = "商品信息ID", required = true)
    })
    @GetMapping("removeGoodInfo")
    public AjaxResult removeGoodInfo(String goodInfoId) {
        try {
            // 删除商品相关信息，供应、需求、规格、商品信息
            int rs = bizGoodInfoService.deleteBizGoodInfoByIds(goodInfoId);
            return AjaxResult.success(rs);
        } catch (Exception e) {
            return AjaxResult.error("删除商品信息异常.", e);
        }
    }

}
