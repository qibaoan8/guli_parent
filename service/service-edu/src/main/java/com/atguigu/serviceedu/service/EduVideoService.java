package com.atguigu.serviceedu.service;

import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
public interface EduVideoService extends IService<EduVideo> {
    public void removeVideoById(String videoId);
    public String uploadVideo(MultipartFile file);
}
