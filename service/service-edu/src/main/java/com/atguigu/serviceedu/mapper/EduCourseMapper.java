package com.atguigu.serviceedu.mapper;

import com.atguigu.serviceedu.entity.EduCourse;
import com.atguigu.serviceedu.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo selectCoursePublishVoById(String id);
}
