package com.sinonc.base.api;

import com.sinonc.base.api.config.FeignConfig;
import com.sinonc.base.api.domain.*;
import com.sinonc.base.api.factory.RemoteOriginsFallbackFactory;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author lqqu
 * @apiNote 第三方溯源服务
 * @date 2021/5/6 9:50
 */
@FeignClient(contextId = "remoteOriginsService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteOriginsFallbackFactory.class, configuration = FeignConfig.class)
public interface RemoteOriginsService {
    /**
     * 添加企业信息
     * @param list 企业信息
     * @return 添加结果
     */
    @PostMapping("/origins/crop/addBatch")
    AjaxResult addCropBatch(@RequestBody List<OriginsCrop> list);

    /**
     * 添加渔业池塘信息
     * @param list 池塘信息
     * @return 添加结果
     */
    @PostMapping("/origins/fish/pond/addBatch")
    AjaxResult addFishPondBatch(@RequestBody List<OriginsFishPond> list);

    /**
     * 添加渔业用药信息
     * @param list 用药信息
     * @return 添加结果
     */
    @PostMapping("/origins/fish/medicine/addBatch")
    AjaxResult addFishMedicineBatch(@RequestBody List<OriginsFishMedicine> list);

    /**
     * 添加渔业投喂信息
     * @param list 投喂信息
     * @return 添加结果
     */
    @PostMapping("/origins/fish/feed/addBatch")
    AjaxResult addFishFeedBatch(@RequestBody List<OriginsFishFeed> list);

    /**
     * 添加渔业批号信息
     * @param list 批号信息
     * @return 添加结果
     */
    @PostMapping("/origins/fish/batch/addBatch")
    AjaxResult addFishBatchBatch(@RequestBody List<OriginsFishBatch> list);

    /**
     * 添加渔业运输信息
     * @param list 运输信息
     * @return 添加结果
     */
    @PostMapping("/origins/fish/tran/addBatch")
    AjaxResult addFishTranBatch(@RequestBody List<OriginsFishTran> list);

    /**
     * 添加种植地块信息
     * @param list 地块信息
     * @return 添加结果
     */
    @PostMapping("/origins/plant/batch/addBatch")
    AjaxResult addPlantBatchBatch(@RequestBody List<OriginsPlantBatch> list);

    /**
     * 添加种植地块信息
     * @param list 地块信息
     * @return 添加结果
     */
    @PostMapping("/origins/plant/landblock/addBatch")
    AjaxResult addPlantLandBatch(@RequestBody List<OriginsPlantLandblock> list);


    /**
     * 添加种植地块信息
     * @param list 地块信息
     * @return 添加结果
     */
    @PostMapping("/origins/plant/fertilize/addBatch")
    AjaxResult addPlantFertilizeBatch(@RequestBody List<OriginsPlantFertilize> list);

    /**
     * 添加种植地块信息
     * @param list 地块信息
     * @return 添加结果
     */
    @PostMapping("/origins/plant/pesticide/addBatch")
    AjaxResult addPlantPesticideBatch(@RequestBody List<OriginsPlantPesticide> list);

    /**
     * 添加种植地块信息
     * @param list 地块信息
     * @return 添加结果
     */
    @PostMapping("/origins/plant/reap/addBatch")
    AjaxResult addPlantReapBatch(@RequestBody List<OriginsPlantReap> list);

    /**
     * 添加圈舍信息（畜禽）
     * @param list 圈舍信息（畜禽）
     * @return 添加结果
     */
    @PostMapping("/origins/livestock/house/addBatch")
    AjaxResult addLivestockHouseBatch(@RequestBody List<OriginsLivestockHouse> list);

    /**
     * 添加批次信息（畜禽）
     * @param list 批次信息（畜禽）
     * @return 添加结果
     */
    @PostMapping("/origins/livestock/batch/addBatch")
    AjaxResult addLivestockBatchBatch(@RequestBody List<OriginsLivestockBatch> list);

    /**
     * 添加耳标信息（畜类）
     * @param list 耳标信息（畜类）
     * @return 添加结果
     */
    @PostMapping("/origins/livestock/ear/addBatch")
    AjaxResult addLivestockEarBatch(@RequestBody List<OriginsLivestockEar> list);

    /**
     * 添加饲料投喂记录信息（畜禽）
     * @param list 饲料投喂记录信息（畜禽）
     * @return 添加结果
     */
    @PostMapping("/origins/livestock/feed/addBatch")
    AjaxResult addLivestockFeedBatch(@RequestBody List<OriginsLivestockFeed> list);

    /**
     * 添加无害化处理记录信息（畜禽）
     * @param list 无害化处理记录信息（畜禽）信息
     * @return 添加结果
     */
    @PostMapping("/origins/livestock/innocent/addBatch")
    AjaxResult addLivestockInnocentBatch(@RequestBody List<OriginsLivestockInnocent> list);

    /**
     * 添加兽药使用记录信息
     * @param list 兽药使用记录信息
     * @return 添加结果
     */
    @PostMapping("/origins/livestock/use/addBatch")
    AjaxResult addLivestockUseBatch(@RequestBody List<OriginsLivestockUse> list);

    /**
     * 添加生产投料信息
     * @param list 生产投料信息
     * @return 添加结果
     */
    @PostMapping("/origins/product/feedin/addBatch")
    AjaxResult addProFeedinBatch(@RequestBody List<OriginsProFeedin> list);

    /**
     * 添加生产产品信息
     * @param list 生产产品信息
     * @return 添加结果
     */
    @PostMapping("/origins/product/prod/addBatch")
    AjaxResult addProProdBatch(@RequestBody List<OriginsProProd> list);


    /**
     * 添加灌溉信息
     * @param list 灌溉信息
     * @return 添加结果
     */
    @PostMapping("/irrigation/addBatch")
    AjaxResult addIrrigationBatch(@RequestBody List<OriginsIrrigation> list);

    /**
     * 添加采购信息
     * @param list 采购信息
     * @return 添加结果
     */
    @PostMapping("/origins/purchase/addBatch")
    AjaxResult addPurchaseBatch(@RequestBody List<OriginsPurchase> list);
}

