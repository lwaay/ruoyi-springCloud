package com.sinonc.base.api;

import com.sinonc.base.api.config.FeignConfig;
import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.api.domain.YouzanSell;
import com.sinonc.base.api.factory.RemoteResourceFallbackFactory;
import com.sinonc.base.api.factory.RemoteYSellFallbackFactory;
import com.sinonc.common.core.constant.ServiceNameConstants;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxinlong
 * @date 2020/11/7  10:12
 */
@FeignClient(contextId = "remoteYSellService", value = ServiceNameConstants.BASE_SERVICE,
        fallbackFactory = RemoteYSellFallbackFactory.class, configuration = FeignConfig.class)
public interface RemoteYSellService {

    /**
     * 新增有赞云销售数据
     *
     * @param youzanSell
     * @return
     */
    @PostMapping("/ysell/add")
    R<AjaxResult> addYouzanSell(@RequestBody YouzanSell youzanSell);


    /**
     * 查询有赞云销售数据列表
     *
     * @return
     */
    @PostMapping(value = "/ysell/api")
    List<YouzanSell> getYouzanSellList(@RequestBody YouzanSell youzanSell);


    /**
     * 同步所有门店订单
     *
     * @return
     */
    @PostMapping("/ysell/sysncAllNodeKdTrade")
    R<AjaxResult> sysncAllNodeKdTrade();

}
