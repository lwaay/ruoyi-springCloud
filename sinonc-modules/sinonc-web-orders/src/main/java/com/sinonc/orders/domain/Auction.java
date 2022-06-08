package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 竞拍活动表 od_auction
 *
 * @author sinonc
 * @date 2019-11-12
 */
@Data
public class Auction {

    private static final long serialVersionUID = 1L;

    /** id自增 */
    private Long auctionId;
    /** 竞拍商品id */
    private Long goodsId;
    /** 竞拍活动编号 */
    private String auctionNum;
    /** 竞拍活动名称 */
    private String auctionName;
    /** 竞拍活动物品描述 */
    private String auctionDesc;
    /** 竞拍活动开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auctionBegintime;
    /** 竞拍活动结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auctionEndtime;
    /** 市场参考价 */
    private BigDecimal referencePrice;
    /** 竞拍起始价 */
    private BigDecimal auctionStartingprice;
    /** 竞拍当前价 */
    private BigDecimal auctionNowprice;
    /** 发起人 */
    private String createUser;
    /** 最低加价 */
    private BigDecimal lowprice;
    /** 最高加价 */
    private BigDecimal mostprice;
    /** 审核状态:0未审核 1审核 */
    private Long type;
    /** 竞拍是否结束 */
    private Integer isEnd;
    /**竞拍开始时间转换字段*/
    private String auctionBegintimeString;
    /**竞拍结束时间转换字段*/
    private String auctionEndtimeString;
    /**竞拍押金*/
    private String auctionBond;

    /**
     * 开始时间
     */
    @JsonIgnore
    private String beginTime;
    /**
     * 结束时间
     */
    @JsonIgnore
    private String endTime;

    private Map<String, String> params;

    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", goodsId=" + goodsId +
                ", auctionNum='" + auctionNum + '\'' +
                ", auctionName='" + auctionName + '\'' +
                ", auctionDesc='" + auctionDesc + '\'' +
                ", auctionBegintime=" + auctionBegintime +
                ", auctionEndtime=" + auctionEndtime +
                ", auctionStartingprice=" + auctionStartingprice +
                ", auctionNowprice=" + auctionNowprice +
                ", createUser='" + createUser + '\'' +
                ", lowprice=" + lowprice +
                ", mostprice=" + mostprice +
                ", type=" + type +
                ", isEnd=" + isEnd +
                ", auctionBegintimeString='" + auctionBegintimeString + '\'' +
                ", auctionEndtimeString='" + auctionEndtimeString + '\'' +
                ", auctionBond='" + auctionBond + '\'' +
                '}';
    }
}
