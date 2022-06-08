package com.sinonc.orders.service.impl;

import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.payment.notify.PayMessage;
import com.sinonc.common.payment.notify.PayObserver;
import com.sinonc.common.payment.notify.RefundMessage;
import com.sinonc.common.payment.notify.RefundObserver;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.orders.common.OrderConstant;
import com.sinonc.orders.domain.*;
import com.sinonc.orders.mapper.*;
import com.sinonc.orders.service.AdoptItemService;
import com.sinonc.orders.vo.AdoptItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 认养服务明细 服务层实现
 *
 * @author sinonc
 * @date 2019-08-09
 */
@Service
public class AdoptItemServiceImpl implements AdoptItemService, PayObserver, RefundObserver {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private SpecsMapper specsMapper;

    @Autowired
    private OdSpecsSpecsvalueMapper specsvalueMapper;

    @Autowired
    private SpecsValueMapper specsValueMapper;

    @Autowired
    private AdoptItemMapper adoptItemMapper;

    @Autowired
    private AdoptUseMapper adoptUseMapper;

    /**
     * 查询认养服务明细信息
     *
     * @param adoptItemId 认养服务明细ID
     * @return 认养服务明细信息
     */
    @Override
    public AdoptItem getAdoptItemById(Long adoptItemId) {
        return adoptItemMapper.selectAdoptItemById(adoptItemId);
    }

    /**
     * 查询认养服务明细列表
     *
     * @param adoptItem 认养服务明细信息
     * @return 认养服务明细集合
     */
    @Override
    public List<AdoptItem> listAdoptItem(AdoptItem adoptItem) {
        adoptItem.setDelFlag(0);
        return adoptItemMapper.selectAdoptItemList(adoptItem);
    }

    /**
     * 查询认养服务视图明细列表
     *
     * @param adoptItemVo 认养服务视图对象明细信息
     * @return 认养服务视图对象列表
     */
    @Override
    public List<AdoptItemVo> listAdoptItemVo(AdoptItemVo adoptItemVo) {
        adoptItemVo.setDelFlag(0);
        return adoptItemMapper.selectAdoptItemVoList(adoptItemVo);

    }

    /**
     * 服务项目核销
     *
     * @param adoptItem 服务项目
     * @return
     */
    @Override
    @Transactional
    public int AdoptItemUse(AdoptItem adoptItem) {


        //获取当前登陆用户名
        String loginName = SecurityUtils.getUsername();

        //查询已有记录
        AdoptItem update = adoptItemMapper.selectAdoptItemById(adoptItem.getAdoptItemId());

        if (update == null) {
            throw new BusinessException("服务项目不存在");
        }

        //判断剩余次数是否大于核销次数
        int num = update.getNumber() - adoptItem.getUseNumber();

        if (num < 0) {
            throw new BusinessException("剩余使用次数不足");
        }


        update.setUpdateTime(new Date());
        update.setUpdateBy(loginName);
        update.setUseTime(new Date());
        update.setItemStatus(1);
        //更新剩余使用次数
        update.setNumber(num);
        //更新已使用次数
        update.setUseNumber(update.getUseNumber() + adoptItem.getUseNumber());

        AdoptUse adoptUse = new AdoptUse();
        adoptUse.setCreateBy(loginName);
        adoptUse.setCreateTime(new Date());
        adoptUse.setItemIdP(update.getAdoptItemId());
        adoptUse.setItemType(update.getItemType());
        adoptUse.setNumbers(adoptItem.getUseNumber());
        adoptUse.setSpecsValue(update.getItemName());
        adoptUse.setUnit(update.getUnit());
        adoptUse.setUseInfo(adoptItem.getRemark());

        //修改服务明细记录
        int updateRows = adoptItemMapper.updateAdoptItem(update);
        //添加核销记录
        int insertRows = adoptUseMapper.insertAdoptUse(adoptUse);

        return updateRows * insertRows;
    }


    /**
     * 新增认养服务明细
     *
     * @param adoptItem 认养服务明细信息
     * @return 结果
     */
    @Override
    public int addAdoptItem(AdoptItem adoptItem) {

        return adoptItemMapper.insertAdoptItem(adoptItem);
    }

    /**
     * 修改认养服务明细
     *
     * @param adoptItem 认养服务明细信息
     * @return 结果
     */
    @Override
    public int updateAdoptItem(AdoptItem adoptItem) {
        String loginName = SecurityUtils.getUsername();
        adoptItem.setUpdateTime(new Date());
        adoptItem.setUpdateBy(loginName);
        return adoptItemMapper.updateAdoptItem(adoptItem);
    }

    /**
     * 删除认养服务明细对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAdoptItemByIds(String ids) {
        return adoptItemMapper.deleteAdoptItemByIds(Convert.toStrArray(ids));
    }


    @Override
    public void payNotify(PayMessage message) throws Exception {

        Date date = new Date();
        String orderNo = message.getOrderNo();

        //判断是否为认养单
        if (orderNo.contains("DJ") || orderNo.contains("WK") || orderNo.contains("JP")) {
            return;
        }

        Order order = orderMapper.selectOrderByNo(orderNo);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderIdP(order.getOrderId());
        List<OrderItem> orderItems = orderItemMapper.selectOrderItemList(orderItem);


        //判断订单类型，如果是认养订单，则将服务项写入服务项目表
        if (OrderConstant.TYPE_ADOPT == order.getOrderType()) {

            for (OrderItem item : orderItems) {

                Specs specs = specsMapper.selectSpecsById(item.getGoodsSpecsIdP());
                //获取规格id获取规格关联关系
                List<OdSpecsSpecsvalue> odSpecsSpecsvalues = specsvalueMapper.selectSpecsSpecsValueBySpecsId(item.getGoodsSpecsIdP());

                for (OdSpecsSpecsvalue odSpecsSpecsvalue : odSpecsSpecsvalues) {

                    //根据规格关联关系获取规格值
                    SpecsValue specsValue = specsValueMapper.selectSpecsValueById(odSpecsSpecsvalue.getSpecsValueIdp());

                    AdoptItem adoptItem = new AdoptItem();
                    adoptItem.setOrderIdP(order.getOrderId());
                    adoptItem.setCreateTime(date);
                    adoptItem.setStartTime(date);
                    adoptItem.setEndTime(DateUtils.addDays(date, 365));
                    adoptItem.setGoodsName(item.getGoodsName());
                    adoptItem.setSpecsName(specs.getSpecsName());
                    adoptItem.setItemType(specsValue.getType());
                    adoptItem.setItemName(specsValue.getSpecsValue());
                    adoptItem.setMemberIdP(order.getMemberIdP());
                    adoptItem.setOrderNo(order.getOrderNo());
                    adoptItem.setUnit(specsValue.getUnit());
                    adoptItem.setNumber(specsValue.getNumber());

                    //判断认养套餐的份数，如果是多份，则对明细表插入对应份数的服务项目
                    for (int i = 0; i < item.getGoodsCount(); i++) {
                        adoptItemMapper.insertAdoptItem(adoptItem);
                    }
                }

            }
        }
    }

    @Override
    public void refundNotify(RefundMessage message) throws Exception {

        String orderNo = message.getOrderNo();

        //判断是否为认养单
        if (orderNo.contains("DJ") || orderNo.contains("WK") || orderNo.contains("JP")) {
            return;
        }

        //退款则将服务项目状态调整为已过期
        Order order = orderMapper.selectOrderByNo(orderNo);

        AdoptItem adoptItem = new AdoptItem();
        adoptItem.setOrderIdP(order.getOrderId());
        List<AdoptItem> items = adoptItemMapper.selectAdoptItemList(adoptItem);

        for (AdoptItem item : items) {
            item.setItemStatus(2);
            adoptItemMapper.updateAdoptItem(item);
        }

    }
}
