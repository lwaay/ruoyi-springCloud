package com.sinonc.base.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyun.oss.model.OSSObject;
import com.sinonc.base.api.domain.FileResource;
import com.sinonc.base.api.vo.FileResourceVo;
import com.sinonc.base.service.IFileResourceService;
import com.sinonc.common.core.domain.R;
import com.sinonc.common.core.utils.IpUtils;
import com.sinonc.common.core.utils.poi.ExcelUtil;
import com.sinonc.common.core.web.controller.BaseController;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.core.web.page.TableDataInfo;
import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.oss.service.UploadUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sinonc.common.log.annotation.Log;
import com.sinonc.common.log.enums.BusinessType;
import com.sinonc.common.security.annotation.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 文件管理Controller
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@RestController
@RequestMapping("/resource")
public class FileResourceController extends BaseController {
    @Autowired
    private IFileResourceService fileResourceService;

    @Autowired
    private UploadUtil uploadService;

    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public AjaxResult upload( @RequestParam("fileLabel") String fileLabel,@RequestParam("files") MultipartFile[] files, @RequestParam("uploadClient") String uploadClient) {
        if (files == null || files.length < 1) {
            return AjaxResult.error("上传失败,未获取到上传文件");
        }
        if (StringUtils.isEmpty(uploadClient)) {
            return AjaxResult.error("上传失败,请发送上传文件客户端名称");
        }
        List<FileResource> list = new ArrayList<>();
        for (MultipartFile file : files){
            if (file.isEmpty() || !uploadService.checkFileSize(file)){
                continue;
            }
            String ossPath = uploadService.upload(file);
            if (StringUtils.isEmpty(ossPath)) {
                continue;
            }
            String ip = IpUtils.getHostIp();
            String fileName = file.getOriginalFilename();
            String fileSize = uploadService.formatFileSize(file.getSize());
            String fileSuffix = StringUtils.isEmpty(fileName) ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
            String ossName = ossPath.substring(ossPath.lastIndexOf("/") + 1);
            list.add(new FileResource(fileLabel, ossPath, ossName, fileSuffix, fileSize, new Date(),
                    ip, uploadClient));
        }
        int result = fileResourceService.insertFileResources(list);
        String fileResult;
        if (result < 1){
            fileResult = "文件管理添加失败";
        }else {
            fileResult = "文件管理添加成功,共添加: " +result+" 条文件";
        }
        return AjaxResult.success("上传成功,共上传: " + list.size() + " 个文件," + fileResult, list);
    }

    /**
     * 查询文件管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(FileResource fileResource) {
        startPage();
        List<FileResource> list = fileResourceService.selectFileResourceList(fileResource);
        return getDataTable(list);
    }

    /**
     * 查询文件管理列表
     */
    @PostMapping("/pageFileResource")
    public TableDataInfo list(@RequestBody FileResourceVo fileResourceVo) {
        FileResource query = new FileResource();
        query.setUploadClient(fileResourceVo.getUploadClient());
        startPage();
        List<FileResource> list = fileResourceService.selectFileResourceList(query);
        return getDataTable(list);
    }

    /**
     * 导出文件管理列表
     */
    @PreAuthorize(hasPermi = "base:resource:export")
    @Log(title = "文件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FileResource fileResource) throws IOException {
        List<FileResource> list = fileResourceService.selectFileResourceList(fileResource);
        ExcelUtil<FileResource> util = new ExcelUtil<FileResource>(FileResource.class);
        util.exportExcel(response, list, "resource");
    }

    /**
     * 获取文件管理详细信息
     */
    @PreAuthorize(hasPermi = "base:resource:query")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId) {
        return AjaxResult.success(fileResourceService.selectFileResourceById(fileId));
    }

//    /**
//     * 新增文件管理
//     */
//    @Log(title = "文件管理", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody FileResource fileResource) {
//        return toAjax(fileResourceService.insertFileResource(fileResource));
//    }

    /**
     * 新增文件管理
     */
    @Log(title = "文件管理", businessType = BusinessType.INSERT)
    @PostMapping("/addResources")
    public AjaxResult addResources(@RequestBody List<FileResource> fileResources) {
        int result = fileResourceService.insertFileResources(fileResources);
        if (result < 1){
            return AjaxResult.error("文件管理添加失败");
        }
        return AjaxResult.success("文件管理添加成功,共添加: " +result+" 条文件");
    }

    /**
     * 修改文件管理删除状态
     */
    @PostMapping("/updateDelFlag")
    public AjaxResult updateDelFlag(@RequestBody String ossName){
        return toAjax(fileResourceService.updateDelFlag(ossName));
    }

    /**
     * 批量修改文件管理删除状态
     */
    @PostMapping("/batchUpdateDelFlag")
    public AjaxResult batchUpdateDelFlag(@RequestBody List<String> ossNames){
        return toAjax(fileResourceService.batchUpdateDelFlag(ossNames));
    }

    /**
     * 根据oss文件名称获取文件管理数据
     */
    @PostMapping("/findByOssName")
    public AjaxResult findByOssName(@RequestBody String ossName){
        FileResource resource = fileResourceService.findByOssName(ossName);
        if (resource == null){
            return AjaxResult.error();
        }
        return AjaxResult.success(resource);
    }

    /**
     * 修改文件管理
     */
    @PreAuthorize(hasPermi = "base:resource:edit")
    @Log(title = "文件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FileResource fileResource) {
        return toAjax(fileResourceService.updateFileResource(fileResource));
    }

    /**
     * 删除文件管理
     */
    @PreAuthorize(hasPermi = "base:resource:remove")
    @Log(title = "文件管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds) {
        return toAjax(fileResourceService.deleteFileResourceByIds(fileIds));
    }

    /**
     * 文件下载
     */
    @PreAuthorize(hasPermi = "base:resource:edit")
    @Log(title = "文件下载", businessType = BusinessType.OTHER)
    @PostMapping("/download")
    public void download(HttpServletResponse response, FileResource fileResource) {
        if (fileResource == null || fileResource.getFileId()<1L){
            return;
        }
        Long fileId =fileResource.getFileId();
        if (fileId<1L){
            return;
        }
        FileResource resource = fileResourceService.selectFileResourceById(fileId);
        if (resource == null || StringUtils.isEmpty(resource.getOssPath()) || StringUtils.isEmpty(resource.getFileName())){
            return;
        }
        OSSObject ossObject = uploadService.getOSSObjectByName(resource.getOssName());
        if (ossObject == null){
            return;
        }
        try(BufferedInputStream inputStream = new BufferedInputStream(ossObject.getObjectContent())){
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + resource.getOssName());
            byte[] buffBytes = new byte[1024];
            int read = 0;
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            while ((read = inputStream.read(buffBytes)) != -1) {
                outStream.write(buffBytes, 0, read);
            }
            IOUtils.write(outStream.toByteArray(),response.getOutputStream());
        }catch (IOException e){
            logger.error(e.toString());
        }
    }
}
