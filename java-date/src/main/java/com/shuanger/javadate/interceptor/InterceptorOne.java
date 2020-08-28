package com.shuanger.javadate.interceptor;//package com.shuanger.demo.filterinterceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-12 21:45
 * @description:
 */
@Slf4j
@Component
public class InterceptorOne extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            log.info("interceptor#preHandle called. Thread--------------------------: " + Thread.currentThread().getName());
            return true;

        }

        @Override
        public void postHandle (HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            log.info("interceptor#postHandle called. Thread-------------------------: " +
                    Thread.currentThread()
                            .getName());
        }

        @Override
        public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            log.info("interceptor#afterCompletion called Thread---------------------------: " +
                    Thread.currentThread()
                            .getName());
        }

        @Override
        public void afterConcurrentHandlingStarted (HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            log.info("interceptor#afterConcurrentHandlingStarted called.---------------------------- " +
                    "Thread: " +
                    Thread.currentThread()
                            .getName());
        }
}
