package com.shuanger.interceptor.interceptor;//package com.shuanger.demo.filterinterceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
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
@Component
public class InterceptorOne implements HandlerInterceptor {
    ThreadLocal<StopWatch> stopWatch = new ThreadLocal<StopWatch>();


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("interceptor one pre handle---------------------------");

        StopWatch sw = new StopWatch();
        stopWatch.set(sw);
        sw.start();

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("interceptor one post handle--------------------------------");

        stopWatch.get().stop();
        stopWatch.get().start();
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("interceptor one after completion---------------------------------");

        StopWatch sw = this.stopWatch.get();
        sw.stop();

        String method = handler.getClass().getSimpleName();
        if(handler instanceof HandlerMethod) {
            String beanType = ((HandlerMethod) handler).getBeanType().getName();
            String methodName = ((HandlerMethod) handler).getMethod().getName();
            method = beanType + "."+methodName;
        }
        log.info("uri: {}; method: {}; status: {}", request.getRequestURI(), method, response.getStatus());

        log.info("totalTime: {}ms; firstTask: {}ms; lastTask: {}ms",
                sw.getTotalTimeMillis(),
                sw.getTotalTimeMillis()-sw.getLastTaskTimeMillis(),
                sw.getLastTaskTimeMillis());

        stopWatch.remove();

    }
}
