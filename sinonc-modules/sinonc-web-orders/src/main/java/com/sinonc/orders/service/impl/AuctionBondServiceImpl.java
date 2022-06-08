package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.payment.notify.PayMessage;
import com.sinonc.common.payment.notify.PayObserver;
import com.sinonc.common.payment.notify.RefundMessage;
import com.sinonc.common.payment.notify.RefundObserver;
import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.AuctionBond;
import com.sinonc.orders.mapper.AuctionBondMapper;
import com.sinonc.orders.mapper.AuctionMapper;
import com.sinonc.orders.service.AuctionBondService;
import com.sinonc.orders.service.RefundService;
import com.sinonc.orders.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 竞拍活动押金 服务层实现
 *
 * @author sinonc
 * @date 2019-11-19
 */
@Service
public class AuctionBondServiceImpl implements AuctionBondService, PayObserver, RefundObserver {

    @Autowired
    private AuctionBondMapper auctionBondMapper;
    @Autowired
    private AuctionMapper auctionMapper;
    @Autowired
    private RefundService refundService;

    /**
     * 查询竞拍活动押金信息
     *
     * @param auctionbondId 竞拍活动押金ID
     * @return 竞拍活动押金信息
     */
    @Override
    public AuctionBond getAuctionBondById(Long auctionbondId) {
        return auctionBondMapper.selectAuctionBondById(auctionbondId);
    }

    /**
     * 查询竞拍活动押金列表
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 竞拍活动押金集合
     */
    @Override
    public List<AuctionBond> listAuctionBond(AuctionBond auctionBond) {
        return auctionBondMapper.selectAuctionBondList(auctionBond);
    }

    /**
     * 新增竞拍活动押金
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 结果
     */
    @Override
    public int addAuctionBond(AuctionBond auctionBond) {
        BigDecimal price = new BigDecimal("0");
        Auction auction = auctionMapper.selectAuctionById(auctionBond.getAuctionId()); //查询活动需支付押金
        if (auction != null) {
            price = new BigDecimal(auction.getAuctionBond());
        }
        auctionBond.setPrice(price);
        auctionBond.setAuctionOrderno("JP" + OrderUtil.createOrderNo());
        auctionBond.setCreateTime(new Date());
        auctionBond.setPaymentStatus(0); //未支付
        int row = auctionBondMapper.insertAuctionBond(auctionBond);
        if (row > 0) {
            return Integer.parseInt(auctionBond.getAuctionbondId().toString());
        }

        return 0;
    }

    /**
     * 修改竞拍活动押金
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 结果
     */
    @Override
    public int updateAuctionBond(AuctionBond auctionBond) {
        return auctionBondMapper.updateAuctionBond(auctionBond);
    }

    /**
     * 删除竞拍活动押金对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAuctionBondByIds(String ids) {
        return auctionBondMapper.deleteAuctionBondByIds(Convert.toStrArray(ids));
    }

    /**
     * 查询用户是否交押金
     *
     * @param memberId
     * @return
     */
    @Override
    public AuctionBond selectApiAuctionBond(Long memberId, Long goodsId, Integer status) {
        return auctionBondMapper.selectApiAuctionBond(memberId, goodsId, status);
    }

    /**
     * 押金退还逻辑
     *
     * @return
     */
    @Override
    public boolean refundAuctionBond(Auction auction) {
        return false;
    }

    /**
     * 竞拍活动结束，标识最终获得者
     * @param memberId
     * @param goodsId
     * @return
     */
    @Override
    @Transactional
    public int updateAuctionBoonForWin(Long memberId, Long goodsId) {
        AuctionBond auctionBond =  auctionBondMapper.selectApiAuctionBond(memberId,goodsId, null);
        if(auctionBond !=null){
            auctionBond.setWin(Integer.parseInt(auctionBond.getMemberId().toString()));
            return auctionBondMapper.updateAuctionBond(auctionBond);
        }
        return 0;
    }


    /**
     * 活动结束后除获得者其他参与竞拍者自动退款
     * @param auctionId
     */
    @Override
    public void auctionTK(Long auctionId) throws Exception{
        //根据竞拍id查询竞拍押金,（除了获得者，已支付押金的的所有押金）
        List<AuctionBond> list =  auctionBondMapper.selectAuctionBondForAuctionId(auctionId);
        if(list.size()>0){
            for (AuctionBond auctionBond:list) {
                refundService.refund(auctionBond.getAuctionOrderno(),auctionBond.getPrice(),"竞拍结束自动退款","System");
            }
        }
    }

    /**
     * 活动结束后获得者退款
     * @param auctionId
     * @return
     */
    @Override
    public Integer tkOwnAuction(Long auctionId) throws Exception{
        AuctionBond auctionBond =  auctionBondMapper.selectAuctionBondOwnForAuctionId(auctionId);
        if(auctionBond == null){ //没有获得者流拍
            return 0;
        }
        refundService.refund(auctionBond.getAuctionOrderno(),auctionBond.getPrice(),"竞拍获得者退款","System");
        return 1;
    }


    /**
     * 支付通知
     * @param message 支付通知
     * @throws Exception
     */
    @Override
    @Transactional
    public void payNotify(PayMessage message) throws Exception {

        String orderNo = message.getOrderNo();

        //非竞拍通知，不处理
        if (!orderNo.contains("JP")) {
            return;
        }

        AuctionBond auctionBond = new AuctionBond();
        auctionBond.setPaymentStatus(1);//支付状态 1已支付
        auctionBond.setPayType(message.getPayType());//支付类型
        auctionBond.setOtherOrderno(message.getOutTrade());//第三方订单号
        auctionBond.setAuctionOrderno(orderNo);

        auctionBondMapper.updateAuctionBondByorderNo(auctionBond);
    }

    @Override
    public void refundNotify(RefundMessage message) throws Exception {

        String orderNo = message.getOrderNo();

    //非竞拍通知，不处理
        if (!orderNo.contains("JP")) {
        return;
    }

    AuctionBond auctionBond = new AuctionBond();

    //退款操作
        auctionBond.setPaymentStatus(2);
        auctionBond.setRefundPrice(new BigDecimal(message.getRefundAmount()));
        auctionBond.setRefundTime(new Date());
        auctionBond.setRefundNo(message.getOutTradeNo());

        auctionBondMapper.updateAuctionBondByorderNo(auctionBond);
}
}
