package com.shuanger.interceptor.interceptor;//package com.shuanger.demo.filterinterceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-28 20:35
 * @description:
 */
@Slf4j
public class InterceptorOne implements HandlerInterceptor {
    ThreadLocal<StopWatch> stopWathch = new ThreadLocal<StopWatch>();


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("interceptor one pre handle");

//        BufferedReader reader = request.getReader();

//        ServletInputStream stream = request.getInputStream();

//        String token = request.getHeader("token");
//        if (StringUtils.isEmpty(token)) {
//            return false;
//        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("interceptor one post handle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("interceptor one after completion");

    }
}
