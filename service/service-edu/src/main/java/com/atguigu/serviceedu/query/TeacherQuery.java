package com.atguigu.serviceedu.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "教师查询对象", description = "教师对象封装")
public class TeacherQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教师名称，模糊查询", example = "王老师")
    private String name;

    @ApiModelProperty(value = "教师级别", example = "1")
    private Integer level;

    @ApiModelProperty(value = "start time", example = "2021-02-01 00:00:00")
    private String begin;

    @ApiModelProperty(value = "end time", example = "2021-02-01 00:00:00")
    private String end;
}

