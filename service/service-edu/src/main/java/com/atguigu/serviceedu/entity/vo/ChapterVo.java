package com.atguigu.serviceedu.entity.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ChapterVo {
    private String id;
    private String title;
    private ArrayList<VideoVo> children;

}
