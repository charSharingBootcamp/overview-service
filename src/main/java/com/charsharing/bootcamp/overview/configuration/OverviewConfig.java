package com.charsharing.bootcamp.overview.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

//@Configuration --> Auskommentiert da nicht funktional
    public class OverviewConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("/static/", "/public/")
                    .setCachePeriod(0)
                    .resourceChain(true)
                    .addResolver(new PathResourceResolver());
        }
    }

