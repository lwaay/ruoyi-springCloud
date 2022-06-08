package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.ExpPrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 物流价格模版 数据层
 *
 * @author sinonc
 * @date 2019-11-26
 */
@Mapper
public interface ExpPriceMapper {
	/**
     * 查询物流价格模版信息
     *
     * @param expPriceId 物流价格模版ID
     * @return 物流价格模版信息
     */
	public ExpPrice selectExpPriceById(Long expPriceId);

	/**
     * 查询物流价格模版列表
     *
     * @param expPrice 物流价格模版信息
     * @return 物流价格模版集合
     */
	public List<ExpPrice> selectExpPriceList(ExpPrice expPrice);

	/**
     * 新增物流价格模版
     *
     * @param expPrice 物流价格模版信息
     * @return 结果
     */
	public int insertExpPrice(ExpPrice expPrice);

	/**
     * 修改物流价格模版
     *
     * @param expPrice 物流价格模版信息
     * @return 结果
     */
	public int updateExpPrice(ExpPrice expPrice);

	/**
     * 删除物流价格模版
     *
     * @param expPriceId 物流价格模版ID
     * @return 结果
     */
	public int deleteExpPriceById(Long expPriceId);

	/**
     * 批量删除物流价格模版
     *
     * @param expPriceIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteExpPriceByIds(String[] expPriceIds);

	/**
	 * 根据店铺ID删除物流价格模版记录
	 * @param shopId
	 * @return
	 */
	public int deleteExpPriceByShopId(Long shopId);

}
