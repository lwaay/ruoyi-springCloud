package com.sinonc.origins.mapper;

import com.sinonc.origins.api.domain.PmBusiness;
import com.sinonc.origins.api.domain.ProProductInfo;

import java.util.List;

/**
 * 经营主体Mapper接口
 *
 * @author ruoyi
 * @date 2020-10-21
 */
public interface PmBusinessMapper {

    /**
     * 统计
     * @param baseArea
     * @return
     */
    public long selectAreaCount(String baseArea);

    /**
     * 查询经营主体
     *
     * @param busiId 经营主体ID
     * @return 经营主体
     */
    public PmBusiness selectPmBusinessById(Long busiId);


    /**
     * 根据部门id查询经营主体
     * @param deptId 经营主体ID
     * @return 经营主体
     */
    public PmBusiness selectPmBusinessByDeptId(Long deptId);

    /**
     * 查询经营主体列表
     *
     * @param pmBusiness 经营主体
     * @return 经营主体集合
     */
    public List<PmBusiness> selectPmBusinessList(PmBusiness pmBusiness);

    /**
     * 新增经营主体
     *
     * @param pmBusiness 经营主体
     * @return 结果
     */
    public int insertPmBusiness(PmBusiness pmBusiness);

    /**
     * 修改经营主体
     *
     * @param pmBusiness 经营主体
     * @return 结果
     */
    public int updatePmBusiness(PmBusiness pmBusiness);

    /**
     * 删除经营主体
     *
     * @param busiId 经营主体ID
     * @return 结果
     */
    public int deletePmBusinessById(Long busiId);

    /**
     * 批量删除经营主体
     *
     * @param busiIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePmBusinessByIds(Long[] busiIds);
}
