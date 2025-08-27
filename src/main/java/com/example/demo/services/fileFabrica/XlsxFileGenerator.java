package com.example.demo.services.fileFabrica;

import com.example.demo.dto.EmployeeDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class XlsxFileGenerator implements FileGenerator {
    @Override
    public String generateFile(EmployeeDto employee){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("Resume");

        Row row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue(employee.getName());

        Row row2 = sheet.createRow(2);
        row2.createCell(0).setCellValue("Phone");
        row2.createCell(1).setCellValue(employee.getTelephon());

        Row row3 = sheet.createRow(3);
        row3.createCell(0).setCellValue("Telegram");
        row3.createCell(1).setCellValue(employee.getTelephon());

        try {
            String file = "D:\\IDEA.xlsx";
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
