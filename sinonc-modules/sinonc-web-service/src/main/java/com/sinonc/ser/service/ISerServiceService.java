package com.sinonc.ser.service;

import java.util.List;

import com.sinonc.service.domain.SerService;

/**
 * 社会化服务Service接口
 *
 * @author ruoyi
 * @date 2022-02-16
 */
public interface ISerServiceService {
    /**
     * 查询社会化服务
     *
     * @param serviceId 社会化服务ID
     * @return 社会化服务
     */
    public SerService selectSerServiceById(Long serviceId);

    /**
     * 查询社会化服务列表
     *
     * @param serService 社会化服务
     * @return 社会化服务集合
     */
    public List<SerService> selectSerServiceList(SerService serService);

    /**
     * 新增社会化服务
     *
     * @param serService 社会化服务
     * @return 结果
     */
    public int insertSerService(SerService serService);

    /**
     * 修改社会化服务
     *
     * @param serService 社会化服务
     * @return 结果
     */
    public int updateSerService(SerService serService);

    /**
     * 批量删除社会化服务
     *
     * @param serviceIds 需要删除的社会化服务ID
     * @return 结果
     */
    public int deleteSerServiceByIds(Long[] serviceIds);

    /**
     * 删除社会化服务信息
     *
     * @param serviceId 社会化服务ID
     * @return 结果
     */
    public int deleteSerServiceById(Long serviceId);
}
