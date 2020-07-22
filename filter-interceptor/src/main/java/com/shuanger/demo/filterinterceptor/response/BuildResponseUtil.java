package com.shuanger.demo.filterinterceptor.response;

import com.shuanger.demo.filterinterceptor.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-11 09:59
 * @description: build custom response when check failed
 */
@Slf4j
public class BuildResponseUtil {
    public static void buildResponse(HttpServletResponse response, RespCode responseCode) {

        try {
            ResponseEntity result = new ResponseEntity(responseCode);
            writeResponse(response, result);
        } catch (Exception e) {
            log.error("返回页面结果信息失败", e);
        }
    }

    private static void writeResponse(HttpServletResponse response, ResponseEntity result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.print(GsonUtils.toJson(result));
        printWriter.flush();
        printWriter.close();
    }
}
