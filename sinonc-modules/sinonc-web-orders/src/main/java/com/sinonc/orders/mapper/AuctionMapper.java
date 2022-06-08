package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Auction;
import com.sinonc.orders.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 竞拍活动 数据层
 *
 * @author sinonc
 * @date 2019-11-12
 */
@Mapper
public interface AuctionMapper {
	/**
     * 查询竞拍活动信息
     *
     * @param auctionId 竞拍活动ID
     * @return 竞拍活动信息
     */
	public Auction selectAuctionById(Long auctionId);

	/**
     * 查询竞拍活动列表
     *
     * @param auction 竞拍活动信息
     * @return 竞拍活动集合
     */
	public List<Auction> selectAuctionList(Auction auction);

	/**
     * 新增竞拍活动
     *
     * @param auction 竞拍活动信息
     * @return 结果
     */
	public int insertAuction(Auction auction);

	/**
     * 修改竞拍活动
     *
     * @param auction 竞拍活动信息
     * @return 结果
     */
	public int updateAuction(Auction auction);

	/**
     * 删除竞拍活动
     *
     * @param auctionId 竞拍活动ID
     * @return 结果
     */
	public int deleteAuctionById(Long auctionId);

	/**
     * 批量删除竞拍活动
     *
     * @param auctionIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAuctionByIds(String[] auctionIds);

	/**
	 * 查询竞拍商品
	 * @return
	 */
    List<Goods> selectGoodsForAuction();

	/**
	 * 根据商品id查询竞拍活动，锁住
	 * @return
	 */
	Auction selectAuctionByGoodsIdForUpdate(@Param("goodsId") Long goodsId);

	/**
	 * 根据商品id查询竞拍活动
	 * @param goodsId
	 * @return
	 */
    Auction selectAuctionForGoodsId(@Param("goodsId") Long goodsId);

	/**
	 * 查询我得竞拍列表（最终获得者）
	 * @param memberId
	 * @return
	 */
	List<Map<String,Object>> selectMyAuction(@Param("memberId") Long memberId);

	/**
	 * 查询活动列表
	 * @return
	 */
	List<Map<String,Object>> listAuctionDesc();

	/**
	 * 根据商品id查询活动
	 * @param goodsId
	 * @return
	 */
	Auction selectAuctionBygoodsId(Long goodsId);
}
