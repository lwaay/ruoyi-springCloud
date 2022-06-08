package com.sinonc.orders.mapper;

import java.util.List;

import com.sinonc.orders.domain.YxShippingTemplates;

/**
 * 运费模板Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface YxShippingTemplatesMapper {
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
     * 删除运费模板
     *
     * @param id 运费模板ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesById(Integer id);

    /**
     * 批量删除运费模板
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesByIds(Integer[] ids);

    /**
     * 获取我的运费模板
     */
    public List<YxShippingTemplates> choseTemplates(YxShippingTemplates yxShippingTemplates);
}
