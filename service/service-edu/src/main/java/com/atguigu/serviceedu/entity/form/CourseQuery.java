package com.atguigu.serviceedu.entity.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;

    private String teacherId;

    private String subjectParentId;

    private String subjectId;

    private Integer current;

    private Integer limit;
}
