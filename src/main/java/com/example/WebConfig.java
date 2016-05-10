package com.example;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.controller.MDCLoggingFilter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean mdcLoggingFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new MDCLoggingFilter());
        bean.setOrder(1);
        return bean;
    }
}
