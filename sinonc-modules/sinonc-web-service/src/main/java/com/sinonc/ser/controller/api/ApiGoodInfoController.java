package com.sinonc.ser.controller.api;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.RemoteFruitService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.domain.FruiterInfo;
import com.sinonc.common.core.domain.PageResult;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.ser.dto.BizGoodBuyDto;
import com.sinonc.ser.service.IBizGoodBuyService;
import com.sinonc.ser.vo.BizGoodBuyParaVo;
import com.sinonc.service.domain.BizGoodInfo;
import com.sinonc.ser.service.IBizGoodInfoService;
import com.sinonc.system.api.domain.SysDictData;
import com.sinonc.system.api.domain.WxUser;
import com.sinonc.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author zhangxinlong
 * @apiNote 商品采购接口
 * @date 2020/8/1  15:37
 */
@Api(tags = "主页商品展示API")
@RestController
@RequestMapping("/api/biz/goodInfo")
public class ApiGoodInfoController extends BaseController {

//    @Autowired
//    private IGuildhallService guildhallService;
//
//    @Autowired
//    private RedisUtil redisUtil;

    @Autowired
    private IBizGoodInfoService bizGoodInfoService;

    @Autowired
    private IBizGoodBuyService bizGoodBuyService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private RemoteBaseFarmService remoteBaseFarmService;
    @Autowired
    private RemoteFruitService remoteFruitService;

//    /**
//     * 主页商会推荐商品
//     * @return 结果
//     */
//    @ApiOperation(value = "主页商会推荐商品")
//    @GetMapping("/index")
//    public AjaxResult index(){
//        List<Guildhall> guildhalls = redisUtil.lGet(redisUtil.getProfileKey("guildhalls"),0,-1,Guildhall.class);
//        if (guildhalls == null || guildhalls.isEmpty()){
//            guildhalls = guildhallService.orderGuildhallWithLinksList(new Guildhall());
//            redisUtil.del(redisUtil.getProfileKey("guildhalls"));
//            redisUtil.lSet(redisUtil.getProfileKey("guildhalls"),guildhalls,4, TimeUnit.HOURS);
//        }
//        return AjaxResult.success(guildhalls);
//    }

//    @ApiOperation(value = "供应商商品列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "supplyMemberId", value = "供应商id", required = true),
//    })
//    @GetMapping(value = "listGoodInfoBySupply")
//    public R<List<BizGoodInfo>> addmemberMessage(Long supplyMemberId) {
//        if (supplyMemberId ==null || supplyMemberId<1L) {
//            return R.fail("获取供应商信息失败");
//        }
//        List<BizGoodInfo> list = bizGoodInfoService.listSupplyGoodsInfoByMember(supplyMemberId);
//        if (list == null || list.isEmpty()) {
//            return R.fail("获取供应商售卖商品失败,或供应商没有上架商品");
//        }
//        return R.ok(list);
//    }

    /**
     * 获取推荐求购信息
     * @return
     */
    @GetMapping("recommend")
    public R<PageResult<BizGoodBuyDto>> list(){
        startPage();
        PageHelper.orderBy("top_flag");
        Set<String> relatedMangoType = getRelatedMangoType();
        BizGoodBuyParaVo bizGoodBuyParaVo = new BizGoodBuyParaVo();
        bizGoodBuyParaVo.setBreeds(StringUtils.join(relatedMangoType,","));
        List<BizGoodBuyDto> bizGoodBuyDtoList = bizGoodBuyService.selectBizGoodBuyForPage(bizGoodBuyParaVo);
        if(CollectionUtils.isEmpty(bizGoodBuyDtoList)){
            startPage();
            PageHelper.orderBy("top_flag,issue_time desc");
            bizGoodBuyDtoList = bizGoodBuyService.selectBizGoodBuyForPage(new BizGoodBuyParaVo());
        }
        PageInfo<BizGoodBuyDto> pageInfo = new PageInfo<>(bizGoodBuyDtoList);
        PageResult<BizGoodBuyDto> pageResult = new PageResult<BizGoodBuyDto>();
        pageResult.setRows(bizGoodBuyDtoList);
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setHasNext(pageInfo.isHasNextPage());
        pageResult.setHasPre(pageInfo.isHasPreviousPage());
        return R.ok(pageResult);
    }

    /**
     * 获取个人关联芒果品类
     * @return
     */
    private Set<String> getRelatedMangoType(){
        Set<String> types = new HashSet<>();
        LoginUser loginUser = tokenService.getLoginUser();
        if (!Optional.ofNullable(loginUser).isPresent()){
            throw new BusinessException("用户未登录或用户信息错误");
        }
        WxUser wxUser = loginUser.getWxUser();
        if (!Optional.ofNullable(wxUser).isPresent()){
            throw new BusinessException("用户未登录或用户信息错误");
        }
        if(StringUtils.isNotBlank(wxUser.getEntityId())){
            BaseFarm baseFarm = new BaseFarm();
            baseFarm.setEntityId(Long.valueOf(wxUser.getEntityId()));
            List<BaseFarm> baseFarmList = remoteBaseFarmService.list(baseFarm).getData();
            if(!CollectionUtils.isEmpty(baseFarmList)){
                baseFarmList.forEach( base ->{
                    FruiterInfo fruit= new FruiterInfo();
                    fruit.setOrchId(base.getFarmId());
                    List<FruiterInfo> fruitInfoList = remoteFruitService.listFruit(fruit).getData();
                    //将类别放入set
                    if(!CollectionUtils.isEmpty(fruitInfoList)){
                        fruitInfoList.forEach( info -> {
                            types.add(info.getFruType());
                        });
                    }
                });
            }
        }
        return types;
    }
}
