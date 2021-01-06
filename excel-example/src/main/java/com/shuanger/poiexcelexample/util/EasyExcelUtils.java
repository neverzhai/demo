package com.shuanger.poiexcelexample.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-12-31 16:53
 * @description:
 */
@Component
public class EasyExcelUtils {

    public static <T> void exportWebExcel(HttpServletResponse response, List<T> dataList, Class<T> clazz,
                                          String fileName) {

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 单元格样式策略 定义
        WriteCellStyle style = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
        style.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.RED.getIndex());

//        Map<Integer,Integer> errRecord = new HashMap<>();
//        errRecord.put(1,1);
//        errRecord.put(2,2);
//        ProductWriteErrHandler handler = new ProductWriteErrHandler(style,errRecord);


        try {
            // 导出excel
            EasyExcel.write(response.getOutputStream(), clazz)
                    // 设置过滤字段策略
//                    .excludeColumnFiledNames(excludeFiledNames)
                    // 选择导入时的 handler
//                    .registerWriteHandler(handler)
                    .sheet(fileName)
                    .doWrite(dataList);
        } catch (IOException e) {
            System.err.println("创建文件异常!");
        }

    }


}
