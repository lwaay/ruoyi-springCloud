package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteOriginsService;
import com.sinonc.base.api.domain.*;
import com.sinonc.common.core.web.domain.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huanghao
 * @apiNote 基础信息降级
 * @date 2020/10/26 9:57
 */
@Component
public class RemoteOriginsFallbackFactory implements FallbackFactory<RemoteOriginsService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteOriginsFallbackFactory.class);

    @Override
    public RemoteOriginsService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteOriginsService() {
            @Override
            public AjaxResult addCropBatch(List<OriginsCrop> list) {
                log.error(throwable.toString());
                return AjaxResult.error("添加企业数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addFishPondBatch(List<OriginsFishPond> list) {
                return AjaxResult.error("添加渔业池塘数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addFishMedicineBatch(List<OriginsFishMedicine> list) {
                return AjaxResult.error("添加渔业用药数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addFishFeedBatch(List<OriginsFishFeed> list) {
                return AjaxResult.error("添加渔业投喂数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addFishBatchBatch(List<OriginsFishBatch> list) {
                return AjaxResult.error("添加渔业批号数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addFishTranBatch(List<OriginsFishTran> list) {
                return AjaxResult.error("添加渔业运输数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addPlantBatchBatch(List<OriginsPlantBatch> list) {
                return AjaxResult.error("添加种植批号数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addPlantLandBatch(List<OriginsPlantLandblock> list) {
                return AjaxResult.error("添加种植地块数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addPlantFertilizeBatch(List<OriginsPlantFertilize> list) {
                return AjaxResult.error("添加种植施肥数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addPlantPesticideBatch(List<OriginsPlantPesticide> list) {
                return AjaxResult.error("添加种植施药数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addPlantReapBatch(List<OriginsPlantReap> list) {
                return AjaxResult.error("添加种植采收数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addLivestockHouseBatch(List<OriginsLivestockHouse> list) {
                return AjaxResult.error("添加圈舍信息失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addLivestockBatchBatch(List<OriginsLivestockBatch> list) {
                return AjaxResult.error("添加批次信息失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addLivestockEarBatch(List<OriginsLivestockEar> list) {
                return AjaxResult.error("添加耳标信息失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addLivestockFeedBatch(List<OriginsLivestockFeed> list) {
                return AjaxResult.error("添加饲料投喂记录信息失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addLivestockInnocentBatch(List<OriginsLivestockInnocent> list) {
                return AjaxResult.error("添加无害化处理记录信息失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addLivestockUseBatch(List<OriginsLivestockUse> list) {
                return AjaxResult.error("添加兽药使用记录信息数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addProFeedinBatch(List<OriginsProFeedin> list) {
                return AjaxResult.error("添加生产投料信息失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addProProdBatch(List<OriginsProProd> list) {
                return AjaxResult.error("添加生产产品信息失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addIrrigationBatch(List<OriginsIrrigation> list) {
                return AjaxResult.error("添加灌溉信息失败：" + throwable.getMessage());
            }
            @Override
            public AjaxResult addPurchaseBatch(List<OriginsPurchase> list) {
                return AjaxResult.error("添加采购信息失败：" + throwable.getMessage());
            }
        };
    }
}
