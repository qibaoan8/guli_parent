package com.atguigu.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.servicebase.exception.FeiException;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.ExcelSubjectData;
import com.atguigu.serviceedu.entity.vo.SubjectNestedVo;
import com.atguigu.serviceedu.entity.vo.SubjectVo;
import com.atguigu.serviceedu.listener.SbujectExcelListener;
import com.atguigu.serviceedu.mapper.EduSubjectMapper;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-01-31
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public Boolean importSubject(MultipartFile file, EduSubjectService eduSubjectService) {

        try {
            EasyExcel.read(file.getInputStream(), ExcelSubjectData.class,
                    new SbujectExcelListener(eduSubjectService)).sheet().doRead();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FeiException(20004, "导入excel文件失败");
        }

    }

    @Override
    public List<SubjectNestedVo> nextedList() {
        ArrayList<SubjectNestedVo> subjectNestedVos = new ArrayList<>();

        //    查询一级数据
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", "0");
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> eduSubjects = baseMapper.selectList(queryWrapper);

        // 查询二级数据
        QueryWrapper<EduSubject> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.ne("parent_id", "0");
        queryWrapper1.orderByAsc("sort", "id");
        List<EduSubject> eduSubjects1 = baseMapper.selectList(queryWrapper1);

        // 填充一级分类vo数据
        int count = eduSubjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject eduSubject = eduSubjects.get(i);

            //    创建一级vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(eduSubject, subjectNestedVo);
            System.out.println(subjectNestedVo);
            subjectNestedVos.add(subjectNestedVo);

            //    填充二级分类vo数据
            ArrayList<SubjectVo> subjectVos = new ArrayList<>();
            for (int i1 = 0; i1 < eduSubjects1.size(); i1++) {
                EduSubject eduSubject1 = eduSubjects1.get(i1);
                if (eduSubject1.getParentId().equals(eduSubject.getId())) {
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(eduSubject1, subjectVo);
                    subjectVos.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVos);

        }

        return subjectNestedVos;
    }
}
