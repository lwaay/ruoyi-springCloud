package com.sinonc.orders.service;

import com.sinonc.orders.domain.Auctionmember;

import java.util.List;

/**
 * 竞拍记录 服务层
 *
 * @author sinonc
 * @date 2019-11-12
 */
public interface AuctionmemberService {

	/**
     * 查询竞拍记录信息
     *
     * @param auctionmemberId 竞拍记录ID
     * @return 竞拍记录信息
     */
	public Auctionmember getAuctionmemberById(Long auctionmemberId);

	/**
     * 查询竞拍记录列表
     *
     * @param auctionmember 竞拍记录信息
     * @return 竞拍记录集合
     */
	public List<Auctionmember> listAuctionmember(Auctionmember auctionmember);

	/**
     * 新增竞拍记录
     *
     * @param auctionmember 竞拍记录信息
     * @return 结果
     */
	public int addAuctionmember(Auctionmember auctionmember);

	/**
     * 修改竞拍记录
     *
     * @param auctionmember 竞拍记录信息
     * @return 结果
     */
	public int updateAuctionmember(Auctionmember auctionmember);

	/**
     * 删除竞拍记录信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAuctionmemberByIds(Long[] ids);


	/**
	 * 竞拍接口
	 * @param memberId
	 * @param goodsId
	 * @param price
	 * @return
	 */
    int auctionsAdd(Long memberId, Long goodsId, String price);

	/**
	 * 查询最大竞拍者
	 * @return
	 */
	Auctionmember  listAuctionMerberMax(Long goodsId);

	/**
	 * 根据商品id倒叙查询竞拍记录
	 * @param goodsId
	 * @return
	 */
	List<Auctionmember> selectApiAuctionListdesc(Long goodsId);
}
