package com.sinonc.common.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.OSSObject;
import com.sinonc.common.oss.properties.OSSProperties;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.DeleteError;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

@Slf4j
public class UploadUtil {

    @Autowired(required = false)
    private OSS ossClient;
    @Autowired(required = false)
    private MinioClient minioClient;
    @Autowired(required = false)
    private OSSProperties ossProperties;

    /**
     * 上传文件
     *
     * @param files 文件
     * @return 访问路径URL
     * @throws Exception 上传失败异常
     */
    public String upload(MultipartFile[] files) {
        StringBuilder result = new StringBuilder();
        for (MultipartFile file : files) {

            if (!file.isEmpty()) {

                InputStream inputStream;

                try {
                    inputStream = file.getInputStream();
                } catch (IOException e) {
                    log.error(e.getMessage());
                    throw new RuntimeException(e);
                }

                StringBuilder stringBuilder = new StringBuilder();

                String name = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

                //Minio上传
                if (minioClient != null) {

                    try {
                        minioClient.putObject(ossProperties.getBucket(), name, inputStream, file.getContentType());
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        throw new RuntimeException(e);
                    }

                    stringBuilder.append(ossProperties.getEndpoint());
                    stringBuilder.append("/");
                    stringBuilder.append(ossProperties.getBucket());
                    stringBuilder.append("/");
                    stringBuilder.append(name);

                    //阿里云OSS上传
                } else if (ossClient != null) {

                    ossClient.putObject(ossProperties.getBucket(), name, inputStream);

                    String prefix = ossProperties.getEndpoint().substring(0, ossProperties.getEndpoint().lastIndexOf("/") + 1);
                    String suffix = ossProperties.getEndpoint().substring(ossProperties.getEndpoint().lastIndexOf("/") + 1);

                    stringBuilder.append(prefix);
                    stringBuilder.append(ossProperties.getBucket());
                    stringBuilder.append(".");
                    stringBuilder.append(suffix);
                    stringBuilder.append("/");
                    stringBuilder.append(name);

                } else {
                    log.error("OSS客户端创建失败");
                    throw new RuntimeException("OSS客户端创建失败");
                }

                if (result.toString().isEmpty()) {
                    result.append(stringBuilder.toString());
                } else {
                    result.append(",").append(stringBuilder.toString());
                }
            }
        }

        return result.toString();
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 访问路径URL
     * @throws Exception 上传失败异常
     */
    public String upload(MultipartFile file) {
        StringBuilder result = new StringBuilder();
        if (file.isEmpty()){
            return "";
        }
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

        StringBuilder stringBuilder = new StringBuilder();

        String name = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        //Minio上传
        if (minioClient != null) {

            try {
                minioClient.putObject(ossProperties.getBucket(), name, inputStream, file.getContentType());
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }

            stringBuilder.append(ossProperties.getEndpoint());
            stringBuilder.append("/");
            stringBuilder.append(ossProperties.getBucket());
            stringBuilder.append("/");
            stringBuilder.append(name);

            //阿里云OSS上传
        } else if (ossClient != null) {

            ossClient.putObject(ossProperties.getBucket(), name, inputStream);

            String prefix = ossProperties.getEndpoint().substring(0, ossProperties.getEndpoint().lastIndexOf("/") + 1);
            String suffix = ossProperties.getEndpoint().substring(ossProperties.getEndpoint().lastIndexOf("/") + 1);

            stringBuilder.append(prefix);
            stringBuilder.append(ossProperties.getBucket());
            stringBuilder.append(".");
            stringBuilder.append(suffix);
            stringBuilder.append("/");
            stringBuilder.append(name);
        } else {
            log.error("OSS客户端创建失败");
            throw new RuntimeException("OSS客户端创建失败");
        }
        if (result.toString().isEmpty()) {
            result.append(stringBuilder.toString());
        } else {
            result.append(",").append(stringBuilder.toString());
        }
        return result.toString();
    }

    /**
     * 上传文件
     *
     * @param inputStream 输入流
     * @param contentType 文件类型
     * @param suffix      文件名后缀
     * @return 文件地址
     */
    public String upload(InputStream inputStream, ContentType contentType, String suffix) {


        StringBuilder stringBuilder = new StringBuilder();

        String name = UUID.randomUUID().toString() + suffix;

        //Minio上传
        if (minioClient != null) {

            try {
                minioClient.putObject(ossProperties.getBucket(), name, inputStream, contentType.getMimeType());
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }

            stringBuilder.append(ossProperties.getEndpoint());
            stringBuilder.append("/");
            stringBuilder.append(ossProperties.getBucket());
            stringBuilder.append("/");
            stringBuilder.append(name);

            //阿里云OSS上传
        } else if (ossClient != null) {

            ossClient.putObject(ossProperties.getBucket(), name, inputStream);

            String pre = ossProperties.getEndpoint().substring(0, ossProperties.getEndpoint().lastIndexOf("/") + 1);
            String suf = ossProperties.getEndpoint().substring(ossProperties.getEndpoint().lastIndexOf("/") + 1);

            stringBuilder.append(pre);
            stringBuilder.append(ossProperties.getBucket());
            stringBuilder.append(".");
            stringBuilder.append(suf);
            stringBuilder.append("/");
            stringBuilder.append(name);

        } else {
            log.error("OSS客户端创建失败");
            throw new RuntimeException("OSS客户端创建失败");
        }

        return stringBuilder.toString();
    }

    /**
     * 上传文件
     *
     * @param inputStream 输入流
     * @param name      文件名
     * @return 文件地址
     */
    public String uploadExcel(InputStream inputStream, String name) {

        StringBuilder stringBuilder = new StringBuilder();
        if (ossClient != null) {

            //删除同名称文件
            ossClient.deleteObject(ossProperties.getBucket(), name);
            ossClient.putObject(ossProperties.getBucket(), name, inputStream);

            String pre = ossProperties.getEndpoint().substring(0, ossProperties.getEndpoint().lastIndexOf("/") + 1);
            String suf = ossProperties.getEndpoint().substring(ossProperties.getEndpoint().lastIndexOf("/") + 1);

            stringBuilder.append(pre);
            stringBuilder.append(ossProperties.getBucket());
            stringBuilder.append(".");
            stringBuilder.append(suf);
            stringBuilder.append("/");
            stringBuilder.append(name);

        } else {
            log.error("OSS客户端创建失败");
            throw new RuntimeException("OSS客户端创建失败");
        }

        return stringBuilder.toString();
    }

    public String upload(byte[] bytes, ContentType contentType, String suffix) {
        return null;
    }

    /**
     * 从OSS中删除文件
     *
     * @param objectName 对象名称
     */
    public void delete(String objectName) {
        if (minioClient != null) {
            try {
                minioClient.removeObject(ossProperties.getBucket(), objectName);
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        } else if (ossClient != null) {
            ossClient.deleteObject(ossProperties.getBucket(), objectName);
        }
    }

    /**
     * 从OSS中批量删除文件
     *
     * @param objectNames
     * @throws Exception
     */
    public void batchDelete(List<String> objectNames) {

        if (minioClient != null) {
            Iterable<Result<DeleteError>> results = minioClient.removeObjects(ossProperties.getBucket(), objectNames);

            for (Result<DeleteError> result : results) {

                try {
                    DeleteError error = result.get();
                    log.error("删除OSS文件[" + error.objectName() + "]异常");
                } catch (Exception e) {
                    log.error(e.getMessage());
                }


            }
        } else if (ossClient != null) {
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(ossProperties.getBucket()).withKeys(objectNames));
            deleteObjectsResult.getDeletedObjects();
        }

    }


    /**
     * 转换文件大小
     */
    public String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 根据oss地址获取OSSObject
     */
    public OSSObject getOSSObjectByName(String ossPath){
        OSSObject ossObject = ossClient.getObject(ossProperties.getBucket(),ossPath);
        return ossObject;
    }

    /**
     * 过滤太大的文件
     */
    public boolean checkFileSize(MultipartFile file){
        if (file.isEmpty()){
            return false;
        }
        BigDecimal size = new BigDecimal(file.getSize());
        BigDecimal mb = new BigDecimal("1048576");
        if (size.compareTo(mb)<0){
            return true;
        }
        BigDecimal mbSize = size.divide(mb).setScale(2, RoundingMode.HALF_UP);
        if (mbSize.compareTo(new BigDecimal(ossProperties.getMaxSize())) >0){
            return false;
        }
        return true;
    }
}
