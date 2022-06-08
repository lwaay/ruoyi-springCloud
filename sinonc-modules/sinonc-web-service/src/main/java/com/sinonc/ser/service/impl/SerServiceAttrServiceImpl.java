package com.sinonc.ser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.ser.mapper.SerServiceAttrMapper;
import com.sinonc.service.domain.SerServiceAttr;
import com.sinonc.ser.service.ISerServiceAttrService;

/**
 * 社会化服务规格Service业务层处理
 *
 * @author ruoyi
 * @date 2022-02-16
 */
@Service
public class SerServiceAttrServiceImpl implements ISerServiceAttrService {
    @Autowired
    private SerServiceAttrMapper serServiceAttrMapper;

    /**
     * 查询社会化服务规格
     *
     * @param attrId 社会化服务规格ID
     * @return 社会化服务规格
     */
    @Override
    public SerServiceAttr selectSerServiceAttrById(Long attrId) {
        return serServiceAttrMapper.selectSerServiceAttrById(attrId);
    }

    /**
     * 查询社会化服务规格列表
     *
     * @param serServiceAttr 社会化服务规格
     * @return 社会化服务规格
     */
    @Override
    public List<SerServiceAttr> selectSerServiceAttrList(SerServiceAttr serServiceAttr) {
        return serServiceAttrMapper.selectSerServiceAttrList(serServiceAttr);
    }

    /**
     * 新增社会化服务规格
     *
     * @param serServiceAttr 社会化服务规格
     * @return 结果
     */
    @Override
    public int insertSerServiceAttr(SerServiceAttr serServiceAttr) {
        return serServiceAttrMapper.insertSerServiceAttr(serServiceAttr);
    }

    /**
     * 修改社会化服务规格
     *
     * @param serServiceAttr 社会化服务规格
     * @return 结果
     */
    @Override
    public int updateSerServiceAttr(SerServiceAttr serServiceAttr) {
        return serServiceAttrMapper.updateSerServiceAttr(serServiceAttr);
    }

    /**
     * 批量删除社会化服务规格
     *
     * @param attrIds 需要删除的社会化服务规格ID
     * @return 结果
     */
    @Override
    public int deleteSerServiceAttrByIds(Long[] attrIds) {
        return serServiceAttrMapper.deleteSerServiceAttrByIds(attrIds);
    }

    /**
     * 删除社会化服务规格信息
     *
     * @param attrId 社会化服务规格ID
     * @return 结果
     */
    @Override
    public int deleteSerServiceAttrById(Long attrId) {
        return serServiceAttrMapper.deleteSerServiceAttrById(attrId);
    }

    /**
     * 删除社会化服务规格信息
     *
     * @param attrId 社会化服务规格ID
     * @return 结果
     */
    @Override
    public int deleteSerServiceAttrByServiceId(Long serviceId) {
        return serServiceAttrMapper.deleteSerServiceAttrByServiceId(serviceId);
    }


    /**
     * 根据服务id获取所有的服务规格
     */
    @Override
    public List<SerServiceAttr> allServiceAttrByServiceId(Long serviceId){
        SerServiceAttr query = new SerServiceAttr();
        query.setServiceIdP(serviceId);
        return serServiceAttrMapper.selectSerServiceAttrList(query);
    }
}
