package com.portal.smarthealth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/facilities").setViewName("facilities");
        registry.addViewController("/financial").setViewName("financial");
        registry.addViewController("/telemedicine").setViewName("telemedicine");
        registry.addViewController("/crowdfunding").setViewName("crowdfunding");
        registry.addViewController("/records").setViewName("records");
        registry.addViewController("/tips").setViewName("health-tips");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        // Specific handlers for individual images if they are directly linked and not via /images/
        registry.addResourceHandler("/smart health logo.jpeg").addResourceLocations("classpath:/static/images/smart health logo.jpeg");
        registry.addResourceHandler("/healthy lifestyle.jpeg").addResourceLocations("classpath:/static/images/healthy lifestyle.jpeg");
    }
}