package com.sinonc.base.oss;


import com.sinonc.common.core.utils.StringUtils;
import com.sinonc.common.core.web.domain.AjaxResult;
import com.sinonc.common.oss.service.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 通过ajax接口上传文件至OSS
 */
@Api(tags = "文件上传接口")
@RequestMapping("/oss")
@RestController
@ConditionalOnProperty(prefix = "oss", name = "enable", havingValue = "true")
public class ApiUploadController {

    @Autowired
    private UploadUtil uploadService;

    @ApiOperation("上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "files", name = "files", dataType = "Array", paramType = "query", required = true)
    })
    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("files") MultipartFile[] files) {
        try{
            String upload = uploadService.upload(files);
            return StringUtils.isEmpty(upload)?AjaxResult.error("上传失败"):AjaxResult.success("上传成功", upload);
        }catch (Exception ex){
            return AjaxResult.error("上传失败");
        }
    }

    @ApiOperation("删除文件")
    @PostMapping("delete")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "token", name = "token", required = true, paramType = "header"),
            @ApiImplicitParam(value = "fileName", name = "文件名", dataType = "String", paramType = "query", required = true)
    })
    public AjaxResult delete(String fileName) {
        uploadService.delete(fileName);
        return AjaxResult.success();
    }

    @ApiOperation("批量删除文件")
    @PostMapping("batchDelete")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "token", name = "token", required = true, paramType = "header"),
            @ApiImplicitParam(value = "fileName", name = "文件名1,文件名2,文件名3", dataType = "String", paramType = "query", required = true)
    })
    public AjaxResult batchDelete(List<String> files) {
        uploadService.batchDelete(files);
        return AjaxResult.success();
    }
}
