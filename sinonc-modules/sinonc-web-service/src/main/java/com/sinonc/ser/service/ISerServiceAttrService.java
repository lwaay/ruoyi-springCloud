package com.sinonc.ser.service;

import java.util.List;

import com.sinonc.service.domain.SerServiceAttr;

/**
 * 社会化服务规格Service接口
 *
 * @author ruoyi
 * @date 2022-02-16
 */
public interface ISerServiceAttrService {
    /**
     * 查询社会化服务规格
     *
     * @param attrId 社会化服务规格ID
     * @return 社会化服务规格
     */
    public SerServiceAttr selectSerServiceAttrById(Long attrId);

    /**
     * 查询社会化服务规格列表
     *
     * @param serServiceAttr 社会化服务规格
     * @return 社会化服务规格集合
     */
    public List<SerServiceAttr> selectSerServiceAttrList(SerServiceAttr serServiceAttr);

    /**
     * 新增社会化服务规格
     *
     * @param serServiceAttr 社会化服务规格
     * @return 结果
     */
    public int insertSerServiceAttr(SerServiceAttr serServiceAttr);

    /**
     * 修改社会化服务规格
     *
     * @param serServiceAttr 社会化服务规格
     * @return 结果
     */
    public int updateSerServiceAttr(SerServiceAttr serServiceAttr);

    /**
     * 批量删除社会化服务规格
     *
     * @param attrIds 需要删除的社会化服务规格ID
     * @return 结果
     */
    public int deleteSerServiceAttrByIds(Long[] attrIds);

    /**
     * 删除社会化服务规格信息
     *
     * @param attrId 社会化服务规格ID
     * @return 结果
     */
    public int deleteSerServiceAttrById(Long attrId);

    /**
     * 删除社会化服务规格信息
     *
     * @param serviceId 社会化服务ID
     * @return 结果
     */
    public int deleteSerServiceAttrByServiceId(Long serviceId);

    /**
     * 根据服务id获取所有的服务规格
     */
    public List<SerServiceAttr> allServiceAttrByServiceId(Long serviceId);
}
