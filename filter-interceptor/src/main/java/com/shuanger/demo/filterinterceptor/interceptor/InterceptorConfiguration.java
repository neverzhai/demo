package com.shuanger.demo.filterinterceptor.interceptor;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-05-12 21:52
 * @description:
 */
@SpringBootConfiguration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Resource
    private InterceptorOne interceptorOne;
    @Resource
    private InterceptorTwo interceptorTwo;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registrationOne = registry.addInterceptor(interceptorOne);
        registrationOne.addPathPatterns("/**");

        InterceptorRegistration registration = registry.addInterceptor(interceptorTwo);
        registration.addPathPatterns("/**");

    }

}
