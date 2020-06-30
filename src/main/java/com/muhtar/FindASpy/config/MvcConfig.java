package com.muhtar.FindASpy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("rooms");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/rooms").setViewName("rooms");
        registry.addViewController("/rooms/**").setViewName("room");
        registry.addViewController("/registration").setViewName("registration");
    }
}
