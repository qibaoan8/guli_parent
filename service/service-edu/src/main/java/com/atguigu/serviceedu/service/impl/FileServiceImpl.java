package com.atguigu.serviceedu.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.service.FileService;
import com.atguigu.serviceedu.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;

        String uploadUrl = null;

        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        try {
            InputStream inputStream = file.getInputStream();

            String datePath = new DateTime().toString("yyyy/MM/dd");

            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString().replace("-", "");
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = datePath + "/" + fileName + fileType;
            ossClient.putObject(bucketName, newName, inputStream);
            ossClient.shutdown();

            uploadUrl = "https://" + bucketName + "." + endPoint + "/" + newName;

        } catch (Exception e) {
            throw new FeiException(20004, "oss文件上传失败");
        }

        return uploadUrl;
    }
}
