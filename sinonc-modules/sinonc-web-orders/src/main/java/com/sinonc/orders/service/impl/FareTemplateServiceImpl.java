package com.sinonc.orders.service.impl;

import com.sinonc.common.core.text.Convert;
import com.sinonc.orders.domain.CarryMode;
import com.sinonc.orders.domain.FareTemplate;
import com.sinonc.orders.domain.Specs;
import com.sinonc.orders.dto.FareDto;
import com.sinonc.orders.mapper.CarryModeMapper;
import com.sinonc.orders.mapper.FareTemplateMapper;
import com.sinonc.orders.mapper.SpecsMapper;
import com.sinonc.orders.service.FareTemplateService;
import com.sinonc.orders.service.SpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 运费模板 服务层实现
 *
 * @author sinonc
 * @date 2019-11-27
 */
@Service
public class FareTemplateServiceImpl implements FareTemplateService {
	@Autowired
	private FareTemplateMapper fareTemplateMapper;
	@Autowired
	private CarryModeMapper carryModeMapper;
    @Autowired
    private SpecsService specsService;
	@Autowired
	private SpecsMapper specsMapper;

	/**
     * 查询运费模板信息
     *
     * @param fareId 运费模板ID
     * @return 运费模板信息
     */
    @Override
	public FareTemplate getFareTemplateById(Long fareId) {
	    return fareTemplateMapper.selectFareTemplateById(fareId);
	}

	@Override
	public FareTemplate getFareTemplateByShopId(Long shopId) {
		return fareTemplateMapper.selectFareTemplateByShopId(shopId);
	}

	/**
     * 查询运费模板列表
     *
     * @param fareTemplate 运费模板信息
     * @return 运费模板集合
     */
	@Override
	public List<FareTemplate> listFareTemplate(FareTemplate fareTemplate) {
	    return fareTemplateMapper.selectFareTemplateList(fareTemplate);
	}

    /**
     * 新增运费模板
     *
     * @param fareDto 运费模板信息
     * @return 结果
     */
	@Override
	@Transactional
	public int addFareTemplate(FareDto fareDto) {
		FareTemplate fareTemplate = fareDto.getFareTemplate();
		fareTemplate.setCreateTime(new Date());

		//Integer valuaTionmodel=2;//计价方式固定为计重
		//fareTemplate.setValuaTionmodel(2);
		//新增运费模板
		int fareRow = fareTemplateMapper.insertFareTemplate(fareTemplate);
		//更新规格模板ID
        updateSpecs(fareDto,fareTemplate.getFareId());

		int carryModeRow = 0;
		int carryModelistRow = 0;
		Integer valuaTionmodel = fareTemplate.getValuaTionmodel(); //计价方式（重量：2 /件：1）

		//获取默认运费方式
		CarryMode carryModedto = fareDto.getCarryModeldefault();
		//获取自定义运费方式
		List<CarryMode> list =  fareDto.getCarryModeList();
		if (list!=null){
			CarryMode carryMode = new CarryMode();
			carryMode.setFareId(fareTemplate.getFareId());
			carryMode.setRegionAddr("默认地区运费");
			if(valuaTionmodel == 1){ //计件
				carryMode.setFirstPiece(Integer.parseInt(carryModedto.getFirstWeight().toString())); //首件
				carryMode.setSecondPiece(Integer.parseInt(carryModedto.getSecondWeight().toString())); //续件
			}else if(valuaTionmodel == 2){ //计重
				carryMode.setFirstWeight(carryModedto.getFirstWeight()); //首重
				carryMode.setSecondWeight(carryModedto.getSecondWeight()); //续重
			}
			carryMode.setFirstAmount(carryModedto.getFirstAmount()); //首费
			carryMode.setSecondAmount(carryModedto.getSecondAmount()); //续费
			carryMode.setCarryWay(carryModedto.getCarryWay());
			carryMode.setIsDefault(1); //默认运送方式1 / 2自定义
			//新增默认运费方式
			carryModeRow = carryModeMapper.insertCarryMode(carryMode);
			for (CarryMode carryModedefaul:list) { //循环添加自定义运费方式
				batchInsertCarryModeByRegionAddr(carryModedefaul,fareTemplate,valuaTionmodel);
			}
		}
		return fareRow;
	}

	private void updateSpecs(FareDto fareDto,Long fareId){
		specsMapper.updateSpecsClearByFareId(fareId);
		String specsIds=fareDto.getFareTemplate().getSpecsId();

		if(specsIds!=null){
			String[] specsIdsArr=specsIds.split(",");

			for (int i = 0; i < specsIdsArr.length; i++) {
				Specs specs=specsService.getSpecsById(Long.valueOf(specsIdsArr[i]));
				specs.setFareIdP(fareId);
				specsService.updateSpecs(specs);
			}
		}
    }

	private void batchInsertCarryModeByRegionAddr(CarryMode carryModedefaul,FareTemplate fareTemplate,Integer valuaTionmodel){
		String regionAddr=carryModedefaul.getRegionAddr();
		String [] regionAddrArr=regionAddr.split(",");
		for (int i = 0; i < regionAddrArr.length; i++) {
			CarryMode carryModeown = new CarryMode();
			carryModeown.setFareId(fareTemplate.getFareId());
			carryModeown.setRegionAddr(regionAddrArr[i]);
			if(valuaTionmodel == 1){
				carryModeown.setFirstPiece(Integer.parseInt(carryModedefaul.getFirstWeight().toString())); //首件
				carryModeown.setSecondPiece(Integer.parseInt(carryModedefaul.getSecondWeight().toString())); //续件
			}else if(valuaTionmodel == 2){
				carryModeown.setFirstWeight(carryModedefaul.getFirstWeight()); //首重
				carryModeown.setSecondWeight(carryModedefaul.getSecondWeight()); //续重
			}
			carryModeown.setFirstAmount(carryModedefaul.getFirstAmount()); //首费
			carryModeown.setSecondAmount(carryModedefaul.getSecondAmount()); //续费
			carryModeown.setCarryWay(carryModedefaul.getCarryWay());
			carryModeown.setIsDefault(2); //默认运送方式1 / 2自定义
			//新增自定义运费方式
			carryModeMapper.insertCarryMode(carryModeown);
		}

	}

	/**
     * 修改运费模板
     *
     * @param fareDto 运费模板信息
     * @return 结果
     */
	@Override
	@Transactional
	public int updateFareTemplate(FareDto fareDto) {
		FareTemplate fareTemplate = fareDto.getFareTemplate();
		fareTemplate.setUpdateTime(new Date());
		//Integer valuaTionmodel=2;//计价方式固定为计重
		//fareTemplate.setValuaTionmodel(2);

		//修改运费模板
		int fareRow = fareTemplateMapper.updateFareTemplate(fareTemplate);
		//更新规格模板ID
		updateSpecs(fareDto,fareTemplate.getFareId());


		int carryModeRow = 0;
		int carryModelistRow = 0;
		//删除该模板下的运费方式，重新添加
		carryModeMapper.deleteCarryModeByFareId(fareTemplate.getFareId());
		Integer valuaTionmodel = fareTemplate.getValuaTionmodel(); //计价方式（重量：2 /件：1）
		//获取默认运费方式
		CarryMode carryModedto = fareDto.getCarryModeldefault();
		//获取自定义运费方式
		List<CarryMode> list =  fareDto.getCarryModeList();
		CarryMode carryMode = new CarryMode();
		carryMode.setFareId(fareTemplate.getFareId());
		carryMode.setRegionAddr("默认地区运费");
		if(valuaTionmodel == 1){ //计件
			carryMode.setFirstPiece(Integer.parseInt(carryModedto.getFirstWeight().toString())); //首件
			carryMode.setSecondPiece(Integer.parseInt(carryModedto.getSecondWeight().toString())); //续件
		}else if(valuaTionmodel == 2){ //计重
			carryMode.setFirstWeight(carryModedto.getFirstWeight()); //首重
			carryMode.setSecondWeight(carryModedto.getSecondWeight()); //续重
		}
		carryMode.setFirstAmount(carryModedto.getFirstAmount()); //首费
		carryMode.setSecondAmount(carryModedto.getSecondAmount()); //续费
		carryMode.setCarryWay(carryModedto.getCarryWay());
		carryMode.setIsDefault(1); //默认运送方式1 / 2自定义
		//新增默认运费方式
		carryModeRow = carryModeMapper.insertCarryMode(carryMode);
		for (CarryMode carryModedefaul:list) { //循环添加自定义运费方式
			CarryMode carryModeown = new CarryMode();
			carryModeown.setFareId(fareTemplate.getFareId());
			carryModeown.setRegionAddr(carryModedefaul.getRegionAddr());
			if(valuaTionmodel == 1){
				carryModeown.setFirstPiece(Integer.parseInt(carryModedefaul.getFirstWeight().toString())); //首件
				carryModeown.setSecondPiece(Integer.parseInt(carryModedefaul.getSecondWeight().toString())); //续件
			}else if(valuaTionmodel == 2){
				carryModeown.setFirstWeight(carryModedefaul.getFirstWeight()); //首重
				carryModeown.setSecondWeight(carryModedefaul.getSecondWeight()); //续重
			}
			carryModeown.setFirstAmount(carryModedefaul.getFirstAmount()); //首费
			carryModeown.setSecondAmount(carryModedefaul.getSecondAmount()); //续费
			carryModeown.setCarryWay(carryModedefaul.getCarryWay());
			carryModeown.setIsDefault(2); //默认运送方式1 / 2自定义
			//新增自定义运费方式
			carryModelistRow = carryModeMapper.insertCarryMode(carryModeown);
		}
		return fareRow * carryModeRow * carryModelistRow;
	}

	/**
     * 删除运费模板对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFareTemplateByIds(String ids) {
		specsMapper.updateSpecsClearByFareId(Long.valueOf(ids));
		carryModeMapper.deleteCarryModeByFareId(Long.valueOf(ids));
		return fareTemplateMapper.deleteFareTemplateByIds(Convert.toStrArray(ids));
	}

}
