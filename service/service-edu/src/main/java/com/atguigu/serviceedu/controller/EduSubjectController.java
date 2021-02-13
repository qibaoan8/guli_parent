package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.vo.SubjectNestedVo;
import com.atguigu.serviceedu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-01-31
 */
@RestController
@RequestMapping("/serviceedu/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        Boolean bool = eduSubjectService.importSubject(file, eduSubjectService);
        if (bool){
            return R.ok();
        }else{
            return R.error();
        }
    }
    @GetMapping("getSubject/list")
    public R getSubject(){
        List<SubjectNestedVo> subjectNestedVos = eduSubjectService.nextedList();
        return R.ok().data("items", subjectNestedVos);
    }
}

