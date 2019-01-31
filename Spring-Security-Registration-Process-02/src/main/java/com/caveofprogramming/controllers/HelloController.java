package com.caveofprogramming.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping("/rest")
    public String rest() {
        return "Greetings from Spring Boot!";
    }
    
}
