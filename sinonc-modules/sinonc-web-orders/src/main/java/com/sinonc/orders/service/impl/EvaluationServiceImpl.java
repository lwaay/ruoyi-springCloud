package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.orders.common.OrderConstant;
import com.sinonc.orders.domain.Evaluation;
import com.sinonc.orders.domain.Order;
import com.sinonc.orders.domain.OrderItem;
import com.sinonc.orders.mapper.EvaluationMapper;
import com.sinonc.orders.mapper.OrderItemMapper;
import com.sinonc.orders.mapper.OrderMapper;
import com.sinonc.orders.service.EvaluationService;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单评论 服务层实现
 *
 * @author sinonc
 * @date 2019-12-24
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 查询订单评论信息
     *
     * @param evaluationId 订单评论ID
     * @return 订单评论信息
     */
    @Override
    public Evaluation getEvaluationById(Long evaluationId) {
        return evaluationMapper.selectEvaluationById(evaluationId);
    }

    /**
     * 查询订单评论列表
     *
     * @param evaluation 订单评论信息
     * @return 订单评论集合
     */
    @Override
    public List<Evaluation> listEvaluation(Evaluation evaluation) {
        return evaluationMapper.selectEvaluationList(evaluation);
    }

    /**
     * 新增订单评论
     *
     * @param evaluation 订单评论信息
     * @return 结果
     */
    @Override
    @Transactional
    public int addEvaluation(Evaluation evaluation) {

        Order order = orderMapper.selectOrderById(evaluation.getOrderId());

        if (order == null) {
            throw new BusinessException("订单不存在");
        }

        if (!order.getMemberIdP().equals(evaluation.getMemberId())) {
            throw new BusinessException("订单身份信息不符");
        }

        OrderItem orderItem = orderItemMapper.selectOrderItemByOrderId(order.getOrderId());

        Date date = new Date();
        evaluation.setOrderId(order.getOrderId());
        evaluation.setGoodsId(orderItem.getGoodsIdP());
        evaluation.setShopId(order.getShopIdP());
        evaluation.setCreateTime(date);
        evaluation.setUpdateTime(date);

        //更新订单评价状态
        Order update = new Order();
        update.setOrderId(order.getOrderId());
        update.setOrderStatus(OrderConstant.STATUS_TRADE_SUCCESSFUL);
        update.setEvaluationStatus(1);
        update.setUpdateTime(date);

        orderMapper.updateOrder(update);
        evaluation.setEvaluationContent(EmojiParser.parseToAliases(evaluation.getEvaluationContent()));
        return evaluationMapper.insertEvaluation(evaluation);
    }

    /**
     * 修改订单评论
     *
     * @param evaluation 订单评论信息
     * @return 结果
     */
    @Override
    public int updateEvaluation(Evaluation evaluation) {
        return evaluationMapper.updateEvaluation(evaluation);
    }

    /**
     * 删除订单评论对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEvaluationByIds(String ids) {
        return evaluationMapper.deleteEvaluationByIds(Convert.toStrArray(ids));
    }

    @Override
    public List<Evaluation> getListBetweenStar(Long goodsId, String stars) {
        return evaluationMapper.selectListBetweenStar(goodsId, StringUtils.isNotEmpty(stars) ? stars.split(",") : null);
    }

    @Override
    public Map<String, String> getStarTypeCount(Long goodsId) {
        return evaluationMapper.selectStarTypeCount(goodsId);
    }

}
