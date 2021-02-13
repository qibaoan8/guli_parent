package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.vo.SubjectNestedVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-31
 */
public interface EduSubjectService extends IService<EduSubject> {

    public Boolean importSubject(MultipartFile file, EduSubjectService eduSubjectService);

    public List<SubjectNestedVo> nextedList();
}
