package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.form.CourseInfoForm;
import com.atguigu.serviceedu.entity.form.CourseQuery;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
public interface EduCourseService extends IService<EduCourse> {
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfo(String id);

    void updateCourseInfo(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishInfo(String id);

    Boolean coursePublish(String courseId);


    void getCourseList(Page<EduCourse> page, CourseQuery courseQuery);

    void removeCourse(EduCourse eduCourse);
}
