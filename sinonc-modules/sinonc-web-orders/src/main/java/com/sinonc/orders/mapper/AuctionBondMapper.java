package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.AuctionBond;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞拍活动押金 数据层
 *
 * @author sinonc
 * @date 2019-11-19
 */
@Mapper
public interface AuctionBondMapper {
	/**
     * 查询竞拍活动押金信息
     *
     * @param auctionbondId 竞拍活动押金ID
     * @return 竞拍活动押金信息
     */
	public AuctionBond selectAuctionBondById(Long auctionbondId);

	/**
     * 查询竞拍活动押金列表
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 竞拍活动押金集合
     */
	public List<AuctionBond> selectAuctionBondList(AuctionBond auctionBond);

	/**
     * 新增竞拍活动押金
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 结果
     */
	public int insertAuctionBond(AuctionBond auctionBond);

	/**
     * 修改竞拍活动押金
     *
     * @param auctionBond 竞拍活动押金信息
     * @return 结果
     */
	public int updateAuctionBond(AuctionBond auctionBond);

	/**
     * 删除竞拍活动押金
     *
     * @param auctionbondId 竞拍活动押金ID
     * @return 结果
     */
	public int deleteAuctionBondById(Long auctionbondId);

	/**
     * 批量删除竞拍活动押金
     *
     * @param auctionbondIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAuctionBondByIds(String[] auctionbondIds);

	/**
	 * 查询用户是否交押金
	 * @param memberId
	 * @return
	 */
    AuctionBond selectApiAuctionBond(@Param("memberId") Long memberId, @Param("goodsId") Long goodsId, @Param("status")Integer status);

	/**
	 * 根据订单编号修改竞拍订单
	 * @param auctionBond
	 * @return
	 */
	public int updateAuctionBondByorderNo(AuctionBond auctionBond);

	/**
	 * 根据押金号查询押金信息
	 * @param orderNo 押金号
	 * @return 结果
	 */
    public AuctionBond selectByAuctionOrderNo(String orderNo);

	/**
	 * 活动结束后除获得者其他参与竞拍者自动退款
	 * @param auctionId
	 * @return
	 */
	List<AuctionBond> selectAuctionBondForAuctionId(Long auctionId);

	/**
	 *
	 * @param auctionId
	 * @return
	 */
	AuctionBond selectAuctionBondOwnForAuctionId(Long auctionId);
}
