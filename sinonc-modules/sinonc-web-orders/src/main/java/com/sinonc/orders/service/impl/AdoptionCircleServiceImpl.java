package com.sinonc.orders.service.impl;

import com.github.pagehelper.PageHelper;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.security.service.TokenService;
import com.sinonc.consume.api.RemoteWxUserConsumeService;
import com.sinonc.consume.api.domain.WxUserConsume;
import com.sinonc.orders.domain.AdoptLike;
import com.sinonc.orders.domain.AdoptionCircle;
import com.sinonc.orders.domain.AdoptionCircleLike;
import com.sinonc.orders.domain.AdoptionCircleReply;
import com.sinonc.orders.mapper.AdoptLikeMapper;
import com.sinonc.orders.mapper.AdoptionCircleMapper;
import com.sinonc.orders.service.AdoptionCircleService;
import com.sinonc.orders.service.CommentReplyService;
import com.sinonc.orders.service.IAdoptionCircleLikeService;
import com.sinonc.orders.service.IAdoptionCircleReplyService;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 朋友圈 服务层实现
 *
 * @author sinonc
 * @date 2019-08-14
 */
@Service
public class AdoptionCircleServiceImpl implements AdoptionCircleService {
	@Autowired
	private AdoptionCircleMapper adoptionCircleMapper;

	@Autowired
	private IAdoptionCircleLikeService circleLikeService;

	@Autowired
	private IAdoptionCircleReplyService circleReplyService;

	@Autowired
	private RemoteWxUserConsumeService userService;

	@Autowired
	private RemoteWxUserConsumeService consumeService;
	@Autowired
	AdoptLikeMapper adoptLikeMapper;

	@Value("${spring.demo:true}")
	boolean isDemo;

	/**
     * 查询朋友圈信息
     *
     * @param adoptionId 朋友圈ID
     * @return 朋友圈信息
     */
    @Override
	public AdoptionCircle getAdoptionCircleById(Long adoptionId) {
	    return adoptionCircleMapper.selectAdoptionCircleById(adoptionId);
	}

	/**
     * 查询朋友圈列表
     *
     * @param adoptionCircle 朋友圈信息
     * @return 朋友圈集合
     */
	@Override
	public List<AdoptionCircle> listAdoptionCircle(AdoptionCircle adoptionCircle) {
		List<AdoptionCircle> adoptionCircleList = adoptionCircleMapper.selectAdoptionCircleList(adoptionCircle);
		adoptionCircleList.forEach(x->{
			WxUserConsume user = userService.getUserById(x.getMemberId()).getData();
			if(!ObjectUtils.isEmpty(user)){
				x.setUserName(user.getName());
			}
		});
		return adoptionCircleList;
	}

    /**
     * 新增朋友圈
     *
     * @param adoptionCircle 朋友圈信息
     * @return 结果
     */
	@Override
	public int addAdoptionCircle(AdoptionCircle adoptionCircle) {

		adoptionCircle.setCreateTime(new Date());
		if(isDemo){
			adoptionCircle.setStatus("0");
		}else{
			adoptionCircle.setStatus("1");
		}
		adoptionCircle.setIsDeleted("0");
		adoptionCircle.setContent(EmojiParser.parseToAliases(adoptionCircle.getContent()));
	    return adoptionCircleMapper.insertAdoptionCircle(adoptionCircle);
	}

	/**
     * 修改朋友圈
     *
     * @param adoptionCircle 朋友圈信息
     * @return 结果
     */
	@Override
	public int updateAdoptionCircle(AdoptionCircle adoptionCircle) {
	    return adoptionCircleMapper.updateAdoptionCircle(adoptionCircle);
	}

	/**
	 * 修改朋友圈(限定id)
	 *
	 * @param adoptionCircle 朋友圈信息
	 * @return 结果
	 */
	@Override
	public int updateAdoptionCircleWithMemberId(AdoptionCircle adoptionCircle) {
		return adoptionCircleMapper.updateAdoptionCircleWithMemberId(adoptionCircle);
	}

	/**
     * 删除朋友圈对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAdoptionCircleByIds(String ids) {
		return adoptionCircleMapper.deleteAdoptionCircleByIds(Convert.toStrArray(ids));
	}

	/**
	 * API查询朋友圈信息
	 * @return
	 */
    @Override
    public List<Map<String, Object>> selectAdoptionCircleListForApi() {
        return adoptionCircleMapper.selectAdoptionCircleListForApi();
    }

	@Override
	public List<Map<String, Object>> selectAdoptionCircleListForApiTwo() {
		List<Map<String,Object>> list =adoptionCircleMapper.selectAdoptionCircleListForApiTwo();
		for (Map<String, Object> tempMap : list) {
			tempMap.put("content", EmojiParser.parseToUnicode((String) tempMap.get("content")));
			tempMap.put("islike", 0);
			Object images = tempMap.get("images");
			String[] imagesArray = new String[0];
			if (images != null) {
				imagesArray = images.toString().split(",");
			}
			tempMap.put("images", imagesArray);
			Long adoptionId = Long.parseLong(tempMap.get("adoption_id").toString());

			AdoptionCircleLike like = new AdoptionCircleLike();
			like.setAdoptionId(adoptionId);
			like.setDelFlg("0");
			int likes = circleLikeService.selectAdoptionCircleLikeCount(like);
			tempMap.put("likes", likes);

			AdoptionCircleReply reply = new AdoptionCircleReply();
			reply.setAdoptionId(adoptionId);
			reply.setDelFlg("0");
			int replyCount = circleReplyService.selectAdoptionCircleReplyListCount(reply);
			// 如果5个以上app应该显示 更多
			tempMap.put("commentsCount", replyCount);
			// 预览只显示最新5个
			PageHelper.startPage(1,5,"create_time desc");
			List<AdoptionCircleReply> adoptionCircleReplies = circleReplyService.selectAdoptionCircleReplyList(reply);
			adoptionCircleReplies.forEach(x->{
				x.setUsername(consumeService.getUserById(x.getMemberId()).getData().getWxname());
				if(x.getTargetId() == null || x.getTargetId() == 0L){
					return;
				}else{
					x.setTargetName(consumeService.getUserById(x.getTargetId()).getData().getWxname());
				}
			});
			tempMap.put("comments", adoptionCircleReplies);
//			AdoptLike adoptLike = new AdoptLike();
//			adoptLike.setAdoptionIdP(adoptionId);
//			List<AdoptLike> likeList = adoptLikeMapper.selectAdoptLikeList(adoptLike);
//			tempMap.put("like", likeList);

//			List<Map<String, Object>> map = commentReplyService.selectCommontReplyByTypeAndGoodIdP(adoptionId);
//			tempMap.put("comments", map);
//			Long deptId = (Long) tempMap.get("deptId");
//			String headImage = selectShopLogoForHeadImage(deptId);
//			tempMap.put("headImage", headImage);
			WxUserConsume user = userService.getUserById(Convert.toLong(tempMap.get("member_id"))).getData();
			try {
                tempMap.put("headImage", user.getHeadimg());
                tempMap.put("username", user.getWxname());
            }catch (Exception e){
                e.printStackTrace();
            }
		}
		return list;
	}


	/**
	 * 新增朋友圈(后台)
	 *
	 * @param adoptionCircle 朋友圈
	 * @return 结果
	 */
	@Override
	public int insertAdoptionCircle(AdoptionCircle adoptionCircle) {
		adoptionCircle.setCreateTime(DateUtils.getNowDate());
		adoptionCircle.setIsDeleted("0");
		return adoptionCircleMapper.insertAdoptionCircle(adoptionCircle);
	}

}
