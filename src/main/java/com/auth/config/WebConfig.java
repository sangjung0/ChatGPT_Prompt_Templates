package com.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

import com.auth.Constants;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry
                .addMapping("/api/auth/**")
                .allowedOrigins("*")
                .allowedMethods("POST");
        registry
                .addMapping("/api/verify/**")
                .allowedOrigins("*")
                .allowedMethods("POST");
        registry
                .addMapping("/")
                .allowedOrigins("*")
                .allowedMethods("GET");
        registry
                .addMapping("/index.html")
                .allowedOrigins("*")
                .allowedMethods("GET");
        registry
                .addMapping("/**")
                .exposedHeaders(Constants.ACCESS_TOKEN);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver(){
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        if (resourcePath.startsWith("/api/")) return null;

                        Resource requestedResource = location.createRelative(resourcePath);
                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : new ClassPathResource("/static/index.html");
                    }
                });
    }
}