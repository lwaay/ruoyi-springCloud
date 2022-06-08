package com.sinonc.ser.mapper;

import com.sinonc.service.domain.BizGoodBuy;
import com.sinonc.ser.dto.BizGoodBuyBackDto;
import com.sinonc.ser.dto.BizGoodBuyDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 采购Mapper接口
 *
 * @author ruoyi
 * @date 2020-07-29
 */
public interface BizGoodBuyMapper {
    /**
     * 查询采购
     *
     * @param buyId 采购ID
     * @return 采购
     */
    BizGoodBuy selectBizGoodBuyById(Long buyId);

    /**
     * 查询采购列表
     *
     * @param bizGoodBuy 采购
     * @return 采购集合
     */
    List<BizGoodBuyBackDto> selectBizGoodBuyList(BizGoodBuy bizGoodBuy);

    /**
     * 新增采购
     *
     * @param bizGoodBuy 采购
     * @return 结果
     */
    int insertBizGoodBuy(BizGoodBuy bizGoodBuy);

    /**
     * 修改采购
     *
     * @param bizGoodBuy 采购
     * @return 结果
     */
    int updateBizGoodBuy(BizGoodBuy bizGoodBuy);

    /**
     * 删除采购
     *
     * @param buyId 采购ID
     * @return 结果
     */
    int deleteBizGoodBuyById(Long buyId);

    /**
     * 批量删除采购
     *
     * @param buyIds 需要删除的数据ID
     * @return 结果
     */
    int deleteBizGoodBuyByIds(Long[] buyIds);

    /**
     * 分页查询采购
     *
     * @param memberIdP        会员ID
     * @param maxBuyWeight     最大购买重量
     * @param minBuyWeight     最小购买重量
     * @param breedsArray      品类
     * @param shipAddressArray 源地址编码
     * @param preference       优选(1优选 2普通)
     * @param saleAble         上下架 (0上架 1下架)
     * @return @see com.sinonc.dto.BizGoodBuyDto
     */
    List<BizGoodBuyDto> selectBizGoodBuyForPage(@Param("infoName") String infoName, @Param("preference") String preference, @Param("saleAble") String saleAble,
                                                @Param("memberIdP") Long memberIdP, @Param("maxBuyWeight") Integer maxBuyWeight,
                                                @Param("minBuyWeight") Integer minBuyWeight, @Param("breedsArray") String[] breedsArray,
                                                @Param("shipAddressArray") String[] shipAddressArray);

    /**
     * 查询采购商品详情
     *
     * @param infoId
     * @return
     */
    BizGoodBuyDto selectBizGoodBuyByInfoId(Long infoId);

    /**
     * 插入一条访问记录
     *
     * @param memberIdP 被访问者id
     * @param memberId  访问者id
     */
    @Insert("insert into member_visit(member_id_p,be_member_id_p) values(#{memberId},#{memberIdP})")
    void insertVisit(@Param("memberIdP") Long memberIdP, @Param("memberId") Long memberId);
}
