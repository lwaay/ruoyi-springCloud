package com.sinonc.base.api.factory;

import com.sinonc.base.api.RemoteResourceService;
import com.sinonc.base.api.RemoteYSellService;
import com.sinonc.base.api.domain.FileResource;
import com.sinonc.base.api.domain.YouzanSell;
import com.sinonc.base.api.vo.FileResourceVo;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxinlong
 * @apiNote TODO
 * @date 2020/11/7  10:14
 */
public class RemoteYSellFallbackFactory implements FallbackFactory<RemoteYSellService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteYSellFallbackFactory.class);

    @Override
    public RemoteYSellService create(Throwable throwable) {
        log.error("添加网络销售数据调用失败:{}", throwable.getMessage());
        return new RemoteYSellService() {
            @Override
            public R<AjaxResult> addYouzanSell(YouzanSell youzanSell) {
                return R.fail("添加网络销售数据调用失败：" + throwable.getMessage());
            }

            @Override
            public List<YouzanSell> getYouzanSellList(YouzanSell youzanSell) {
                return new ArrayList<>();
            }

            @Override
            public R<AjaxResult> sysncAllNodeKdTrade() {
                return R.fail("同步所有店铺订单任务调用失败：" + throwable.getMessage());
            }

        };
    }

}
