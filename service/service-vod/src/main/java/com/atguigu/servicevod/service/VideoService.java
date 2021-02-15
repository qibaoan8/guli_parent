package com.atguigu.servicevod.service;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {
    String uploadVideo(MultipartFile file) throws IOException;
    void  deleteVideo(String videoId);
}
