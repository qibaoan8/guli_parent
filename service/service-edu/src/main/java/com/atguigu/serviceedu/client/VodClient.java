package com.atguigu.serviceedu.client;

import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.config.ServiceFeignConfiguration;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "service-vod", configuration = ServiceFeignConfiguration.class)
@Component
public interface VodClient {
    @PostMapping(value = "/vod/video/delete")
    public R removeVideos(@RequestParam("videoIds") String videoIds);

    @PostMapping(value = "/vod/video/upload", consumes = "multipart/form-data")
    public R uploadVideo(@RequestPart("file") MultipartFile file);

}
