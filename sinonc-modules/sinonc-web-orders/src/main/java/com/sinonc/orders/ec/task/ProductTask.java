//package com.sinonc.orders.ec.task;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.sinonc.common.core.utils.StringUtils;
//import com.sinonc.common.redis.service.RedisService;
//import com.sinonc.orders.ec.domain.EshopProduct;
//import com.sinonc.orders.ec.domain.EshopProductSale;
//import com.sinonc.orders.ec.mapper.EshopProductMapper;
//import com.sinonc.orders.ec.mapper.EshopProductRealtimeMapper;
//import com.sinonc.orders.ec.mapper.EshopProductSaleMapper;
//import com.sinonc.orders.ec.vo.RealTimeStatisticVo;
//import com.sinonc.orders.ec.vo.RealTimeStoreStatisticVo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author lw
// * 电商实时销售数据
// */
//@Component
//@Slf4j
//public class ProductTask {
//
//    @Resource
//    private EshopProductMapper eshopProductMapper;
//    @Resource
//    private EshopProductSaleMapper productSaleMapper;
//    @Resource
//    private EshopProductRealtimeMapper productRealtimeMapper;
//    @Resource
//    private RedisService redisService;
//
//    private final static String GOODS_KEY = "goods_";
//    private final static String SHOOP_KEY = "shop_";
//
//
//    @Scheduled(cron = "0 0/5 * * * ?")
//    public void setProductRedis(){
//        log.info("电商销售实时数据更新");
//        //获取商品品类下拉
//        List<String> productTypeList = productRealtimeMapper.listOpenGoodsType();
//        for (String productType : productTypeList) {
//            // 今天销售情况
//            RealTimeStatisticVo realTimeStatisticVo = new RealTimeStatisticVo();
//            List<EshopProductSale> todayList = productSaleMapper.listTypeRealTimeStatisticDataToday(productType);
//            if (!CollectionUtils.isEmpty(todayList)){
//                EshopProductSale today = productSaleMapper.getTypeRealTimeStatisticDataToday(productType);
//                BigDecimal todaySum = totalSaleAmount(todayList);
//                if (today != null){
//                    //昨日销售情况
//                    List<EshopProductSale> lastList= productSaleMapper.listTypeRealTimeStatisticDataLast(productType,today.getCreateTime());
//                    BigDecimal lastSum = BigDecimal.ZERO;
//                    if (!CollectionUtils.isEmpty(lastList)){
//                        lastSum = totalSaleAmount(lastList);
//                    }
//                    EshopProductSale last = productSaleMapper.getTypeRealTimeStatisticDataLast(productType,today.getCreateTime());
//                    if(last ==null){
//                        realTimeStatisticVo.setTodaySales(todaySum);
//                        realTimeStatisticVo.setTodayOrders(today.getSale().longValue());
//                        realTimeStatisticVo.setYesterdayOrdersRate(BigDecimal.ZERO);
//                        realTimeStatisticVo.setYesterdaySalesRate(BigDecimal.ZERO);
//                    }else{
//
//                        //计算订单量
//                        Integer todaySale = today.getSale() - last.getSale();
//                        if (todaySale > 0){
//                            realTimeStatisticVo.setTodayOrders(todaySale.longValue());
//                        }else {
//                            realTimeStatisticVo.setTodayOrders(0L);
//                        }
//                        Integer lastSale = this.countTypeYesterdayOrder(productType,last);
//                        if (lastSale <= 0){
//                            realTimeStatisticVo.setYesterdayOrdersRate(BigDecimal.ZERO);
//                        }else{
//                            BigDecimal rate = BigDecimal.ZERO;
//                            BigDecimal difference = new BigDecimal(todaySale).subtract(new BigDecimal(lastSale));
//                            if (difference.compareTo(BigDecimal.ZERO)!=0){
//                                rate = difference.divide(new BigDecimal(lastSale),2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_DOWN);
//                            }
//                            realTimeStatisticVo.setYesterdayOrdersRate(rate);
//                        }
//
//                        //计算订单额
//                        realTimeStatisticVo.setTodaySales(todaySum);
//                        if (todaySum.compareTo(BigDecimal.ZERO)==0 || lastSum.compareTo(BigDecimal.ZERO)==0){
//                            realTimeStatisticVo.setYesterdaySalesRate(BigDecimal.ZERO);
//                        }else{
//                            BigDecimal rate = BigDecimal.ZERO;
//                            BigDecimal difference = todaySum.subtract(lastSum);
//                            if (difference.compareTo(BigDecimal.ZERO)!=0){
//                                rate = difference.divide(lastSum,2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_DOWN);
//                            }
//                            realTimeStatisticVo.setYesterdaySalesRate(rate);
//                        }
//                    }
//                }
////                else {
////                    if (Boolean.FALSE.equals(redisService.hasKey(GOODS_KEY + productType))) {
////                         redisService.setCacheObject(GOODS_KEY+productType,realTimeStatisticVo);
////                    }
////                }
//            }
//            //存redis
//            redisService.setCacheObject(GOODS_KEY + productType,realTimeStatisticVo);
//        }
//    }
//
//    @Scheduled(cron = "0 0/5 * * * ?")
//    public void setShopRedis(){
//        log.info("店铺销售实时数据更新");
//        List<String> goodsStoreList = productRealtimeMapper.listOpenStore();
//        for (String goodsStore : goodsStoreList) {
//            // 今天销售情况
//            List<EshopProductSale> eshopProductSalesToday = productSaleMapper.getStoreRealTimeStatisticDataToday(goodsStore);
//            List<RealTimeStoreStatisticVo> result = new ArrayList<>();
//            //今日无数据
//            if (!CollectionUtils.isEmpty(eshopProductSalesToday)){
//                eshopProductSalesToday.forEach(today->{
//                    RealTimeStoreStatisticVo storeStatisticVo = new RealTimeStoreStatisticVo();
//                    EshopProductSale last = productSaleMapper.getStoreRealTimeStatisticDataLast(goodsStore,today.getGoodsId(),today.getCreateTime());
//                    EshopProduct product = eshopProductMapper.selectOne(new QueryWrapper<EshopProduct>().eq("goods_id", today.getGoodsId()).last(" order by eshop_id desc limit 1"));
//                    if (product == null){
//                        return;
//                    }
//                    if (last == null){
//                        storeStatisticVo.setProductName(product.getTitle());
//                        BigDecimal sale = BigDecimal.ZERO;
//                        if (today.getSale()>0 && product.getPrice()!=null && product.getPrice().compareTo(BigDecimal.ZERO)!=0){
//                            sale = product.getPrice().multiply(new BigDecimal(today.getSale()));
//                        }
//                        storeStatisticVo.setSale(sale.doubleValue());
//                        storeStatisticVo.setSalesRate(BigDecimal.ZERO);
//                        storeStatisticVo.setCreateTime(today.getCreateTime());
//                        result.add(storeStatisticVo);
//                    }else{
//                        int todaySale = today.getSale() - last.getSale();
//                        int yesterdaySale = countYesterdaySale(goodsStore,today.getGoodsId(),last);
//                        storeStatisticVo.setProductName(product.getTitle());
//                        BigDecimal sale = BigDecimal.ZERO;
//                        if (todaySale>0 && product.getPrice()!=null && product.getPrice().compareTo(BigDecimal.ZERO)!=0){
//                            sale = product.getPrice().multiply(new BigDecimal(todaySale));
//                        }
//                        storeStatisticVo.setSale(sale.doubleValue());
//                        BigDecimal rate = BigDecimal.ZERO;
//                        if (yesterdaySale > 0 && todaySale> 0){
//                            BigDecimal difference = new BigDecimal(todaySale).subtract(new BigDecimal(yesterdaySale));
//                            if (difference.compareTo(BigDecimal.ZERO)!=0){
//                                rate = difference.divide(new BigDecimal(yesterdaySale),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_DOWN);
//                            }
//                        }
//                        storeStatisticVo.setSalesRate(rate);
//                        storeStatisticVo.setCreateTime(today.getCreateTime());
//                        result.add(storeStatisticVo);
//                    }
//                });
//            }
//            //存redis
//            redisService.setCacheList(SHOOP_KEY+ goodsStore,result);
//        }
//    }
//
//    /**
//     * 计算昨日销量(店铺)
//     * @return
//     */
//    private int countYesterdaySale(String goodsStore, Long goodsId, EshopProductSale today){
//        if (StringUtils.isEmpty(goodsStore) || goodsId ==null || today ==null || today.getSale()==null){
//            return 0;
//        }
//        EshopProductSale after = productSaleMapper.getStoreRealTimeStatisticDataLast(goodsStore,today.getGoodsId(),today.getCreateTime());
//        if (after == null || after.getSale()==null){
//            return 0;
//        }
//        Integer count = today.getSale() - after.getSale();
//        if (count<0){
//            return 0;
//        }
//        return count;
//    }
//
//    /**
//     * 计算昨日订单量(类型)
//     */
//    private int countTypeYesterdayOrder(String productType, EshopProductSale today){
//        if (StringUtils.isEmpty(productType)  || today ==null || today.getSale()==null){
//            return 0;
//        }
//        EshopProductSale after = productSaleMapper.getTypeRealTimeStatisticDataLast(productType,today.getCreateTime());
//        if (after == null || after.getSale()==null){
//            return 0;
//        }
//        Integer sale = today.getSale() - after.getSale();
//        if (sale<0){
//            return 0;
//        }
//        return sale;
//    }
//
//    /**
//     * 统计今日销售额
//     */
//    private BigDecimal totalSaleAmount(List<EshopProductSale> list){
//        BigDecimal res = BigDecimal.ZERO;
//        if (CollectionUtils.isEmpty(list)){
//            return res;
//        }
//        for(EshopProductSale e :list){
//            //获取商品信息
//            EshopProduct product = eshopProductMapper.selectOne(new QueryWrapper<EshopProduct>().eq("goods_id", e.getGoodsId()).last(" order by eshop_id desc limit 1"));
//            if (product ==null || product.getPrice()==null || product.getPrice().compareTo(BigDecimal.ZERO)<1){
//                continue;
//            }
//            //获取历史商品销售数量
//            EshopProductSale last = productSaleMapper.getRealTimeStatisticDataLast(e.getGoodsId(),e.getCreateTime());
//            Integer todaySale = e.getSale();
//            Integer lastSale = 0;
//            //计算销量
//            if (last != null){
//                lastSale = last.getSale();
//            }
//            Integer diff = todaySale - lastSale;
//            if (diff == 0){
//                continue;
//            }
//            BigDecimal total = BigDecimal.ZERO;
//            total = new BigDecimal(diff).multiply(product.getPrice());
//            if (total.compareTo(BigDecimal.ZERO)<1){
//                continue;
//            }
//            res = res.add(total);
//        }
//        return res;
//    }
//
//}
