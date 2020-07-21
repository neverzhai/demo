package com.shuanger.demo.filterinterceptor.filter.implMethod;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-28 20:12
 * @description:
 */
@Slf4j
@WebFilter(urlPatterns = "/include/filter/*")
public class MyFilterOne extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("---------------filter one, implemented with @WebFiler");

        chain.doFilter(request, response);
    }
}
