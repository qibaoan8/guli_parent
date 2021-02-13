package excelActionDemo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import entity.DemoData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ReadExcel extends AnalysisEventListener<DemoData> {

    List<DemoData> list =new ArrayList<DemoData>();

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("data:" + demoData);
        list.add(demoData);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public static void main(String[] args) {
        String filename = "/Users/wang.gaofei/Downloads/1.xlsx";
        EasyExcel.read(filename,DemoData.class,new ReadExcel()).sheet().doRead();
    }
}
