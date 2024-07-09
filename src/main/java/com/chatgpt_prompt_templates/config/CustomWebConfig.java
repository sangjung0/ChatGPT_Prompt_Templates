package com.chatgpt_prompt_templates.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry
                .addMapping("/api/template/**")
                .allowedOrigins("*")
                .allowedMethods("POST");
    }
}