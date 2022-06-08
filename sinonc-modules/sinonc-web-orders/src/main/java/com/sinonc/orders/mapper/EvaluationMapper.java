package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Evaluation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 订单评论 数据层
 *
 * @author sinonc
 * @date 2019-12-24
 */
@Mapper
public interface EvaluationMapper {
	/**
     * 查询订单评论信息
     *
     * @param evaluationId 订单评论ID
     * @return 订单评论信息
     */
	public Evaluation selectEvaluationById(Long evaluationId);

	/**
     * 查询订单评论列表
     *
     * @param evaluation 订单评论信息
     * @return 订单评论集合
     */
	public List<Evaluation> selectEvaluationList(Evaluation evaluation);

	/**
     * 新增订单评论
     *
     * @param evaluation 订单评论信息
     * @return 结果
     */
	public int insertEvaluation(Evaluation evaluation);

	/**
     * 修改订单评论
     *
     * @param evaluation 订单评论信息
     * @return 结果
     */
	public int updateEvaluation(Evaluation evaluation);

	/**
     * 删除订单评论
     *
     * @param evaluationId 订单评论ID
     * @return 结果
     */
	public int deleteEvaluationById(Long evaluationId);

	/**
     * 批量删除订单评论
     *
     * @param evaluationIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteEvaluationByIds(String[] evaluationIds);


	/**
	 * 查询星星区间
	 * @param goodsId
	 * @param stars
	 * @return
	 */
	List<Evaluation> selectListBetweenStar(Long goodsId, String[] stars);

	/**
	 * 查询 好、中、差评数量
	 * @param goodsId 结果
	 * @return
	 */
	Map<String, String> selectStarTypeCount(Long goodsId);
}
