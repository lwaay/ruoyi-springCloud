package com.sinonc.orders.ec.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinonc.common.core.exception.CustomException;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.redis.service.RedisService;
import com.sinonc.orders.ec.domain.EshopProduct;
import com.sinonc.orders.ec.bo.ProductSellBo;
import com.sinonc.orders.ec.constants.Constants;
import com.sinonc.orders.ec.domain.EshopBrand;
import com.sinonc.orders.ec.domain.EshopProductRealtime;
import com.sinonc.orders.ec.domain.EshopProductSale;
import com.sinonc.orders.ec.domain.ProductType;
import com.sinonc.orders.ec.dto.GroupByTypeTimeDto;
import com.sinonc.orders.ec.mapper.*;
import com.sinonc.orders.ec.service.IEshopProductService;
import com.sinonc.orders.ec.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 电商数据Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-27
 */
@Service
public class EshopProductServiceImpl extends ServiceImpl<EshopProductMapper, EshopProduct> implements IEshopProductService {
    @Resource
    private EshopProductMapper eshopProductMapper;
    @Resource
    private ProductSellMapper productSellMapper;
    @Resource
    private EshopProductSaleMapper productSaleMapper;
    @Resource
    private EshopProductRealtimeMapper productRealtimeMapper;
    @Resource
    private ProductTypeMapper productTypeMapper;
    @Resource
    private EshopBrandMapper eshopBrandMapper;

    @Autowired
    private RedisService redisService;

    private final static String GOODS_KEY = "goods_";
    private final static String SHOOP_KEY = "shop_";
    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 15;

    /**
     * 导入电商数据
     *
     * @param eshopProductList        用户数据列表
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importEshop(List<EshopProduct> eshopProductList, String operName) {
        if (StringUtils.isNull(eshopProductList) || eshopProductList.size() == 0) {
            throw new CustomException("导入电商数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (EshopProduct eshopProduct : eshopProductList) {
            try {
                this.insertEshopProduct(eshopProduct);
                successNum++;
//                successMsg.append("<br/>" + successNum + "、商品 " + eshopProduct.getTitle() + " 导入成功");
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、商品 " + eshopProduct.getTitle() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }


    /**
     * 查询电商数据
     *
     * @param eshopId 电商数据ID
     * @return 电商数据
     */
    @Override
    public EshopProduct selectEshopProductById(Long eshopId) {
        return eshopProductMapper.selectEshopProductById(eshopId);
    }

    /**
     * 查询电商数据列表
     *
     * @param eshopProduct 电商数据
     * @return 电商数据
     */
    @Override
    public List<EshopProduct> selectEshopProductList(EshopProduct eshopProduct) {
        return eshopProductMapper.selectEshopProductList(eshopProduct);
    }
    /**
     * 查询电商数据列表
     *
     * @param eshopProduct 电商数据
     * @return 电商数据
     */
    @Override
    public List<EshopProduct> selectProductList(EshopProduct eshopProduct) {
        return eshopProductMapper.selectProductList(eshopProduct);
    }

    /**
     * 新增电商数据
     *
     * @param eshopProduct 电商数据
     * @return 结果
     */
    @Override
    public int insertEshopProduct(EshopProduct eshopProduct) {
        return eshopProductMapper.insertEshopProduct(eshopProduct);
    }

    /**
     * 修改电商数据
     *
     * @param eshopProduct 电商数据
     * @return 结果
     */
    @Override
    public int updateEshopProduct(EshopProduct eshopProduct) {
        return eshopProductMapper.updateEshopProduct(eshopProduct);
    }

    /**
     * 批量删除电商数据
     *
     * @param eshopIds 需要删除的电商数据ID
     * @return 结果
     */
    @Override
    public int deleteEshopProductByIds(Long[] eshopIds) {
        return eshopProductMapper.deleteEshopProductByIds(eshopIds);
    }

    /**
     * 删除电商数据信息
     *
     * @param eshopId 电商数据ID
     * @return 结果
     */
    @Override
    public int deleteEshopProductById(Long eshopId) {
        return eshopProductMapper.deleteEshopProductById(eshopId);
    }

    /**
     * 转译 字段
     *
     * @param form 来源
     * @return 转移后
     */
    public static String wrapperKey(Integer form) {
        switch (form) {
            case 1:
                return "sale_count";
            case 2:
                return "price";
            case 4:
                return "goods_id";
            case 5:
                return "name";
            default:
                return "sale_price";
        }
    }


    @Override
    public List<EshopPorductVo> getRankList(ProductSortVo productSortVo) {
        return this.listRankByType(productSortVo);
        /*int index = Integer.parseInt(productSortVo.getKey());
        //转译 字段
        productSortVo.setKey(wrapperKey(index));
        List<EshopProduct> eshopProducts = eshopProductMapper.selectByTypeIds(productSortVo);
        List<EshopPorductVo> result = new ArrayList<>();
        eshopProducts.forEach(entity -> {
            EshopPorductVo eshopPorductVo = new EshopPorductVo(wrapperPlatform(entity.getPlatform()),
                    entity.getTitle(),
                    entity.getPrice(),
                    Math.toIntExact(entity.getSaleCount()),
                    entity.getSalePrice(),
                    entity.getSaleTime()
            );
            result.add(eshopPorductVo);
        });
        return result;*/
    }

    private List<EshopPorductVo> listRankByType(ProductSortVo productSortVo){
        List<EshopPorductVo> res =  new ArrayList<>();
        if (productSortVo == null){
            return res;
        }
        int index = Integer.parseInt(productSortVo.getKey());
        //转译 字段
        productSortVo.setKey(wrapperKey(index));
        List<ProductType> types = new ArrayList<>();
        if (productSortVo.getTypeIds() == null || productSortVo.getTypeIds().length==0){
            ProductType query = new ProductType();
            query.setStatus("0");
            types = productTypeMapper.selectProductTypeList(query);
        }else{
            List<ProductType> finalTypes =types;
            Arrays.asList(productSortVo.getTypeIds()).forEach(typeId-> finalTypes.add(productTypeMapper.selectProductTypeById(typeId)));
        }

        if (CollectionUtils.isEmpty(types)){
            return res;
        }
//        List<String> typeNames = types.stream().map(item->item.getProductType().split(",")).distinct().flatMap(Arrays::stream).collect(Collectors.toList());
        //品类是单选，只有一个
        List<String> typeNames = Arrays.asList(types.get(0).getProductType().split(","));
        if (StringUtils.isEmpty(typeNames)){
            return res;
        }
        productSortVo.setTypeNames(typeNames);
        productSortVo.setIsFilter(types.get(0).getIsFilter());
        //判断维度：4商品，5店铺
        String dimension = productSortVo.getDimension();
        List<EshopProduct> eshopProducts = new ArrayList<>();
        if(Constants.goods.equals(dimension)){
            eshopProducts = eshopProductMapper.selectByTypeNames(productSortVo);
        }else {
            eshopProducts = eshopProductMapper.selectByShop(productSortVo);
        }

        eshopProducts.forEach(entity -> {
            EshopPorductVo eshopPorductVo = new EshopPorductVo(wrapperPlatform(entity.getPlatform()==null ?0:entity.getPlatform()),
                    entity.getTitle(),
                    entity.getPrice(),
                    Math.toIntExact(entity.getSaleCount()),
                    entity.getSalePrice(),
                    entity.getSaleTime()
            );
            res.add(eshopPorductVo);
        });
        return res;
    }

    /**
     * 电商农产品本月销售额走势分析数据查询
     * @param productSortVo
     * @return
     */
    @Override
    public ProductBrandTypeVo getBrandTypeList(ProductSortVo productSortVo) {
        //取出对应的类别进行分别查出对应的分组汇总值：type,salePrice,month
        Long[] typeIds =  productSortVo.getTypeIds();
        List<GroupByTypeTimeDto> dto = new ArrayList<GroupByTypeTimeDto>();
        typeIds = getDefaultType(typeIds);
        for(Long typePid : typeIds){
              //取得当前类别信息
              ProductType currType = productTypeMapper.selectProductTypeById(typePid);
              //mapper参数
              productSortVo.setTypeNames(Arrays.asList(currType.getProductType().split(",")));
              List<GroupByTypeTimeDto> listArr = eshopProductMapper.groupSaleAmountByTypeAndTime(productSortVo);
              if(null!= listArr && listArr.size()>0){
                  for(GroupByTypeTimeDto tmpPo : listArr){
                      tmpPo.setType(currType.getTypeName());
                      dto.add(tmpPo);
                  }

              }
        }
        //List<GroupByTypeTimeDto> dto = eshopProductMapper.groupByTypeAndTime(productSortVo);

        ProductBrandTypeVo result = new ProductBrandTypeVo();
        Set<String> x = new HashSet<>();
        Set<String> type = new HashSet<>();
        dto.forEach(entity -> {
            x.add(entity.getMonth());
            type.add(entity.getType());
        });
        List<String> x1 = new ArrayList<>(x);
        Collections.sort(x1);
        result.setX(x1);
        result.setType(type);
        List<List<BigDecimal>> salePrice = new ArrayList<>();
        for (String s : type) {
            List<BigDecimal> price = new ArrayList<>();
            for (String month : x1) {
                price.add(getGroupByTypeTimeDto(s, month, dto));
            }
            salePrice.add(price);
        }
        result.setSalePrice(salePrice);
        return result;
    }

    public static BigDecimal getGroupByTypeTimeDto(String s, String month, List<GroupByTypeTimeDto> dto) {
        for (GroupByTypeTimeDto groupByTypeTimeDto : dto) {
            if (s.equals(groupByTypeTimeDto.getType()) && groupByTypeTimeDto.getMonth().equals(month)) {
                return groupByTypeTimeDto.getSalePrice();
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * 转译 来源
     *
     * @param form 来源
     * @return 转移后
     */
    public static String wrapperPlatform(Integer form) {
        switch (form) {
            case 1:
                return "淘宝";
            case 2:
                return "天猫";
            case 3:
                return "京东";
            case 4:
                return "拼多多";
            default:
                return "未知";
        }
    }

    /**
     * 电商月销售统计
     *
     * @param productSortVo
     * @return
     */
    @Override
    public List<EshopMonthVo> getListByMonth(ProductSortVo productSortVo) {
        List<EshopMonthVo> eshopMonthVoList = productSellMapper.selectListByMonth(productSortVo);
        eshopMonthVoList.forEach(entity -> {
            entity.setName(wrapperPlatform(Integer.valueOf(entity.getName())));
        });
        return eshopMonthVoList;
    }

    /**
     * 查询各产品销售量占比
     *
     * @return
     */
    @Override
    public ProductSellBo getSellProportion(ProductSortVo productSortVo) {
        ProductSellBo productSellBo = new ProductSellBo();
        Long[] typeIds = productSortVo.getTypeIds();
        //判断typeIds
        typeIds = getDefaultType(typeIds);

        //品类销售额占比
        List<ProductSellVo> duckList = new ArrayList<>();
        //品类销量占比
        List<ProductSellVo> productSellVoList = new ArrayList<>();
        //品牌产品数占比
        List<ProductSellVo> goodsList = new ArrayList<>();
        for (Long typeId : typeIds) {
            ProductType productType = productTypeMapper.selectProductTypeById(typeId);
            //mapper参数
            productSortVo.setTypeNames(Arrays.asList(productType.getProductType().split(",")));
            productSortVo.setIsFilter(productType.getIsFilter());

            ProductSellVo duck = productSellMapper.selectDuckList(productSortVo);
            ProductSellVo sell = productSellMapper.selectSellProportion(productSortVo);
            ProductSellVo goods = productSellMapper.selectGoodsList(productSortVo);
            if (!Optional.ofNullable(duck).isPresent()){
                duck = new ProductSellVo();
            }
            if (!Optional.ofNullable(sell).isPresent()){
                sell = new ProductSellVo();
            }
            if (!Optional.ofNullable(goods).isPresent()){
                goods = new ProductSellVo();
            }
            duck.setName(productType.getTypeName());
            duckList.add(duck);
            sell.setName(productType.getTypeName());
            productSellVoList.add(sell);
            goods.setName(productType.getTypeName());
            goodsList.add(goods);
        }
        productSellBo.setDuckList(duckList);
        productSellBo.setBrandList(productSellVoList);
        productSellBo.setGoodsList(goodsList);

        return productSellBo;
    }

    /**
     * 梁平区电商品牌销售对比分析
     *
     * @return
     */
    @Override
    public List<ProductSellVo> getBrandPriceList(ProductSortVo productSortVo) {
        List<ProductSellVo> productSellVoList = new ArrayList<>();
        Long[] brandIds = productSortVo.getTypeIds();
        brandIds = getDefaultBrand(brandIds);
        for (Long brandId : brandIds) {
            EshopBrand eshopBrand = eshopBrandMapper.selectEshopBrandById(brandId);
            if(eshopBrand == null){
                continue;
            }
            productSortVo.setTypeNames(Arrays.asList(eshopBrand.getBrands().split(",")));
            productSortVo.setIsFilter(eshopBrand.getIsFilter());
            ProductSellVo productSellVo = productSellMapper.selectBrandPriceList(productSortVo);
            if(productSellVo == null){
                continue;
            }
            productSellVo.setName(eshopBrand.getBrandName());
            productSellVoList.add(productSellVo);
        }
        return productSellVoList;
    }

    /**
     * 梁平区农产品特产店铺销售对比分析
     *
     * @param productSortVo
     * @return
     */
    @Override
    public List<ProductSellVo> getShopList(ProductSortVo productSortVo) {
        Long[] typeIds = productSortVo.getTypeIds();
        typeIds = getDefaultType(typeIds);
        //品类单选
        ProductType productType = productTypeMapper.selectProductTypeById(typeIds[0]);
        if(productType != null){
            productSortVo.setTypeNames(Arrays.asList(productType.getProductType().split(",")));
            productSortVo.setIsFilter(productType.getIsFilter());
        }

        return productSellMapper.selectShopList(productSortVo);
    }

    @Override
    public int changeReptile(String goodsId) {
        EshopProductRealtime eshopProduct = productRealtimeMapper.selectOne(new QueryWrapper<EshopProductRealtime>().eq("goods_id", goodsId).isNotNull("goods_id"));
        eshopProduct.setReptileStatus(eshopProduct.getReptileStatus() == 0 ? 1 : 0);
        return productRealtimeMapper.updateById(eshopProduct);
    }

    @Override
    public RealTimeStatisticVo getShopRealTimeStatisticData(String productType) {
        if (Boolean.TRUE.equals(redisService.hasKey(GOODS_KEY + productType))) {
            return (RealTimeStatisticVo) redisService.getCacheObject(GOODS_KEY+productType);
        }
//        // 今天销售情况
//        RealTimeStatisticVo realTimeStatisticVo = new RealTimeStatisticVo();
//        List<EshopProductSale> todayList = productSaleMapper.listTypeRealTimeStatisticDataToday(productType);
//        if (CollectionUtils.isEmpty(todayList)){
//            return realTimeStatisticVo;
//        }
//        EshopProductSale today = productSaleMapper.getTypeRealTimeStatisticDataToday(productType);
//        if (today == null){
//            return realTimeStatisticVo;
//        }
//        //昨日销售情况
//        List<EshopProductSale> lastList= productSaleMapper.listTypeRealTimeStatisticDataLast(productType,today.getCreateTime());
//        BigDecimal todaySum = totalSaleAmount(todayList);
//        BigDecimal lastSum = BigDecimal.ZERO;
//        if (!CollectionUtils.isEmpty(lastList)){
//            lastSum = totalSaleAmount(lastList);
//        }
//        EshopProductSale last = productSaleMapper.getTypeRealTimeStatisticDataLast(productType,today.getCreateTime());
//        if(last ==null){
//            realTimeStatisticVo.setTodaySales(todaySum);
//            realTimeStatisticVo.setTodayOrders(today.getSale().longValue());
//            realTimeStatisticVo.setYesterdayOrdersRate(BigDecimal.ZERO);
//            realTimeStatisticVo.setYesterdaySalesRate(BigDecimal.ZERO);
//        }else{
//
//            //计算订单量
//            Integer todaySale = today.getSale() - last.getSale();
//            if (todaySale > 0){
//                realTimeStatisticVo.setTodayOrders(todaySale.longValue());
//            }else {
//                realTimeStatisticVo.setTodayOrders(0L);
//            }
//            Integer lastSale = this.countTypeYesterdayOrder(productType,last);
//            if (lastSale <= 0){
//                realTimeStatisticVo.setYesterdayOrdersRate(BigDecimal.ZERO);
//            }else{
//                BigDecimal rate = BigDecimal.ZERO;
//                BigDecimal difference = new BigDecimal(todaySale).subtract(new BigDecimal(lastSale));
//                if (difference.compareTo(BigDecimal.ZERO)!=0){
//                    rate = difference.divide(new BigDecimal(lastSale),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_DOWN);
//                }
//                realTimeStatisticVo.setYesterdayOrdersRate(rate);
//            }
//
//            //计算订单额
//            realTimeStatisticVo.setTodaySales(todaySum);
//            if (todaySum.compareTo(BigDecimal.ZERO)==0 || lastSum.compareTo(BigDecimal.ZERO)==0){
//                realTimeStatisticVo.setYesterdaySalesRate(BigDecimal.ZERO);
//            }else{
//                BigDecimal rate = BigDecimal.ZERO;
//                BigDecimal difference = todaySum.subtract(lastSum);
//                if (difference.compareTo(BigDecimal.ZERO)!=0){
//                    rate = difference.divide(lastSum,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_DOWN);
//                }
//                realTimeStatisticVo.setYesterdaySalesRate(rate);
//            }
//        }
//        //存redis
//        redisService.setCacheObject(GOODS_KEY + productType,realTimeStatisticVo,Constants.EXPIRE, TimeUnit.SECONDS);
        return new RealTimeStatisticVo();
    }

    @Override
    public List<RealTimeStoreStatisticVo> getStoreRealTimeStatisticData(String goodsStore) {
        if (Boolean.TRUE.equals(redisService.hasKey(SHOOP_KEY + goodsStore))) {
            return redisService.getCacheList(SHOOP_KEY + goodsStore);
        }
//        // 今天销售情况
//        List<EshopProductSale> eshopProductSalesToday = productSaleMapper.getStoreRealTimeStatisticDataToday(goodsStore);
//        List<RealTimeStoreStatisticVo> result = new ArrayList<>();
//        //今日无数据
//        if (CollectionUtils.isEmpty(eshopProductSalesToday)){
//            return result;
//        }
//        eshopProductSalesToday.forEach(today->{
//            RealTimeStoreStatisticVo storeStatisticVo = new RealTimeStoreStatisticVo();
//            EshopProductSale last = productSaleMapper.getStoreRealTimeStatisticDataLast(goodsStore,today.getGoodsId(),today.getCreateTime());
//            EshopProduct product = eshopProductMapper.selectOne(new QueryWrapper<EshopProduct>().eq("goods_id", today.getGoodsId()).last(" order by eshop_id desc limit 1"));
//            if (product == null){
//                return;
//            }
//            if (last == null){
//                storeStatisticVo.setProductName(product.getTitle());
//                BigDecimal sale = BigDecimal.ZERO;
//                if (today.getSale()>0 && product.getPrice()!=null && product.getPrice().compareTo(BigDecimal.ZERO)!=0){
//                    sale = product.getPrice().multiply(new BigDecimal(today.getSale()));
//                }
//                storeStatisticVo.setSale(sale.doubleValue());
//                storeStatisticVo.setSalesRate(BigDecimal.ZERO);
//                storeStatisticVo.setCreateTime(today.getCreateTime());
//                result.add(storeStatisticVo);
//            }else{
//                int todaySale = today.getSale() - last.getSale();
//                int yesterdaySale = countYesterdaySale(goodsStore,today.getGoodsId(),last);
//                storeStatisticVo.setProductName(product.getTitle());
//                BigDecimal sale = BigDecimal.ZERO;
//                if (todaySale>0 && product.getPrice()!=null && product.getPrice().compareTo(BigDecimal.ZERO)!=0){
//                    sale = product.getPrice().multiply(new BigDecimal(todaySale));
//                }
//                storeStatisticVo.setSale(sale.doubleValue());
//                BigDecimal rate = BigDecimal.ZERO;
//                if (yesterdaySale > 0 && todaySale> 0){
//                    BigDecimal difference = new BigDecimal(todaySale).subtract(new BigDecimal(yesterdaySale));
//                    if (difference.compareTo(BigDecimal.ZERO)!=0){
//                        rate = difference.divide(new BigDecimal(yesterdaySale),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_DOWN);
//                    }
//                }
//                storeStatisticVo.setSalesRate(rate);
//                storeStatisticVo.setCreateTime(today.getCreateTime());
//                result.add(storeStatisticVo);
//            }
//        });
//        //存redis,设置过期时间
//        redisService.setCacheList(SHOOP_KEY+ goodsStore,result);
//        redisService.expire(SHOOP_KEY+ goodsStore,EXPIRE_TIME);
        return new ArrayList<>();
    }

    @Override
    public List<String> getReptileProductType() {
        return productRealtimeMapper.listOpenGoodsType();
    }

    @Override
    public List<String> getReptileProductStore() {
        return productRealtimeMapper.listOpenStore();
    }

    /**
     * 根据品牌名称模糊查询
     * @param name
     * @return
     */
    @Override
    public List<String> selectBrandListByName(String name){
        return eshopProductMapper.selectBrandListByName(name);
    }

    /**
     * 取得电商数据表中最后一个日期数
     * @return
     */
    @Override
    public String selectDateForParam() {
        return productSellMapper.selectDateForParam();
    }

    /**
     * 计算昨日销量(店铺)
     * @return
     */
    private int countYesterdaySale(String goodsStore, Long goodsId, EshopProductSale today){
        if (StringUtils.isEmpty(goodsStore) || goodsId ==null || today ==null || today.getSale()==null){
            return 0;
        }
        EshopProductSale after = productSaleMapper.getStoreRealTimeStatisticDataLast(goodsStore,today.getGoodsId(),today.getCreateTime());
        if (after == null || after.getSale()==null){
            return 0;
        }
        Integer count = today.getSale() - after.getSale();
        if (count<0){
            return 0;
        }
        return count;
    }


    /**
     * 计算昨日订单量(类型)
     */
    private int countTypeYesterdayOrder(String productType, EshopProductSale today){
        if (StringUtils.isEmpty(productType)  || today ==null || today.getSale()==null){
            return 0;
        }
        EshopProductSale after = productSaleMapper.getTypeRealTimeStatisticDataLast(productType,today.getCreateTime());
        if (after == null || after.getSale()==null){
            return 0;
        }
        Integer sale = today.getSale() - after.getSale();
        if (sale<0){
            return 0;
        }
        return sale;
    }

    /**
     * 统计今日销售额
     */
    private BigDecimal totalSaleAmount(List<EshopProductSale> list){
        BigDecimal res = BigDecimal.ZERO;
        if (CollectionUtils.isEmpty(list)){
            return res;
        }
        for(EshopProductSale e :list){
            //获取商品信息
            EshopProduct product = eshopProductMapper.selectOne(new QueryWrapper<EshopProduct>().eq("goods_id", e.getGoodsId()).last(" order by eshop_id desc limit 1"));
            if (product ==null || product.getPrice()==null || product.getPrice().compareTo(BigDecimal.ZERO)<1){
                continue;
            }
            //获取历史商品销售数量
            EshopProductSale last = productSaleMapper.getRealTimeStatisticDataLast(e.getGoodsId(),e.getCreateTime());
            Integer todaySale = e.getSale();
            Integer lastSale = 0;
            //计算销量
            if (last != null){
                lastSale = last.getSale();
            }
            Integer diff = todaySale - lastSale;
            if (diff == 0){
                continue;
            }
            BigDecimal total = BigDecimal.ZERO;
            total = new BigDecimal(diff).multiply(product.getPrice());
            if (total.compareTo(BigDecimal.ZERO)<1){
                continue;
            }
            res = res.add(total);
        }
        return res;
    }

    /**
     * 判断typeIds是否为空，为空取默认品类
     * @param typeIds
     * @return
     */
    private Long[] getDefaultType(Long[] typeIds){
        if(null==typeIds || typeIds.length==0){
            //获取默认品类
            List<ProductType> productTypeList = productTypeMapper.selectListDefault();
            typeIds = new Long[productTypeList.size()];
            for (int i = 0; i < productTypeList.size(); i++) {
                typeIds[i] = productTypeList.get(i).getTypeId();
            }
        }
        return typeIds;
    }

    /**
     * 判断typeIds是否为空，为空取默认品牌
     * @param typeIds
     * @return
     */
    private Long[] getDefaultBrand(Long[] typeIds){
        if(null==typeIds || typeIds.length==0){
            //获取默认品牌
            List<EshopBrand> eshopBrandList = eshopBrandMapper.selectListDefault();
            typeIds = new Long[eshopBrandList.size()];
            for (int i = 0; i < eshopBrandList.size(); i++) {
                typeIds[i] = eshopBrandList.get(i).getBrandId();
            }
        }
        return typeIds;
    }
}
