package com.caveofprogramming.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

//	  @RequestMapping("/")
//	  String index2() {
//	    return "index2";
//	  }
	  
	  @RequestMapping("/about")
	  String about() {
	    return "about";
	  }
	  
	  @RequestMapping("/info")
	  String info() {
	    return "info";
	  }
    
	  @RequestMapping("/hello")
	  String hello() {
	    return "hello";
	  }
	  
}
