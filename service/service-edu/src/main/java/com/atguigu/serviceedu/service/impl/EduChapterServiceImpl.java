package com.atguigu.serviceedu.service.impl;

import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.entity.vo.ChapterVo;
import com.atguigu.serviceedu.entity.vo.VideoVo;
import com.atguigu.serviceedu.mapper.EduChapterMapper;
import com.atguigu.serviceedu.service.EduChapterService;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);
    }

    @Override
    public ArrayList<ChapterVo> getChapterTree(String courseId) {

        ArrayList<ChapterVo> chapterVos = new ArrayList<>();

        // 查询章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.orderByAsc("sort", "id");
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        // 查询小节
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id", courseId);
        wrapper1.orderByAsc("id");
        List<EduVideo> eduVideos = eduVideoService.list(wrapper1);

        //遍历章节
        for (EduChapter eduChapter : eduChapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            // 遍历小节
            ArrayList<VideoVo> videoVos = new ArrayList<>();
            for (EduVideo eduVideo : eduVideos) {
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    videoVos.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVos);
            chapterVos.add(chapterVo);
        }

        return chapterVos;
    }

    @Override
    public Boolean deleteChapter(EduChapter eduChapter) {

        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id", eduChapter.getId());
        int count = eduVideoService.count(queryWrapper);
        if (count>0){
            throw new FeiException(20008, "请先删除小节再删除章节");
        }else {
            baseMapper.deleteById(eduChapter.getId());
        }
        return true;
    }
}
