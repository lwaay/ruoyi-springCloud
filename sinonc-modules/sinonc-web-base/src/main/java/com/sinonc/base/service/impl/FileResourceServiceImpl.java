package com.sinonc.base.service.impl;

import com.sinonc.base.api.domain.FileResource;
import com.sinonc.base.mapper.FileResourceMapper;
import com.sinonc.base.service.IFileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 文件管理Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@Service
public class FileResourceServiceImpl implements IFileResourceService {
    @Autowired
    private FileResourceMapper fileResourceMapper;

    /**
     * 查询文件管理
     *
     * @param fileId 文件管理ID
     * @return 文件管理
     */
    @Override
    public FileResource selectFileResourceById(Long fileId) {
        return fileResourceMapper.selectFileResourceById(fileId);
    }

    /**
     * 查询文件管理列表
     *
     * @param fileResource 文件管理
     * @return 文件管理
     */
    @Override
    public List<FileResource> selectFileResourceList(FileResource fileResource) {
        return fileResourceMapper.selectFileResourceList(fileResource);
    }

    /**
     * 新增文件管理
     *
     * @param fileResource 文件管理
     * @return 结果
     */
    @Override
    public int insertFileResource(FileResource fileResource) {
        return fileResourceMapper.insertFileResource(fileResource);
    }

    /**
     * 新增文件管理
     *
     * @param fileResources 文件管理
     * @return 结果
     */
    @Override
    public int insertFileResources(List<FileResource> fileResources) {
        int result = 0;
        if (CollectionUtils.isEmpty(fileResources)){
            return result;
        }
        for (FileResource resource :fileResources){
            result += fileResourceMapper.insertFileResource(resource);
        }
        return result;
    }

    /**
     * 修改文件管理状态
     */
    public int updateDelFlag(String ossName){
        if (StringUtils.isEmpty(ossName)){
            return 0;
        }
        FileResource old = fileResourceMapper.findFileResourceByOssName(ossName);
        if (old == null || old.getFileId() == null || old.getFileId()<1){
            return 0;
        }
        old.setDelFlag(1);
        return fileResourceMapper.updateFileResource(old);
    }

    /**
     * 修改文件管理状态
     */
    @Override
    public int batchUpdateDelFlag(List<String> ossNames){
        int result = 0;
        if (CollectionUtils.isEmpty(ossNames)){
            return result;
        }

        for (String ossName : ossNames){
            result += this.updateDelFlag(ossName);
        }
        return result;
    }

    /**
     * 根据ossName文件管理信息
     * @Param ossName
     */
    @Override
    public FileResource findByOssName(String ossName){
        if (StringUtils.isEmpty(ossName)){
            return null;
        }
        return fileResourceMapper.findFileResourceByOssName(ossName);
    }

    /**
     * 修改文件管理
     *
     * @param fileResource 文件管理
     * @return 结果
     */
    @Override
    public int updateFileResource(FileResource fileResource) {
        return fileResourceMapper.updateFileResource(fileResource);
    }

    /**
     * 批量删除文件管理
     *
     * @param fileIds 需要删除的文件管理ID
     * @return 结果
     */
    @Override
    public int deleteFileResourceByIds(Long[] fileIds) {
        return fileResourceMapper.deleteFileResourceByIds(fileIds);
    }

    /**
     * 删除文件管理信息
     *
     * @param fileId 文件管理ID
     * @return 结果
     */
    @Override
    public int deleteFileResourceById(Long fileId) {
        return fileResourceMapper.deleteFileResourceById(fileId);
    }
}
