package com.sinonc.orders.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author huanghao
 * @apiNote TODO
 * @date 2022/3/31 11:22
 */
@Data
public class MemberFollowDescription extends OdMemberAttention{

    private String title;

    private String image;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auctionEndDate;

    private String price;
}
