package com.sinonc.orders.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.CategoryItem;
import com.sinonc.orders.domain.FarmGrade;
import com.sinonc.orders.domain.GradeCategory;
import com.sinonc.orders.mapper.CategoryItemMapper;
import com.sinonc.orders.mapper.FarmGradeMapper;
import com.sinonc.orders.mapper.GradeCategoryMapper;
import com.sinonc.orders.service.FarmGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基地评级 服务层实现
 *
 * @author sinonc
 * @date 2019-11-23
 */
@Service
public class FarmGradeServiceImpl implements FarmGradeService {
	@Autowired
	private FarmGradeMapper farmGradeMapper;
	@Autowired
	private GradeCategoryMapper gradeCategoryMapper;
	@Autowired
	private CategoryItemMapper categoryItemMapper;



	/**
     * 查询基地评级信息
     *
     * @param gradeId 基地评级ID
     * @return 基地评级信息
     */
    @Override
	public FarmGrade getFarmGradeById(Long gradeId) throws JsonProcessingException {
    	//查询评级类别
		GradeCategory gradeCategory=new GradeCategory();
		gradeCategory.setGradeIdP(gradeId);
		List<GradeCategory> gradeCategoryList=gradeCategoryMapper.selectGradeCategoryList(gradeCategory);

		//查询评级项目
		List categoryItemAll=new ArrayList();
		for (int i = 0; i < gradeCategoryList.size(); i++) {
			GradeCategory gradeCategoryTemp=gradeCategoryList.get(i);
			selectCategoryItem(categoryItemAll,gradeCategoryTemp);
		}

		//转换为json数据
		ObjectMapper mapper = new ObjectMapper();
		String categoryItemAllJson = mapper.writeValueAsString(categoryItemAll);
		FarmGrade farmGrade= farmGradeMapper.selectFarmGradeById(gradeId);
		farmGrade.setGradeItemJson(categoryItemAllJson);

		return farmGrade;
	}

	private void selectCategoryItem(List categoryItemAll,GradeCategory gradeCategoryTemp){
		CategoryItem categoryItem=new CategoryItem();
		categoryItem.setCategoryIdP(gradeCategoryTemp.getCategoryId());
		List<CategoryItem> categoryItemList=categoryItemMapper.selectCategoryItemList(categoryItem);
		for (int i = 0; i < categoryItemList.size(); i++) {
			CategoryItem categoryItemTemp=categoryItemList.get(i);
			categoryItemTemp.setCategoryName(gradeCategoryTemp.getCategoryName());
		}
		categoryItemAll.addAll(categoryItemList);
	}

	/**
     * 查询基地评级列表
     *
     * @param farmGrade 基地评级信息
     * @return 基地评级集合
     */
	@Override
	public List<FarmGrade> listFarmGrade(FarmGrade farmGrade) {
	    return farmGradeMapper.selectFarmGradeList(farmGrade);
	}

    /**
     * 新增基地评级
     *
     * @param farmGrade 基地评级信息
     * @return 结果
     */
	@Override
	@Transactional
	public int addFarmGrade(FarmGrade farmGrade) throws Exception {

		String itemJson=farmGrade.getGradeItemJson();
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap> checkItems = mapper.readValue(itemJson,new TypeReference<List<HashMap>>() { });

		Map gradeCategoryMap=new HashMap();
		BigDecimal gradeValue=new BigDecimal("0");
		for (int i = 0; i <8 ; i++) {
			GradeCategory tempGradeCategory=generateCategoryAndItem(String.valueOf(i),checkItems);
			if(tempGradeCategory==null){
				break;
			}
			gradeCategoryMap.put(String.valueOf(i),tempGradeCategory);
			BigDecimal tempCategoryValue=tempGradeCategory.getCategoryValue().multiply(tempGradeCategory.getCategoryPercent()).
					divide(new BigDecimal("100"));
			gradeValue=gradeValue.add(tempCategoryValue);
		}
		farmGrade.setGradeValue(gradeValue);
		farmGrade.setGradeCategoryMap(gradeCategoryMap);
		farmGrade.setGradeResult(changeValueToGrade(gradeValue));

	    return persistenceFarmGrade(farmGrade);
	}


	/**
	 * 持久化
	 * @param farmGrade
	 * @return
	 */
	private int persistenceFarmGrade(FarmGrade farmGrade){
		int rows=0;
		rows=rows+farmGradeMapper.insertFarmGrade(farmGrade);

		Map<String,GradeCategory> gradeCategoryMap=farmGrade.getGradeCategoryMap();

		for(String key:gradeCategoryMap.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
			GradeCategory tempGradeCategory = gradeCategoryMap.get(key);

			tempGradeCategory.setGradeIdP(farmGrade.getGradeId());
			rows=rows+gradeCategoryMapper.insertGradeCategory(tempGradeCategory);
			List categoryItemList=tempGradeCategory.getCategoryItemList();
			for (int i = 0; i < categoryItemList.size(); i++) {
				CategoryItem categoryItem=(CategoryItem)categoryItemList.get(i);
				categoryItem.setCategoryIdP(tempGradeCategory.getCategoryId());
				rows=rows+categoryItemMapper.insertCategoryItem(categoryItem);
			}
		}

		return rows;
	}

	/**
	 * 生成评级种类
	 * @param category
	 * @param checkItems
	 * @return
	 */
	private GradeCategory generateCategoryAndItem(String category,List<HashMap> checkItems){
		GradeCategory gradeCategory=new GradeCategory();
		processGradeCategory(gradeCategory,category);

		List<CategoryItem>  categoryItemList=new ArrayList<CategoryItem>();
		BigDecimal categoryValue=new BigDecimal("0");
		for (int i = 0; i < checkItems.size(); i++) {
			HashMap tempHashMap=checkItems.get(i);
			String tempCategory=(String)tempHashMap.get("categoryName");
			if(tempCategory.compareTo(category)==0){
				CategoryItem categoryItem=changeMaptoCategoryItem(tempHashMap);
				BigDecimal tempCategoryValue=categoryItem.getItemValue().multiply(categoryItem.getItemPercent()).
						divide(new BigDecimal("100"));
				categoryValue=categoryValue.add(tempCategoryValue);
				categoryItemList.add(categoryItem);
			}
		}
		if(categoryItemList.size()==0){
			return null;
		}

		gradeCategory.setCategoryValue(categoryValue);
		gradeCategory.setCategoryResult(changeValueToGrade(categoryValue));
		gradeCategory.setCategoryItemList(categoryItemList);

		return gradeCategory;
	}

	/**
	 * 将分数转换为等级
	 * @param categoryValue
	 * @return
	 */
	private String changeValueToGrade(BigDecimal categoryValue){
		if(categoryValue.compareTo(new BigDecimal("95"))==0||categoryValue.compareTo(new BigDecimal("95"))==1){
			return "A+";
		}
		if(categoryValue.compareTo(new BigDecimal("95"))==-1&&
				(categoryValue.compareTo(new BigDecimal("90"))==0||categoryValue.compareTo(new BigDecimal("90"))==1)){
			return "A";
		}
		if(categoryValue.compareTo(new BigDecimal("90"))==-1&&
				(categoryValue.compareTo(new BigDecimal("80"))==0||categoryValue.compareTo(new BigDecimal("80"))==1)){
			return "B+";
		}
		if(categoryValue.compareTo(new BigDecimal("80"))==-1&&
				(categoryValue.compareTo(new BigDecimal("70"))==0||categoryValue.compareTo(new BigDecimal("70"))==1)){
			return "B";
		}
		if(categoryValue.compareTo(new BigDecimal("70"))==-1&&
				(categoryValue.compareTo(new BigDecimal("60"))==0||categoryValue.compareTo(new BigDecimal("60"))==1)){
			return "C+";
		}
		return "C+";
	}

	/**
	 * 将Map转换为详细项目
	 * @param tempHashMap
	 * @return
	 */
	private CategoryItem changeMaptoCategoryItem(HashMap tempHashMap){
		CategoryItem categoryItem =new CategoryItem();

		categoryItem.setItemName((String) tempHashMap.get("itemName"));
		categoryItem.setCaption((String) tempHashMap.get("caption"));
		categoryItem.setItemValue(new BigDecimal((String) tempHashMap.get("itemValue")));
		categoryItem.setItemPercent(new BigDecimal((String) tempHashMap.get("itemPercent")));
		categoryItem.setSumUp((String) tempHashMap.get("sumUp"));

		return categoryItem;
	}

	/**
	 * 设置评级类别初始值
	 * @param gradeCategory
	 * @param category
	 */
	private void processGradeCategory(GradeCategory gradeCategory,String category){
		gradeCategory.setCategoryName(category);
		switch (category){
			case "0":
				gradeCategory.setCategoryPercent(new BigDecimal(15));
				break;

			case "1":
				gradeCategory.setCategoryPercent(new BigDecimal(15));
				break;
			case "2":
				gradeCategory.setCategoryPercent(new BigDecimal(15));
				break;
			case "3":
				gradeCategory.setCategoryPercent(new BigDecimal(15));
				break;
			case "4":
				gradeCategory.setCategoryPercent(new BigDecimal(15));
				break;
			case "5":
				gradeCategory.setCategoryPercent(new BigDecimal(15));
				break;
			case "6":
				gradeCategory.setCategoryPercent(new BigDecimal(10));
				break;
		}
	}

	/**
     * 修改基地评级
     *
     * @param farmGrade 基地评级信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateFarmGrade(FarmGrade farmGrade) throws Exception {
		deleteFarmGradeByIds(String.valueOf(farmGrade.getGradeId()));
		int rows=addFarmGrade(farmGrade);
	    return rows;
	}

	/**
     * 删除基地评级对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional
	public int deleteFarmGradeByIds(String ids) {
		FarmGrade farmGrade=farmGradeMapper.selectFarmGradeById(Long.valueOf(ids));

		GradeCategory gradeCategoryPara=new GradeCategory();
		gradeCategoryPara.setGradeIdP(farmGrade.getGradeId());
		List<GradeCategory>  gradeCategoryList=gradeCategoryMapper.selectGradeCategoryList(gradeCategoryPara);
		for (int i = 0; i < gradeCategoryList.size(); i++) {
			GradeCategory gradeCategory=gradeCategoryList.get(i);
			CategoryItem categoryItemPara=new CategoryItem();
			categoryItemPara.setCategoryIdP(gradeCategory.getCategoryId());
			List<CategoryItem> categoryItemList=categoryItemMapper.selectCategoryItemList(categoryItemPara);
			for (int j = 0; j < categoryItemList.size(); j++) {
				CategoryItem categoryItem=categoryItemList.get(j);
				categoryItemMapper.deleteCategoryItemById(categoryItem.getItemId());
			}
			gradeCategoryMapper.deleteGradeCategoryById(gradeCategory.getCategoryId());
		}

		return farmGradeMapper.deleteFarmGradeByIds(Convert.toStrArray(ids));
	}

	@Override
	public FarmGrade selectGradeCategoryByFarmId(String farmId) {
		FarmGrade farmGradePara=new FarmGrade();
		farmGradePara.setFarmIdP(Long.valueOf(farmId));
		List<FarmGrade> farmGradeList=farmGradeMapper.selectFarmGradeList(farmGradePara);
		FarmGrade farmGrade=farmGradeList.get(0);


		GradeCategory gradeCategoryPara=new GradeCategory();
		gradeCategoryPara.setGradeIdP(farmGrade.getGradeId());
		List<GradeCategory> gradeCategoryList=gradeCategoryMapper.selectGradeCategoryList(gradeCategoryPara);
		Map gradeCategoryMap=new HashMap();
		for (int i = 0; i <gradeCategoryList.size() ; i++) {
			GradeCategory tempGradeCategory=gradeCategoryList.get(i);
			gradeCategoryMap.put(tempGradeCategory.getCategoryName(),tempGradeCategory);
		}
		farmGrade.setGradeCategoryMap(gradeCategoryMap);
		return farmGrade;
	}

	@Override
	public GradeCategory selectCategoryItemByFarmId(String farmId,String categoryName) {
		FarmGrade farmGradePara=new FarmGrade();
		farmGradePara.setFarmIdP(Long.valueOf(farmId));
		List<FarmGrade> farmGradeList=farmGradeMapper.selectFarmGradeList(farmGradePara);
		FarmGrade farmGrade=farmGradeList.get(0);

		GradeCategory gradeCategoryPara=new GradeCategory();
		gradeCategoryPara.setGradeIdP(farmGrade.getGradeId());
		gradeCategoryPara.setCategoryName(categoryName);
		List<GradeCategory> gradeCategoryList=gradeCategoryMapper.selectGradeCategoryList(gradeCategoryPara);
		GradeCategory gradeCategory=gradeCategoryList.get(0);

		CategoryItem categoryItem=new CategoryItem();
		categoryItem.setCategoryIdP(gradeCategory.getCategoryId());
		List<CategoryItem> categoryItemList=categoryItemMapper.selectCategoryItemList(categoryItem);
		gradeCategory.setCategoryItemList(categoryItemList);

		return gradeCategory;
	}

}
