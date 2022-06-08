package com.sinonc.orders.mapper;

import com.sinonc.orders.domain.Auctionmember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 竞拍记录 数据层
 *
 * @author sinonc
 * @date 2019-11-12
 */
@Mapper
public interface AuctionmemberMapper {
	/**
     * 查询竞拍记录信息
     *
     * @param auctionmemberId 竞拍记录ID
     * @return 竞拍记录信息
     */
	public Auctionmember selectAuctionmemberById(Long auctionmemberId);

	/**
     * 查询竞拍记录列表
     *
     * @param auctionmember 竞拍记录信息
     * @return 竞拍记录集合
     */
	public List<Auctionmember> selectAuctionmemberList(Auctionmember auctionmember);

	/**
     * 新增竞拍记录
     *
     * @param auctionmember 竞拍记录信息
     * @return 结果
     */
	public int insertAuctionmember(Auctionmember auctionmember);

	/**
     * 修改竞拍记录
     *
     * @param auctionmember 竞拍记录信息
     * @return 结果
     */
	public int updateAuctionmember(Auctionmember auctionmember);

	/**
     * 删除竞拍记录
     *
     * @param auctionmemberId 竞拍记录ID
     * @return 结果
     */
	public int deleteAuctionmemberById(Long auctionmemberId);

	/**
     * 批量删除竞拍记录
     *
     * @param auctionmemberIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteAuctionmemberByIds(Long[] auctionmemberIds);

	/**
	 * 查询当前最大价格
	 * @return
	 */
    String selectMaxAuctionmember(@Param("goodsId") Long goodsId);

	/**
	 * 查询竞拍得者
	 * @return
	 */
	Auctionmember selecthdAuction();

	/**
	 * 查询最大竞拍者
	 * @param goodsId
	 * @return
	 */
	Auctionmember  listAuctionMerberMax(@Param("goodsId") Long goodsId);

	/**
	 * 根据商品id倒叙查询竞拍记录
	 * @param goodsId
	 * @return
	 */
	List<Auctionmember> selectApiAuctionListdesc(@Param("goodsId") Long goodsId);
}
