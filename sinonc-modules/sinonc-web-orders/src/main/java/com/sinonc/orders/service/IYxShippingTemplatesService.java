package com.sinonc.orders.service;

import java.math.BigDecimal;
import java.util.List;

import com.sinonc.orders.domain.Address;
import com.sinonc.orders.domain.OrderItem;
import com.sinonc.orders.domain.YxShippingTemplates;
import com.sinonc.orders.dto.ShippingTemplatesDto;

/**
 * 运费模板Service接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface IYxShippingTemplatesService {
    /**
     * 查询运费模板
     *
     * @param id 运费模板ID
     * @return 运费模板
     */
    public YxShippingTemplates selectYxShippingTemplatesById(Integer id);

    /**
     * 查询运费模板列表
     *
     * @param yxShippingTemplates 运费模板
     * @return 运费模板集合
     */
    public List<YxShippingTemplates> selectYxShippingTemplatesList(YxShippingTemplates yxShippingTemplates);

    /**
     * 新增运费模板
     *
     * @param yxShippingTemplates 运费模板
     * @return 结果
     */
    public int insertYxShippingTemplates(YxShippingTemplates yxShippingTemplates);

    /**
     * 修改运费模板
     *
     * @param yxShippingTemplates 运费模板
     * @return 结果
     */
    public int updateYxShippingTemplates(YxShippingTemplates yxShippingTemplates);

    /**
     * 修改运费模板
     *
     * @param yxShippingTemplates 运费模板
     * @return 结果
     */
    public int saveYxShippingTemplates(ShippingTemplatesDto shippingTemplatesDto);

    /**
     * 批量删除运费模板
     *
     * @param ids 需要删除的运费模板ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesByIds(Integer[] ids);

    /**
     * 删除运费模板信息
     *
     * @param id 运费模板ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesById(Integer id);


    /**
     * 根据运费模板算法返回邮费
     * @param userAddress 地址
     * @return double
     */
    public BigDecimal handlePostage(List<OrderItem> items, Address userAddress);
}
