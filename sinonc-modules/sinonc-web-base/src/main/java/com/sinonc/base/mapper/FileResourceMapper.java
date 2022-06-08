package com.sinonc.base.mapper;

import com.sinonc.base.api.domain.FileResource;

import java.util.List;
/**
 * 文件管理Mapper接口
 *
 * @author ruoyi
 * @date 2020-11-05
 */
public interface FileResourceMapper {
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
     * 修改文件管理
     *
     * @param fileResource 文件管理
     * @return 结果
     */
    public int updateFileResource(FileResource fileResource);

    /**
     * 删除文件管理
     *
     * @param fileId 文件管理ID
     * @return 结果
     */
    public int deleteFileResourceById(Long fileId);

    /**
     * 批量删除文件管理
     *
     * @param fileIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileResourceByIds(Long[] fileIds);

    /**
     * 根据ossName获取文件管理信息
     */
    public FileResource findFileResourceByOssName(String ossName);
}
