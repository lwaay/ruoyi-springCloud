package com.sinonc.agriculture.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sinonc.agriculture.domain.ExpertInfo;
import com.sinonc.agriculture.domain.GrowTech;
import com.sinonc.agriculture.domain.MemberInfo;
import com.sinonc.agriculture.service.CropDictService;
import com.sinonc.base.api.RemoteCorpDictService;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.text.Convert;
import com.sinonc.agriculture.constants.MemberDictConstants;
import com.sinonc.agriculture.constants.MemberInfoConstants;
import com.sinonc.agriculture.mapper.*;
import com.sinonc.agriculture.service.GrowTechService;
import com.sinonc.agriculture.vo.GrowTechVo;
import com.sinonc.common.core.utils.bean.BeanUtils;
import com.sinonc.common.security.utils.SecurityUtils;
import com.sinonc.system.api.RemoteWxUserService;
import com.sinonc.system.api.domain.WxUser;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 种养殖技术Service业务层处理
 *
 * @author zhang.xl
 * @date 2020-03-06
 */
@Slf4j
@Service
public class GrowTechServiceImpl implements GrowTechService {
    @Autowired
    private GrowTechMapper growTechMapper;

    @Autowired
    private GrowtechLikeMapper growtechLikeMapper;

    @Autowired
    private GrowtechCommentMapper growtechCommentMapper;

    @Autowired
    private RemoteCorpDictService cropDictMapper;

    @Autowired
    private CommentReplyMapper commentReplyMapper;


    @Autowired
    private RemoteWxUserService wxUserService;

    @Autowired
    private ExpertInfoMapper expertInfoMapper;

    @Autowired
    private RemoteWxUserService remoteWxUserService;

    @Autowired
    private CropDictService cropDictService;

    /**
     * 查询种养殖技术
     *
     * @param growId 种养殖技术ID
     * @return 种养殖技术
     */
    @Override
    public GrowTechVo selectGrowTechById(Long growId) {
        GrowTechVo growTechVo = new GrowTechVo();

        GrowTech growTech = growTechMapper.selectGrowTechById(growId);
        BeanUtils.copyBeanProp(growTechVo, growTech);

        List<Map<String, Object>> likes = growtechLikeMapper.selectGrowtechLikeListByGrowTechIdForMap(growTech.getGrowId());
        growTechVo.getTempMap().put("wxname", SecurityUtils.getUsername());
        if (likes != null) {
            growTechVo.getTempMap().put("like", likes);
            growTechVo.getTempMap().put("likeSize", likes.size());
        } else {
            growTechVo.getTempMap().put("like", likes);
            growTechVo.getTempMap().put("likeSize", 0);
        }


        List<Map<String, Object>> comments = growtechCommentMapper.selectGrowtechCommentListByGrowTechIdForMap(growTech.getGrowId());

        if (comments != null) {
            for (int i = 0; i < comments.size(); i++) {
                Map tempCommentMap = comments.get(i);

                Long memberId = (Long) tempCommentMap.get("member_id_p");
                R<WxUser> wxUserOK = wxUserService.getWxUserByMemberId(memberId);
                WxUser wxUser = new WxUser();
                if (wxUserOK != null) {
                   wxUser  = wxUserOK.getData();
                }
                tempCommentMap.put("memberInfo", wxUser);
                tempCommentMap.put("wxname", wxUser.getWxname());
                ExpertInfo expertInfo = expertInfoMapper.selectExpertInfoById(memberId);
                if (expertInfo != null) {
                    tempCommentMap.put("memberInfoType", MemberInfoConstants.ROLE_EXPERT);
                } else {
                    tempCommentMap.put("memberInfoType", MemberInfoConstants.ROLE_FARMER);
                }

                Long commentId = (Long) tempCommentMap.get("reply_id");
                List nextComment = selectReplyCommentToComment(commentId);
                tempCommentMap.put("nextComment", nextComment);
            }

            growTechVo.getTempMap().put("comments", comments);
            growTechVo.getTempMap().put("commentsSize", comments.size());
        } else {
            growTechVo.getTempMap().put("comments", new ArrayList());
            growTechVo.getTempMap().put("commentsSize", 0);
        }


        return growTechVo;
    }

    /**
     * 查询种养殖技术列表
     *
     * @param growTech 种养殖技术
     * @return 种养殖技术
     */
    @Override
    public List<GrowTech> selectGrowTechList(GrowTech growTech)
    {
        List<GrowTech> rsGrowTechList= growTechMapper.selectGrowTechList(growTech);

        if (rsGrowTechList != null && rsGrowTechList.size() > 0) {
            for (int i = 0; i < rsGrowTechList.size(); i++) {
                GrowTech growTechTemp = rsGrowTechList.get(i);
                //如果主图为null,则设置为默认值
                if(growTechTemp.getMainImg()==null){
                    growTechTemp.setMainImg(MemberDictConstants.SHARE_MAIN_IMAGE);
                }
            }
        }

        return rsGrowTechList;
    }

    /**
     * 查询种养殖技术列表
     *
     * @param growTech 种养殖技术
     * @return 种养殖技术
     */
    @Override
    public List<Map<String, Object>> selectGrowTechListForMap(GrowTech growTech, Long memberId,Long parentCropId) {
        List<Map<String, Object>> list = growTechMapper.selectGrowTechListForMap(growTech);

        for (int i = 0; i < list.size(); i++) {
            Map tempMap = list.get(i);
            Long userId = Long.valueOf(String.valueOf(tempMap.get("user_id")));
            WxUser wxUser = remoteWxUserService.getWxUserByMemberId(userId).getData();
            if(Optional.ofNullable(wxUser).isPresent()){
                tempMap.put("headimg",wxUser.getHeadimg());
                tempMap.put("wxname",wxUser.getWxname());
            }
            iniGrowTech(tempMap, memberId);
        }

        return list;
    }

    private void iniGrowTech(Map tempMap, Long memberId) {

        if (!tempMap.containsKey("video_url")) {
            tempMap.put("video_url", null);
        }

        Long growTechId = (Long) tempMap.get("grow_id");
        String main_img = (String) tempMap.get("main_img");
        if (main_img == null) {
            tempMap.put("main_img", "");
        }
        List pictures = new ArrayList();
        pictures.add(main_img);
        tempMap.put("pictures", pictures);

        List<Map<String, Object>> likes = growtechLikeMapper.selectGrowtechLikeListByGrowTechIdForMap(growTechId);
        if (likes != null && likes.size() > 0) {
            tempMap.put("like", likes);
            tempMap.put("likeSize", likes.size());
        } else {
            tempMap.put("like", new ArrayList());
            tempMap.put("likeSize", 0);
        }
        if (memberId != null) {
            log.error("当前登录用户id：{}，种养殖技术id：{}",memberId,growTechId);
            List<Map<String, Object>> ownLikes = growtechLikeMapper.selectGrowtechLikeListByGrowTechIdAndMemberIdForMap(growTechId, memberId);

            if (ownLikes != null && ownLikes.size() > 0) {
                tempMap.put("ownLike", true);
            } else {
                tempMap.put("ownLike", false);
            }
        } else {
            tempMap.put("ownLike", false);
        }


        List<Map<String, Object>> comments = growtechCommentMapper.selectGrowtechCommentListByGrowTechIdForMap(growTechId);

        //获取评论的回复
        for (int j = 0; j < comments.size(); j++) {
            Map tempCommentMap = comments.get(j);
            Long commentId = (Long) tempCommentMap.get("reply_id");
            List nextComment = selectReplyCommentToComment(commentId);
            tempCommentMap.put("nextComment", nextComment);
        }

        tempMap.put("comments", comments);
        tempMap.put("commentsSize", comments.size());
    }


    private List<Map<String, Object>> selectReplyCommentToComment(Long commentId){
        List<Map<String, Object>> thisSet = new LinkedList();

        List<Map<String, Object>>  replyComments = commentReplyMapper.selectCommentReplyListForMap(commentId);

        if (replyComments.size()>0){
            for (int i = 0; i < replyComments.size(); i++) {
                Map<String, Object> tempCommentReply=(Map<String, Object>)replyComments.get(i);
                WxUser comUser = wxUserService.getWxUserByMemberId((Long) tempCommentReply.get("memberId")).getData();
                WxUser replyUser = wxUserService.getWxUserByMemberId((Long) tempCommentReply.get("replyMemberId")).getData();
                tempCommentReply.put("commentUserName",comUser.getWxname());
                tempCommentReply.put("replyUserName", replyUser.getWxname());
                thisSet.add(tempCommentReply);
                Long nextCommentId=(Long)tempCommentReply.get("reply_id");
                List<Map<String, Object>> nextComments=selectReplyCommentToComment(nextCommentId);
                thisSet.addAll(nextComments);
            }
        }
        return thisSet;
    }


    /**
     * 新增种养殖技术
     *
     * @param growTech 种养殖技术
     * @return 结果
     */
    @Override
    public int insertGrowTech(GrowTech growTech) {

        growTech.setCreateTime(DateUtils.getNowDate());

        return growTechMapper.insertGrowTech(growTech);
    }

    /**
     * 修改种养殖技术
     *
     * @param growTech 种养殖技术
     * @return 结果
     */
    @Override
    public int updateGrowTech(GrowTech growTech) {
//        Subject subject = SecurityUtils.getSubject();
//        User principal = (User) subject.getPrincipal();

//        growTech.setUserId(principal.getUserId());
//        growTech.setUserName(principal.getUserName());
//        growTech.setUpdateBy(String.valueOf(principal.getUserId()));
        growTech.setUpdateTime(DateUtils.getNowDate());

        return growTechMapper.updateGrowTech(growTech);
    }

    /**
     * 删除种养殖技术对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteGrowTechByIds(Long[] ids) {
        return growTechMapper.deleteGrowTechByIds(ids);
    }

    /**
     * 删除种养殖技术信息
     *
     * @param growId 种养殖技术ID
     * @return 结果
     */
    @Override
    public int deleteGrowTechById(Long growId) {
        return growTechMapper.deleteGrowTechById(growId);
    }

    @Override
    @Transactional
    public GrowTech summGrowTechReadCount(Long growId) {
        GrowTech growTech = growTechMapper.selectGrowTechByIdForUpdate(growId);

        if (growTech.getReadCount() != null) {
            long readCount = (long) growTech.getReadCount();
            readCount = readCount + 1;
            growTech.setReadCount(readCount);
        } else {
            long readCount = Long.valueOf(1);
            growTech.setReadCount(readCount);
        }
        growTechMapper.updateGrowTech(growTech);
        return growTech;
    }

    private class ProgNode{
        private String id;
        private String name;

        public ProgNode() {
        }

        public ProgNode(String id, String name, String level) {
            super();
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }



        public List getChild(){
            CropDict parentCropDict=new CropDict();
            parentCropDict.setParentId(Long.valueOf(id));
            List cropDictList=cropDictMapper.listWithoutSplit(parentCropDict).getRows();

            JSONArray jsonArray = new JSONArray(cropDictList);
            List<ProgNode> progNodes= new ArrayList<ProgNode>();
            if(jsonArray.size()>0){
                for (int i = 0;i < jsonArray.size();i++){
                    ProgNode node = new ProgNode();
                    String progId = jsonArray.getJSONObject(i).getString("cropId");
                    String progName = jsonArray.getJSONObject(i).getString("cropName");
                    if(progId != null && progId != ""){
                        node.setId(progId);
                        node.setName(progName);
                        progNodes.add(node);
                    }
                }
            }
            return progNodes;   //返回一个集合
        }

    }

    @Override
    public JSONArray getCropDictTree() {
        //查询一级属性id
        CropDict parentCropDict=new CropDict();
        parentCropDict.setParentId(Long.valueOf(0));
        List cropDictList=cropDictMapper.listWithoutSplit(parentCropDict).getRows();

        JSONArray jsonArray = new JSONArray(cropDictList);

        List<ProgNode> progNodes= new ArrayList<ProgNode>();
        for (int i = 0;i < jsonArray.size();i++){
            ProgNode node = new ProgNode();
            node.setId(jsonArray.getJSONObject(i).getString("cropId"));
            node.setName(jsonArray.getJSONObject(i).getString("cropName"));
            progNodes.add(node);
        }

        JSONArray rtJA = new JSONArray();
        rtJA = getChildInfo(progNodes);    //根据一级查找子级

        return rtJA;
    }

    @Override
    public List<Map<String, Object>> selectOwnCommentGrowTechList(Long memberId) {
        List<Map<String, Object>> list = growTechMapper.selectOwnCommentGrowTechListForMap(memberId);

        for (int i = 0; i < list.size(); i++) {
            Map tempMap = list.get(i);
            iniGrowTech(tempMap, memberId);
        }

        return list;
    }

    @Override
    public List<GrowTech> selectGrowtechListByGrowtechVo(GrowTechVo growTechVo) {
        return growTechMapper.selectGrowtechListByGrowtechVo(growTechVo);
    }

    @Override
    @Transactional
    public GrowTech summGrowTechShareCount(Long growId) {
        GrowTech growTech = growTechMapper.selectGrowTechByIdForUpdate(growId);

        if (growTech.getShareCount() != null) {
            long shareCount = (long) growTech.getShareCount();
            shareCount = shareCount + 1;
            growTech.setShareCount(shareCount);
        } else {
            long shareCount = Long.valueOf(1);
            growTech.setShareCount(shareCount);
        }
        growTechMapper.updateGrowTech(growTech);
        return growTech;
    }

    public JSONArray getChildInfo(List<ProgNode> progNodes) {
        JSONArray rtJA = new JSONArray();
        for (ProgNode prog : progNodes) {
            List<ProgNode> progList = prog.getChild();//查询该属性下的子属性
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", prog.getId());
            jsonObject.put("name", prog.getName());

            if (progList.size() > 0) {//如果该属性还有子属性,继续做查询,直到该属性没有孩子,也就是最后一个节点
                jsonObject.put("children", getChildInfo(progList));
            }
            rtJA.add(jsonObject);
        }
        return rtJA;
    }

    @Override
    public Integer count() {
        return growTechMapper.count();
    }
}
