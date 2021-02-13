package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class EduLoginController {

    @PostMapping("login")
    public R login() {

        return R.ok().data("token", "your_token");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("name", "大飞哥").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
