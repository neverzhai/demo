package com.shuanger.demo.filterinterceptor.filter.implMethod;

import com.shuanger.demo.filterinterceptor.response.BuildResponseUtil;
import com.shuanger.demo.filterinterceptor.response.BusinessException;
import com.shuanger.demo.filterinterceptor.response.RespCode;
import com.shuanger.demo.filterinterceptor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-28 20:29
 * @description:
 */
@Slf4j
@Order(2)
@Component
public class MyFilterTwo extends HttpFilter {

    @Resource
    private TestService testService;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("---------------filter two, implemented with @Component");

//        log.info("===== call test service: {}", testService.getName());
        if(true) {
            BuildResponseUtil.buildResponse(response, RespCode.FAILED);
        }
        chain.doFilter(request, response);
    }
}
