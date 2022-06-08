package com.sinonc.iot.api.factory;


import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.iot.api.RemoteIotService;
import com.sinonc.iot.api.domain.DeviceInfo;
import com.sinonc.iot.api.domain.DeviceMonitor;
import com.sinonc.iot.api.domain.DeviceProperty;
import com.sinonc.iot.api.domain.DeviceType;
import com.sinonc.iot.api.dto.ProdEnviStatisticsDto;
import com.sinonc.iot.api.vo.DevicePropertyVo;
import com.sinonc.iot.api.vo.ProStaticVo;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author yehuiwang
 * @apiNote 物联网信息降级
 * @date 2020/10/26 9:57
 */
public class RemoteIotFallbackFactory implements FallbackFactory<RemoteIotService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteIotFallbackFactory.class);

    @Override
    public RemoteIotService create(Throwable throwable) {
        log.error("物联网服务调用失败:{}", throwable.getMessage());
        return new RemoteIotService() {
            @Override
            public R<DeviceInfo> getDeviceInfo(Long id) {
                return R.fail("获取设备信息失败：" + throwable.getMessage());
            }

            @Override
            public Integer getDeviceCount() {
                System.out.println("获取设备总数失败：" + throwable.getMessage());
                return 0;
            }

            @Override
            public Integer getMonitorCount() {
                System.out.println("获取监控总数失败：" + throwable.getMessage());
                return 0;
            }

            @Override
            public R<ProdEnviStatisticsDto> getProStatic(ProStaticVo proStaticVo) {
                System.out.println("获取设备数据统计失败：" + throwable.getMessage());
                return R.fail("获取设备数据统计失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult realData(String deviceId) {
                System.out.println("获取实时数据失败：" + throwable.getMessage());
                return AjaxResult.error("获取实时数据失败：" + throwable.getMessage());
            }

            //            @Override
//            public R<AjaxResult> put(List<DataPoint> dataPoints) {
//
//                return R.fail("上报失败");
//            }
//
//            @Override
//            public R<AjaxResult> query(QueryDataVo queryDataVo) {
//                return R.fail("查询失败");
//            }
//


            @Override
            public R<List<DevicePropertyVo>> propertyList() {
                return R.fail("查询实时数据失败");
            }

            @Override
            public AjaxResult getMonitorList(Long farmId) {
                System.out.println("获取实时数据失败：" + throwable.getMessage());
                return AjaxResult.error("获取实时数据失败：" + throwable.getMessage());
            }
            @Override
            public R<List<DeviceMonitor>> getMonitorListByFarm(Long farmId) {
                System.out.println("获取实时数据失败：" + throwable.getMessage());
                return R.fail("获取实时数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult addMonitor(DeviceMonitor deviceMonitor) {
                System.out.println("获取实时数据失败：" + throwable.getMessage());
                return AjaxResult.error("获取实时数据失败：" + throwable.getMessage());
            }

            @Override
            public AjaxResult getMonitorDetail(Long id) {
                System.out.println("获取实时数据失败：" + throwable.getMessage());
                return AjaxResult.error("获取实时数据失败：" + throwable.getMessage());
            }

            @Override
            public R<DeviceType> getDeviceType(String type) {
                return R.fail("获取List<DeviceType>数据失败");
            }

            @Override
            public R<List<DeviceInfo>> getDeviceList(String typeId) {
                return R.fail("获取List<DeviceInfo>数据失败");
            }

            @Override
            public R<List<DeviceProperty>> getPropertyByType(String id) {
                return R.fail("获取List<DeviceProperty>数据失败");
            }

            @Override
            public R<Integer> insertDevice(DeviceInfo deviceInfo) {
                return R.fail("插入DeviceInfo数据失败");
            }

            @Override
            public R<Integer> warnCount() {
                return R.ok(0);
            }
        };
    }
}
