package com.sinonc.orders.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.sinonc.orders.domain.YxShippingTemplatesFree;
import org.apache.ibatis.annotations.Param;

/**
 * 模板运费表Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-23
 */
public interface YxShippingTemplatesFreeMapper {
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
     * 删除模板运费表
     *
     * @param id 模板运费表ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesFreeById(Long id);

    /**
     * 批量删除模板运费表
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYxShippingTemplatesFreeByIds(Long[] ids);

    /**
     * 根据模板id删除运费
     */
    public int deleteFreeByTempId(Integer tempId);

    /**
     * 根据运费查询是否包邮
     */
    public Long countFree(@Param("tempId") Integer tempId,@Param("cityId") Integer cityId,@Param("number") Double number,@Param("price") BigDecimal price);
}
