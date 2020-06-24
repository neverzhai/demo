package com.shuanger.requestresponse.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-24 15:19
 * @description: request and response test
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/request/parameter")
    public Object testRequest(HttpServletRequest request) {
        log.info(request.getRequestURI());
        log.info(request.getParameter("test"));

        log.info("-----------------------parameter names");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String nextElement = parameterNames.nextElement();
            log.info(nextElement);
        }

        log.info("-----------------------parameter values");
        String[] parameterValues = request.getParameterValues("test");
        for (String value : parameterValues) {
            log.info(value);
        }

        log.info("-----------------------parameter map");
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.forEach((p1, p2)-> {
            log.info(p1);
            log.info(parameterMap.get(p1).toString());
        });

        return "success";
    }

    @RequestMapping("/request/body")
    public Object testBodyRequest(HttpServletRequest request) throws IOException {
        log.info(request.getRequestURI());
        BufferedReader reader = request.getReader();

//        while (reader.lines())
//        log.info(reader);

        return "success";
    }
}
