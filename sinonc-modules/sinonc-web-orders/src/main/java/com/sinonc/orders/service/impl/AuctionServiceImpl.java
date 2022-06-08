package com.sinonc.orders.service.impl;

import com.sinonc.common.core.exception.BaseException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.orders.delaytask.DelayTask;
import com.sinonc.orders.delaytask.DelayTaskServiceImpl;
import com.sinonc.orders.delaytask.TaskTypeConstants;
import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.domain.GoodsSpecs;
import com.sinonc.orders.mapper.AuctionMapper;
import com.sinonc.orders.mapper.GoodsMapper;
import com.sinonc.orders.mapper.GoodsSpecsMapper;
import com.sinonc.orders.mapper.SpecsMapper;
import com.sinonc.orders.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 竞拍活动 服务层实现
 *
 * @author sinonc
 * @date 2019-11-12
 */
@Service("auction")
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    private AuctionMapper auctionMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;
    @Autowired
    private SpecsMapper specsMapper;
    @Autowired
    private DelayTaskServiceImpl delayTaskService;

    /**
     * 查询竞拍活动信息
     *
     * @param auctionId 竞拍活动ID
     * @return 竞拍活动信息
     */
    @Override
    public Auction getAuctionById(Long auctionId) {
        return auctionMapper.selectAuctionById(auctionId);
    }

    /**
     * 查询竞拍活动列表
     *
     * @param auction 竞拍活动信息
     * @return 竞拍活动集合
     */
    @Override
    public List<Auction> listAuction(Auction auction) {
        return auctionMapper.selectAuctionList(auction);
    }

    /**
     * 新增竞拍活动
     *
     * @param auction 竞拍活动信息
     * @return 结果
     */
    @Override
    public int addAuction(Auction auction) {
        Date date = new Date();
        //创建定时任务，用于更新预订状态
        if (auction.getAuctionBegintime().getTime() >= auction.getAuctionEndtime().getTime()) {
            throw new BusinessException("开始时间必须小于结束时间");
        }

        if (auction.getAuctionBegintime().getTime() < System.currentTimeMillis()) {
            throw new BusinessException("开始时间必须大于当前系统时间");
        }
        auction.setType(0L); //创建竞拍活动为未审核
        auction.setIsEnd(0); //创建竞拍活动为未开始

        //判断正在竞拍商品是否有重复
        Auction auc = auctionMapper.selectAuctionBygoodsId(auction.getGoodsId());

        if (auc != null) {
            int i = auctionMapper.deleteAuctionById(auc.getAuctionId());
            if (i == 0) {
                throw new BusinessException("更新竞拍商品失败");
            }
        }

        int row = auctionMapper.insertAuction(auction);

        //创建竞拍活动创建延时队列，开始与结束
        delayTaskService.addTask(DelayTask.getInstance(TaskTypeConstants.AUCTION_START_TASK, auction.getAuctionBegintime().getTime(), System.currentTimeMillis(), auction));
        delayTaskService.addTask(DelayTask.getInstance(TaskTypeConstants.AUCTION_END_TASK, auction.getAuctionEndtime().getTime(), System.currentTimeMillis(), auction));

        return row;
    }


    /**
     * 修改竞拍活动
     *
     * @param auction 竞拍活动信息
     * @return 结果
     */
    @Override
    public int updateAuction(Auction auction) {
        if (auction.getAuctionBegintime().getTime() >= auction.getAuctionEndtime().getTime()) {
            throw new BusinessException("竞拍开始时间必须小于竞拍结束时间");
        }

        if (auction.getAuctionBegintime().getTime() < System.currentTimeMillis()) {
            throw new BusinessException("竞拍开始时间必须大于当前系统时间");
        }
        Auction oldAuction = auctionMapper.selectAuctionBygoodsId(auction.getGoodsId());
        auction.setAuctionId(oldAuction.getAuctionId());
        if(oldAuction.getIsEnd()== 1 && !DateUtils.isAfter(oldAuction.getAuctionBegintime(), DateUtils.getNowDate())){
            throw new BaseException("该竞拍商品已开始竞拍，无法修改");
        }
        return auctionMapper.updateAuction(auction);
    }

    /**
     * 修改竞拍活动金额
     *
     * @param auction 竞拍活动信息
     * @return 结果
     */
    @Override
    public int updateAuctionPrice(Auction auction) {
        Auction oldAuction = auctionMapper.selectAuctionBygoodsId(auction.getGoodsId());
        auction.setAuctionId(oldAuction.getAuctionId());
        if(oldAuction.getIsEnd() != 1 && DateUtils.isAfter(oldAuction.getAuctionBegintime(), DateUtils.getNowDate())){
            throw new BaseException("该竞拍商品未开始竞拍或竞拍结束");
        }
        return auctionMapper.updateAuction(auction);
    }

    /**
     * 删除竞拍活动对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteAuctionByIds(String ids) {

        List<String> goodsIds = new ArrayList<>(); //商品ids
        List<String> SpcesIds = new ArrayList<>(); //规格ids
        List<String> goodsIdSpcesId = new ArrayList<>(); //商品规格关联表ids

        //根据活动id查询商品id
        String[] idsArr = Convert.toStrArray(ids);

        for (String str : idsArr) {
            //根据活动id查询活动
            Auction auction = auctionMapper.selectAuctionById(Long.parseLong(str));
            if (auction != null) {
                //根据商品id查询商品
                Goods goods = goodsMapper.selectGoodsById(auction.getGoodsId());

                if (goods != null) {
                    goodsIds.add(goods.getGoodsId().toString());
                    SpcesIds.add(goods.getSpecsIds());
                    //根据商品id和规格id查询商品规格关联表
                    GoodsSpecs goodsSpecs = goodsSpecsMapper.selectGoodsIdAndSpecsId(goods.getGoodsId(), Long.parseLong(goods.getSpecsIds()));
                    goodsIdSpcesId.add(goodsSpecs.getGoodsSpecId().toString());

                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }

        //集合转数组
        String[] t1 = new String[goodsIds.size()];
        goodsIds.toArray(t1);
        String[] t2 = new String[SpcesIds.size()];
        SpcesIds.toArray(t2);
        String[] t3 = new String[goodsIdSpcesId.size()];
        goodsIdSpcesId.toArray(t3);

        //删除ids
        int auctionRow = auctionMapper.deleteAuctionByIds(Convert.toStrArray(ids));
        int goodsIdsRow = goodsMapper.deleteGoodsByIds(t1);
        int SpcesIdsRow = specsMapper.deleteSpecsByIds(t2);
        int goodsIdSpcesIdRow = goodsSpecsMapper.deleteGoodsSpecsByIds(t3);

        return auctionRow * goodsIdsRow * SpcesIdsRow * goodsIdSpcesIdRow;
    }

    /**
     * 查询竞拍商品
     *
     * @return
     */
    @Override
    public List<Goods> selectGoodsForAuction() {
        System.out.println(auctionMapper.selectGoodsForAuction());
        return auctionMapper.selectGoodsForAuction();
    }

    /**
     * 竞拍接口
     *
     * @param memberId
     * @param goodsId
     * @param price
     * @return
     */
   /* @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int auctionsAdd(Long memberId, Long goodsId, String price) {
        BigDecimal cjPrice = new BigDecimal(price+""); //出价金额

        //根据商品id查询竞拍活动，锁住
        Auction auctionById = auctionMapper.selectAuctionByGoodsIdForUpdate(goodsId);
      *//*  System.out.println(auctionById.getIsEnd());
        if(auctionById.getIsEnd() == 0){
            System.out.println("竞拍未结束");
        }else if(auctionById.getIsEnd() == 1){
            System.out.println("竞拍已结束");
        }*//*
        if (auctionById != null) {
            if (auctionById.getAuctionNowprice() != null) {
                BigDecimal nowPrice = new BigDecimal(auctionById.getAuctionNowprice().toString());//获取当前出价最高价格
                Auctionmember auctionmember = new Auctionmember();
                auctionmember.setMemberId(memberId);
                auctionmember.setAuctionPrice(cjPrice);
                auctionmember.setGoodsId(goodsId);
                auctionmember.setCreateTime(new Date());
                int row = auctionmemberMapper.insertAuctionmember(auctionmember); //插入竞拍记录
                if (row > 0) {
                    //更新竞拍活动表数据
                    //用户出价与起拍价相加，得出最新价格
                    auctionById.setAuctionNowprice(cjPrice.add(nowPrice));
                   int rows = auctionMapper.updateAuction(auctionById);
                }

                return Integer.parseInt(auctionmember.getAuctionmemberId().toString());
            }

        }

        return 0;
    }*/

    /**
     * 根据商品id查询竞拍活动
     *
     * @param goodsId
     * @return
     */
    @Override
    public Auction selectAuctionForGoodsId(Long goodsId) {
        return auctionMapper.selectAuctionForGoodsId(goodsId);
    }

    /**
     * 查询我得竞拍列表（最终获得者）
     *
     * @param memberId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectMyAuction(Long memberId) {
        return auctionMapper.selectMyAuction(memberId);
    }

    /**
     * 查询活动列表
     *
     * @return
     */
    @Override
    public List<Map<String,Object>> listAuctionDesc() {
        return auctionMapper.listAuctionDesc();
    }


}
