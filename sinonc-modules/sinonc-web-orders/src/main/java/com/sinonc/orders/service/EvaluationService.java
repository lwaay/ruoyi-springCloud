package com.sinonc.orders.service;

import com.sinonc.orders.domain.Evaluation;

import java.util.List;
import java.util.Map;

/**
 * 订单评论 服务层
 *
 * @author sinonc
 * @date 2019-12-24
 */
public interface EvaluationService {

	/**
     * 查询订单评论信息
     *
     * @param evaluationId 订单评论ID
     * @return 订单评论信息
     */
	public Evaluation getEvaluationById(Long evaluationId);

	/**
     * 查询订单评论列表
     *
     * @param evaluation 订单评论信息
     * @return 订单评论集合
     */
	public List<Evaluation> listEvaluation(Evaluation evaluation);

	/**
     * 新增订单评论
     *
     * @param evaluation 订单评论信息
     * @return 结果
     */
	public int addEvaluation(Evaluation evaluation);

	/**
     * 修改订单评论
     *
     * @param evaluation 订单评论信息
     * @return 结果
     */
	public int updateEvaluation(Evaluation evaluation);

	/**
     * 删除订单评论信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteEvaluationByIds(String ids);

	/**
	 * 获取区间列表
	 * @param goodsId 商品id
	 * @param stars 分数区间
	 * @return 结果
	 */
	public List<Evaluation> getListBetweenStar(Long goodsId, String stars);

	/**
	 * 获取好中差评数量
	 * @param goodsId 商品id
	 * @return 结果
	 */
	public Map<String, String> getStarTypeCount(Long goodsId);

}
