package com.sinonc.base.mapper;

import java.util.List;

import com.sinonc.base.domain.KInsect;

/**
 * 病虫害Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-19
 */
public interface KInsectMapper {
    /**
     * 查询病虫害
     *
     * @param id 病虫害ID
     * @return 病虫害
     */
    public KInsect selectKInsectById(Long id);

    /**
     * 查询病虫害列表
     *
     * @param kInsect 病虫害
     * @return 病虫害集合
     */
    public List<KInsect> selectKInsectList(KInsect kInsect);

    /**
     * 新增病虫害
     *
     * @param kInsect 病虫害
     * @return 结果
     */
    public int insertKInsect(KInsect kInsect);

    /**
     * 修改病虫害
     *
     * @param kInsect 病虫害
     * @return 结果
     */
    public int updateKInsect(KInsect kInsect);

    /**
     * 删除病虫害
     *
     * @param id 病虫害ID
     * @return 结果
     */
    public int deleteKInsectById(Long id);

    /**
     * 批量删除病虫害
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKInsectByIds(Long[] ids);
}
