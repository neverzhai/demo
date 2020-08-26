package com.shuanger.javadate.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-02-06 22:41
 * @description: util
 */
@Slf4j
public class ExcelWriteUtil {

    public static SXSSFWorkbook createWorkbook(String sheetName) {

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        workbook.createSheet(sheetName);

        return workbook;

    }

    public static SXSSFWorkbook writeHeader(SXSSFWorkbook wb, String[] title) {
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

        //声明列对象
        Cell cell = null;

        //创建标题
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cellStyle);
        }

        return wb;
    }


    public static SXSSFWorkbook writeRowAndCell(SXSSFWorkbook wb, String values,int rowIndex, int columnIndex, int sheetIndex) {

        CellStyle cellStyle = wb.createCellStyle();
        Font font = wb.createFont();
        font.setColor(IndexedColors.RED.getIndex());
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);

        Sheet sheet = wb.getSheetAt(sheetIndex);
        sheet.setColumnWidth(columnIndex, 100 * 256);
        Row row = sheet.getRow(rowIndex);
        row.setHeightInPoints((short) 100);
        Cell cell1 = row.createCell(columnIndex);
        cell1.setCellValue(values);
        cell1.setCellStyle(cellStyle);

        return wb;
    }

    public static SXSSFWorkbook writeContent(SXSSFWorkbook wb, String[][] values, int sheetIndex) {
        Sheet sheet = wb.getSheetAt(sheetIndex);
        int rowIndex = sheet.getLastRowNum();
        Row row = null;
        //创建内容
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                row = sheet.createRow(rowIndex + 1);
                rowIndex++;
                for (int j = 0; j < values[i].length; j++) {
                    //将内容按顺序赋给对应的列对象
                    row.createCell(j).setCellValue(values[i][j]);
                }
            }
        }
        return wb;
    }

    public static void export(HttpServletResponse response, Workbook workbook, String fileName) throws Exception{
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder
                .encode(fileName, "UTF-8") + ".xlsx");

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream output;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            workbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (IOException e) {
            log.error("导出Excel文件异常: {}", e.getMessage());
            log.error("导出Excel文件异常, 文件名称: {}", fileName);
        }
    }

}
