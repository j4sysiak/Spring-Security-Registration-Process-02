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
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("forward:/home");
//    	registry.addViewController("/").setViewName("forward:/login");
//    	registry.addViewController("/").setViewName("home");     oryginalnie tak było w https://github.com/Baeldung/spring-security-registration
    	registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registration.html");
        
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/greeting").setViewName("greeting");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index2").setViewName("index2");
        registry.addViewController("/info").setViewName("info");
    }
    
 

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/");
    }

 

}
