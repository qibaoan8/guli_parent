package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
public interface EduChapterService extends IService<EduChapter> {
     ArrayList<ChapterVo> getChapterTree(String courseId);
     Boolean deleteChapter(EduChapter eduChapter);

    void removeChapterByCourseId(String courseId);
}
