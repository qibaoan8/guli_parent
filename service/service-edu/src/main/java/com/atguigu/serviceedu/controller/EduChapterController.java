package com.atguigu.serviceedu.controller;


import com.alibaba.excel.util.StringUtils;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.entity.EduChapter;
import com.atguigu.serviceedu.entity.form.CourseInfoForm;
import com.atguigu.serviceedu.entity.vo.ChapterVo;
import com.atguigu.serviceedu.service.EduChapterService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
@RestController
@RequestMapping("/serviceedu/chapter")
@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @PostMapping("add")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    @GetMapping("get")
    public R getChapter(@RequestParam(name = "chapterId", required = true) String chapterId ) {
        EduChapter chapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapterInfo", chapter);
    }

    @PostMapping("update")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        if (!Strings.isEmpty(eduChapter.getId())){
            eduChapterService.updateById(eduChapter);
        }else{
            throw new FeiException(20007,"chapter update not id");
        }
        return R.ok();
    }

    @PostMapping("delete")
    public R deleteChapter(@RequestBody EduChapter eduChapter) {
        //TODO 需要加上判断是否有小节的逻辑
        eduChapterService.deleteChapter(eduChapter);
        return R.ok();
    }

    @GetMapping("getTree")
    public R getChapterTree(@RequestParam(name = "courseId",required = true) String courseId){

        ArrayList<ChapterVo> chapterTree = eduChapterService.getChapterTree(courseId);

        return R.ok().data("chapterTree", chapterTree);

    }
}

