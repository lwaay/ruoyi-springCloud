package com.sinonc.orders.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.common.OrderConstant;
import com.sinonc.orders.delaytask.DelayTask;
import com.sinonc.orders.delaytask.DelayTaskServiceImpl;
import com.sinonc.orders.delaytask.TaskTypeConstants;
import com.sinonc.orders.domain.Express;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.mapper.ExpressMapper;
import com.sinonc.orders.mapper.OrderMapper;
import com.sinonc.orders.mapper.ShopMapper;
import com.sinonc.orders.service.ExpressService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 物流 服务层实现
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Service
public class ExpressServiceImpl implements ExpressService {

    @Resource
    private ExpressMapper expressMapper;

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private DelayTaskServiceImpl delayTaskService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private ShopMapper shopMapper;
    @Resource
    private RemoteWxUserService wxUserService;
    /**
     * 查询物流信息
     *
     * @param expressId 物流ID
     * @return 物流信息
     */
    @Override
    public Express getExpressById(Long expressId) {
        return expressMapper.selectExpressById(expressId);
    }

    /**
     * 查询物流列表
     *
     * @param express 物流信息
     * @return 物流集合
     */
    @Override
//    @UserDataScope(column = "shop_id_p")
    public List<Express> listExpress(Express express) {
        List<Express> expresses = expressMapper.selectExpressList(express);
        if(StringUtils.isNotEmpty(expresses)){
            for (Express express1 : expresses) {
                Shop shop = shopMapper.selectShopById(express1.getShopIdP());
                if (shop!=null){
                    express1.setShopName(shop.getShopName());
                }
                WxUser wxUser = wxUserService.getWxUserByMemberId(express1.getMemberIdP()).getData();
                if (wxUser!=null){
                    express1.setMemberName(wxUser.getName());
                }
            }
        }
        return expresses;
    }

    /**
     * 新增物流
     *
     * @param express 物流信息
     * @return 结果
     */
    @Override
    @Transactional
    public int addExpress(Express express) {

        Date date = new Date();

        express.setCreateTime(date);
        express.setCreateBy(SecurityUtils.getUsername());
//            //添加用户id
//        Order order1 = orderMapper.selectOrderById(express.getOrderIdP());
//        if (order1!=null){
//            express.setMemberIdP(order1.getMemberIdP());
//        }
        //写入物流信息
        Express exist = expressMapper.selectExpressByOrderId(express.getOrderIdP());
        int rowsExp = 0;
        if(ObjectUtil.isNotEmpty(exist)){
            rowsExp = expressMapper.updateExpressByOrderId(express);
        }else{
            rowsExp = expressMapper.insertExpress(express);
        }


        Order order = new Order();
        //设置默认自动收货时间为十天
        order.setAutoConfirmTime(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 10)));
        order.setOrderId(express.getOrderIdP());
        order.setExpressIdP(express.getExpressId());
        order.setUpdateTime(date);
        order.setExpressTime(date);
        order.setOrderStatus(OrderConstant.STATUS_WAITING_FOR_CONFIRMATION);
        int rowsOrder = orderMapper.updateOrder(order);

        if (rowsExp == 0 || rowsOrder == 0) {
            throw new RuntimeException("添加物流信息失败");
        }

        //添加定时收货
        DelayTask task = DelayTask.getInstance(TaskTypeConstants.AUTO_CONFIRM_TASK, order.getAutoConfirmTime().getTime(), date.getTime(), order);
        delayTaskService.addTask(task);

        return rowsExp;
    }

    /**
     * 修改物流
     *
     * @param express 物流信息
     * @return 结果
     */
    @Override
    public int updateExpress(Express express) {
        return expressMapper.updateExpress(express);
    }

    /**
     * 删除物流对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpressByIds(String ids) {
        return expressMapper.deleteExpressByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询物流详情
     *
     * @param expressNo 物流单号
     * @return
     */
    @Override
    public Object getExpressDetail(String expressNo) {

        Map<String, String> headers = new HashMap<>();

        headers.put(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        headers.put(HttpHeaders.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        headers.put(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate");
        headers.put(HttpHeaders.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9");
        headers.put(HttpHeaders.CONNECTION, "keep-alive");
        headers.put(HttpHeaders.HOST, "www.baidu.com");
        headers.put("Upgrade-Insecure-Requests", "1");

        try {

            String BAIDUID = redisTemplate.opsForValue().get("EXP_COOKIE");

            if (BAIDUID == null) {
                Connection.Response execute = Jsoup.connect("https://www.baidu.com/").headers(headers).execute();
                BAIDUID = execute.cookie("BAIDUID");
                redisTemplate.opsForValue().set("EXP_COOKIE", BAIDUID, 7L, TimeUnit.DAYS);
            }

            Document document = Jsoup.connect("https://sp0.baidu.com/9_Q4sjW91Qh3otqbppnN2DJv/pae/channel/data/asyncqury?appid=4001&nu=" + expressNo + "&_=" + new Date().getTime()).cookie("BAIDUID", BAIDUID).ignoreContentType(true).get();
            String body = document.body().text();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(body);

            return jsonNode.get("data").get("info");
        } catch (IOException e) {
            throw new RuntimeException("获取物流详情信息失败");
        }
    }

    @Override
    public Express getExpressByOrderId(Long orderId) {
        return expressMapper.selectExpressByOrderId(orderId);
    }

}
