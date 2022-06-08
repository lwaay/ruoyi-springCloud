package com.sinonc.orders.controller.api;

import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.PageDataInfo;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.payment.notify.PayMessage;
import com.sinonc.common.payment.notify.PayObserver;
import com.sinonc.common.security.annotation.PreAuthorize;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.dto.OrderDto;
import com.sinonc.orders.dto.SendDto;
import com.sinonc.orders.mapper.OrderItemMapper;
import com.sinonc.orders.mapper.OrderMapper;
import com.sinonc.orders.service.*;
import com.sinonc.orders.vo.FareVo;
import com.sinonc.orders.vo.GoodsVo;
import com.sinonc.orders.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 订单API接口工程
 */
@Api(tags = "订单API接口")
@RestController
@RequestMapping("api/order")
@Slf4j
public class ApiOrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private ExpressService expressService;

    /**
     * 创建订单
     *
     * @param orderVO 订单Vo对象
     * @return 创建成功的订单信息
     */
    @ApiOperation(value = "创建订单接口")
    @PostMapping(value = "create")
    public AjaxResult createOrder(@RequestBody @Valid OrderVO<GoodsVo> orderVO, BindingResult result) {
        if (result.hasErrors()) {
            return AjaxResult.error(406, result.getFieldError().getDefaultMessage());
        }
        return AjaxResult.success(orderService.createOrder(orderVO));
    }

    /**
     * 查询会员的所有订单列表信息
     *
     * @return 订单信息列表
     */
    @PostMapping("/listOrder")
    public PageDataInfo list(@RequestBody OrderDto odOrder) {
        PageHelper.startPage(odOrder.getPageNum() == null ? 1 : odOrder.getPageNum(), odOrder.getPageSize() == null ? 10 : odOrder.getPageSize());
        List<OrderVO> list = orderService.listOrderVO(odOrder);
        return getPageTable(list);
    }


    /**
     * 查询会员的所有订单列表信息
     *
     * @return 订单信息列表
     */
    @ApiOperation(value = "查询会员的所有订单列表")
    @PostMapping(value = "list")
    public PageDataInfo listOrder(@RequestBody Order order) {
        Long memberId = SecurityUtils.getUserId();
        order.setMemberIdP(memberId);
        order.setDelFlag(0);
        PageHelper.startPage(order.getPageNum() == null ? 1 : order.getPageNum(), order.getPageSize() == null ? 10 : order.getPageSize());
        List<OrderVO> list = orderService.listOrderVO(order);
        return getPageTable(list);
    }

    @ApiOperation(value = "查询会员的各状态订单数量")
    @GetMapping(value = "statusCount")
    public AjaxResult listStatusCount() {
        Order order = new Order();
        order.setMemberIdP(SecurityUtils.getUserId());
        return AjaxResult.success(orderService.getOrderStatusCount(order));
    }

    @ApiOperation(value = "查询店铺各状态订单数量")
    @GetMapping(value = "listShopStatusCount")
    public AjaxResult listShopStatusCount(OrderDto orderDto) {
        Shop shop = new Shop();
        shop.setEntityId(orderDto.getEntityId());
        List<Shop> shopList = shopService.selectShopList(shop);
        if (shopList != null && shopList.size() > 0) {
            Shop tempShop = shopList.get(0);
            Order order = new Order();
            order.setShopIdP(tempShop.getShopId());
            return AjaxResult.success(orderService.getOrderStatusCount(order));
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("waitPay", 0);
            map.put("waitExp", 0);
            map.put("waitConfirm", 0);
            map.put("waitRest", 0);
            map.put("all", 0);
            return AjaxResult.success(map);
        }

    }

    /**
     * 查询单个订单查询信息
     *
     * @param orderId 订单ID
     * @return 订单详情
     */
    @ApiOperation("根据订单ID查询订单查询信息")
    @GetMapping(value = "get")
    public AjaxResult getOrderDetail(Long orderId) {
        if (orderId == null) {
            return AjaxResult.error("orderId不能为空");
        }
        return AjaxResult.success(orderService.getOrderVoById(orderId));
    }


    @ApiOperation("根据订单ID取消订单接口")
    @PostMapping(value = "cancel")
    public AjaxResult cancelOrder(Long orderId) {
        if (orderId == null) {
            return AjaxResult.error("orderId不能为空");
        }
        Long memberId = SecurityUtils.getUserId();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setMemberIdP(memberId);
        return AjaxResult.success(orderService.cancelOrder(order));
    }

    @ApiOperation("根据订单ID确认收货接口")
    @PostMapping(value = "confirm")
    public AjaxResult confirmOrder(Long orderId) {
        if (orderId == null) {
            return AjaxResult.error("orderId不能为空");
        }
        Long memberId = SecurityUtils.getUserId();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setMemberIdP(memberId);
        return AjaxResult.success(orderService.confirmOrder(order));
    }

    @ApiOperation("根据订单计算运费接口")
    @PostMapping(value = "fareCount")
    public AjaxResult fareCount(@RequestBody FareVo fareVo) {
        try {
            if (!Optional.ofNullable(fareVo).isPresent()) {
                return AjaxResult.error("获取基础参数失败，无法计算订单运费");
            }
            if (CollectionUtils.isEmpty(fareVo.getItems())) {
                return AjaxResult.error("获取商品明细失败，无法计算订单运费");
            }
            Address address = addressService.getAddressById(fareVo.getAddressId());
            if (!Optional.ofNullable(address).isPresent()) {
                return AjaxResult.error("获取收货地址失败，无法计算订单运费");
            }
            fareVo.setAddress(address);
            BigDecimal fare = orderService.getFare(fareVo);
            return AjaxResult.success(fare);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResult.error("计算运费出错");
        }

    }


    @ApiOperation("根据订单计算运费接口")
    @PostMapping(value = "fareCountTwo")
    public AjaxResult fareCountTwo(FareVo fareVo) {
        try {
            if (!Optional.ofNullable(fareVo).isPresent()) {
                return AjaxResult.error("获取基础参数失败，无法计算订单运费");
            }
            if (CollectionUtils.isEmpty(fareVo.getItems())) {
                return AjaxResult.error("获取商品明细失败，无法计算订单运费");
            }
            if (!Optional.ofNullable(fareVo.getAddress()).isPresent()) {
                return AjaxResult.error("获取收货地址失败，无法计算订单运费");
            }
            Address address = addressService.getAddressById(fareVo.getAddressId());
            fareVo.setAddress(address);
            BigDecimal fare = orderService.getFare(fareVo);
            return AjaxResult.success(fare);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return AjaxResult.error("计算运费出错:" + e.getMessage());
        }

    }

    /**
     * 订单发货
     */
    @PostMapping("/send")
    public AjaxResult saveSend(@RequestBody SendDto send) {

        if (!Optional.ofNullable(send).isPresent()) {
            return AjaxResult.error("参数错误");
        }
        try {
            Express express = new Express();
            BeanUtils.copyProperties(send, express);
            return toAjax(expressService.addExpress(express));
        } catch (Exception e) {
            log.error("新增物流信息失败," + e.getMessage());
            return AjaxResult.error("新增物流信息失败：" + e.getMessage());
        }
    }


    @Autowired
    private CertService certService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @PostMapping("/addCert")
    public AjaxResult addCert() {
        Order order = orderMapper.selectOrderByNo("1651981396797251");

        //判断是否是认养订单
        if (order != null && order.getOrderType().equals(0)) {

            //支付成功之后创建认养证书
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderIdP(order.getOrderId());

            List<OrderItem> orderItems = orderItemMapper.selectOrderItemList(orderItem);

            //判断认养数量，如果有多个，则创建多分证书
            for (OrderItem item : orderItems) {
                Cert cert = new Cert();
                cert.setCertType(1);
                cert.setMasterName(order.getReceiver());
                cert.setUserId(order.getMemberIdP());
                cert.setOrderId(order.getOrderId());
                cert.setSpecsName(item.getGoodsSpecsName());
                certService.addCert(cert);
            }
        }
        return AjaxResult.success();

    }

}
