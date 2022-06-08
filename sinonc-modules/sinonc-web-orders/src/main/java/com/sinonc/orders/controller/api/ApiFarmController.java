//package com.sinonc.orders.controller.api;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sinonc.order.api.domain.Shop;
//import com.sinonc.orders.domain.ShopVisit;
//import com.sinonc.orders.domain.Specs;
//import com.sinonc.orders.mapper.ShopMapper;
//import com.sinonc.orders.mapper.ShopVisitMapper;
//import com.sinonc.orders.service.GoodsService;
//import com.sinonc.orders.service.ShopService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.*;
//
//@Api(tags = "订单系统果园数据接口")
//@RestController
//@RequestMapping("/api/farm")
//@Slf4j
//public class ApiFarmController extends BaseController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private ShopService shopService;
//
//    @Autowired
//    private FarminfoService farminfoService;
//
//    @Autowired
//    private ISysDictDataService dictDataService;
//
//    @Autowired
//    private YsService ysService;
//
//    @Autowired
//    private ShopMapper shopMapper;
//
//    @Autowired
//    private ShopVisitMapper shopVisitMapper;
//
//    @Autowired
//    private ISysDictDataService iSysDictDataService;
//
//    @Autowired
//    private FarminfoMapper farminfoMapper;
//
//    @Autowired
//    private GoodsService goodsService;
//
//
//    //物联网系统数据地址
//    @Value("${iot.url}")
//    private String iotUrl;
//
//    //高德接口地址
//    @Autowired
//    private AmpProperties ampProperties;
//
//    @ApiOperation(value = "根据店铺ID查询店铺基地物联网数据")
//    @GetMapping("/iot")
//    @ApiImplicitParam(name = "shopId", value = "店铺ID", required = true, dataType = "string", paramType = "query")
//    public AjaxResult iotData(Long shopId) {
//
//        try {
//
//            if (shopId == null || shopId == 0L) {
//                return AjaxResult.error("shopId不能为空");
//            }
//
//            Map<String, Object> data = new LinkedHashMap<>();
//
//            //获取天气信息
//            String weatherStr = restTemplate.getForObject(ampProperties.getUrl() + "/v3/weather/weatherInfo?key=" + ampProperties.getKey() + "&city=360722&extensions=base", String.class);
//
//
//            //解析高德天气接口，获取气象数据
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.readTree(weatherStr);
//            int status = jsonNode.get("status").asInt();
//
//            //判断高德接口返回状态
//            if (status != 0) {
//                JsonNode lives = jsonNode.get("lives");
//                JsonNode live = lives.get(0);
//                String weather = live.get("weather").asText();
//                String temperature = live.get("temperature").asText();
//                String windpower = live.get("windpower").asText();
//                String humidity = live.get("humidity").asText();
//
//                data.put("weather", weather);
//                data.put("temperature", temperature);
//                data.put("windpower", windpower);
//                data.put("humidity", humidity);
//            } else {
//                return AjaxResult.error("天气API接口异常");
//            }
//
//            Shop shop = shopService.getShopById(shopId);
//            if (shop == null) {
//                return AjaxResult.error("店铺不存在");
//            }
//            Long baseId = shop.getBaseId();
//
//            Farminfo farminfo = farminfoService.getFarminfoById(Long.valueOf(baseId));
//
//            if (farminfo == null) {
//                return AjaxResult.error("店铺关联基地不存在");
//            }
//            String baseCode = farminfo.getAreaCode();
//
//            //获取物联网设备数据
//            Map<String, String> params = new LinkedHashMap<>();
//            params.put("baseCode", baseCode);
//            params.put("metric", "KQWD,KQSD,TRWD,TRSD,GZ,POWER,YL,FS,FX,PH");
//            params.put("deep", "1");
//            params.put("start", String.valueOf(DateUtils.addHours(new Date(), -1).getTime()));
//            params.put("downsample", "HOUR_AVG");
//
//            ResponseEntity<String> iotEntity = restTemplate.getForEntity(iotUrl + "/api/multipledataquery?baseCode={baseCode}&metric={metric}&deep={deep}&start={start}&downsample={downsample}", String.class, params);
//
//            //判断http请求是否正常
//            if (!iotEntity.getStatusCode().isError()) {
//
//                String iotDataStr = iotEntity.getBody();
//                JsonNode iotJson = objectMapper.readTree(iotDataStr);
//                int resultCode = iotJson.get("resultCode").asInt();
//
//                if (resultCode == 200) {
//                    JsonNode datas = iotJson.get("datas");
//                    if (datas.isArray()) {
//                        Iterator<JsonNode> iterator = datas.iterator();
//                        List<Map<String, String>> iotData = new LinkedList<>();
//                        data.put("iot", iotData);
//                        while (iterator.hasNext()) {
//                            JsonNode next = iterator.next();
//                            Map<String, String> nextMap = new HashMap<>();
//                            nextMap.put("key", next.get("metricName").asText());
//                            nextMap.put("metric", next.get("metric").asText());
//                            try {
//                                nextMap.put("value", next.get("value").iterator().next().asText());
//                            } catch (Exception e) {
//                                nextMap.put("value", "暂无");
//                            }
//                            nextMap.put("unit", next.get("unit").asText());
//                            iotData.add(nextMap);
//                        }
//                    }
//                }
//            } else {
//                return AjaxResult.error("物联网设备数据接口异常");
//            }
//
//
//            return AjaxResult.success(data);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @ApiOperation("获取果园列表")
//    @ApiImplicitParam(name = "areaCode", value = "areaCode", required = true, paramType = "query", dataType = "string")
//    @GetMapping("list")
//    @SuppressWarnings("unchecked")
//    public AjaxResult list(@RequestParam(defaultValue = "360722") String areaCode) {
//        startPage();
//
//        //获取果园列表
//        List<Farminfo> farminfos = farminfoService.selectFarmInfosByOne(areaCode);
//
//        List<Map<String, Object>> result = new LinkedList<>();
//
//        for (Farminfo farminfo : farminfos) {
//
//            Map<String, Object> farmMap = new LinkedHashMap<>();
//
//            farmMap.put("farmId", farminfo.getFarmId());
//            farmMap.put("farmName", farminfo.getFarmName());
//            farmMap.put("remark", farminfo.getRemark());
//            farmMap.put("address", farminfo.getAddress());
//            farmMap.put("star", "2.5");
//            farmMap.put("orangeTypes", new LinkedList<>());
//            farmMap.put("img", farminfo.getPictures().split(",")[0]);
//            farmMap.put("stock", "7800");
//
//            String orangeTypes = farminfo.getOrangeTypes();
//
//            List<Long> ids = new LinkedList<>();
//
//            if (orangeTypes.contains(",")) {
//                String[] split = orangeTypes.split(",");
//                for (String s : split) {
//                    ids.add(Long.valueOf(s));
//                }
//            } else {
//                ids.add(Long.valueOf(farminfo.getOrangeTypes()));
//            }
//
//            List<SysDictData> sysDictData = dictDataService.selectDictDataByDictValues(ids, "orange_type");
//
//            List<String> types = (List<String>) farmMap.get("orangeTypes");
//            for (SysDictData sysDictDatum : sysDictData) {
//                types.add(sysDictDatum.getDictLabel());
//            }
//
//            result.add(farmMap);
//        }
//
//        return AjaxResult.success(result);
//    }
//
//
//    @ApiOperation("获取果园列表")
//    @GetMapping("listTwo")
//    @SuppressWarnings("unchecked")
//    public AjaxResult listTwo(Farminfo farmInfoPara) {
//
//        try {
//            startPage();
//
//            //获取果园列表
//            List<Farminfo> farminfos = farminfoService.selectFarminfoListByCreateDate(farmInfoPara);
//
//            List<Map<String, Object>> result = new LinkedList<>();
//
//            for (Farminfo farminfo : farminfos) {
//
//                Map<String, Object> farmMap = changeFarminfoToMap(farminfo);
//
//                result.add(farmMap);
//            }
//
//            return AjaxResult.success(result);
//        } catch (Exception e) {
//            return AjaxResult.error(e.getMessage());
//        }
//
//    }
//
//
//    @ApiOperation("获取果园列表")
//    @GetMapping("listThree")
//    @SuppressWarnings("unchecked")
//    public AjaxResult listThree(Farminfo farmInfoPara) {
//        try {
//            if (farmInfoPara.getPageNum() == null || farmInfoPara.getPageSize() == null) {
//                farmInfoPara.setLimitBegin(0);
//                farmInfoPara.setLimitEnd(20);
//            } else {
//                farmInfoPara.setLimitBegin(farmInfoPara.getPageNum() * farmInfoPara.getPageSize());
//                farmInfoPara.setLimitEnd(farmInfoPara.getPageSize());
//                //置空
//                farmInfoPara.setPageNum(null);
//                farmInfoPara.setPageSize(null);
//            }
//
//
//            //获取果园列表
//            List<Farminfo> farminfos = farminfoService.selectFarminfoListByTreeAge(farmInfoPara);
//            List<Map<String, Object>> result = new LinkedList<>();
//
//            for (Farminfo farminfo : farminfos) {
//                Map<String, Object> farmMap = changeFarminfoToMap(farminfo);
//                //获取战胜多少
//                genOvercomeDescribe(farmMap, farminfo, farminfos);
//                //获取可销售总重量，已销售量，已上架量
//                nabSaleAllWeightAndSpecsWeight(farmMap, farminfo);
//
//                result.add(farmMap);
//            }
//
//            return AjaxResult.success(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return AjaxResult.error(e.getMessage());
//        }
//    }
//
//    @ApiOperation("脐橙类型")
//    @GetMapping("selectDictDataByTypeOrangeType")
//    @SuppressWarnings("unchecked")
//    public AjaxResult selectDictDataByTypeOrangeType() {
//
//        try {
//            List<SysDictData> sysDictDataList = iSysDictDataService.selectDictDataByType("orange_type");
//
//            return AjaxResult.success(sysDictDataList);
//        } catch (Exception e) {
//            return AjaxResult.error(e.getMessage());
//        }
//
//    }
//
//    private void nabSaleAllWeightAndSpecsWeight(Map<String, Object> farmMap, Farminfo farminfo) {
//        try {
//            Long shopId = farminfo.getShopId();
//            BigDecimal saleAllWeight = goodsService.nabSaleAllWeight(shopId);
//            farmMap.put("saleAllWeight", saleAllWeight);
//
//            BigDecimal orderItemWeight = goodsService.nabOrderItemWeight(shopId);
//            farmMap.put("orderItemWeight", orderItemWeight);
//
//            List<Specs> changeSpecsList = new ArrayList<>();
//            BigDecimal specsWeight = goodsService.nabSpecsWeight(shopId, changeSpecsList, "");
//            farmMap.put("specsWeight", specsWeight);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 产生战胜描述
//     *
//     * @return
//     */
//    private void genOvercomeDescribe(Map<String, Object> farmMap, Farminfo farminfo, List<Farminfo> farminfos) {
//        try {
//            Double rsDouble = farminfoMapper.selectOvercomeDescribe(farminfo.getFarmId());
//
//            BigDecimal rsBigDecimal = new BigDecimal("0.00");
//
//            rsBigDecimal = rsBigDecimal.add(new BigDecimal(rsDouble)).multiply(new BigDecimal("100"));
//
//            farmMap.put("overcomeDescribe", rsBigDecimal + "%");
//        } catch (Exception e) {
//            farmMap.put("overcomeDescribe", "0%");
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * 将Farminfo转为Map
//     *
//     * @param farminfo
//     * @return
//     */
//    private Map<String, Object> changeFarminfoToMap(Farminfo farminfo) {
//        Map<String, Object> farmMap = new LinkedHashMap<>();
//
//        farmMap.put("farmId", farminfo.getFarmId());
//        farmMap.put("farmName", farminfo.getFarmName());
//        farmMap.put("remark", farminfo.getRemark());
//        farmMap.put("address", farminfo.getAddress());
//        //farmMap.put("star", "2.5");
//        changeGradeToStar(farmMap, farminfo);
//        farmMap.put("orangeTypes", new LinkedList<>());
//        farmMap.put("img", farminfo.getPictures().split(",")[0]);
//        farmMap.put("stock", "7800");
//
//        farmMap.put("farmArea", farminfo.getFarmArea());//基地种植面积
//        farmMap.put("farmCreateDate", farminfo.getFarmCreateDate());//基地创建时间
//
//        farmMap.put("grade", farminfo.getGrade());
//        farmMap.put("townsp", farminfo.getTownsp());
//
//
//        farmMap.put("gradeValue", farminfo.getGradeValue());
//        String  grade=farminfo.getGrade();
//        if(StringUtils.isNotEmpty(grade)&&"A+".compareTo(grade)==0){
//            farmMap.put("gradeValue", "96.5");
//        }
//        if(StringUtils.isNotEmpty(grade)&&"A".compareTo(grade)==0){
//            farmMap.put("gradeValue", "91.2");
//        }
//        if(StringUtils.isNotEmpty(grade)&&"B+".compareTo(grade)==0){
//            farmMap.put("gradeValue", "89.3");
//        }
//
//        farmMap.put("lng", farminfo.getLng());//经度
//        farmMap.put("lat", farminfo.getLat());//维度
//
//        changeOrangeTypes(farmMap, farminfo);//脐橙类型
//        try {
//            queryShopConcern(farmMap, farminfo);//关注数
//        } catch (Exception e) {
//            farmMap.put("attentionNumber", 0);//找不到则为0
//        }
//
//        try {
//            queryShopVisit(farmMap, farminfo);//访问量
//        } catch (Exception e) {
//            farmMap.put("visitNumber", 0);//找不到则为0
//        }
//
//        return farmMap;
//    }
//
//    private void changeGradeToStar(Map<String, Object> farmMap, Farminfo farminfo) {
//        try {
//            String grade = farminfo.getGrade();
//            if (grade == null) {
//                farmMap.put("star", "0");
//            }
//            if (grade.compareTo("A+") == 0) {
//                farmMap.put("star", "5");
//            }
//            if (grade.compareTo("A") == 0) {
//                farmMap.put("star", "4");
//            }
//            if (grade.compareTo("B+") == 0) {
//                farmMap.put("star", "3");
//            }
//            if (grade.compareTo("B") == 0) {
//                farmMap.put("star", "2");
//            }
//            if (grade.compareTo("C+") == 0) {
//                farmMap.put("star", "1");
//            }
//        } catch (Exception e) {
//            farmMap.put("star", "0");
//        }
//
//    }
//
//    /**
//     * 转换脐橙类型
//     *
//     * @param farmMap
//     * @param farminfo
//     */
//    private void changeOrangeTypes(Map<String, Object> farmMap, Farminfo farminfo) {
//
//
//        try {
//            String orangeTypes = farminfo.getOrangeTypes();
//
//            if (orangeTypes == null) {
//                return;
//            }
//
//            List<Long> ids = new LinkedList<>();
//
//            if (orangeTypes.contains(",")) {
//                String[] split = orangeTypes.split(",");
//                for (String s : split) {
//                    ids.add(Long.valueOf(s));
//                }
//            } else {
//                ids.add(Long.valueOf(farminfo.getOrangeTypes()));
//            }
//
//            List<SysDictData> sysDictData = dictDataService.selectDictDataByDictValues(ids, "orange_type");
//
//            List<String> types = (List<String>) farmMap.get("orangeTypes");
//            for (SysDictData sysDictDatum : sysDictData) {
//                types.add(sysDictDatum.getDictLabel());
//            }
//        } catch (Exception e) {
//        }
//
//    }
//
//    /**
//     * 查询关注数
//     *
//     * @param farmMap
//     * @param farminfo
//     */
//    private void queryShopConcern(Map<String, Object> farmMap, Farminfo farminfo) {
//        Shop shop = shopMapper.selectShopByFarmId(farminfo.getFarmId());
//        String attentions = shopMapper.selectShopAttention(shop.getShopId());
//        String[] attentionArr = attentions.split(",");
//
//        farmMap.put("attentionNumber", attentionArr.length);//关注数
//
//    }
//
//    /**
//     * 查询访问量
//     *
//     * @param farmMap
//     * @param farminfo
//     */
//    private void queryShopVisit(Map<String, Object> farmMap, Farminfo farminfo) {
//        Shop shop = shopMapper.selectShopByFarmId(farminfo.getFarmId());
//
//        ShopVisit shopVisit = new ShopVisit();
//        shopVisit.setShopIdP(shop.getShopId());
//        Long visitNumber = shopVisitMapper.sumShopVisitList(shopVisit);
//
//        farmMap.put("visitNumber", visitNumber);//访问量
//    }
//
//    /**
//     * 根据shopId 获取农场视频直播地址
//     *
//     * @param shopId 店铺ID
//     * @return 结果
//     */
//    @GetMapping("lives")
//    @ApiOperation(value = "根据店铺ID查询农场直播设备信息")
//    @ApiImplicitParam(name = "shopId", value = "店铺ID", required = true, paramType = "query", dataType = "long")
//    public AjaxResult lives(Long shopId) {
//
//        if (shopId == null || shopId == 0L) {
//            return AjaxResult.error("shopId不能为空");
//        }
//
//        Shop shopById = shopService.getShopById(shopId);
//
//        if (shopById == null) {
//            return AjaxResult.error("店铺信息不存在");
//        }
//
//        List<Live> lives = ysService.getLives(Long.valueOf(shopById.getBaseId()));
//
//        if (lives == null || lives.size() == 0) {
//            return AjaxResult.error("视频直播设备不存在");
//        }
//
//        return AjaxResult.success(lives);
//    }
//
//}
