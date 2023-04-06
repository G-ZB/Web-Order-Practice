package com.academy.techcenture.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    private XSSFWorkbook workbook;
    private XSSFSheet worksheet;

    //Constructor
    public ExcelReader(String filePath, String sheetName) {
        File file = new File(filePath);

//try catch because we are dealing with SIO
        try {
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet worksheet = workbook.getSheet(sheetName);
            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Object[][] getData() {

        int rows = worksheet.getLastRowNum(); // returns number of rows
        int cols = worksheet.getRow(0).getLastCellNum(); // returns number of col

        Object[][] data = new Object[rows][1];

        for (int i = 0; i < rows; i++) {
            Map<String, String> map = new HashMap<>();


            for (int j = 0; j < cols; j++) {
                // each column name is a key
                XSSFCell cell = worksheet.getRow(i+1).getCell(j); // might be null sometimes if
                map.put(worksheet.getRow(0).getCell(j).toString(),cell == null ? "" : cell.toString()); //each cell under column name will be value

            }

            data[i][0] = map;
        }
        return data;
    }
}
