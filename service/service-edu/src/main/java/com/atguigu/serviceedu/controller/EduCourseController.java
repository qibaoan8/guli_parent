package com.atguigu.serviceedu.controller;


import com.alibaba.excel.util.StringUtils;
import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.EduCourseDescription;
import com.atguigu.serviceedu.entity.form.CourseInfoForm;
import com.atguigu.serviceedu.entity.form.CourseQuery;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.atguigu.serviceedu.service.EduCourseDescriptionService;
import com.atguigu.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
@RestController
@CrossOrigin
@RequestMapping("/serviceedu/course/")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("save_course_info")
    public R saveCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseID = eduCourseService.saveCourseInfo(courseInfoForm);

        if (!StringUtils.isEmpty(courseID)) {
            return R.ok().data("courseId", courseID);
        } else {
            return R.error().message("保存失败");
        }
    }

    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id) {
        CourseInfoForm courseInfoForm = eduCourseService.getCourseInfo(id);
        return R.ok().data("courseInfo", courseInfoForm);
    }

    @PostMapping("updateCourseInfo/{id}")
    public R updateCourseInfo(@PathVariable String id, @RequestBody CourseInfoForm courseInfoForm) {
        courseInfoForm.setId(id);
        eduCourseService.updateCourseInfo(courseInfoForm);
        return R.ok();
    }

    @GetMapping("getCoursePublishInfo")
    public R getCoursePublishInfo(@RequestParam(name = "courseId", required = true) String courseId) {

        CoursePublishVo coursePublishInfo = eduCourseService.getCoursePublishInfo(courseId);

        return R.ok().data("coursePublishInfo", coursePublishInfo);
    }

    @PutMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        Boolean bool = eduCourseService.coursePublish(id);
        if (bool) {
            return R.ok().message("发布成功");
        } else {
            return R.error().message("发布失败");
        }
    }

    @GetMapping("getCourseList")
    public R getCourseList(CourseQuery courseQuery) {
        Integer current = courseQuery.getCurrent();
        Integer limit = courseQuery.getLimit();

        if (current == null || !(current > 0)) {
            current = 1;
        }

        if (limit == null || !(limit > 0)) {
            limit = 10;
        }

        Page<EduCourse> page = new Page<>(current, limit);

        eduCourseService.getCourseList(page, courseQuery);

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("rows", page.getRecords());
        return R.ok().data(map);
    }

    @PostMapping("deleteCourse")
    public R deleteCourse(@RequestBody EduCourse eduCourse){
        eduCourseService.removeCourse(eduCourse);
        return R.ok();
    }
}

