package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Express;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 物流 数据层
 *
 * @author sinonc
 * @date 2019-07-25
 */
@Mapper
public interface ExpressMapper {
	/**
     * 查询物流信息
     *
     * @param expressId 物流ID
     * @return 物流信息
     */
	public Express selectExpressById(Long expressId);

	/**
	 * 根据订单修改物流
	 * @param orderId
	 * @return
	 */
	public int updateExpressByOrderId(Express express);

	/**
     * 查询物流列表
     *
     * @param express 物流信息
     * @return 物流集合
     */
	public List<Express> selectExpressList(Express express);

	/**
     * 新增物流
     *
     * @param express 物流信息
     * @return 结果
     */
	public int insertExpress(Express express);

	/**
     * 修改物流
     *
     * @param express 物流信息
     * @return 结果
     */
	public int updateExpress(Express express);

	/**
     * 删除物流
     *
     * @param expressId 物流ID
     * @return 结果
     */
	public int deleteExpressById(Long expressId);

	/**
     * 批量删除物流
     *
     * @param expressIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteExpressByIds(String[] expressIds);

	/**
	 * 获取物流信息
	 *
	 * @param orderId
	 * @return
	 */
    public Express selectExpressByOrderId(Long orderId);
}
