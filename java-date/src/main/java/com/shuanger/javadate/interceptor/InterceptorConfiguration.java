package com.shuanger.javadate.interceptor;

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

//    @Resource
//    private InterceptorOne interceptorTwo;

    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration registration = registry.addInterceptor(interceptorOne);
        registration.addPathPatterns("/**")
                .order(2);

//        InterceptorRegistration registrationOne = registry.addInterceptor(interceptorOne);
//        registrationOne.addPathPatterns("/**")
//                .order(1);


    }

}
