package com.atguigu.serviceedu.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.client.VodClient;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.mapper.EduVideoMapper;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

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
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("course_id", courseId);
        List<EduVideo> videos = baseMapper.selectList(queryWrapper);

        //获取视频id
        ArrayList<String> videoSourceIdList = new ArrayList<>();
        for (EduVideo video : videos) {
            String videoSourceId = video.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                videoSourceIdList.add(videoSourceId);
            }
        }
        // 如果存在视频就批量删除
        if (videoSourceIdList.size() > 0) {
            String videoSourceIdsString = StringUtils.join(videoSourceIdList.toArray(), ",");
            vodClient.removeVideos(videoSourceIdsString);
        }
        //删除小节本身
        baseMapper.delete(queryWrapper);

    }

    @Override
    public void removeVideoSource(String videoSourceId) {
        vodClient.removeVideos(videoSourceId);
    }

    @Override
    public void removeVideo(EduVideo eduVideo) {
        String videoId = eduVideo.getId();
        String videoSourceId = eduVideo.getVideoSourceId();

        // 如果没有阿里云视频id，并且有小节id，那就在数据库里面查一下有没有阿里云视频id
        if (StringUtils.isEmpty(videoSourceId) && !StringUtils.isEmpty(videoId)) {
            System.out.println("if videoId" + videoId);
            EduVideo videoFormDb = baseMapper.selectById(videoId);
            if (!StringUtils.isEmpty(videoFormDb.getVideoSourceId())) {
                videoSourceId = videoFormDb.getVideoSourceId();
                System.out.println("videoSourceId " + videoSourceId);
            }
        }
        //删除视频id
        if (!StringUtils.isEmpty(videoSourceId)) {
            System.out.println("del videoSourceId " + videoSourceId);
            vodClient.removeVideos(videoSourceId);
        }
        // 删除小节
        if (!StringUtils.isEmpty(videoId)) {
            baseMapper.deleteById(videoId);
        }
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
