package com.atguigu.serviceedu.service.impl;

import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.mapper.EduTeacherMapper;
import com.atguigu.serviceedu.query.TeacherQuery;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-24
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_modified");

        if (teacherQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String start = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(start)) {
            queryWrapper.ge("gmt_create", start);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_modified", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);

    }
}
