package com.atguigu.serviceedu.controller;

import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("")
    public R hello(){
        return R.ok();
    }
}
