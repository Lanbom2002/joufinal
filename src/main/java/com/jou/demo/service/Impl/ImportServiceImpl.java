package com.jou.demo.service.Impl;

import com.jou.demo.service.ImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ImportServiceImpl implements ImportService {
    @Override
    public void readIn() throws IOException {
        // 示例：读取文件
        String filePath = "path/to/your/excel/file.xlsx"; // 这里填写你的Excel文件的路径
        String[][] data = readExcel(filePath);

        // 打印数据以确保读取正确
        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }



    @Override
    public String cellToString(Cell cell) {
        String cellValue;
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = cell.getCellFormula();
                break;
            case BLANK:
                cellValue = "";
                break;
            default:
                cellValue = "不支持的单元格类型";
        }
        return cellValue;
    }

    @Override
    public String[][] readExcel(String filePath) throws IOException {
        Workbook workbook;
        // 根据文件后缀名选择合适的Workbook实现
        if (filePath.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
        } else if (filePath.endsWith(".xls")) {
            workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
        } else {
            throw new IllegalArgumentException("文件格式不支持");
        }

        Sheet sheet = workbook.getSheetAt(0); // 读取第一个工作表
        int numRows = sheet.getLastRowNum() + 1; // 获取行数
        Row firstRow = sheet.getRow(0);
        int numCols = firstRow.getLastCellNum(); // 获取列数

        String[][] data = new String[numRows][numCols]; // 创建二维数组存储数据

        for (int i = 0; i < numRows; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < numCols; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                data[i][j] = cellToString(cell); // 转换单元格并存储数据
            }
        }

        workbook.close(); // 关闭资源
        return data; // 返回解析后的数据
    }
}
