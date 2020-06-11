package com.shuanger.demo.filterinterceptor.interceptor;

import com.shuanger.demo.filterinterceptor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-28 20:35
 * @description:
 */
@Slf4j
@Component
public class InterceptorOne implements HandlerInterceptor {

    @Resource
    private TestService testService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("interceptor one pre handle");
        testService.getName();

        BufferedReader reader = request.getReader();

        ServletInputStream stream = request.getInputStream();

//        String token = request.getHeader("token");
//        if (StringUtils.isEmpty(token)) {
//            return false;
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("interceptor one post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("interceptor one after completion");

    }
}
