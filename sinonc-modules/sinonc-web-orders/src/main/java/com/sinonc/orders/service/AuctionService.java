package com.sinonc.orders.service;

import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.Goods;

import java.util.List;
import java.util.Map;

/**
 * 竞拍活动 服务层
 *
 * @author sinonc
 * @date 2019-11-12
 */
public interface AuctionService {

	/**
     * 查询竞拍活动信息
     *
     * @param auctionId 竞拍活动ID
     * @return 竞拍活动信息
     */
	public Auction getAuctionById(Long auctionId);

	/**
     * 查询竞拍活动列表
     *
     * @param auction 竞拍活动信息
     * @return 竞拍活动集合
     */
	public List<Auction> listAuction(Auction auction);

	/**
     * 新增竞拍活动
     *
     * @param auction 竞拍活动信息
     * @return 结果
     */
	public int addAuction(Auction auction);

	/**
     * 修改竞拍活动
     *
     * @param auction 竞拍活动信息
     * @return 结果
     */
	public int updateAuction(Auction auction);

	/**
     * 修改竞拍活动金额
     *
     * @param auction 竞拍活动信息
     * @return 结果
     */
	public int updateAuctionPrice(Auction auction);

	/**
     * 删除竞拍活动信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAuctionByIds(String ids);

	/**
	 * 查询竞拍商品
	 * @return
	 */
	public List<Goods> selectGoodsForAuction();

	/**
	 * 竞拍接口
	 * @param memberId
	 * @param goodsId
	 * @param price
	 * @return
	 */
	//int auctionsAdd(Long memberId, Long goodsId, String price);

	/**
	 * 根据商品id查询竞拍活动
	 * @param goodsId
	 * @return
	 */
	Auction selectAuctionForGoodsId(Long goodsId);

	/**
	 * 查询我得竞拍列表（最终获得者）
	 * @param memberId
	 * @return
	 */
	List<Map<String,Object>> selectMyAuction(Long memberId);

	/**
	 * 查询竞拍活动列表
	 * @return
	 */
	List<Map<String,Object>> listAuctionDesc();
}
