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
@ApiModel(value="EduVideo对象", description="")
public class EduVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "章节id")
    private String chapterId;

    @ApiModelProperty(value = "章节标题")
    private String title;

    @ApiModelProperty(value = "视频源id")
    private String videoSourceId;

    @ApiModelProperty(value = "视频源文件名称")
    private String videoOriginalName;

    @ApiModelProperty(value = "排序字段")
    private String sort ;

    @ApiModelProperty(value = "播放次数")
    private String playCount;

    @ApiModelProperty(value = "是否可以免费试听：0收费 1免费")
    private String isFree;

    @ApiModelProperty(value = "视频时长，单位秒")
    private String duration;

    @ApiModelProperty(value = "Empty未上传 Transcoding转码中  Normal正常")
    private String status;

    @ApiModelProperty(value = "视频源文件大小 字节")
    private String size;

    @ApiModelProperty(value = "乐观锁")
    private String version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "最后修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
