package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AdoptionCircle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 朋友圈 数据层
 *
 * @author sinonc
 * @date 2019-08-14
 */
@Mapper
public interface AdoptionCircleMapper {
	/**
     * 查询朋友圈信息
     *
     * @param adoptionId 朋友圈ID
     * @return 朋友圈信息
     */
	public AdoptionCircle selectAdoptionCircleById(Long adoptionId);

	/**
     * 查询朋友圈列表
     *
     * @param adoptionCircle 朋友圈信息
     * @return 朋友圈集合
     */
	public List<AdoptionCircle> selectAdoptionCircleList(AdoptionCircle adoptionCircle);

	/**
     * 新增朋友圈
     *
     * @param adoptionCircle 朋友圈信息
     * @return 结果
     */
	public int insertAdoptionCircle(AdoptionCircle adoptionCircle);

	/**
     * 修改朋友圈
     *
     * @param adoptionCircle 朋友圈信息
     * @return 结果
     */
	public int updateAdoptionCircle(AdoptionCircle adoptionCircle);

	/**
     * 删除朋友圈
     *
     * @param adoptionId 朋友圈ID
     * @return 结果
     */
	public int deleteAdoptionCircleById(Long adoptionId);

	/**
     * 批量删除朋友圈
     *
     * @param adoptionIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAdoptionCircleByIds(String[] adoptionIds);

	/**
	 * API查询朋友圈信息
	 * @return
	 */
    List<Map<String, Object>> selectAdoptionCircleListForApi();

	/**
	 * API查询朋友圈信息
	 * @return
	 */
	List<Map<String, Object>> selectAdoptionCircleListForApiTwo();

	int updateAdoptionCircleWithMemberId(AdoptionCircle adoptionCircle);
}
