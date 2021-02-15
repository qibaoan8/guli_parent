package com.atguigu.servicevod.controller;

import com.atguigu.commonutils.R;
import com.atguigu.servicevod.service.VideoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/vod/video")
public class VideoAdminController {

    @Autowired
    private VideoService videoService;

    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws IOException {
        String videoId = videoService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }

    @PostMapping("delete")
    public R deleteVideo(
            @RequestParam("videoIds") String videoIds) {
        videoService.deleteVideo(videoIds);
        return R.ok().message("视频删除成功");
    }
}