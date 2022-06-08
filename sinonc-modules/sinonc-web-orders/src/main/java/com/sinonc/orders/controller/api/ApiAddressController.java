package com.sinonc.orders.controller.api;

import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.Address;
import com.sinonc.orders.domain.YxSystemCity;
import com.sinonc.orders.service.AddressService;
import com.sinonc.orders.service.IYxSystemCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 收货地址API接口
 */
@Api(tags = "收货地址API接口")
@RestController
@RequestMapping("api/address")
public class ApiAddressController extends BaseController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private IYxSystemCityService yxSystemCityService;

    /**
     * 根据会员ID获取会员的收货地址列表
     *
     * @return 会员地址列表
     */
    @ApiOperation(value = "查询用户地址列表")
    @GetMapping(value = "list")
    public AjaxResult listAddress() {
        //从Token中获取MemberId
        Long memberId = SecurityUtils.getUserId();
        Address address = new Address();
        address.setDelFlag(0);
        address.setMemberIdP(memberId);
        List<Address> addresses = addressService.listAddress(address);
        return AjaxResult.success(addresses);
    }

    /**
     * 新增收货地址
     *
     * @param address 收货地址
     * @return 结果
     */
    @ApiOperation(value = "添加收货地址接口")
    @PostMapping(value = "add")
    public AjaxResult addAddress(@RequestBody Address address) {
        //从Token中获取MemberId
        Long memberId = SecurityUtils.getUserId();
        address.setMemberIdP(memberId);
        return toAjax(addressService.addAddress(address));
    }

    /**
     * 获取收货地址详情
     *
     * @param addressId 收货地址ID
     * @return 收货地址
     */
    @ApiOperation(value = "获取收货地址详情接口")
    @GetMapping("get/{addressId}")
    public AjaxResult getAddress(@PathVariable Long addressId) {
        Address address = addressService.getAddressById(addressId);
        return AjaxResult.success(address);
    }


    /**
     * 更新收货地址
     *
     * @param address 收货地址
     * @return 处理结果
     */
    @ApiOperation(value = "更新收货地址接口")
    @PostMapping(value = "update")
    public AjaxResult updateAddress(@RequestBody Address address) {
        //从Token中获取MemberId
        Long memberId = SecurityUtils.getUserId();
        address.setMemberIdP(memberId);
        return toAjax(addressService.updateAddress(address));
    }

    @ApiOperation(value = "删除收货地址")
    @PostMapping("/delete/{ids}")
    public AjaxResult deleteAddress(@PathVariable("ids") String ids) {
        return toAjax(addressService.deleteAddressByIds(ids));
    }

    @ApiOperation(value = "获取所有省份信息")
    @GetMapping("/province")
    public AjaxResult privince(){
        List<YxSystemCity> list = yxSystemCityService.selectYxSystemCityList(YxSystemCity.builder().level(0).build());
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "获取子类城市信息")
    @GetMapping("/getChildCity/{parentId}")
    public AjaxResult getChildCity(@PathVariable("parentId")Integer parentId){
        List<YxSystemCity> childs = yxSystemCityService.selectYxSystemCityList(YxSystemCity.builder().parentId(parentId).build());
        return AjaxResult.success(childs);
    }

    @ApiOperation(value = "获取用户默认地址")
    @GetMapping("/getDefaults")
    public AjaxResult getDefaults(){
        if (SecurityUtils.getUserId()==null ||SecurityUtils.getUserId()<1L){
            return AjaxResult.error("获取用户信息失败,无法获取默认地址");
        }
        return AjaxResult.success(addressService.getDefaults(SecurityUtils.getUserId()));
    }
}
