package com.sinonc.base.service.impl;


import com.sinonc.base.api.domain.BaseFarm;
import com.sinonc.base.mapper.PlantAPIMapper;
import com.sinonc.base.service.IBaseFarmService;
import com.sinonc.base.service.PlantAPIService;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.system.api.RemoteDictService;
import com.sinonc.system.api.domain.SysDictData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 药剂 服务层实现
 *
 * @author sinonc
 * @date 2019-11-17
 */
@Slf4j
@Service
public class PlantAPIServiceImpl implements PlantAPIService {

    @Autowired
    private PlantAPIMapper plantAPIMapper;

    @Autowired
    private RemoteDictService remoteDictService;

    @Autowired
    private IBaseFarmService baseFarmService;


    @Override
    public Map<String, Object> findFertilizerSchema(Long forchardId) {
        BaseFarm baseFarm = baseFarmService.selectBaseFarmById(forchardId);
        List<Map<String, String>> treeList = plantAPIMapper.listFarmGrowInfo(forchardId);
        Map<String, Object> result = new HashMap<String, Object>();
        //初始化数据
        result.put("farmImg", baseFarm.getPictures().split(","));
        result.put("seedling", null);//幼树数据
        result.put("seedlingStatus", false);//是否有幼树
        result.put("beginTree", null);//初果树数据
        result.put("beginTreeStatus", false);//是否有初果树
        result.put("adultTree", null);//成年树
        result.put("adultTreeStatus", false);//是否有成年树
        result.put("oldTree", null);//衰老树
        result.put("oldTreeStatus", false);//是否有衰老树

        //查询果树果品类别
        Map<String, Object> typeMap = plantAPIMapper.listFarmGrowInfoBy(forchardId);
        if (!CollectionUtils.isEmpty(typeMap)){
            String types = String.valueOf(typeMap.get("types"));
            String plantNum = String.valueOf(typeMap.get("plantNum"));
            String plantArea = String.valueOf(typeMap.get("plantArea"));
            String plantAge = String.valueOf(typeMap.get("plantAge"));
            result.put("plantNum", plantNum);
            result.put("plantAge", plantAge);
            result.put("plantArea", plantArea);
            R<List<SysDictData>> dictResult = remoteDictService.getLabelBy("mango_type");
            if (dictResult.getCode() == 200) {
                List<SysDictData> dictDataList = dictResult.getData();
                StringBuffer typeNames = new StringBuffer();
                for (SysDictData sysDictData : dictDataList) {
                    if (types.contains(sysDictData.getDictValue())) {
                        typeNames.append(sysDictData.getDictLabel() + "，");
                    }
                }
                result.put("type", typeNames.toString().substring(0,typeNames.length()-1));
            }
        }

        StringBuffer strbuffer = new StringBuffer();
        strbuffer.append("您的果园");
        if (treeList.size() > 0) {
            for (Map<String, String> treeGrow : treeList) {
                //树龄
                String treeAge = String.valueOf(treeGrow.get("treeAge"));
                //种植面积
                String growArea = String.valueOf(treeGrow.get("growArea"));
                //种植数量
                String growStub = String.valueOf(treeGrow.get("growStub"));
                //数据采集时间
                String dataYear = treeGrow.get("dataYear");

                Map<String, Object> schema = new HashMap<String, Object>();

                int age = Integer.parseInt(treeAge);
                if (age > 0 && age < 3) {
                    //幼树
                    Map<String, String> fert = plantAPIMapper.selectFertilizerByageCode(String.valueOf(1));
                    if (fert != null) {
                        String title = "您的果园有幼树" + growStub + "株，建议施肥方案：";
                        schema.put("title", title);
                        setFertilizerContent(schema, fert);
                        strbuffer.append("幼树" + growStub + "株，" + "种植面积" + growArea + "亩;");
                        result.put("seedling", schema);
                        result.put("seedlingStatus", true);
                    }
                } else if (age >= 3 && age < 7) {
                    //初果树
                    Map<String, String> fert = plantAPIMapper.selectFertilizerByageCode(String.valueOf(2));
                    if (fert != null) {
                        String title = "您的果园有初果树" + growStub + "株，建议施肥方案：";
                        schema.put("title", title);
                        setFertilizerContent(schema, fert);
                        strbuffer.append("初果树" + growStub + "株，" + "种植面积" + growArea + "亩;");
                        result.put("beginTree", schema);
                        result.put("beginTreeStatus", true);
                    }
                } else if (age >= 7 && age < 30) {
                    //成年树
                    Map<String, String> fert = plantAPIMapper.selectFertilizerByageCode(String.valueOf(3));
                    if (fert != null) {
                        String title = "您的果园有成年树" + growStub + "株，建议施肥方案：";
                        schema.put("title", title);
                        setFertilizerContent(schema, fert);
                        strbuffer.append("成年树" + growStub + "株，" + "种植面积" + growArea + "亩;");
                        result.put("adultTree", schema);
                        result.put("adultTreeStatus", true);
                    }
                } else {
                    //衰老树
                    Map<String, String> fert = plantAPIMapper.selectFertilizerByageCode(String.valueOf(9));
                    if (fert != null) {
                        String title = "您的果园有衰老树" + growStub + "株，建议施肥方案：";
                        schema.put("title", title);
                        setFertilizerContent(schema, fert);
                        strbuffer.append("衰老树" + growStub + "株，" + "种植面积" + growArea + "亩;");
                        result.put("oldTree", schema);
                        result.put("oldTreeStatus", true);
                    }
                }
            }
        }
        String farmTreeInfo = strbuffer.toString();
        result.put("farmTreeInfo", farmTreeInfo);
        return result;
    }

    /**
     * 查询当前时间段的流行病虫害
     *
     * @return
     */
    @Override
    public Map<String, Object> selectCurrentInsectInfo() {
        Map<String, Object> result = new HashMap<String, Object>();
        String[] insectCodeArr = null;
        //当前时间段流行病虫害集合
        List<Map<String, Object>> insectList = new ArrayList<Map<String, Object>>();
        //流行病虫害名称集合
        List<Map<String, Object>> insectNameList = new ArrayList<Map<String, Object>>();

        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String currentMonth = sdf.format(new Date());
        Map<String, String> currentInfo = plantAPIMapper.selectCurrentInsectInfo(Integer.valueOf(currentMonth));
        String insectCode = currentInfo.get("insectCode");
        if (insectCode != null) {
            insectCodeArr = insectCode.split(",");
        }
        //查询当前时间段流行的病虫害
        if (insectCodeArr != null && insectCodeArr.length > 0) {
            for (String code : insectCodeArr) {
                Map<String, Object> insect = plantAPIMapper.selectInsectByCode(code);
                //查询病虫害常用药剂
                List<Map<String, String>> listPesticide = plantAPIMapper.listInsectPesticide(code);
                Map<String, Object> nameMap = new HashMap<String, Object>();
                nameMap.put("insectName", insect.get("insectName"));
                nameMap.put("insectCode", insect.get("insectCode"));
                insect.put("listPesticide", listPesticide);
                insectList.add(insect);
                insectNameList.add(nameMap);
            }
        }
        //月份描述
        String description = currentInfo.get("description");
        String period = currentInfo.get("period");
        String periodDesc = "";
        if ("01".equals(period)) {
            periodDesc = "病虫潜伏越冬期";
        } else if ("02".equals(period)) {
            periodDesc = "病虫潜伏期";
        } else if ("03".equals(period)) {
            periodDesc = "病虫高峰期";
        } else if ("04".equals(period)) {
            periodDesc = "病虫严重期";
        }
        String title = "当前时间段为" + description + ",为" + periodDesc;
        result.put("title", title);
        setInsectSchema(result, insectList.get(0));
        result.put("currentInsect", insectNameList);
        result.put("currentMonth", currentMonth);
        return result;
    }

    /**
     * 根据病虫害编码查询病虫害信息
     *
     * @param insectCode
     * @return
     */
    @Override
    public Map<String, Object> selectInsectByCode(String insectCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> insect = plantAPIMapper.selectInsectByCode(insectCode);
        result.put("insectSchemaInfo", null);
        if (insect != null) {
            //查询病虫害常用药剂
            List<Map<String, String>> listPesticide = plantAPIMapper.listInsectPesticide(insectCode);
            insect.put("listPesticide", listPesticide);
        }
        setInsectSchema(result, insect);
        return result;
    }

    /**
     * 根据病虫害名称查询病虫害信息
     *
     * @param insectName
     * @return
     */
    @Override
    public Map<String, Object> selectInsectByName(String insectName) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> insect = plantAPIMapper.selectInsectByName(insectName);
        result.put("insectSchemaInfo", null);
        if (insect != null) {
            //查询病虫害常用药剂
            List<Map<String, String>> listPesticide = plantAPIMapper.listInsectPesticide(insect.get("insectCode").toString());
            insect.put("listPesticide", listPesticide);
        }
        setInsectSchema(result, insect);
        return result;
    }

    /**
     * 根据月份查询流行病虫害信息
     *
     * @param month
     * @return
     */
    @Override
    public Map<String, Object> selectInsectByMonth(Integer month) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, String> periodInfo = plantAPIMapper.selectCurrentInsectInfo(month);
        String[] insectCodeArr = null;
        //流行病虫害名称集合
        List<Map<String, Object>> insectNameList = new ArrayList<Map<String, Object>>();
        String insectCode = periodInfo.get("insectCode");
        if (insectCode != null) {
            insectCodeArr = insectCode.split(",");
        }
        //查询当前时间段流行的病虫害
        if (insectCodeArr != null && insectCodeArr.length > 0) {
            for (String code : insectCodeArr) {
                Map<String, Object> insect = plantAPIMapper.selectInsectByCode(code);
                Map<String, Object> nameMap = new HashMap<String, Object>();
                nameMap.put("insectName", insect.get("insectName"));
                nameMap.put("insectCode", insect.get("insectCode"));
                insectNameList.add(nameMap);
            }
        }
        //月份描述
        String description = periodInfo.get("description");
        String period = periodInfo.get("period");
        String periodDesc = "";
        if ("01".equals(period)) {
            periodDesc = "病虫潜伏越冬期";
        } else if ("02".equals(period)) {
            periodDesc = "病虫潜伏期";
        } else if ("03".equals(period)) {
            periodDesc = "病虫高峰期";
        } else if ("04".equals(period)) {
            periodDesc = "病虫严重期";
        }
        periodInfo.put("period", periodDesc);
        String title = "当前时间段为" + description + ",为" + periodDesc + ",常见的病虫害有以下几种。";
        result.put("title", title);
        result.put("periodInfo", periodInfo);
        result.put("insectInfo", insectNameList);
        return result;
    }

    /**
     * 查询所有的施肥技术
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> listFertilizerSkill() {
        List<Map<String, Object>> listData = plantAPIMapper.listFertilizerSkill();
        return listData;
    }

    /**
     * 根据施肥ID查询施肥技术详情
     *
     * @param skillId
     * @return
     */
    @Override
    public Map<String, Object> selectFertilizerSkillById(Long skillId) {
        Map<String, Object> skill = plantAPIMapper.selectFertilizerSkillById(skillId);
        return skill;
    }

    /**
     * 根据元素名称模糊查询施肥技术详情
     *
     * @param eleName
     * @return
     */
    @Override
    public Map<String, Object> selectFertilizerSkillByEleName(String eleName) {
        Map<String, Object> data = plantAPIMapper.selectFertilizerSkillByEleName(eleName);
        return data;
    }

    /**
     * 设置病虫害方案内容
     *
     * @param result
     * @param insect
     */
    public void setInsectSchema(Map<String, Object> result, Map<String, Object> insect) {
        //病虫害防治药剂
        Object pesticideObj = insect.get("listPesticide");
        StringBuffer userDesc = new StringBuffer();
        StringBuffer remark = new StringBuffer();
        if (pesticideObj != null) {
            List<Map<String, String>> listPesticide = (List<Map<String, String>>) pesticideObj;
            if (listPesticide.size() > 0) {
                for (Map<String, String> pesticide : listPesticide) {
                    userDesc.append(pesticide.get("pesticideName") + ",");
                    userDesc.append("每年最多使用次数" + pesticide.get("maxTime") + ",");
                    userDesc.append("安全周期" + pesticide.get("safePeriod") + "；");
                    if (pesticide.get("remark") != null) {
                        remark.append(pesticide.get("pesticideName") + ",");
                        remark.append(pesticide.get("remark") + "；");
                    }
                }
            }
        }
        String[] imageList = null;
        Object imageObj = insect.get("insectImage");
        if (imageObj != null) {
            String imageUrl = imageObj.toString();
            if (imageUrl.length() > 0) {
                imageList = imageUrl.split(",");
            }
        }
        insect.put("imageList", imageList);
        insect.put("insectDescTitle", "病虫害介绍：");
        insect.put("cureDescTitle", "化学防治时期或指标:");
        insect.put("pesticideTitle", "常用防治药剂:");
        insect.put("remarkTitle", "注意事项:");
        if (userDesc.length() > 0) {
            insect.put("pesticideContent", userDesc.toString());
        } else {
            insect.put("pesticideContent", "未查找到常用的药剂信息");
        }
        if (remark.length() > 0) {
            insect.put("remarkContent", remark.toString());
        } else {
            insect.put("remarkContent", "未查找到需要特别注意的相关内容");
        }
        result.put("insectSchemaInfo", insect);
    }

    /**
     * 设置施肥方案内容
     *
     * @param schema
     * @param fert
     */
    public void setFertilizerContent(Map<String, Object> schema, Map<String, String> fert) {
        StringBuffer str = new StringBuffer();
        String organicSchema = fert.get("organicSchema");
        String ureaSchema = fert.get("ureaSchema");
        String nitrogenSchema = fert.get("nitrogenSchema");
        String aciditySoilSchema = fert.get("aciditySoilSchema");
        String alkalineSoilSchema = fert.get("alkalineSoilSchema");
        if (StringUtils.isNotEmpty(organicSchema)) {
            str.append(organicSchema);
            str.append(" ");
        }
        if (StringUtils.isNotEmpty(ureaSchema)) {
            str.append(ureaSchema);
            str.append(" ");
        }
        if (StringUtils.isNotEmpty(nitrogenSchema)) {
            str.append(nitrogenSchema);
            str.append(" ");
        }
        if (StringUtils.isNotEmpty(aciditySoilSchema)) {
            str.append(aciditySoilSchema);
            str.append(" ");
        }
        if (StringUtils.isNotEmpty(alkalineSoilSchema)) {
            str.append(alkalineSoilSchema);
        }
        schema.put("content", str.toString());
        schema.put("fertDetail", fert);
    }


}
