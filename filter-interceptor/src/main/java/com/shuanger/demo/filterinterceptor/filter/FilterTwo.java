package com.shuanger.demo.filterinterceptor.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-28 17:32
 * @description:
 */
@Slf4j
public class FilterTwo implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("do filter--------------------filter two");
        chain.doFilter(request, response);

    }
}
