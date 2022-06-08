package com.sinonc.orders.ec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinonc.orders.ec.domain.EshopProductRealtime;

import java.util.List;

/**
 * 实时爬取电商数据Service接口
 *
 * @author hhao
 * @date 2021-03-24
 */
public interface IEshopProductRealtimeService extends IService<EshopProductRealtime> {
    /**
     * 查询实时爬取电商数据
     *
     * @param eshopId 实时爬取电商数据ID
     * @return 实时爬取电商数据
     */
    public EshopProductRealtime selectEshopProductRealtimeById(Long eshopId);

    /**
     * 查询实时爬取电商数据列表
     *
     * @param eshopProductRealtime 实时爬取电商数据
     * @return 实时爬取电商数据集合
     */
    public List<EshopProductRealtime> selectEshopProductRealtimeList(EshopProductRealtime eshopProductRealtime);

    /**
     * 新增实时爬取电商数据
     *
     * @param eshopProductRealtime 实时爬取电商数据
     * @return 结果
     */
    public int insertEshopProductRealtime(EshopProductRealtime eshopProductRealtime);

    /**
     * 修改实时爬取电商数据
     *
     * @param eshopProductRealtime 实时爬取电商数据
     * @return 结果
     */
    public int updateEshopProductRealtime(EshopProductRealtime eshopProductRealtime);

    /**
     * 批量删除实时爬取电商数据
     *
     * @param eshopIds 需要删除的实时爬取电商数据ID
     * @return 结果
     */
    public int deleteEshopProductRealtimeByIds(Long[] eshopIds);

    /**
     * 删除实时爬取电商数据信息
     *
     * @param eshopId 实时爬取电商数据ID
     * @return 结果
     */
    public int deleteEshopProductRealtimeById(Long eshopId);

    /**
     * 获取开启状态的realTime定时任务
     * @return
     */
    public List<EshopProductRealtime> listEshopProductRealtimeTask();
}
