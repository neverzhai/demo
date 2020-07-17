package com.shuanger.demo.filterinterceptor.filter.implMethod;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-28 20:30
 * @description:
 */
public class MyFilterThree extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        super.doFilter(request, response, chain);
    }

    @Bean
    public FilterRegistrationBean<MyFilterThree> filter() {
        FilterRegistrationBean<MyFilterThree> bean = new FilterRegistrationBean<>();

        bean.setFilter(new MyFilterThree());
        bean.addUrlPatterns("/execute/*");  // or use setUrlPatterns()

        return bean;
    }
}

