package com.example.rental.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.example.rental.config.OSSConfig;
import com.example.rental.service.IOssService;
import com.example.rental.utils.FileUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OssServiceImpl implements IOssService {

    @Resource
    private OSSConfig ossConfig;


    @Override
    public String upload(MultipartFile file) {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //新文件名
        String newFileName = FileUtils.getFileName(fileName);

        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret());
        try {
            //完成上传处理
            ossClient.putObject(ossConfig.getBucketName(), newFileName, file.getInputStream());
            String url = "https://"+ossConfig.getBucketName()+"."+ossConfig.getEndpoint()+"/"+newFileName;
            return url;
        }catch (Exception e) {
            throw new RuntimeException("上传失败");
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public Boolean delete(String Url) {
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret());
        String host = "https://" + ossConfig.getBucketName() + "." + ossConfig.getEndpoint() + "/";
        String objectName = StrUtil.removePrefix(Url,host);
        try {
            ossClient.deleteObject(ossConfig.getBucketName(), objectName);
            return true;
        }catch (Exception e) {
            throw new RuntimeException("删除失败");
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
