package com.sinonc.base.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinonc.base.domain.CheckItem;
import com.sinonc.base.domain.Checkup;
import com.sinonc.base.mapper.CheckItemMapper;
import com.sinonc.base.mapper.CheckupMapper;
import com.sinonc.base.service.CheckupService;
import com.sinonc.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 土壤、果蔬检测 服务层实现
 *
 * @author sinonc
 * @date 2019-11-14
 */
@Service
public class CheckupServiceImpl implements CheckupService {
	@Autowired
	private CheckupMapper checkupMapper;

	@Autowired
	private CheckItemMapper checkItemMapper;

	/**
     * 查询土壤、果蔬检测信息
     *
     * @param checkId 土壤、果蔬检测ID
     * @return 土壤、果蔬检测信息
     */
    @Override
	public Checkup getCheckupById(Long checkId) throws JsonProcessingException {
		CheckItem CheckItem=new CheckItem();
		CheckItem.setCheckIdP(checkId);
		List checkItemList= checkItemMapper.selectCheckItemList(CheckItem);

		String checkItemIds=this.selectCheckItemIdsByCheckId(checkId);

		ObjectMapper mapper = new ObjectMapper();
		String checkItemListJson = mapper.writeValueAsString(checkItemList);
		Checkup checkup= checkupMapper.selectCheckupById(checkId);
		checkup.setCheckItemJson(checkItemListJson);
		checkup.setCheckItemIds(checkItemIds);



		return checkup;
	}

	/**
     * 查询土壤、果蔬检测列表
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 土壤、果蔬检测集合
     */
	@Override
	public List<Checkup> listCheckup(Checkup checkup) {
	    return checkupMapper.selectCheckupList(checkup);
	}

    /**
     * 新增土壤、果蔬检测
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 结果
     */
	@Override
	@Transactional
	public int addCheckup(Checkup checkup) throws IOException {
		//检测单
		Date date = new Date();
		String loginName = SecurityUtils.getUsername();
		checkup.setCreateTime(date);
		checkup.setCreateBy(loginName);

		int rows=checkupMapper.insertCheckup(checkup);

		//检测项目
		String itemJson=checkup.getCheckItemJson();
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap> checkItems = mapper.readValue(itemJson,new TypeReference<List<HashMap>>() { });
		for (int i = 0; i < checkItems.size(); i++) {
			HashMap itemMap=checkItems.get(i);
			CheckItem tempCheckItem=changMapToCheckItem(itemMap);

			tempCheckItem.setCheckIdP(checkup.getCheckId());
			tempCheckItem.setCreateTime(date);
			tempCheckItem.setCreateBy(loginName);

			rows=rows+checkItemMapper.insertCheckItem(tempCheckItem);
		}

		return rows;
	}

	private CheckItem changMapToCheckItem(Map itemMap){
		CheckItem tempCheckItem=new CheckItem();
		tempCheckItem.setCheckItem((String)itemMap.get("checkItem"));
		tempCheckItem.setValueType((String)itemMap.get("valueType"));
		tempCheckItem.setCheckValue(Double.parseDouble(String.valueOf(itemMap.get("checkValue"))));
		tempCheckItem.setCheckUnit((String)itemMap.get("checkUnit"));
		tempCheckItem.setComment((String)itemMap.get("comment"));

		return tempCheckItem;
	}

	/**
     * 修改土壤、果蔬检测
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateCheckup(Checkup checkup) throws IOException {

		Date date = new Date();
		String loginName = SecurityUtils.getUsername();

		//更新检测单
		checkup.setUpdateTime(date);
		checkup.setUpdateBy(loginName);
		int rows=checkupMapper.updateCheckup(checkup);


		//更新检测项目
		String checkItemJson=checkup.getCheckItemJson();
		ObjectMapper mapper = new ObjectMapper();

		String haveCheckItemIds="";
		List<HashMap> checkItemList = mapper.readValue(checkItemJson,new TypeReference<List<HashMap>>() { });
		for (int i = 0; i <checkItemList.size() ; i++) {
			HashMap checkItemMap=checkItemList.get(i);
			CheckItem checkItem=changMapToCheckItem(checkItemMap);
			checkItem.setCheckIdP(checkup.getCheckId());
			if(checkItemMap.get("checkItemId")!=null&&checkItemMap.get("checkItemId")!=""){
				//更新
				checkItem.setUpdateTime(date);
				checkItem.setUpdateBy(loginName);
				String checkItemId=String.valueOf(checkItemMap.get("checkItemId"));
				checkItem.setCheckItemId(Long.valueOf(checkItemId));
				haveCheckItemIds=haveCheckItemIds+","+checkItemId;
				checkItemMapper.updateCheckItem(checkItem);
			}else {
				//添加
				checkItem.setCreateTime(date);
				checkItem.setCreateBy(loginName);
				checkItemMapper.insertCheckItem(checkItem);
			}
		}

		String checkItemIds=checkup.getCheckItemIds();

		String[] checkItemIdArr=checkItemIds.split(",");

		for (int i = 0; i < checkItemIdArr.length; i++) {
			String tempCheckItemId=checkItemIdArr[i];
			if(haveCheckItemIds.indexOf(tempCheckItemId)==-1){
				rows=rows+checkItemMapper.deleteCheckItemById(Long.parseLong(tempCheckItemId));
			}
		}

		return rows;
	}

	/**
     * 删除土壤、果蔬检测对象
     *
     * @return 结果
     */
	@Override
	@Transactional
	public int deleteCheckupById(Long id) {
		checkItemMapper.deleteCheckItemByCheckIdP(id);
		return checkupMapper.deleteCheckupById(id);
	}

	/**
	 * 删除土壤、果蔬检测信息
	 *
	 * @return 结果
	 */
	@Transactional
	@Override
	public int deleteCheckupByIds(Long[] ids){
		for (Long id : ids) {
			checkItemMapper.deleteCheckItemByCheckIdP(id);
		}
		return checkupMapper.deleteCheckupByIds(ids);
	}

	@Override
	public String selectCheckItemIdsByCheckId(Long checkId) {
		CheckItem CheckItem=new CheckItem();
		CheckItem.setCheckIdP(checkId);
		List<CheckItem> checkItemList= checkItemMapper.selectCheckItemList(CheckItem);
		String checkItemIds="";
		for (int i = 0; i < checkItemList.size(); i++) {
			CheckItem tempCheckItem=checkItemList.get(i);
			if(checkItemIds.compareTo("")==0){
				checkItemIds=String.valueOf(tempCheckItem.getCheckItemId());
			}else {
				checkItemIds=checkItemIds+","+String.valueOf(tempCheckItem.getCheckItemId());
			}
		}
		return checkItemIds;
	}

	@Override
	public List<CheckItem> queryCheckupAndItem(Checkup checkup,String checkTitle) {
		checkup.setCheckType(checkTitle);//检测类型
		List<Checkup> checkupList=checkupMapper.selectCheckupList(checkup);
		Checkup tempCheckup=checkupList.get(0);//取第一个

		CheckItem checkItem=new CheckItem();
		checkItem.setCheckIdP(tempCheckup.getCheckId());
		List<CheckItem> tempCheckItemList=checkItemMapper.selectCheckItemList(checkItem);

		return tempCheckItemList;
	}


    @Override
    public List<CheckItem> queryCheckupAndItemTwo(Checkup checkup,String checkTitle,Map rsMap) {
        String latelyYear=checkupMapper.selectLatelyYear(checkup.getFarmId());
        checkup.setLatelyYear(latelyYear);
		checkup.setCheckType(checkTitle);
		Checkup rsCheckup=checkupMapper.selectCheckupListByYear(checkup);
		rsMap.put("sumUp",rsCheckup.getSumUp());

        List<CheckItem> tempCheckItemList=checkItemMapper.selectCheckItemListByCheckId(String.valueOf(rsCheckup.getCheckId()));

        return tempCheckItemList;
    }

    @Override
    public String selectAreaCodeByShopId(String shopId) {
        return checkupMapper.selectAreaCodeByShopId(shopId);
    }

}
