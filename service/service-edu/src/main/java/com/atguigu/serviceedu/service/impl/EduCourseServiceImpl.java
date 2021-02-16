package com.atguigu.serviceedu.service.impl;

import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduCourseDescription;
import com.atguigu.serviceedu.entity.form.CourseInfoForm;
import com.atguigu.serviceedu.entity.form.CourseQuery;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.atguigu.serviceedu.mapper.EduCourseMapper;
import com.atguigu.serviceedu.service.EduChapterService;
import com.atguigu.serviceedu.service.EduCourseDescriptionService;
import com.atguigu.serviceedu.service.EduCourseService;
import com.atguigu.serviceedu.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {


    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduCourseService eduCourseService;

    @Override
    public void removeCourse(EduCourse eduCourse) {
        //删除课程使用批量删除，查询课程删除，查询章节删除，查询小节删除，查询视频删除
        String courseId = eduCourse.getId();

        // 删除小节 和 视频
        eduVideoService.removeVideoByCourseId(courseId);

        // 删除章节
        eduChapterService.removeChapterByCourseId(courseId);

        // 删除描述
        eduCourseDescriptionService.removeById(courseId);

        // 删除课程
        baseMapper.deleteById(courseId);

    }

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        boolean bool = this.save(eduCourse);
        if (!bool) {
            throw new FeiException(20005, "课程信息保存失败");

        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        boolean resultDesc = eduCourseDescriptionService.save(eduCourseDescription);
        if (!resultDesc) {
            throw new FeiException(20006, "课程描述信息保存失败");
        }
        System.out.println("getid" + eduCourse.getId());
        return eduCourse.getId();
    }

    @Override
    public CourseInfoForm getCourseInfo(String id) {
        CourseInfoForm courseInfoForm = new CourseInfoForm();

        EduCourse course = this.getById(id);
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(id);
        BeanUtils.copyProperties(course, courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        boolean bool = this.updateById(eduCourse);
        if (!bool) {
            throw new FeiException(20005, "更新方法-课程信息保存失败");
        }


        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseInfoForm.getId());
        boolean resultDesc = eduCourseDescriptionService.updateById(eduCourseDescription);
        if (!resultDesc) {
            throw new FeiException(20006, "更新方法-课程描述信息保存失败");
        }
    }

    @Override
    public CoursePublishVo getCoursePublishInfo(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public Boolean coursePublish(String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus(EduCourse.COURSE_NORMAL);
        int i = baseMapper.updateById(eduCourse);
        System.out.println("update:" + i);
        return i > 0;
    }

    @Override
    public void getCourseList(Page<EduCourse> page, CourseQuery courseQuery) {

        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");

        if (courseQuery == null) {
            baseMapper.selectPage(page, queryWrapper);
            return;
        }

        String subjectId = courseQuery.getSubjectId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        baseMapper.selectPage(page,queryWrapper);

    }
}
