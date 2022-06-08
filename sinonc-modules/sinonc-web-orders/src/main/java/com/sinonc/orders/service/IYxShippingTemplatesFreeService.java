package com.sinonc.orders.service;

import java.util.List;

import com.sinonc.orders.domain.YxShippingTemplatesFree;

/**
 * 模板运费表Service接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface IYxShippingTemplatesFreeService {
    /**
     * 查询模板运费表
     *
     * @param id 模板运费表ID
     * @return 模板运费表
     */
    public YxShippingTemplatesFree selectYxShippingTemplatesFreeById(Long id);

    /**
     * 查询模板运费表列表
     *
     * @param yxShippingTemplatesFree 模板运费表
     * @return 模板运费表集合
     */
    public List<YxShippingTemplatesFree> selectYxShippingTemplatesFreeList(YxShippingTemplatesFree yxShippingTemplatesFree);

    /**
     * 新增模板运费表
     *
     * @param yxShippingTemplatesFree 模板运费表
     * @return 结果
     */
    public int insertYxShippingTemplatesFree(YxShippingTemplatesFree yxShippingTemplatesFree);

    /**
     * 修改模板运费表
     *
     * @param yxShippingTemplatesFree 模板运费表
     * @return 结果
     */
    public int updateYxShippingTemplatesFree(YxShippingTemplatesFree yxShippingTemplatesFree);

    /**
     * 批量删除模板运费表
     *
     * @param ids 需要删除的模板运费表ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesFreeByIds(Long[] ids);

    /**
     * 删除模板运费表信息
     *
     * @param id 模板运费表ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesFreeById(Long id);
}
