package com.caveofprogramming.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
    public MvcConfig() {
        super();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
    	 registry.addViewController("/").setViewName("forward:/login");
    	 registry.addViewController("/login");
    	 registry.addViewController("/registration.html");
    	 registry.addViewController("/logout.html");
    }
    
 

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/");
    }

 

}
