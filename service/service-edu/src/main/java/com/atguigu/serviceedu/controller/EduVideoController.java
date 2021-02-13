package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.entity.EduVideo;
import com.atguigu.serviceedu.service.EduVideoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
@RestController
@RequestMapping("/serviceedu/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @PostMapping("add")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    @GetMapping("get")
    public R getVideo(@RequestParam(name = "videoId", required = true) String videoId) {
        EduVideo video = eduVideoService.getById(videoId);
        return R.ok().data("videoInfo", video);
    }

    @PostMapping("update")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        if (!Strings.isEmpty(eduVideo.getId())) {
            eduVideoService.updateById(eduVideo);
        } else {
            throw new FeiException(20007, "chapter update not id");
        }
        return R.ok();
    }

    @PostMapping("delete")
    public R deleteVideo(@RequestBody EduVideo eduVideo) {
        //TODO 需要加上判断是否有视频的逻辑
        eduVideoService.removeById(eduVideo.getId());
        return R.ok();
    }

}

