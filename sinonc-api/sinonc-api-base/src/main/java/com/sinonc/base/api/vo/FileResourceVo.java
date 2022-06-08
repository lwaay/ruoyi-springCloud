package com.sinonc.base.api.vo;

import com.sinonc.base.api.domain.FileResource;

/**
 * 文件管理对象 file_resource
 *
 * @author ruoyi
 * @date 2020-11-05
 */
public class FileResourceVo extends FileResource {
    private static final long serialVersionUID = 1L;


   private String appLabel;

   private String appKey;

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
