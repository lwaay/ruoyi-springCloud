package com.sinonc.agriculture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinonc.agriculture.mapper.IllnessDictMapper;
import com.sinonc.agriculture.domain.IllnessDict;
import com.sinonc.agriculture.service.IllnessDictService;
import com.sinonc.common.core.text.Convert;

/**
 * 疾病字典Service业务层处理
 *
 * @author huangao
 * @date 2020-04-18
 */
@Service
public class IllnessDictServiceImpl implements IllnessDictService
{
    @Autowired
    private IllnessDictMapper illnessDictMapper;

    /**
     * 查询疾病字典
     *
     * @param id 疾病字典ID
     * @return 疾病字典
     */
    @Override
    public IllnessDict getIllnessDictById(Long id)
    {
        return illnessDictMapper.selectIllnessDictById(id);
    }

    /**
     * 查询疾病字典列表
     *
     * @param illnessDict 疾病字典
     * @return 疾病字典
     */
    @Override
    public List<IllnessDict> getIllnessDictList(IllnessDict illnessDict)
    {
        return illnessDictMapper.selectIllnessDictList(illnessDict);
    }

    /**
     * 新增疾病字典
     *
     * @param illnessDict 疾病字典
     * @return 结果
     */
    @Override
    public int addIllnessDict(IllnessDict illnessDict)
    {
        return illnessDictMapper.insertIllnessDict(illnessDict);
    }

    /**
     * 修改疾病字典
     *
     * @param illnessDict 疾病字典
     * @return 结果
     */
    @Override
    public int updateIllnessDict(IllnessDict illnessDict)
    {
        return illnessDictMapper.updateIllnessDict(illnessDict);
    }

    /**
     * 删除疾病字典对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIllnessDictByIds(String ids)
    {
        return illnessDictMapper.deleteIllnessDictByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除疾病字典信息
     *
     * @param id 疾病字典ID
     * @return 结果
     */
    @Override
    public int deleteIllnessDictById(Long id)
    {
        return illnessDictMapper.deleteIllnessDictById(id);
    }
}
