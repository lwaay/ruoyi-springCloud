package com.sinonc.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sinonc.base.domain.CheckItem;
import com.sinonc.base.domain.Checkup;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 土壤、果蔬检测 服务层
 *
 * @author sinonc
 * @date 2019-11-14
 */
public interface CheckupService {

    /**
     * 查询土壤、果蔬检测信息
     *
     * @param checkId 土壤、果蔬检测ID
     * @return 土壤、果蔬检测信息
     */
    public Checkup getCheckupById(Long checkId) throws JsonProcessingException;

    /**
     * 查询土壤、果蔬检测列表
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 土壤、果蔬检测集合
     */
    public List<Checkup> listCheckup(Checkup checkup);

    /**
     * 新增土壤、果蔬检测
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 结果
     */
    public int addCheckup(Checkup checkup) throws IOException;

    /**
     * 修改土壤、果蔬检测
     *
     * @param checkup 土壤、果蔬检测信息
     * @return 结果
     */
    public int updateCheckup(Checkup checkup) throws IOException;

    /**
     * 删除土壤、果蔬检测信息
     *
     * @return 结果
     */
    public int deleteCheckupById(Long id);

    /**
     * 删除土壤、果蔬检测信息
     *
     * @return 结果
     */
    public int deleteCheckupByIds(Long[] ids);

    /**
     * 根据检测单主键获取所有检测项ID集合
     *
     * @param checkId
     * @return
     */
    public String selectCheckItemIdsByCheckId(Long checkId);

    /**
     * 根据基地编号及检测标题查询检验结果
     *
     * @param checkup
     * @return
     */
    public List<CheckItem> queryCheckupAndItem(Checkup checkup, String checkTitle);

    public List<CheckItem> queryCheckupAndItemTwo(Checkup checkup, String checkTitle, Map rsMap);

    public String selectAreaCodeByShopId(String shopId);
}
