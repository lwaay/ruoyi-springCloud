package com.sinonc.base.service.impl;

import com.sinonc.base.api.domain.AreaCode;
import com.sinonc.base.mapper.AreaCodeMapper;
import com.sinonc.base.service.IAreaCodeService;
import com.sinonc.base.vo.AreaCodeTreeSelect;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.system.api.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 行政区域Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-23
 */
@Service
public class AreaCodeServiceImpl implements IAreaCodeService {
    @Autowired
    private AreaCodeMapper areaCodeMapper;

    /**
     * 查询行政区域
     *
     * @param code 行政区域ID
     * @return 行政区域
     */
    @Override
    public AreaCode selectAreaCodeById(Long code) {
        return areaCodeMapper.selectAreaCodeById(code);
    }

    /**
     * 查询行政区域列表
     *
     * @param areaCode 行政区域
     * @return 行政区域
     */
    @Override
    public List<AreaCode> selectAreaCodeList(AreaCode areaCode) {
        return areaCodeMapper.selectAreaCodeList(areaCode);
    }

    /**
     * 新增行政区域
     *
     * @param areaCode 行政区域
     * @return 结果
     */
    @Override
    public int insertAreaCode(AreaCode areaCode) {
        return areaCodeMapper.insertAreaCode(areaCode);
    }

    /**
     * 修改行政区域
     *
     * @param areaCode 行政区域
     * @return 结果
     */
    @Override
    public int updateAreaCode(AreaCode areaCode) {
        return areaCodeMapper.updateAreaCode(areaCode);
    }

    /**
     * 批量删除行政区域
     *
     * @param codes 需要删除的行政区域ID
     * @return 结果
     */
    @Override
    public int deleteAreaCodeByIds(Long[] codes) {
        return areaCodeMapper.deleteAreaCodeByIds(codes);
    }

    /**
     * 删除行政区域信息
     *
     * @param code 行政区域ID
     * @return 结果
     */
    @Override
    public int deleteAreaCodeById(Long code) {
        return areaCodeMapper.deleteAreaCodeById(code);
    }

    /**
     * 根据父行政区域代码，获取子行政区域list
     * @param parentCode
     * @return
     */
    @Override
    public List<AreaCode> listAreaCodeByParentCode(Long parentCode) {
        if (parentCode == null) {
            return null;
        }
        return areaCodeMapper.selectAreaCodeList(new AreaCode(parentCode));
    }

    @Override
    public List<AreaCodeTreeSelect> buildDeptTreeSelect(List<AreaCode> areaCodeList) {
        List<AreaCode> areaCodeTrees = buildAreaCodeTree(areaCodeList);
        return areaCodeTrees.stream().map(AreaCodeTreeSelect::new).collect(Collectors.toList());
    }

    @Override
    public List<AreaCode> selectAreaCodeListLike(AreaCode areaCode) {
//        areaCode.setName("50");
        return areaCodeMapper.selectAreaCodeListLike(areaCode);
    }

    @Override
    public String changeAddressName(String addressCode) {
        try {
            if (StringUtils.isNotEmpty(addressCode)) {
                //省
                String provinceShipAddress = addressCode.substring(0, 2) + "0000000000";
                AreaCode provinceAreaCode = areaCodeMapper.selectAreaCodeById(Long.valueOf(provinceShipAddress));

                //地级市
                String cityShipAddress = addressCode.substring(0, 4) + "00000000";
                AreaCode cityAreaCode = areaCodeMapper.selectAreaCodeById(Long.valueOf(cityShipAddress));

                //区县
                String hsienShipAddress = addressCode.substring(0, 6) + "000000";
                AreaCode hsienAreaCode = areaCodeMapper.selectAreaCodeById(Long.valueOf(hsienShipAddress));
                //返回省及区县拼接
                return provinceAreaCode.getName() + " " + cityAreaCode.getName() + " " + hsienAreaCode.getName();
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public List<AreaCode> buildAreaCodeTree(List<AreaCode> areaCodeList) {
        List<AreaCode> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<Long>();
        for (AreaCode areaCode : areaCodeList) {
            tempList.add(areaCode.getCode());
        }
        for (Iterator<AreaCode> iterator = areaCodeList.iterator(); iterator.hasNext(); ) {
            AreaCode areaCode = (AreaCode) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(areaCode.getPcode())) {
                recursionFn(areaCodeList, areaCode);
                returnList.add(areaCode);
            }
        }
        if (returnList.isEmpty()) {
            returnList = areaCodeList;
        }
        return returnList;
    }


    /**
     * 递归列表
     */
    private void recursionFn(List<AreaCode> list, AreaCode t) {
        // 得到子节点列表
        List<AreaCode> childList = getChildList(list, t);
        t.setChildren(childList);
        for (AreaCode tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<AreaCode> getChildList(List<AreaCode> list, AreaCode t) {
        List<AreaCode> tlist = new ArrayList<>();
        Iterator<AreaCode> it = list.iterator();
        while (it.hasNext()) {
            AreaCode n = it.next();
            if (StringUtils.isNotNull(n.getPcode()) && n.getPcode().longValue() == t.getCode().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<AreaCode> list, AreaCode t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }


    @Override
    public List<AreaCode> parentBaseArea(Integer level,String code){
        AreaCode query = new AreaCode();
        query.setLevel(level);
        if (StringUtils.isEmpty(code)){
            return areaCodeMapper.selectAreaCodeList(query);
        }
        Long codel = Long.parseLong(code);
        AreaCode child = areaCodeMapper.selectAreaCodeById(codel);
        if (child.getLevel().equals(level-1)){
            query.setPcode(child.getCode());
            return areaCodeMapper.selectAreaCodeList(query);
        }
        if (child.getLevel()<level-1){
            return null;
        }
        String codeStr ="";
        switch (level){
            case 1:codeStr = StringUtils.left(child.getCode()+"",2)+"%";break;
            case 2:codeStr = StringUtils.left(child.getCode()+"",4)+"%";break;
            case 3:codeStr = StringUtils.left(child.getCode()+"",6)+"%";break;
            case 4:codeStr = StringUtils.left(child.getCode()+"",9)+"%";break;
            case 5:codeStr = StringUtils.left(child.getCode()+"",12)+"%";break;
        }
        AreaCode checked = areaCodeMapper.selectParentByCodeLevel(codeStr,level);
        query.setPcode(checked.getPcode());
        List<AreaCode> list = areaCodeMapper.selectAreaCodeList(query);
        if (CollectionUtils.isEmpty(list)){
            return list;
        }
        list.forEach(item->{
            if (item.getCode().equals(checked.getCode())){
                item.setChecked(true);
            }
        });
        return list;
    }
}
