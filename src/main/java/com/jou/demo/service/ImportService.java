package com.jou.demo.service;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ImportService {
    //读取excel文件
    void readIn() throws IOException;
    //转化为字符
    String cellToString(Cell cell);
    String[][] readExcel(String filePath) throws IOException;

}
