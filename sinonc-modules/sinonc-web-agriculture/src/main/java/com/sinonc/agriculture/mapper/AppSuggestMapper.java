package com.sinonc.agriculture.mapper;

import java.util.List;
import com.sinonc.agriculture.domain.AppSuggest;

/**
 * 意见反馈Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-12
 */
public interface AppSuggestMapper 
{
    /**
     * 查询意见反馈
     * 
     * @param suggestId 意见反馈ID
     * @return 意见反馈
     */
    public AppSuggest selectAppSuggestById(Long suggestId);

    /**
     * 查询意见反馈列表
     * 
     * @param appSuggest 意见反馈
     * @return 意见反馈集合
     */
    public List<AppSuggest> selectAppSuggestList(AppSuggest appSuggest);

    /**
     * 新增意见反馈
     * 
     * @param appSuggest 意见反馈
     * @return 结果
     */
    public int insertAppSuggest(AppSuggest appSuggest);

    /**
     * 修改意见反馈
     * 
     * @param appSuggest 意见反馈
     * @return 结果
     */
    public int updateAppSuggest(AppSuggest appSuggest);

    /**
     * 删除意见反馈
     * 
     * @param suggestId 意见反馈ID
     * @return 结果
     */
    public int deleteAppSuggestById(Long suggestId);

    /**
     * 批量删除意见反馈
     * 
     * @param suggestIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAppSuggestByIds(String[] suggestIds);
}
