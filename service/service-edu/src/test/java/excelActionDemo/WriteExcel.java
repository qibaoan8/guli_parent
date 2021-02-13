package excelActionDemo;

import com.alibaba.excel.EasyExcel;
import entity.DemoData;

import java.util.ArrayList;
import java.util.List;

public class WriteExcel {

    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("SSS" + i);
            list.add(demoData);

        }
        return list;
    }

    public static void main(String[] args) {
        String filename = "/Users/wang.gaofei/Downloads/1.xlsx";
        EasyExcel.write(filename,DemoData.class).sheet("写入方法1").doWrite(data());
    }
}
