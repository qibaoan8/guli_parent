package com.atguigu.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.ExcelSubjectData;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;

public class SbujectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    public EduSubjectService eduSubjectService;

    public SbujectExcelListener() {
    }

    public SbujectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public EduSubject existOneSubject(String subjectName) {
        QueryWrapper<EduSubject> wapper = new QueryWrapper<>();
        wapper.eq("title", subjectName);
        wapper.eq("parent_id", "0");

        EduSubject eduSubject = this.eduSubjectService.getOne(wapper);
        return eduSubject;
    }

    public EduSubject existTwoSubject(String subjectName, String pid) {
        QueryWrapper<EduSubject> wapper = new QueryWrapper<>();
        wapper.eq("title", subjectName);
        wapper.eq("parent_id", pid);
        EduSubject eduSubject = this.eduSubjectService.getOne(wapper);
        return eduSubject;
    }

    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        System.out.println("value" + excelSubjectData);

        //    添加一级分类
        String oneSubjectName = excelSubjectData.getOneSubjectName();
        EduSubject oneSubject = this.existOneSubject(oneSubjectName);
        if (oneSubject == null) {
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(oneSubjectName);
            eduSubjectService.save(oneSubject);
        }

        // 添加二级分类
        String twoSubjectName = excelSubjectData.getTwoSubjectName();
        EduSubject twoSubject = this.existTwoSubject(twoSubjectName, oneSubject.getId());
        if (twoSubject == null){
            twoSubject = new EduSubject();
            twoSubject.setTitle(twoSubjectName);
            twoSubject.setParentId(oneSubject.getId());
            eduSubjectService.save(twoSubject);
        }


    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("do after all analysed");

    }
}
