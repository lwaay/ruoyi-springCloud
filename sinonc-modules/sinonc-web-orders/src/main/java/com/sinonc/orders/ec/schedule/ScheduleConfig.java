//package com.sinonc.orders.ec.schedule;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.sinonc.common.core.exception.BusinessException;
//import com.sinonc.common.core.utils.DateUtils;
//import com.sinonc.orders.ec.domain.*;
//import com.sinonc.orders.ec.dto.ProductSaleDto;
//import com.sinonc.orders.ec.mapper.CronMapper;
//import com.sinonc.orders.ec.mapper.EshopProductMapper;
//import com.sinonc.orders.ec.mapper.EshopProductReptileMapper;
//import com.sinonc.orders.ec.mapper.EshopProductSaleMapper;
//import com.sinonc.orders.ec.service.IEshopProductConfigService;
//import com.sinonc.orders.ec.service.IEshopProductRealtimeService;
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.util.CollectionUtils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Configuration
//@EnableScheduling
//@Slf4j
//public class ScheduleConfig implements SchedulingConfigurer {
//
//    @Autowired
//    private CronMapper cronMapper;
//    @Autowired
//    private EshopProductSaleMapper productSaleMapper;
//    @Autowired
//    private EshopProductMapper productMapper;
//    @Autowired
//    private EshopProductReptileMapper reptileMapper;
//    @Autowired
//    private IEshopProductRealtimeService realtimeService;
//    @Autowired
//    private IEshopProductConfigService configService;
//
//    /**
//     * 获取淘宝天猫商品销量接口
//     *
//     * @see https://www.dingdanxia.com/doc/161/156
//     */
//    private final static String GET_ESHOP_SALE = "http://api.ds.dingdanxia.com/shop/sales";
//    /**
//     * 订单侠账号秘钥
//     */
//    private final static String API_KEY = "wQtrWu2PbU2ivajLnsL1rBXyxAcA77Or";
//
//    /**
//     * 执行定时任务.
//     *
//     * @Note eshop_product cron_id = 0 。 cron 表 id = 0 的数据是每天执行一次, 以此满足每天爬一次的需求
//     */
//    @Override
//    @SuppressWarnings("all")
//    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//
//        List<Cron> cronList = getCronList();
//        if (CollectionUtils.isEmpty(cronList)) {
//            throw new BusinessException("暂无预设定时任务请先配置!");
//        }
//        cronList.forEach(e -> {
//            taskRegistrar.addTriggerTask(
//                    () -> {
//                        log.info("执行定时任务: 执行周期 cron {},type {} ", e.getCron());
//                        doTask(e.getId());
//                    },
//                    triggerContext -> {
//                        return new CronTrigger(e.getCron()).nextExecutionTime(triggerContext);
//                    });
//        });
//
//    }
//
//    private List<Cron> getCronList() {
//        return cronMapper.selectList(new QueryWrapper<>());
//    }
//
//    /**
//     * 保存数据
//     */
//    private void saveEshopSaleDate(Long cronId, Integer type) {
//        QueryWrapper<EshopProductReptile> queryWrapper = new QueryWrapper<>();
//        if (type == 1) {
//            // 查询开启周期定时任务的商品
//            queryWrapper.eq("cron_type", 1).eq("cron_id", cronId);
//        } else if (type == 2) {
//            // 查询开启实时定时任务的商品
//            queryWrapper.eq("cron_type", 2).eq("cron_id", cronId);
//        } else {
//            throw new BusinessException("为止类型");
//        }
//        List<EshopProductReptile> eshopProductReptiles = reptileMapper.selectList(queryWrapper);
//
//        if (!CollectionUtils.isEmpty(eshopProductReptiles)) {
//            List<EshopProductSale> needInsert = new ArrayList<>();
//            eshopProductReptiles.forEach(e -> {
//                ProductSaleDto productSaleDto = invokeDingDangXiaServer(e.getGoodsId().toString());
//                if (null == productSaleDto) {
//                    return;
//                }
//                EshopProductSale eshopProductSale = new EshopProductSale();
//                BeanUtils.copyProperties(productSaleDto, eshopProductSale);
//                eshopProductSale.setGoodsId(e.getGoodsId());
//                eshopProductSale.setGoodsOrigin(e.getGoodsOrigin());
//                eshopProductSale.setGoodsStore(e.getGoodsStore());
//                eshopProductSale.setGoodsType(e.getGoodsType());
//                productSaleMapper.insert(eshopProductSale);
//            });
//        }
//
//    }
//
//    private void doTask(Long cronId){
//        if (cronId ==null ||cronId<1L){
//            log.info("获取触发器主键失败,放弃执行任务.");
//            return;
//        }
//         List<EshopProductRealtime> tasks = realtimeService.listEshopProductRealtimeTask();
//         if (CollectionUtils.isEmpty(tasks)){
//             log.info("未发现开启的数据抓取配置,放弃执行任务.");
//             return;
//         }
//         EshopProductConfig config = configService.getEshopProductConfig();
//         if (config ==null){
//             log.info("获取定时任务配置失败,放弃执行任务.");
//             return;
//         }
//         Cron cron = cronMapper.selectById(cronId);
//         if (cron == null){
//             log.info("获取触发器配置失败,放弃执行任务.");
//             return;
//         }
//         //根据配置获取任务是否执行
//         if (config.getFiveminute() ==0 && cron.getType()==3){
//             log.info("以关闭执行5分钟定时任务.");
//             return;
//         }
//         if (config.getHalfhour() ==0 && cron.getType()==2){
//             log.info("以关闭执行半小时定时任务.");
//             return;
//         }
//        if (config.getDaily() ==0 && cron.getType()==1){
//            log.info("以关闭执行每日定时任务.");
//            return;
//        }
//        //过滤时间范围大的任务
//        if(filterLongTimeTask(cron,config)){
//            log.info("以开启时间范围较小任务,放弃执行任务.");
//            return;
//        }
//         tasks.forEach(e -> {
//             ProductSaleDto productSaleDto = invokeDingDangXiaServer(e.getGoodsId().toString());
//             if (null == productSaleDto) {
//                 return;
//             }
//             EshopProductSale eshopProductSale = new EshopProductSale();
//             BeanUtils.copyProperties(productSaleDto, eshopProductSale);
//             eshopProductSale.setGoodsId(e.getGoodsId());
//             eshopProductSale.setGoodsOrigin(e.getPlatform());
//             eshopProductSale.setGoodsStore(e.getName());
//             eshopProductSale.setGoodsType(e.getProductType());
//             eshopProductSale.setCreateTime(DateUtils.getNowDate());
//             productSaleMapper.insert(eshopProductSale);
//         });
//    }
//
//    @SuppressWarnings("all")
//    private ProductSaleDto invokeDingDangXiaServer(String productId) {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
//        FormBody formBody = new FormBody.Builder()
//                .add("apikey", API_KEY)
//                .add("id", productId)
//                .build();
//
//        Request request = new Request.Builder()
//                .url(GET_ESHOP_SALE)
//                .post(formBody)
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .build();
//
//        Response response;
//        try {
//            response = okHttpClient.newCall(request).execute();
//            if (response.code() == 200 && null != response.body()) {
//                HashMap hashMap = JSONObject.parseObject(response.body().string(), HashMap.class);
//                if (!hashMap.get("code").equals(200)) {
//                    log.error("请求【订单侠】失败: 返回结果 {}", hashMap.toString());
//                    return null;
//                }
//                Integer sale = (Integer) JSONObject.parseObject(hashMap.get("data").toString(), hashMap.getClass()).get("sale");
//                Integer cout = (Integer) JSONObject.parseObject(hashMap.get("data").toString(), hashMap.getClass()).get("cout");
//                Integer stock = (Integer) JSONObject.parseObject(hashMap.get("data").toString(), hashMap.getClass()).get("stock");
//                return new ProductSaleDto(sale, cout, stock);
//            } else {
//                log.error("请求【订单侠】失败: 请求商品 id{}", productId);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    //过滤时间叫长的任务
//    private Boolean filterLongTimeTask(Cron cron, EshopProductConfig config ){
//        if (cron ==null || config ==null){
//            log.info("获取过滤配置失败,无法过滤任务");
//            return true;
//        }
//        //5分钟任务,不过滤
//       if(cron.getType()==3){
//           return false;
//       }
//       //30分钟任务,5分钟任务开启时不执行
//       if (cron.getType()==2 && config.getFiveminute()==1){
//           return true;
//       }
//       //每日任务,5分钟任务开启时不执行
//       if (cron.getType()==1 && config.getFiveminute()==1){
//           return true;
//       }
//        //每日任务,半小时钟任务开启时不执行
//        if (cron.getType()==1 && config.getHalfhour()==1){
//            return true;
//        }
//        return false;
//    }
//}
