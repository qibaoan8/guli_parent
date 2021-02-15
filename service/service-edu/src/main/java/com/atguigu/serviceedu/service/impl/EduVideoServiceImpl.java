package com.atguigu.serviceedu.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.client.VodClient;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.mapper.EduVideoMapper;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoById(String videoId) {
        EduVideo video = baseMapper.selectById(videoId);
        String videoSourceId = video.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)) {
            vodClient.removeVideos(videoSourceId);
        }
        baseMapper.deleteById(videoId);
    }

    @Override
    public String uploadVideo(MultipartFile file) {

        R r = vodClient.uploadVideo(file);
        if (!r.getSuccess()){
            throw new FeiException(r.getCode(),r.getMessage());
        }
        return r.getData().get("videoId").toString();
    }
}
