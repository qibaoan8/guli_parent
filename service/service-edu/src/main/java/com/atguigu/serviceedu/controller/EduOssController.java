package com.atguigu.serviceedu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/serviceedu/oss/")
public class EduOssController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public R uploadFile(MultipartFile file) {

        String url = fileService.upload(file);
        return R.ok().message("文件上传成功").data("url", url);

    }

}
