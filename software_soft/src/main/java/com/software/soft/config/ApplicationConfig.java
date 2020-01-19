package com.software.soft.config;

import com.software.soft.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //拦截器注册  登录不拦截
        registry.addInterceptor(jwtFilter).addPathPatterns("/**").excludePathPatterns("/**/login");
    }
}
