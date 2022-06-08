package com.sinonc.base.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sinonc.common.core.annotation.Excel;
import com.sinonc.common.core.web.domain.BaseEntity;

import java.util.Date;

/**
 * 文件管理对象 file_resource
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileResource extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 文件资源主键
     */
    private Long fileId;

    /**
     * 文件标签
     */
    private String fileName;

    /**
     * 文件oss地址
     */
    private String ossPath;

    /**
     * 文件oss名称
     */
    private String ossName;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date uploadDate;

    /**
     * 上传地址
     */
    private String uploadIp;

    /**
     * 上传客户端名称
     */
    private String uploadClient;


    /**构造方法**/
    public FileResource(String fileName, String ossPath, String ossName,
                        String fileSuffix, String fileSize, Date uploadDate,
                        String uploadIp, String uploadClient){
        this.fileName = fileName;
        this.ossPath = ossPath;
        this.ossName = ossName;
        this.fileSuffix = fileSuffix;
        this.fileSize = fileSize;
        this.uploadDate = uploadDate;
        this.uploadIp = uploadIp;
        this.uploadClient = uploadClient;
        this.delFlag = 0;
    }

    /**无参的构造方法**/
    public FileResource(){};
    /**
     * 是否删除
     */
    private Integer delFlag;

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath;
    }

    public String getOssPath() {
        return ossPath;
    }

    public void setOssName(String ossName) {
        this.ossName = ossName;
    }

    public String getOssName() {
        return ossName;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadIp(String uploadIp) {
        this.uploadIp = uploadIp;
    }

    public String getUploadIp() {
        return uploadIp;
    }

    public void setUploadClient(String uploadClient) {
        this.uploadClient = uploadClient;
    }

    public String getUploadClient() {
        return uploadClient;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

}
