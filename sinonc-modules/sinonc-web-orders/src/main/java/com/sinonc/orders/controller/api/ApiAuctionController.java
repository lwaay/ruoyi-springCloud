package com.sinonc.orders.controller.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.payment.alipay.AliPayService;
import com.sinonc.common.payment.wechat.WechatAppResp;
import com.sinonc.common.payment.wechat.WechatPayService;
import com.sinonc.common.payment.wechat.WechatPayTradeModel;
import com.sinonc.common.payment.wechat.WechatXcxResp;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.consume.api.RemoteWxUserConsumeService;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.expection.TradeException;
import com.sinonc.orders.mapper.AuctionmemberMapper;
import com.sinonc.orders.service.*;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 竞拍接口
 *
 * @author Administrator
 */
@RestController
public class ApiAuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private AuctionmemberService auctionmemberService;

    @Autowired
    private AuctionmemberMapper auctionmemberMapper;

    @Autowired
    private RemoteWxUserConsumeService wxUserConsumeService;

    @Autowired
    private IOdGoodsService goodsService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AuctionBondService auctionBondService;

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private WechatPayService wechatPayService;

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SpecsService specsService;
    @Autowired
    private IShopService shopService;
    @Autowired
    private TokenService tokenService;


    /**
     * 竞拍获得者退押金
     *
     * @return
     */
    @GetMapping(value = "api/auction/tkownauction")
    public AjaxResult tkOwnAuction(Long auctionId) throws Exception{
       Integer row = auctionBondService.tkOwnAuction(auctionId);
       if(row<=0){
           return AjaxResult.error("退款失败!");
       }
        return AjaxResult.successCode(1,"退款成功");
    }



    /**
     * 创建订单后，返回订单数据
     *
     * @return
     */
    @GetMapping(value = "api/auctionbond/selectauctionbondbyid")
    public AjaxResult selectAuctionBondById(Long auctionBondId) {
        Map<String, Object> result = new HashMap<>();
        if (auctionBondId == null) {
            return AjaxResult.error("参数错误");
        }
        AuctionBond auctionBond = auctionBondService.getAuctionBondById(auctionBondId);
        if (auctionBond != null) {
            //根据商品查询竞拍商品
            OdGoods goods = goodsService.selectOdGoodsById(auctionBond.getGoodsId());
            result.put("goodsName", goods.getName() + "(押金)");
            result.put("auctionBond", auctionBond);
        }
        return AjaxResult.success(result);
    }


    /**
     * 查询用户是否交押金
     *
     * @return
     */
    @RequestMapping(value = "api/auction/selectauctionbond")
    public AjaxResult selectApiAuctionBond(Long goodsId) {
        Long memberId = SecurityUtils.getUserId();
        AuctionBond bond = auctionBondService.selectApiAuctionBond(memberId, goodsId, 0);
        if (bond == null) {
            return AjaxResult.error("没交押金");
        }
        return AjaxResult.success(bond);
    }

    /**
     * 交保证金，创建保证金订单
     *
     * @return
     */
    @PostMapping(value = "api/auction/createapiauctionbond")
    public AjaxResult createApiAuctionBond(@RequestBody AuctionBond auctionBond) {
        if (auctionBond.getAuctionId() == null) {
            return AjaxResult.error("参数错误");
        }
        if (auctionBond.getShopId() == null) {
            return AjaxResult.error("参数错误");
        }
        if (auctionBond.getGoodsId() == null) {
            return AjaxResult.error("参数错误");
        }
        //根据商品id与用户查询竞拍订单
        Long memberId = SecurityUtils.getUserId();
        AuctionBond oldAuctionBonded = auctionBondService.selectApiAuctionBond(memberId, auctionBond.getGoodsId(), null);
        if (Optional.ofNullable(oldAuctionBonded).isPresent()) {
            //删除订单
            auctionBondService.deleteAuctionBondByIds(String.valueOf(oldAuctionBonded.getAuctionbondId()));
        }
        auctionBond.setMemberId(memberId);
        return AjaxResult.success(auctionBondService.addAuctionBond(auctionBond));

    }


    /**
     * 根据商品id倒叙查询竞拍记录
     *
     * @return
     */
    @RequestMapping(value = "api/goods/auction/selectapiauctionlistdesc")
    public AjaxResult selectApiAuctionListdesc(Long goodsId, Integer pageNum, Integer pageSize) {
        List<Map<String, Object>> resultlist = new ArrayList<>();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 6;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Auctionmember> list = auctionmemberService.selectApiAuctionListdesc(goodsId);
        if (list.size() <= 0) {
            return AjaxResult.success(resultlist);
        }
        for (Auctionmember auctionmember : list) {
            Map<String, Object> result = new HashMap<>();
            WxUserConsume wxUserConsume = wxUserConsumeService.getUserById(auctionmember.getMemberId()).getData();
            Auction Auction = auctionService.selectAuctionForGoodsId(goodsId);
            String addr = "您暂时没有地址信息哦!";
            //根据用户id查询用户地址。查询第一个
            Address address = new Address();
            address.setDelFlag(0);
            address.setMemberIdP(wxUserConsume.getId());
            List<Address> lista = addressService.listAddress(address);
            if (lista.size() > 0) {
                addr = lista.get(0).getProvince() + lista.get(0).getCity() + lista.get(0).getCounty() + lista.get(0).getAddr();
            }
            result.put("name", wxUserConsume.getName());
            result.put("headImg", wxUserConsume.getHeadimg());
            result.put("addr", addr);
            result.put("price", auctionmember.getNowPrice());
            result.put("time", auctionmember.getCreateTime());
            result.put("beginTime", Auction.getAuctionBegintime().getTime());
            result.put("endTime", Auction.getAuctionEndtime().getTime());
            result.put("isEnd", Auction.getIsEnd());
            resultlist.add(result);
        }
        return AjaxResult.success(resultlist);
    }


    /**
     * 查询当前出价最大
     *
     * @return
     */
    @RequestMapping(value = "api/goods/auction/selectapiauctionlistmax")
    public AjaxResult selectApiAuctionlistMax(Long goodsId) {

        Map<String, Object> result = new HashMap<>();
        Auctionmember auctionmember = auctionmemberService.listAuctionMerberMax(goodsId);
        if (auctionmember == null) {
            return AjaxResult.success(result);
        }
        WxUserConsume member = wxUserConsumeService.getUserById(auctionmember.getMemberId()).getData();
        Auction Auction = auctionService.selectAuctionForGoodsId(goodsId);
        String addr = "您暂时没有地址信息哦!";
        //根据用户id查询用户地址。查询第一个
        Address address = new Address();
        address.setDelFlag(0);
        address.setMemberIdP(SecurityUtils.getUserId());
        List<Address> list = addressService.listAddress(address);
        if (list.size() > 0) {
            addr = list.get(0).getProvince() + list.get(0).getCity() + list.get(0).getCounty() + list.get(0).getAddr();
        }
        result.put("name", member.getName());
        result.put("addr", addr);
        result.put("price", auctionmember.getNowPrice());
        result.put("time", auctionmember.getCreateTime());
        result.put("beginTime", Auction.getAuctionBegintime().getTime());
        result.put("endTime", Auction.getAuctionEndtime().getTime());
        result.put("isEnd", Auction.getIsEnd());
        return AjaxResult.success(result);
    }

    /**
     * 查询我得竞拍列表
     *
     * @return
     */
    @GetMapping(value = "api/auction/myauction")
    public AjaxResult selectMyAuction() {

        //从Token中获取MemberId
        Long memberId = SecurityUtils.getUserId();
        List<Map<String, Object>> list = auctionService.selectMyAuction(memberId);
        if (list.size() > 0) {
            for (Map<String, Object> map : list) {
                    Long goodsId = Long.parseLong(map.get("goodsId").toString());
                    //根据id查询竞拍活动
                    Auction auc = auctionService.selectAuctionForGoodsId(goodsId);
                    if(auc != null){
                        map.put("auctionId", auc.getAuctionId());
                    }
                    //根据id查询商品信息
                    OdGoods goods = goodsService.selectOdGoodsById(goodsId);
                    //查询获得者
                    Auctionmember auctionmember = auctionmemberService.listAuctionMerberMax(goodsId);
                    if(auctionmember != null){
                        if(auctionmember.getMemberId() == Long.parseLong(map.get("memberId").toString())){
                            map.put("own", "yes");
                        }else{
                            map.put("own","no");
                        }
                    }
                    //根据商品id查询订单商品关联表
                    OrderItem orderitem =  orderItemService.selectOrderItemByGoodsId(goodsId);
                    if(orderitem != null){
                        //根据orderItem查询订单
                        Order order = orderService.getOrderById(orderitem.getOrderIdP());
                        if(order != null){
                            map.put("hasOrder", 1);
                            map.put("isPay",order.getTradeStatus());
                        }else{
                            map.put("hasOrder", 0);
                            map.put("isPay",0);
                        }
                    }else{
                        map.put("hasOrder", 0);
                        map.put("isPay",0);
                    }
                    //查询是否已经退款
                    AuctionBond auctionbond = auctionBondService.selectApiAuctionBond(memberId,goodsId,null);
                    if(auctionbond != null){
                       if(StringUtils.isEmpty(auctionbond.getRefundNo())){
                           map.put("isRefund",0);
                       }else{
                           map.put("isRefund",1);
                       }
                    }
                    //查询商品重量
                    Specs spec = specsService.getSpecsById(Long.parseLong(goods.getSpecsIds()));
                    if(spec != null){
                        map.put("specName", spec.getSpecsName());
                        if(spec.getPerWeight() != null){
                            map.put("weight",spec.getPerWeight());
                        }else{
                            map.put("weight",0);
                        }
                    }else{
                        map.put("weight",0);
                    }
                    //查询店铺
                    Shop shop = shopService.selectShopById(goods.getShopId());
                    if (Optional.ofNullable(shop).isPresent()){
                        map.put("shopName",shop.getShopName());
                        map.put("shopLogo",shop.getShopLogo());
                    }

                    //根据商品id查询竞拍活动是否结束
                    Auction auction = auctionService.selectAuctionForGoodsId(goodsId);
                    map.put("img", goods.getImage());
                    map.put("name", goods.getName());
                    map.put("number", 1); //库存
                    map.put("shopId", goods.getShopId());
                    map.put("specsId", goods.getSpecsIds());
                    map.put("type", 3); //类型 竞拍商品
                    map.put("isEnd", auction.getIsEnd());

            }
        }
        Collections.reverse(list);
        return AjaxResult.success(list);
    }


    /**
     * 查询竞拍活动列表
     *
     * @return
     */
    @GetMapping(value = "api/goods/auction/selectauctionlist")
    public Object selectApiAuctionlist() {
        List<Map<String,Object>> list = auctionService.listAuctionDesc();
        return AjaxResult.success(list);
    }

    /**
     * 根据商品详情查询竞拍详情
     */
    @RequestMapping(value = "api/goods/auction/selectauctiondetail")
    public AjaxResult selectAuctionDetail(Long goodsId) {
        Map<String, Object> result = new HashMap<>();
        OdGoods goods = goodsService.selectOdGoodsById(goodsId);
        Auction auction = auctionService.selectAuctionForGoodsId(goodsId);
        result.put("shopId", goods.getShopId());
        result.put("farmId", goods.getFarmId());
        result.put("image", goods.getImage());
        result.put("auction", auction);
        return AjaxResult.success(result);
    }

    /**
     * 竞拍接口
     *
     * @return 结果
     */
    @ApiOperation(value = "竞拍接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "身份令牌", dataType = "string", paramType = "header", required = true)
    })
    @GetMapping(value = "api/auction/add")
    public AjaxResult auctionsAdd(Long goodsId, String price) {

        Map<String, Object> result = new HashMap<>();

        //从Token中获取MemberId
        Long memberId = SecurityUtils.getUserId();

        if (goodsId == null) {
            return AjaxResult.error("参数错误");
        }

        if (StringUtils.isEmpty(price)) {
            return AjaxResult.error("参数错误");
        }

        int id = auctionmemberService.auctionsAdd(memberId, goodsId, price);

        if (id == 0) {
            result.put("msg", "竞拍结束");
            return AjaxResult.success(result);
        }

        //根据id查询竞拍记录记录
        Auctionmember auctionmember = auctionmemberMapper.selectAuctionmemberById(Long.parseLong(id + ""));
        //根据用户id查询用户信息
        WxUserConsume member = wxUserConsumeService.getUserById(auctionmember.getMemberId()).getData();
        //根据商品id查询竞拍活动
        Auction auction = auctionService.selectAuctionForGoodsId(goodsId);
        //修改最新出价
        Auction newAuction = new Auction();
        newAuction.setAuctionId(auction.getAuctionId());
        newAuction.setGoodsId(auction.getGoodsId());
        newAuction.setAuctionNowprice(auctionmember.getNowPrice());
        auctionService.updateAuctionPrice(newAuction);

        result.put("name", member.getName());
//        result.put("addr", member.getProvince() + member.getCity());
        result.put("price", auctionmember.getNowPrice());
        result.put("time", auctionmember.getCreateTime());
        result.put("beginTime", auction.getAuctionBegintime().getTime());
        result.put("endTime", auction.getAuctionEndtime().getTime());

        return AjaxResult.success(result);
    }

    /**
     * 创建支付宝支付
     *
     * @return
     */
    @PostMapping("api/auction/alipay/create")
    public AjaxResult createAliPay(Long auctionBondId) {

        if (auctionBondId == null) {
            return AjaxResult.error("参数不正确");
        }

        //查询订单信息
        AuctionBond auctionBond = auctionBondService.getAuctionBondById(auctionBondId);

        if (auctionBond == null) {
            return AjaxResult.error("订单异常");
        }

        //判断订单是否已经被支付了
        if (auctionBond.getPaymentStatus() == 1) {
            throw new TradeException(auctionBond.getAuctionOrderno(), "订单已支付,请勿重复支付！");
        }

        if (auctionBond.getWin() != null) {
            return AjaxResult.error("活动已结束");
        }

        //创建支付宝app支付信息
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setOutTradeNo(auctionBond.getAuctionOrderno());
        model.setTotalAmount(auctionBond.getPrice().toString());
        model.setSubject("竞拍押金：" + auctionBond.getAuctionOrderno());
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setPassbackParams(auctionBond.getShopId().toString());


        try {

            //App支付信息，用于调起支付宝客户端
            String payResponse = aliPayService.createAppPayResponse(model);

            AjaxResult success = AjaxResult.success();
            success.put("data", payResponse);

            return success;

        } catch (AlipayApiException e) {
            return AjaxResult.error(StringUtils.format("支付异常:{} {}", e.getErrCode(), e.getErrMsg()));
        }


    }


    @PostMapping("api/auction/wechat/create")
    public AjaxResult createWechatPay(Long auctionBondId) {

        if (auctionBondId == null) {
            return AjaxResult.error("参数不正确");
        }

        //查询订单信息
        AuctionBond auctionBond = auctionBondService.getAuctionBondById(auctionBondId);

        if (auctionBond == null) {
            return AjaxResult.error("订单异常");
        }

        //判断订单是否已经被支付了
        if (auctionBond.getPaymentStatus() == 1) {
            throw new TradeException(auctionBond.getAuctionOrderno(), "订单已支付,请勿重复支付！");
        }

        if (auctionBond.getWin() != null) {
            return AjaxResult.error("活动已结束");
        }

        WechatPayTradeModel model = new WechatPayTradeModel();
//        model.setBody("竞拍押金：" + auctionBond.getAuctionOrderno());
        model.setBody("订单：10086");
        model.setOut_trade_no(auctionBond.getAuctionOrderno());
        //将金额转换为分
        model.setTotal_fee(String.valueOf(auctionBond.getPrice().multiply(new BigDecimal("100")).intValue()));
        model.setAttach(auctionBond.getShopId().toString());
        model.setOpenId(tokenService.getLoginUser().getWxUserConsume().getOpenid());
        try {
            WechatXcxResp payResponse = wechatPayService.createXcxPayResponse(model);
            return AjaxResult.success(payResponse);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }


    }

}
