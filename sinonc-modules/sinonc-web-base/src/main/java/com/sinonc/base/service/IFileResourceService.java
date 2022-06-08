package com.sinonc.base.service;

import com.sinonc.base.api.domain.FileResource;

import java.util.List;

/**
 * 文件管理Service接口
 *
 * @author ruoyi
 * @date 2020-11-05
 */
public interface IFileResourceService {
    /**
     * 查询文件管理
     *
     * @param fileId 文件管理ID
     * @return 文件管理
     */
    public FileResource selectFileResourceById(Long fileId);

    /**
     * 查询文件管理列表
     *
     * @param fileResource 文件管理
     * @return 文件管理集合
     */
    public List<FileResource> selectFileResourceList(FileResource fileResource);

    /**
     * 新增文件管理
     *
     * @param fileResource 文件管理
     * @return 结果
     */
    public int insertFileResource(FileResource fileResource);

    /**
     * 新增文件管理
     *
     * @param fileResources 文件管理
     * @return 结果
     */
    public int insertFileResources(List<FileResource> fileResources);

    /**
     * 修改文件管理状态
     */
    public int updateDelFlag(String ossName);

    /**
     * 修改文件管理状态
     */
    public int batchUpdateDelFlag(List<String> ossNames);

    /**
     * 根据ossName文件管理信息
     * @Param ossName
     */
    public FileResource findByOssName(String ossName);

    /**
     * 修改文件管理
     *
     * @param fileResource 文件管理
     * @return 结果
     */
    public int updateFileResource(FileResource fileResource);

    /**
     * 批量删除文件管理
     *
     * @param fileIds 需要删除的文件管理ID
     * @return 结果
     */
    public int deleteFileResourceByIds(Long[] fileIds);

    /**
     * 删除文件管理信息
     *
     * @param fileId 文件管理ID
     * @return 结果
     */
    public int deleteFileResourceById(Long fileId);
}
