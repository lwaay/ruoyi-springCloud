package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Qnygnb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 轻农业信丰脐橙果农版用户关联 数据层
 *
 * @author sinonc
 * @date 2019-12-11
 */
@Mapper
public interface QnygnbMapper {
	/**
     * 查询轻农业信丰脐橙果农版用户关联信息
     *
     * @param qnyId 轻农业信丰脐橙果农版用户关联ID
     * @return 轻农业信丰脐橙果农版用户关联信息
     */
	public Qnygnb selectQnygnbById(Long qnyId);

	/**
     * 查询轻农业信丰脐橙果农版用户关联列表
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 轻农业信丰脐橙果农版用户关联集合
     */
	public List<Qnygnb> selectQnygnbList(Qnygnb qnygnb);

	/**
     * 新增轻农业信丰脐橙果农版用户关联
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 结果
     */
	public int insertQnygnb(Qnygnb qnygnb);

	/**
     * 修改轻农业信丰脐橙果农版用户关联
     *
     * @param qnygnb 轻农业信丰脐橙果农版用户关联信息
     * @return 结果
     */
	public int updateQnygnb(Qnygnb qnygnb);

	/**
     * 删除轻农业信丰脐橙果农版用户关联
     *
     * @param qnyId 轻农业信丰脐橙果农版用户关联ID
     * @return 结果
     */
	public int deleteQnygnbById(Long qnyId);

	/**
     * 批量删除轻农业信丰脐橙果农版用户关联
     *
     * @param qnyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteQnygnbByIds(String[] qnyIds);

	/**
	 * 根据shopId查询轻农业本店铺的问题
	 * @param shopId
	 * @return
	 */
    List<Qnygnb> selectQnyGnbListByShopId(@Param("shopId") Long shopId);

	/**
	 * 根据shopId查询轻农业本店铺的问题根据年月查问题
	 * @param shopId
	 * @param year
	 * @param month
	 * @return
	 */
	List<Qnygnb> selectQnyGnbListByShopIdForYearAndMonth(@Param("shopId") Long shopId, @Param("year") String year, @Param("month") String month);
}
