package com.jou.demo.service.Impl;

import com.jou.demo.service.OutputService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;
import java.io.IOException;

public class OutputServiceImpl implements OutputService {

    @Override
    public void output() throws IOException {
        // 示例二维数组
        String[][] data = {
                {"姓名", "年龄", "职位"},
                {"张三", "25", "软件工程师"},
                {"李四", "30", "项目经理"},
                {"王五", "28", "产品经理"}
        };

        // 创建新的Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        // 创建一个工作表
        Sheet sheet = workbook.createSheet("员工信息");

        // 使用二维数组填充Excel
        int rowNum = 0;
        for (String[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (String cellData : rowData) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
            }
        }

        // 将Excel写到文件
        try (FileOutputStream outputStream = new FileOutputStream("调查问卷.xlsx")) {
            workbook.write(outputStream);
        } finally {
            workbook.close();
        }

        System.out.println("Excel文件已成功创建！");
    }

}
