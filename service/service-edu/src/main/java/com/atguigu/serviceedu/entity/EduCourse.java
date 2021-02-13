package com.atguigu.serviceedu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author atguigu
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduCourse对象", description="")
public class EduCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String COURSE_DRAFT = "Draft";
    public static final String COURSE_NORMAL = "Normal";

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "课程id")
    private String subjectId;

    @ApiModelProperty(value = "课程父id")
    private String subjectParentId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程单价")
    private Float price;

    @ApiModelProperty(value = "课程数量")
    private Integer lessonNum;

    @ApiModelProperty(value = "商品链接")
    private String cover;

    @ApiModelProperty(value = "卖出数量")
    private Integer buyCount;

    @ApiModelProperty(value = "课程观看数量")
    private Integer viewCount;

    @ApiModelProperty(value = "乐观锁")
    private Integer version;

    @ApiModelProperty(value = "课程商品状态")
    private String status;

    @ApiModelProperty(value = "是否删除0没删除1删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "最后更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
