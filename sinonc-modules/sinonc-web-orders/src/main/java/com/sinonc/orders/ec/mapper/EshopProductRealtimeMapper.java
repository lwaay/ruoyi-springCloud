package com.sinonc.orders.ec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sinonc.orders.ec.domain.EshopProductRealtime;

import java.util.List;

/**
 * 实时爬取电商数据Mapper接口
 *
 * @author hhao
 * @date 2021-03-24
 */
public interface EshopProductRealtimeMapper extends BaseMapper<EshopProductRealtime> {
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
     * 删除实时爬取电商数据
     *
     * @param eshopId 实时爬取电商数据ID
     * @return 结果
     */
    public int deleteEshopProductRealtimeById(Long eshopId);

    /**
     * 批量删除实时爬取电商数据
     *
     * @param eshopIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteEshopProductRealtimeByIds(Long[] eshopIds);

    /**
     * 获取开启状态实时爬取任务商品品类
     */
    public List<String> listOpenGoodsType();

    /**
     * 获取开启状态实时爬取任务商品店铺
     */
    public List<String> listOpenStore();
}
