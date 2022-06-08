package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.Auctionmember;
import com.sinonc.orders.domain.Goods;
import com.sinonc.orders.mapper.AuctionMapper;
import com.sinonc.orders.mapper.AuctionmemberMapper;
import com.sinonc.orders.mapper.GoodsMapper;
import com.sinonc.orders.service.AuctionmemberService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 竞拍记录 服务层实现
 *
 * @author sinonc
 * @date 2019-11-12
 */
@Service
public class AuctionmemberServiceImpl implements AuctionmemberService {
	@Autowired
	private AuctionmemberMapper auctionmemberMapper;
	@Autowired
	private AuctionMapper auctionMapper;
	@Autowired
	private RemoteWxUserService wxUserService;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	/**
     * 查询竞拍记录信息
     *
     * @param auctionmemberId 竞拍记录ID
     * @return 竞拍记录信息
     */
    @Override
	public Auctionmember getAuctionmemberById(Long auctionmemberId) {
	    return auctionmemberMapper.selectAuctionmemberById(auctionmemberId);
	}

	/**
     * 查询竞拍记录列表
     *
     * @param auctionmember 竞拍记录信息
     * @return 竞拍记录集合
     */
	@Override
	public List<Auctionmember> listAuctionmember(Auctionmember auctionmember) {
		List<Auctionmember>auctionmembers= auctionmemberMapper.selectAuctionmemberList(auctionmember);
		if (auctionmembers!=null){
			for (Auctionmember auctionmember1 : auctionmembers) {
				WxUser member = wxUserService.getWxUserByMemberId(auctionmember1.getMemberId()).getData();
				if (member!=null){
					auctionmember1.setMemberName(member.getName());
				}
				Goods goods = goodsMapper.selectGoodsById(auctionmember1.getGoodsId());
				if (goods!=null){
					auctionmember1.setGoodName(goods.getName());
				}
			}
		}
		return auctionmembers;
	}

    /**
     * 新增竞拍记录
     *
     * @param auctionmember 竞拍记录信息
     * @return 结果
     */
	@Override
	public int addAuctionmember(Auctionmember auctionmember) {
	    return auctionmemberMapper.insertAuctionmember(auctionmember);
	}

	/**
     * 修改竞拍记录
     *
     * @param auctionmember 竞拍记录信息
     * @return 结果
     */
	@Override
	public int updateAuctionmember(Auctionmember auctionmember) {
	    return auctionmemberMapper.updateAuctionmember(auctionmember);
	}

	/**
     * 删除竞拍记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAuctionmemberByIds(Long[] ids) {
		return auctionmemberMapper.deleteAuctionmemberByIds(ids);
	}

	/**
	 * 竞拍
	 * @param memberId
	 * @param goodsId
	 * @param price
	 * @return
	 */
    @Override
	@Transactional
    public int auctionsAdd(Long memberId, Long goodsId, String price) {
		BigDecimal cjPrice = new BigDecimal(price+""); //出价金额


		//根据商品id查询竞拍活动，锁住
		Auction auctionById = auctionMapper.selectAuctionByGoodsIdForUpdate(goodsId);

		//查询redis
		String auctionValue = stringRedisTemplate.opsForValue().get("auctionStart"+auctionById.getAuctionId());
		if(StringUtils.isEmpty(auctionValue)){
			return 0;
		}

		if(auctionById.getIsEnd() == 2){
			return 0;
		}

		if (auctionById != null) {
			if (auctionById.getAuctionNowprice() != null) {
				BigDecimal nowPrice = new BigDecimal(auctionById.getAuctionNowprice().toString());//获取当前出价最高价格

				String pri  = auctionmemberMapper.selectMaxAuctionmember(goodsId);
				if(StringUtils.isEmpty(pri)){
					pri = auctionById.getAuctionStartingprice().toString();
					BigDecimal big = new BigDecimal(pri);
				}
				BigDecimal big = new BigDecimal(pri);

				Auctionmember auctionmember = new Auctionmember();
				auctionmember.setMemberId(memberId);
				auctionmember.setAuctionPrice(cjPrice);
				auctionmember.setGoodsId(goodsId);
				auctionmember.setCreateTime(new Date());
				auctionmember.setNowPrice(big.add(cjPrice)); //计算价格

				int row = auctionmemberMapper.insertAuctionmember(auctionmember); //插入竞拍记录

				return Integer.parseInt(auctionmember.getAuctionmemberId().toString());
			}

		}

		return 0;
    }

	/**
	 * 查询最大竞拍者
	 * @return
	 */
	@Override
    public Auctionmember listAuctionMerberMax(Long goodsId) {
        return auctionmemberMapper.listAuctionMerberMax(goodsId);
    }

	/**
	 * 根据商品id倒叙查询竞拍记录
	 * @param goodsId
	 * @return
	 */
	@Override
	public List<Auctionmember> selectApiAuctionListdesc(Long goodsId) {
		return auctionmemberMapper.selectApiAuctionListdesc(goodsId);
	}

}
