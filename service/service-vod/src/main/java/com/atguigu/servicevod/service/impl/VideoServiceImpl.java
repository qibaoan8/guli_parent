package com.atguigu.servicevod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.servicevod.service.VideoService;
import com.atguigu.servicevod.util.AliyunVodSDKUtils;
import com.atguigu.servicevod.util.ConstantPropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public void deleteVideo(String videoId) {
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient();
            client.getAcsResponse(request);
        } catch (Exception e) {
            throw new FeiException(20010, "删除视频失败");
        }
    }

    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, originalFilename, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                String errorMessage = "阿里云上传错误，code:" + response.getCode() + ", message: " + response.getMessage();
                //log.warn(errorMessage);
                System.out.println(errorMessage);
                if (StringUtils.isEmpty(videoId)) {
                    throw new FeiException(20009, errorMessage);
                }
            }
            return videoId;
        } catch (Exception e) {
            throw new FeiException(20009, "vod服务异常");
        }
    }
}
