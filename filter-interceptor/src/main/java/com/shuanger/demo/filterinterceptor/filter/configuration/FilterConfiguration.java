package com.shuanger.demo.filterinterceptor.filter.configuration;

import com.shuanger.demo.filterinterceptor.filter.implMethod.MyFilterThree;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhaixiaoshuang
 * @date: 2020-06-28 20:00
 * @description:
 */
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean<MyFilterThree> filter() {
        FilterRegistrationBean<MyFilterThree> bean = new FilterRegistrationBean<>();

        bean.setFilter(new MyFilterThree());
        bean.addUrlPatterns("/include/filter/*");  // or use setUrlPatterns()

        return bean;
    }
}
