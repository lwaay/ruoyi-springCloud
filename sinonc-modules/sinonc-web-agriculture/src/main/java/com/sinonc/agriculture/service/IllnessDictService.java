package com.sinonc.agriculture.service;

import java.util.List;
import com.sinonc.agriculture.domain.IllnessDict;

/**
 * 疾病字典Service接口
 * 
 * @author huangao
 * @date 2020-04-18
 */
public interface IllnessDictService
{
    /**
     * 查询疾病字典
     * 
     * @param id 疾病字典ID
     * @return 疾病字典
     */
    public IllnessDict getIllnessDictById(Long id);

    /**
     * 查询疾病字典列表
     * 
     * @param illnessDict 疾病字典
     * @return 疾病字典集合
     */
    public List<IllnessDict> getIllnessDictList(IllnessDict illnessDict);

    /**
     * 新增疾病字典
     * 
     * @param illnessDict 疾病字典
     * @return 结果
     */
    public int addIllnessDict(IllnessDict illnessDict);

    /**
     * 修改疾病字典
     * 
     * @param illnessDict 疾病字典
     * @return 结果
     */
    public int updateIllnessDict(IllnessDict illnessDict);

    /**
     * 批量删除疾病字典
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteIllnessDictByIds(String ids);

    /**
     * 删除疾病字典信息
     * 
     * @param id 疾病字典ID
     * @return 结果
     */
    public int deleteIllnessDictById(Long id);
}
