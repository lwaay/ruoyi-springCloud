package com.sinonc.orders.controller.api;

import com.sinonc.base.api.RemoteBaseFarmService;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.domain.Cert;
import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.domain.OrderItem;
import com.sinonc.orders.service.CertService;
import com.sinonc.orders.service.GoodsService;
import com.sinonc.orders.service.OrderItemService;
import com.sinonc.orders.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Api(tags = "我的橙树接口")
@RequestMapping("/api/myTree")
@RestController
@Slf4j
public class ApiMyTreeController {

    @Autowired
    private CertService certService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private RemoteBaseFarmService baseFarmService;

    @GetMapping("info")
    @ApiOperation("获取我的橙树信息列表")
    public AjaxResult baseInfo() {

        Long memberId = SecurityUtils.getUserId();
        log.error("用户id：{}",memberId);
        List<Map<String, Object>> data = new LinkedList<>();

        Cert cert = new Cert();
        cert.setUserId(memberId);
        //查找类型为认养证书
        cert.setCertType(1);
        List<Cert> certs = certService.listCert(cert);

        //系统当前时间
        Date date = new Date();

        for (Cert c : certs) {

            //计算认养结束时间
            Date endDay = DateUtils.addDays(c.getCreateTime(), 365);

            int lastDay = 0;
            int useDay = 365;
            //认养状态，0，正常，1，已过期
            int status = 0;

            //判断认养时间是否已经过期
            if (endDay.getTime() >= date.getTime()) {
                //剩余天数
                lastDay = this.comparePastDate(date, endDay);
                //已认养天数
                useDay = this.comparePastDateFloor(c.getCreateTime(), date);
                status = 1;
            }

            Map<String, Object> map = new HashMap<>();
            map.put("orderId", c.getOrderId());
            log.error("订单id：{}",c.getOrderId());
            OrderItem orderItem = orderItemService.selectOrderItemByOrderId(c.getOrderId());
            Goods goods = goodsService.getGoodsById(orderItem.getGoodsIdP());
            if(goods == null){
                map.put("farmId", null);
                map.put("farmImg", null);
                map.put("goodsUrl", null);
            }else{
                map.put("farmId", goods.getFarmId());
                BaseFarm baseFarm = baseFarmService.getFarmInfo(goods.getFarmId());
                String imgList = baseFarm.getPictures();
                if(!StringUtils.isEmpty(imgList)){
                    String[] images = imgList.split(",", -1);
                    map.put("farmImg", images[0]);
                }else {
                    map.put("farmImg", null);
                }
                map.put("goodsUrl", goods.getImage());
                map.put("goodsName", goods.getName());
            }
            map.put("servNo", c.getServNo());
            map.put("memberId", c.getUserId());
            map.put("adoptTime", c.getCreateTime());
            map.put("specsName", c.getSpecsName());
            map.put("lastDay", lastDay);
            map.put("useDay", useDay);
            map.put("status", status);
            map.put("certUrl", c.getImgUrl());

            data.add(map);
        }

        return AjaxResult.success(data);
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param oldDate
     * @param nowDate
     * @return
     */
    private int comparePastDate(Date oldDate, Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        int day = 0;

        calendar.setTime(oldDate);
        Long oTime = calendar.getTimeInMillis();

        calendar.setTime(nowDate);
        Long nTime = calendar.getTimeInMillis();

        day = (int) Math.ceil((nTime - oTime) / (3600F * 1000 * 24));
        return day;
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param oldDate
     * @param nowDate
     * @return
     */
    private int comparePastDateFloor(Date oldDate, Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        int day = 0;

        calendar.setTime(oldDate);
        Long oTime = calendar.getTimeInMillis();

        calendar.setTime(nowDate);
        Long nTime = calendar.getTimeInMillis();

        day = (int) Math.floor((nTime - oTime) / (3600F * 1000 * 24));
        return day;
    }


}
