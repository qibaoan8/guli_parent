package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.EduApplication;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.query.TeacherQuery;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-01-24
 */

@Api(value = "用户controller", tags = {"用户操作接口"}, description = "教师管理")
@CrossOrigin
@RestController
@RequestMapping("/serviceedu/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有教师列表")
    @GetMapping("all")
    public R listAll() {
        List<EduTeacher> list = eduTeacherService.list(null);

        return R.ok().data("items", list);

    }

    @ApiOperation(value = "根据ID删除教师")
    @DeleteMapping("{id}")
    public R delTeath(
            @ApiParam(name = "id", value = "教师id", required = true)
            @PathVariable String id) {
        boolean bool = eduTeacherService.removeById(id);
        if (bool) {
            return R.ok().message("ok");
        } else {
            return R.error().message("failed");
        }

    }

    @GetMapping("{current}/{limit}")
    public R pageList(@PathVariable long current, @PathVariable long limit, TeacherQuery teacherQuery) throws InterruptedException {
        Page<EduTeacher> pageParam = new Page<>(current, limit);
        //eduTeacherService.page(pageParam, null);
        eduTeacherService.pageQuery(pageParam, teacherQuery);
        long total = pageParam.getTotal();
        List<EduTeacher> records = pageParam.getRecords();

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        //Thread.sleep(3000);
        //try {
        //    int i = 1 / 0;
        //} catch (Exception e) {
        //    throw new FeiException(20003, "被除数不能为0");
        //}

        return R.ok().data(map);
    }

    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("detail", teacher);
    }


    @PutMapping("update/{id}")
    public R updateById(@PathVariable String id, @RequestBody EduTeacher eduTeacher) {
        eduTeacher.setId(id);
        eduTeacherService.updateById(eduTeacher);
        return R.ok();

    }


}

