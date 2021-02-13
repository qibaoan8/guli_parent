package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.query.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-24
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);


}
