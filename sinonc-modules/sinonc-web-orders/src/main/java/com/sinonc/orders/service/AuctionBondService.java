package com.sinonc.orders.service;

import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.AuctionBond;

import java.util.List;

/**
 * 竞拍活动押金 服务层
 *
 * @author sinonc
 * @date 2019-11-19
 */
public interface AuctionBondService {

	/**
     * 查询竞拍活动押金信息
     *
     * @param auctionbondId 竞拍活动押金ID
     * @return 竞拍活动押金信息
     */
	public AuctionBond getAuctionBondById(Long auctionbondId);

	/**
     * 查询竞拍活动押金列表
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 竞拍活动押金集合
     */
	public List<AuctionBond> listAuctionBond(AuctionBond auctionBond);

	/**
     * 新增竞拍活动押金
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 结果
     */
	public int addAuctionBond(AuctionBond auctionBond);

	/**
     * 修改竞拍活动押金
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 结果
     */
	public int updateAuctionBond(AuctionBond auctionBond);

	/**
     * 删除竞拍活动押金信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAuctionBondByIds(String ids);

	/**
	 * 查询用户是否交押金
	 * @return
	 */
    AuctionBond selectApiAuctionBond(Long memberId,Long goodsId, Integer status);

	/**
	 * 同一押金退还
	 * @return 结果
	 */
	public boolean refundAuctionBond(Auction auction);

	/**
	 * 竞拍活动结束，标识最终获得者
	 */
	public int updateAuctionBoonForWin(Long memberId,Long goodsId);

	/**
	 * 活动结束后除获得者其他参与竞拍者自动退款
	 */
	public void auctionTK(Long auctionId) throws Exception;

	/**
	 * 活动结束后获得者退款
	 * @param auctionId
	 * @return
	 */
	Integer tkOwnAuction(Long auctionId) throws Exception;
}
