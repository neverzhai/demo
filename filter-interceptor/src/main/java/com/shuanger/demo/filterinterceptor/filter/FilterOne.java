package com.shuanger.demo.filterinterceptor.filter;


import com.shuanger.demo.filterinterceptor.filter.wrapper.CustomRequestWrapper;
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
 * @date: 2020-06-11 17:41
 * @description: 第一个Filter
 */
@Slf4j
//@WebFilter(urlPatterns = "/include/*")
public class FilterOne extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("do filter----------------- filter one");

        request.getReader();
        chain.doFilter(request, response);
    }
}
