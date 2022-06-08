package com.sinonc.orders.service.impl;

import com.sinonc.order.api.domain.Shop;
import com.sinonc.orders.mapper.AttentionMapper;
import com.sinonc.orders.service.AttentionService;
import com.sinonc.orders.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @anthor wang
 */
@Service
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    private AttentionMapper attentionMapper;
    @Autowired
    private IShopService shopService;

    @Override
    public int like(Long memberId, String shopId) {
        //查询是否收藏
        Boolean aBoolean = selectCollect(memberId, shopId);
        //如果已经收藏，取消收藏
//        if (aBoolean) {
//            int i = disColletc(shopId, memberId);
//            if (i == 1) {
//                return 2;
//            }
//            return 0;
//        }
        //r如果已经收藏。返回你已经收藏了
        if (aBoolean) {
            return 100;
        }
        //收藏
        else {
            int i = giveAColletc(shopId, memberId);
            if (i == 1) {
                return 200;
            }
            return 500;
        }

    }

    @Override
    public int dislike(Long memberId, String shopId) {
        //查询是否收藏
        Boolean aBoolean = selectCollect(memberId, shopId);
        //如果已经收藏，取消收藏
//        if (aBoolean) {
//            int i = disColletc(shopId, memberId);
//            if (i == 1) {
//                return 2;
//            }
//            return 0;
//        }
        //r如果已经收藏。返回你已经取消收藏了
        if (!aBoolean) {
            return 100;
        }
        //取消收藏
        else {
            int i = removeCollect(shopId, memberId);
            if (i == 1) {
                return 200;
            }
            return 500;
        }

    }

    /**
     * 查询是否收藏
     *
     * @param memberId
     * @param shopId
     * @return
     */
    @Override
    public Boolean selectCollect(Long memberId, String shopId) {
        //是否收藏
        boolean Colletc = false;
        //查询店铺关注用户字符
        Map<String, Object> query = new HashMap<>(2);
        query.put("shopId", shopId);
        query.put("memberId", memberId);
        List<String> queryAttention = attentionMapper.selectAttention(query);
        if (queryAttention.size() > 0) {
            Colletc = true;
        }
        return Colletc;
    }

    @Override
    public List<Shop> selectLikeListByMemberId(long memberById) {
        List<Shop> shops = new ArrayList<>();
        List<Long> longs = attentionMapper.selectLikeListByMemberId(memberById);
        for (Long aLong : longs) {
            Shop shopById = shopService.selectShopById(aLong);
            int fans = attentionMapper.queryAttention(String.valueOf(aLong));
            shopById.setFansCount(fans);
            shops.add(shopById);
        }
        return shops;
    }


    /**
     * 取消收藏
     * @param shopId
     * @param memberId
     * @return
     */
//    @Transactional
//    public int disColletc(String shopId,Long memberId){
//        //获取收餐用户字符串
//        String queryAttention = attentionMapper.queryAttention(shopId);
//        String[] split = queryAttention.split(",");
//        //分割后转换成arrayList删除相对应的memberId
//        List<String> list = Arrays.asList(split);
//        ArrayList<String> arrayList = new ArrayList<>(list);
//        //只有arrayList才可以删除
//        arrayList.remove(String.valueOf(memberId));
//        //删除成功后转换回数组
//        String[] strings = arrayList.toArray(new String[0]);
//        String fansIds="";
//        //转换成一个字符串重新存储进去
//        for(int i=0;i<strings.length;i++){
//            if(i==0){
//                fansIds=strings[i];
//            }else{
//                fansIds=fansIds+","+strings[i];
//            }
//        }
//        Map<String,Object> map=new HashMap<>();
//        map.put("shopId",shopId);
//        map.put("fansIds",fansIds);
//        int i = attentionMapper.updateAttention(map);
//        return i;
//    }

    /**
     * 收藏
     *
     * @param shopId
     * @param memberId
     * @return
     */
    public int giveAColletc(String shopId, Long memberId) {

        Map<String, Object> map = new HashMap<>(2);
        map.put("shopId", shopId);
        map.put("attentions", memberId);
        return attentionMapper.insertAttention(map);
    }

    public int removeCollect(String shopId, Long memberId){
        Map<String, Object> map = new HashMap<>(2);
        map.put("shopId", shopId);
        map.put("attentions", memberId);
        return attentionMapper.removeAttention(map);
    }

}
