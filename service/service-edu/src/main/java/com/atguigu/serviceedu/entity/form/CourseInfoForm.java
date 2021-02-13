package com.atguigu.serviceedu.entity.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CourseInfoForm implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id",type = IdType.INPUT)
    private String id;
    @ApiModelProperty(value = "讲师ID")
    private String teacherId;
    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;
    @ApiModelProperty(value = "课程专业ID")
    private String subjectParentId;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程价格")
    private Float price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "课程简介")
    private String description;

}

















