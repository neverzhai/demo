package com.shuanger.javadate.utils;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.*;


@Slf4j
public class ExcelUtil {

    public static List<List<String>> readXlsx(MultipartFile file) {
        List<List<String>> result = new ArrayList<>();

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());

        } catch (IOException e) {
            log.info("解析EXCEl文件异常: {}", e.getMessage());
//            throw new AppException(ResponseCodeEnum.SERVER_ERROR.getCode(), "解析EXCEl异常");
        }

        XSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            XSSFRow currentRow = (XSSFRow)rowIterator.next();
            List<String> rowValue = parseRowValue(currentRow);
            result.add(rowValue);
        }

        return result;
    }

    public static List<List<String>> readXls(MultipartFile file) {
        List<List<String>> result = new ArrayList<>();

        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(file.getInputStream());

        } catch (IOException e) {
            log.info("解析EXCEl文件异常: {}", e.getMessage());
//            throw new AppException(ResponseCodeEnum.SERVER_ERROR.getCode(), "解析EXCEl异常");
        }

        HSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            HSSFRow currentRow = (HSSFRow)rowIterator.next();
            List<String> rowValue = parseRowValue(currentRow);
            result.add(rowValue);
        }

        return result;
    }

    public static List<List<String>> readCSV(MultipartFile file) {
        List<List<String>> result = new ArrayList<>();

        try {

            BufferedReader reader=new BufferedReader(new InputStreamReader(file.getInputStream()));
            reader.readLine();

            String line = null;
            while((line = reader.readLine()) != null){
                List<String> lineValue = parseLineValue(line);
                result.add(lineValue);
            }

        } catch (Exception e) {
            log.info("解析CSV文件异常, {}", e.getMessage());
//            throw new AppException(ResponseCodeEnum.PARAM_ERROR.getCode(),"解析CSV文件异常");
        }

        return result;
    }

    private static List<String> parseLineValue(String line) {
        List<String> list = new ArrayList<>();

        // TODO 使用正则表达式进行分割
        String items[] = line.split(" ");
        for (String item : items) {
            list.add(item);
        }
        return list;
    }

    private static String getValue(String[] item,int index){

        if(item.length > index){
            String value = item[index];
            return value;
        }
        return "";
    }

//    private static Date getCsvDate(String item) throws Exception{
//
//        Date date = DateUtils.parseDate(item);
//
//        return date;
//    }

    private static List<String> parseRowValue(Row row) {
        List<String> list = new ArrayList<>();

        int lastColumnNum = row.getLastCellNum();
        for (int columnIndex = 0; columnIndex < lastColumnNum; columnIndex++) {
            Cell cell = row.getCell(columnIndex);
            String cellValue = parseCellValue(cell);
            list.add(cellValue);
        }

        return list;

    }

    private static String parseCellValue(Cell cell) {
        String cellValue = "";
        if (ObjectUtils.isEmpty(cell)) {
            return cellValue;
        }

        if (ERROR == (cell.getCellType())) {

            String errorMessage = String.format("%d行5d列存在非法字符", cell.getRowIndex(), cell.getColumnIndex());
//            throw new AppException(ResponseCodeEnum.PARAM_ERROR.getCode(),errorMessage);
        }

        if (cell.getCellType() == NUMERIC) {
            cell.setCellType(STRING);
        }

        switch (cell.getCellType()) {
            case NUMERIC: // 数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    cellValue = DateUtil.format(cell.getDateCellValue(),"yyyy-MM-dd");
                } else {
                    cellValue = cell.getNumericCellValue() + "";
                }
                break;
            case STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case FORMULA: // 公式
                cellValue = cell.getCellFormula() + "";
                break;
            case BLANK: //空值
                cellValue = "";
                break;
            default:
                cellValue = "";
                break;
        }

        return cellValue;
    }
}
