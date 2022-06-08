package com.sinonc.order.api;

import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.order.api.domain.Shop;
import com.sinonc.order.api.factory.RemoteShopFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author: lw
 * @date: 2022/4/15 16:53
 * @description:
 */
@FeignClient(contextId = "RemoteShopService", value = ServiceNameConstants.SHOP_ORDER_SERVICE, fallbackFactory = RemoteShopFactory.class)
public interface RemoteShopService {

    /**
     * 根据主体id获取商铺列表
     * @param entityId
     * @return
     */
    @GetMapping("/shopinfo/getShopListByEntityId/{entityId}")
    R<List<Shop>> getShopListByEntityId(@PathVariable("entityId") Long entityId);

    @GetMapping("api/goods/count")
    R<Integer> goodsCount();

}
