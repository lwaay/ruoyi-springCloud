package com.sinonc.orders.controller.api;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.service.AttentionService;
import com.sinonc.orders.service.IShopService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺关注接口
 * @author Administrator
 * @anthor wang
 */
@Api(tags = {"我的关注","关注店铺"})
@RestController
@RequestMapping("/api/attention/shop")
@Slf4j
public class ApiAttentionController extends BaseController {
    
    @Autowired
    private IShopService shopService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private TokenService tokenService;

    @ApiOperation("用户收藏店铺接口")
    @PutMapping()
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "shopId", value = "店铺ID", required = true, dataType = "string", paramType = "query")
    })
    public AjaxResult attention(@RequestBody String shopId){
        //获取用户id
        Long memberId = tokenService.getLoginUser().getWxUserConsume().getId();
        log.info("关注用户id:{}",memberId);
        if (!StringUtils.isNotEmpty(shopId)){
            return AjaxResult.error("店铺不能为空");
        }
        Shop shopById = shopService.selectShopById(Long.parseLong(shopId));
        if (memberId!=null&&shopById!=null){
            int like = attentionService.like(memberId, shopId);
            if (like==200){
                return AjaxResult.success("关注成功",like);
            }
            if (like==100){
                return AjaxResult.error("你已经关注过了",like);
            }else {
                return AjaxResult.error("失败！异常！");
            }
        }
        return AjaxResult.error("用户或者店铺不存在");
    }

    @ApiOperation("用户收藏店铺接口")
    @DeleteMapping()
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "shopId", value = "店铺ID", required = true, dataType = "string", paramType = "query")
    })
    public AjaxResult disAttention(@RequestBody String shopId){
        //获取用户id
        Long memberId = tokenService.getLoginUser().getWxUserConsume().getId();
        log.info("取关用户id:{}",memberId);
        if (!StringUtils.isNotEmpty(shopId)){
            return AjaxResult.error("店铺不能为空");
        }
        Shop shopById = shopService.selectShopById(Long.parseLong(shopId));
        if (memberId!=null&&shopById!=null){
            int like = attentionService.dislike(memberId, shopId);
            if (like==200){
                return AjaxResult.success("取消关注成功",like);
            }
            if (like==100){
                return AjaxResult.error("你已经取消关注该店铺了",like);
            }else {
                return AjaxResult.error("失败！异常！");
            }
        }
        return AjaxResult.error("用户或者店铺不存在");
    }

    @ApiOperation("用户查询收藏店铺列表")
    @GetMapping()
    public AjaxResult attentionList(int pageSize, int pageNum){
        Long memberId = tokenService.getLoginUser().getWxUserConsume().getId();
        Page<Shop> page = new Page();
        if(StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)){
            page = PageHelper.startPage(pageNum, pageSize, "create_time desc");
        }else{
            PageHelper.orderBy("create_time desc");
        }
        List shops = attentionService.selectLikeListByMemberId(memberId);
        PageInfo<Shop> shopPageInfo = page.toPageInfo();
//        shopPageInfo.setList(shops);
        Map<String, Object> ret = new HashMap<>(3);
        ret.put("rows", shops);
        ret.put("total", shopPageInfo.getTotal());
        ret.put("hasNext", shopPageInfo.isHasNextPage());
        return AjaxResult.success(ret);
    }
}
