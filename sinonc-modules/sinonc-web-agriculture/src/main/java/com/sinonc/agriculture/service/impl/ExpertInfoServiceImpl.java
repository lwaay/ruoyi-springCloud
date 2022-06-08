package com.sinonc.agriculture.service.impl;

import com.sinonc.agriculture.constants.ConcernInfoConstants;
import com.sinonc.agriculture.constants.ExpertInfoConstants;
import com.sinonc.agriculture.domain.*;
import com.sinonc.agriculture.dto.ExperInfoDto;
import com.sinonc.agriculture.mapper.ConcernInfoMapper;
import com.sinonc.agriculture.mapper.ExpertCertifiedMapper;
import com.sinonc.agriculture.mapper.ExpertInfoMapper;
import com.sinonc.agriculture.mapper.ExpertInfoModifyMapper;
import com.sinonc.agriculture.service.*;
import com.sinonc.agriculture.vo.ExpertInfoVo;
import com.sinonc.base.api.domain.CropDict;
import com.sinonc.common.core.exception.BusinessException;
import com.sinonc.common.core.text.Convert;
import com.sinonc.common.core.utils.DateUtils;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.utils.timeUtil.TimeUtils;
import com.sinonc.system.api.RemoteConfigService;
import com.sinonc.system.api.RemoteUserService;
import com.sinonc.system.api.domain.SysDictData;
import com.sinonc.system.api.domain.WxUser;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 专家消息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-03-05
 */
@Service
public class ExpertInfoServiceImpl implements ExpertInfoService {
    @Resource
    private ExpertInfoMapper expertInfoMapper;

    @Autowired
    private MemberInfoService memberInfoService;

    @Resource
    private ConcernInfoMapper concernInfoMapper;

    @Autowired
    private CropDictService cropDictService;

    @Autowired
    private SectionDictService sectionDictService;

    @Autowired
    private MemberDictService memberDictService;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @Autowired
    private AgriQuestionService agriQuestionService;

    @Autowired
    private ConcernInfoService concernInfoService;

    @Resource
    private ExpertCertifiedMapper expertCertifiedMapper;

    @Autowired
    private RemoteUserService userService;

    @Resource
    private ExpertInfoModifyMapper expertInfoModifyMapper;

    @Autowired
    private RemoteConfigService remoteConfigService;


    /**
     * 查询专家消息
     *
     * @param expertId 专家消息ID
     * @return 专家消息
     */
    @Override
    public ExpertInfo selectExpertInfoById(Long expertId) {
        return expertInfoMapper.selectExpertInfoById(expertId);
    }

    /**
     * 查询专家消息列表
     *
     * @param expertInfo 专家消息
     * @return 专家消息
     */
    @Override
    public List selectExpertInfoList(ExpertInfo expertInfo) {
        List<ExpertInfo> expertInfoList = expertInfoMapper.selectExpertInfoList(expertInfo);
        Map<String, String> mgmap = new HashMap<>();
        //擅长专业
        List<SysDictData> mangoTypeList = remoteConfigService.dictType("mango_type").getData();

        if(StringUtils.isNotNull(mangoTypeList) && StringUtils.isNotNull(expertInfoList)){
            //JDK1.8用stream比较两个List某个属性
            //mangoType = mangoType.stream().filter(a->a.ListgetDictCode());

            mangoTypeList.forEach(x->{
                mgmap.put(x.getDictValue(),x.getDictLabel());
            });
            for (ExpertInfo info : expertInfoList) {
                List<String> code = Arrays.asList(info.getCropCode().split(","));
                List<String> region = Arrays.asList(info.getRegionCode().split(","));
                StringBuilder cropCode = new StringBuilder();
                StringBuilder regionCode = new StringBuilder();
                List<String> cropName = new ArrayList<>();
                for (String s : code) {
                    if(mgmap.get(s) != null){
                        cropName.add(mgmap.get(s));
                    }
                }
                cropCode.append(StringUtils.join(cropName,","));
                List<String> regionName = new ArrayList<>();
                for (String s : region) {
                    SectionDict sectionDict = sectionDictService.selectSectionDictById(Long.valueOf(s));
                    if (Optional.ofNullable(sectionDict).isPresent()) {
                        regionName.add(sectionDict.getSectionName());
                    }
                }
                regionCode.append(StringUtils.join(regionName,","));
                info.setCropCode(cropCode.toString());
                info.setRegionCode(regionCode.toString());
            }
        }


        return expertInfoList;
    }

    /**
     * 新增专家消息
     *
     * @param expertInfo 专家消息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertExpertInfo(ExpertInfo expertInfo) {
        //根据会员id查询会员信息
        WxUser info = userService.getWxUserById(expertInfo.getMemberId()).getData();
        if (info != null) {
            expertInfo.setPersonalPhoto(info.getHeadimg());
            expertInfo.setPhone(info.getPhone());
            //审核中
            expertInfo.setVerifyStatus(ExpertInfoConstants.VERIFY_STATUS_AUDITING);
        }
        expertInfo.setCreateTime(DateUtils.getNowDate());


        int rows = 0;
        Long expertId = expertInfo.getExpertId();
        if (expertId != null) {
            //如果专家ID不为空，则不是第一次申请
            rows = rows + expertInfoMapper.updateExpertInfo(expertInfo);
        } else {
            rows = rows + expertInfoMapper.insertExpertInfo(expertInfo);
        }

//        expertCertifiedMapper.deleteExpertCertifiedByExpertInfoId(expertInfo.getExpertId());

//        List<ExpertCertified> expertCertifiedList = expertInfo.getExpertCertifiedList();
//
//        if (expertCertifiedList != null && expertCertifiedList.size() > 0) {
//
//            for (int i = 0; i < expertCertifiedList.size(); i++) {
//                ExpertCertified expertCertified = expertCertifiedList.get(i);
//                expertCertified.setExpertId(expertInfo.getExpertId());
//                expertCertified.setMemberId(expertInfo.getMemberId());
//                expertCertifiedMapper.insertExpertCertified(expertCertified);
//            }
//        }

        return rows;
    }

    /**
     * 修改专家消息
     *
     * @param expertInfo 专家消息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateExpertInfo(ExpertInfo expertInfo) {
        ExpertInfo tempExpertInfo = expertInfoMapper.selectExpertInfoById(expertInfo.getExpertId());
        expertInfo.setUpdateTime(DateUtils.getNowDate());
        expertInfo.setMemberId(tempExpertInfo.getMemberId());
        expertInfo.setRoleType(tempExpertInfo.getRoleType());

        if (expertInfo.getVerifyStatus() != null && expertInfo.getVerifyStatus() == ExpertInfoConstants.VERIFY_STATUS_ADOPT) { //审核通过，成为专家，同步修改用户表里的用户角色从农友修改为专家
            MemberInfo memberInfo = memberInfoService.getMemberInfoById(expertInfo.getMemberId());
            if (memberInfo != null) {
                memberInfo.setRole(expertInfo.getRoleType());
                //更新用户信息
            }
        }
        return expertInfoMapper.updateExpertInfo(expertInfo);
    }

    @Override
    public int auditExpertInfo(ExpertInfo expertInfo){
        ExpertInfo param = new ExpertInfo();
        param.setExpertId(expertInfo.getExpertId());
        param.setVerifyStatus(expertInfo.getVerifyStatus());
        return expertInfoMapper.updateExpertInfo(expertInfo);
    }

    /**
     * 删除专家消息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpertInfoByIds(String ids) {
        return expertInfoMapper.deleteExpertInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除专家消息信息
     *
     * @param expertId 专家消息ID
     * @return 结果
     */
    @Override
    public int deleteExpertInfoById(Long expertId) {
        return expertInfoMapper.deleteExpertInfoById(expertId);
    }

    /**
     * 查询专家列表返回list<String,Map>
     *
     * @param expertInfo
     * @return
     */
    @Override
    public List<Map<String, Object>> selectExpertInfoListMap(ExpertInfo expertInfo) {
        return expertInfoMapper.selectExpertInfoListMap(expertInfo);
    }

    /**
     * 根据专家id查询专家
     *
     * @param expertId
     * @return
     */
    @Override
    public Map<String, Object> selectExpertInfoMemberId(Long expertId) {
        return expertInfoMapper.selectExpertInfoMemberId(expertId);
    }

    @Override
    public ExpertInfo selectExpertInfoByUserId(Long userId) {
        return expertInfoMapper.selectExpertInfoByUserId(userId);
    }

    @Override
    public List<ExpertInfo> selectExpertInfoListNotInConcern(ExpertInfo expertInfo) {
        List<ExpertInfo> expertInfos = expertInfoMapper.selectExpertInfoListNotInConcern();
        return expertInfos;
    }

    @Override
    public List selectAllExpertInfoList(ExpertInfo expertInfo) {
        List<ExperInfoDto> expertInfoList = expertInfoMapper.selectAllExpertInfoList();

        for (int i = 0; i < expertInfoList.size(); i++) {
            ExperInfoDto tempExperInfoDto = expertInfoList.get(i);


            ConcernInfo concernInfo = new ConcernInfo();
            concernInfo.setTargetId(tempExperInfoDto.getExpertId());
            concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_EXPERT);
            //关注数
            List<ConcernInfo> concernInfoNumList = concernInfoMapper.selectConcernInfoList(concernInfo);
            //是否被自己关注
            concernInfo.setMemberId(expertInfo.getMemberId());
            List<ConcernInfo> concernInfoCheckList = concernInfoMapper.selectConcernInfoList(concernInfo);
            //查询列表，列表为零则没关注
            if (concernInfoCheckList.size() > 0) {
                tempExperInfoDto.setChecked(true);
            }
            if (concernInfoNumList.size() > 0) {
                tempExperInfoDto.setConcernNum(Long.valueOf(concernInfoNumList.size()));
            }
            findExpertInfoCropDictName(tempExperInfoDto);
            findExpertInfoRegionCodName(tempExperInfoDto);
        }

        return expertInfoList;
    }

//    @Override
//    public List<MemberInfo> selectAllMemberInfoList(Long memberId) {
//        MemberInfo queryMemberInfo = new MemberInfo();
//        List<MemberInfo> memberInfoList = memberInfoMapper.selectMemberInfoList(queryMemberInfo);
//
//        for (int i = 0; i < memberInfoList.size(); i++) {
//            MemberInfo tempMemberInfo = memberInfoList.get(i);
//
//            ConcernInfo concernInfo = new ConcernInfo();
//            concernInfo.setTargetId(tempMemberInfo.getMemberId());
//            concernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_FARMER);
//            //关注数
//            List<ConcernInfo> concernInfoNumList = concernInfoMapper.selectConcernInfoList(concernInfo);
//            //是否被自己关注
//            concernInfo.setMemberId(memberInfo.getMemberId());
//            List<ConcernInfo> concernInfoCheckList = concernInfoMapper.selectConcernInfoList(concernInfo);
//            //查询列表，列表为零则没关注
//            if (concernInfoCheckList.size() > 0) {
//                tempMemberInfo.setChecked(true);
//            }
//            if (concernInfoNumList.size() > 0) {
//                tempMemberInfo.setConcernNum(Long.valueOf(concernInfoNumList.size()));
//            }
//        }
//
//        return memberInfoList;
//    }

    @Override
    public boolean isApplyExpret(Long memberId) {
        ExpertInfo expertInfo = new ExpertInfo();
        expertInfo.setMemberId(memberId);
        expertInfo.setVerifyStatus(ExpertInfoConstants.VERIFY_STATUS_AUDITING);
        List<ExpertInfo> expertInfoList = expertInfoMapper.selectExpertInfoList(expertInfo);
        if (expertInfoList != null && expertInfoList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void selectExpretInfoDetailById(Map<String, Object> result, Long expertInfoId, Long memberId) throws Exception {
        //专家信息
        ExpertInfo expertInfo = this.selectExpertInfoById(expertInfoId);
        if (expertInfo != null) {
            //查询作物名称
            iniRegionCodeName(result, expertInfo);
            //查询专家粉丝数量
            iniFansCount(result, expertInfoId);
            //查询回答数、点赞数
            iniMemberDict(result, expertInfo);
            //查询专家回答问题与回复
            iniQuestionAnswer(result, expertInfo);

            ConcernInfo paraConcernInfo = new ConcernInfo();
            paraConcernInfo.setTargetId(expertInfoId);
            paraConcernInfo.setMemberId(memberId);
            paraConcernInfo.setTargetType(ConcernInfoConstants.CONCERN_INFO_EXPERT);
            List list = concernInfoMapper.selectConcernInfoList(paraConcernInfo);
            if (list != null && list.size() > 0) {
                expertInfo.setChecked(true);
            }

            result.put("expertInfo", expertInfo);
        }
    }

//    @Override
//    public List queryExpertInfoForIndexList(ExpertInfo expertInfo) {
//        expertInfo.setVerifyStatus(ExpertInfoConstants.VERIFY_STATUS_ADOPT);
//        List<ExperInfoDto> expertInfoList = expertInfoMapper.selectExpertInfoNoPageList(expertInfo);
//
//        if (expertInfoList != null) {
//            for (int i = 0; i < expertInfoList.size(); i++) {
//                ExperInfoDto experInfoDto = expertInfoList.get(i);
//                MemberInfo memberInfo = memberInfoMapper.selectMemberInfoById(experInfoDto.getMemberId());
//                experInfoDto.setMemberInfo(memberInfo);
//
//                String cropCodeString = experInfoDto.getCropCode();
//                if (StringUtils.isNotEmpty(cropCodeString)) {
//                    String[] cropCodeArr = cropCodeString.split(",");
//                    List cropCodeList = new ArrayList();
//                    for (int j = 0; j < cropCodeArr.length; j++) {
//                        CropDict cropDict = cropDictService.selectCropDictById(Long.valueOf(cropCodeArr[j]));
//                        cropCodeList.add(cropDict);
//                    }
//                    experInfoDto.setCropCodeList(cropCodeList);
//                }
//
//                String regionCode = experInfoDto.getRegionCode();
//                if (StringUtils.isNotEmpty(regionCode)) {
//                    String[] regionCodeArr = regionCode.split(",");
//                    List regionCodeList = new ArrayList();
//                    for (int j = 0; j < regionCodeArr.length; j++) {
//                        SectionDict sectionDict = sectionDictService.selectSectionDictById(Long.valueOf(regionCodeArr[j]));
//                        regionCodeList.add(sectionDict);
//                    }
//                    experInfoDto.setRegionCodeList(regionCodeList);
//                }
//            }
//        }
//
//        return expertInfoList;
//    }

    @Override
    public ExpertInfoVo selectExpertInfoByIdAndType(long expertId) {
        ExpertInfo expertInfo = expertInfoMapper.selectExpertInfoById(expertId);
        if (expertInfo == null) {
            throw new BusinessException("专家不存在！专家id=" + expertId);
        }
//        if (expertInfo.getRoleType() != ExpertInfoConstants.GUO_JI_YUAN) {
//            throw new BusinessException("该专家不是果技员，专家id=" + expertId + "专家状态type=" + expertInfo.getRoleType());
//        }
        ExpertInfoVo expertInfoVo = new ExpertInfoVo();
        BeanUtils.copyProperties(expertInfo,expertInfoVo);
        // 获取擅长农作物 不用
        String cropCode = expertInfo.getCropCode();
        if (StringUtils.isNotEmpty(cropCode)) {
            List<CropDict> cropDictList = new ArrayList<>();
            String[] cropIds = cropCode.split("[，,]");
            for (String cropId : cropIds) {
                CropDict cropDict = cropDictService.selectCropDictById(Long.valueOf(cropId));
                cropDictList.add(cropDict);
            }
            expertInfoVo.setCropDictList(cropDictList);
        }
        // 获取擅长领域 不用
        String sectionIdStr = expertInfo.getRegionCode();
        if (StringUtils.isNotEmpty(sectionIdStr)) {
            List<SectionDict> sectionDicts = new ArrayList<>();
            String[] sectionIds = sectionIdStr.split("[，,]");
            for (String sectionId : sectionIds) {
                SectionDict sectionDict = sectionDictService.selectSectionDictById(Long.valueOf(sectionId));
                sectionDicts.add(sectionDict);
            }
            expertInfoVo.setSectionDicts(sectionDicts);
        }
//        // 获取资质证明
//        ExpertCertified expertCertified = new ExpertCertified();
//        expertCertified.setExpertId(expertInfo.getExpertId());
//        List<ExpertCertified> expertCertifieds = expertCertifiedMapper.selectExpertCertifiedList(expertCertified);
//        expertInfoVo.setExpertCertifiedList(expertCertifieds);
        return expertInfoVo;
    }

    @Override
    public ExpertInfo selectAuditExpertInfoById(Long expertId) {
        ExpertInfo expertInfo= expertInfoMapper.selectExpertInfoById(expertId);
        selectCropNameList(expertInfo);
        selectSectionNameList(expertInfo);
        return expertInfo;
    }

    private void selectCropNameList(ExpertInfo tempExpertInfo){
        String cropCode=tempExpertInfo.getCropCode();
        String [] cropCodeArr=cropCode.split(",");
        StringBuilder cropName=new StringBuilder();
        for (int i = 0; i < cropCodeArr.length; i++) {
            CropDict cropDict=cropDictService.selectCropDictById(Long.valueOf(cropCodeArr[i]));
            cropName.append(cropDict.getCropName());
            cropName.append(",");
        }
        tempExpertInfo.setCropCode(cropName.toString());
    }

    private void selectSectionNameList(ExpertInfo tempExpertInfo){
        String regionCode=tempExpertInfo.getRegionCode();
        String[] regionCodeArr=regionCode.split(",");
        StringBuilder regionName=new StringBuilder();
        for (int i = 0; i <regionCodeArr.length ; i++) {
            SectionDict sectionDict=sectionDictService.selectSectionDictById(Long.valueOf(regionCodeArr[i]));
            regionName.append(sectionDict.getSectionName());
            regionName.append(",");
        }
        tempExpertInfo.setRegionCode(regionName.toString());
    }

    @Override
    public List<ExpertInfo> selectExpertInfoNoPageList(ExpertInfo expertInfo) {
        expertInfo.setVerifyStatus(ExpertInfoConstants.VERIFY_STATUS_ADOPT);
        List<ExpertInfo> expertInfoList = expertInfoMapper.selectExpertInfoList(expertInfo);

        for (int i = 0; i < expertInfoList.size(); i++) {
            ExpertInfo tempExpertInfo = expertInfoList.get(i);
            //去除敏感信息
            tempExpertInfo.setIdenCard("");
        }

        return expertInfoList;
    }

    @Override
    public List<ExperInfoDto> selectExpertInfoNoPageListFilter(ExpertInfoVo expertInfoVo) {
        expertInfoVo.setVerifyStatus(ExpertInfoConstants.VERIFY_STATUS_ADOPT);

        List<ExperInfoDto> expertInfoList=expertInfoMapper.selectExpertInfoNoPageListFilter(expertInfoVo);

        for (int i = 0; i < expertInfoList.size(); i++) {
            ExperInfoDto tempExpertInfo = expertInfoList.get(i);
            //去除敏感信息
            tempExpertInfo.setIdenCard("");
            // 获取擅长农作物
//            String cropCode = tempExpertInfo.getCropCode();
//            if (StringUtils.isNotEmpty(cropCode)) {
//                List<CropDict> cropDictList = new ArrayList<>();
//                String[] cropIds = cropCode.split("[，,]");
//                for (String cropId : cropIds) {
//                    CropDict cropDict = cropDictService.selectCropDictById(Long.valueOf(cropId));
//                    cropDictList.add(cropDict);
//                }
//                tempExpertInfo.setCropDictList(cropDictList);
//            }
//            // 获取擅长领域
//            String sectionIdStr = tempExpertInfo.getRegionCode();
//            if (StringUtils.isNotEmpty(sectionIdStr)) {
//                List<SectionDict> sectionDicts = new ArrayList<>();
//                String[] sectionIds = sectionIdStr.split("[，,]");
//                for (String sectionId : sectionIds) {
//                    SectionDict sectionDict = sectionDictService.selectSectionDictById(Long.valueOf(sectionId));
//                    sectionDicts.add(sectionDict);
//                }
//                tempExpertInfo.setSectionDicts(sectionDicts);
//            }
        }

        return expertInfoList;
    }


    /**
     * 查询回答数、点赞数
     *
     * @param result
     * @param expertInfo
     */
    private void iniMemberDict(Map<String, Object> result, ExpertInfo expertInfo) {
        List<MemberDict> memberDictList = memberDictService.getMemberDictByMemberId(expertInfo.getMemberId());//查询专家的回答数，和点赞数量
        if (memberDictList != null && memberDictList.size() > 0) {
            Map<String, Object> supportAndanswerMap = new HashMap<>();
            for (MemberDict memberDict : memberDictList) {
                supportAndanswerMap.put(memberDict.getDictType(), memberDict.getDictValue());
            }
            result.put("supportAndanswerMap", supportAndanswerMap);
        } else {
            result.put("supportAndanswerMap", new HashMap<>());
        }
    }

    /**
     * 查询专家粉丝数量
     *
     * @param result
     * @param expertInfoId
     */
    private void iniFansCount(Map<String, Object> result, Long expertInfoId) {
        List<Map<String, Object>> concernInfolist = concernInfoService.selectConcernInfoByIdforMerber(expertInfoId);//专家的粉丝
        if (concernInfolist != null && concernInfolist.size() > 0) {
            result.put("fansCount", concernInfolist.size());
        } else {
            result.put("fansCount", 0);
        }
    }

    /**
     * 查询作物名称
     *
     * @param result
     * @param expertInfo
     */
    private void iniRegionCodeName(Map<String, Object> result, ExpertInfo expertInfo) {
        if (expertInfo.getRegionCode() != null) {
            String[] regionCodeList = expertInfo.getRegionCode().split(",");//获取专业领域
            if (regionCodeList.length > 0) {
                List<String> regionCodName = new ArrayList<>();
                for (String regionCodeid : regionCodeList) {
                    if (!StringUtils.isEmpty(regionCodeid)) {
                        SectionDict sectionDict = sectionDictService.selectSectionDictById(Long.valueOf(regionCodeid));
                        if (sectionDict != null) {
                            regionCodName.add(sectionDict.getSectionName());
                        }
                    }
                }
                result.put("regionCodName", regionCodName);
            }
        } else {
            result.put("regionCodName", new ArrayList());
        }

    }

    /**
     * 查询专家回答问题与回复
     *
     * @param result
     */
    private void iniQuestionAnswer(Map<String, Object> result, ExpertInfo expertInfo) throws ParseException {
        List<Map<String, Object>> questionAnswerlist1 = questionAnswerService.getQuestionAnswerByMemberId(expertInfo.getMemberId());
        if (questionAnswerlist1 != null && questionAnswerlist1.size() > 0) {
            List<Map<String, Object>> questionAnswerlist = removeRepeatMapByKey(questionAnswerlist1, "question_id");

            for (Map<String, Object> questionAnswer : questionAnswerlist) {
                Map<String, Object> agriQuestion = agriQuestionService.getAgriQuestionByIdForMap(Long.parseLong(questionAnswer.get("question_id").toString()));
                if (agriQuestion == null) {
                    continue;
                }
                if (agriQuestion.get("create_time") != null) {
                    String time = agriQuestion.get("create_time").toString().substring(0, agriQuestion.get("create_time").toString().length() - 2);
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
                    agriQuestion.put("time", TimeUtils.format(sDateFormat.parse(time))); //根据问题创建时间，查询问题分钟，小时，天前发布。
                }
                questionAnswer.put("agriQuestion", agriQuestion);
            }
            result.put("questionAnswerlist", questionAnswerlist);
            result.put("dtTotal", questionAnswerlist.size());
        } else {
            result.put("questionAnswerlist", new ArrayList());
            result.put("dtTotal", 0);
        }
    }


    private static List<Map<String, Object>> removeRepeatMapByKey(List<Map<String, Object>> list, String mapKey) {
        if (CollectionUtils.isEmpty(list)) return null;

        //把list中的数据转换成msp,去掉同一id值多余数据，保留查找到第一个id值对应的数据
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Map> msp = new HashMap<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            Map map = list.get(i);
            String id = map.get(mapKey).toString();
            map.remove(mapKey);
            msp.put(id, map);
        }
        //把msp再转换成list,就会得到根据某一字段去掉重复的数据的List<Map>
        Set<String> mspKey = msp.keySet();
        for (String key : mspKey) {
            Map newMap = msp.get(key);
            newMap.put(mapKey, key);
            listMap.add(newMap);
        }
        return listMap;
    }

    private void findExpertInfoCropDictName(ExpertInfo tempExpertInfo) {
        String cropCode = tempExpertInfo.getCropCode();
        if (cropCode != null) {
            String[] cropCodelist = cropCode.split(",");
            List<String> cropDictName = new ArrayList<>();
            Map<String, String> mgmap = new HashMap<>();
            List<SysDictData> mangoType = remoteConfigService.dictType("mango_type").getData();
            mangoType.forEach(x->{
                mgmap.put(x.getDictValue(),x.getDictLabel());
            });
            for (String cropCodeid : cropCodelist) {
                if (!StringUtils.isEmpty(cropCodeid)) {
                    if (mgmap.get(cropCodeid) != null) {
                        cropDictName.add(mgmap.get(cropCodeid));
                    }
                }
            }
            tempExpertInfo.setCropCodeList(cropDictName);
        }
    }


    private void findExpertInfoRegionCodName(ExpertInfo tempExpertInfo) {
        String regionCode = tempExpertInfo.getRegionCode();
        if (regionCode != null) {
            String[] regionCodeList = regionCode.split(",");
            List<String> regionCodName = new ArrayList<>();
            for (String regionCodeid : regionCodeList) {
                if (!StringUtils.isEmpty(regionCodeid)) {
                    SectionDict sectionDict = sectionDictService.selectSectionDictById(Long.valueOf(regionCodeid));
                    if (sectionDict != null) {
                        regionCodName.add(sectionDict.getSectionName());
                    }
                }
            }
            tempExpertInfo.setRegionCodeList(regionCodName);
        }
    }

    /**
     * 查询专家数量
     * @return
     */
    @Override
    public int selectExpertCount(){
        return expertInfoMapper.selectExpertCount();
    }


}
