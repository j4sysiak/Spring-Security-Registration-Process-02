package com.caveofprogramming.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.model.User;
import com.caveofprogramming.service.IUserService;
import com.caveofprogramming.web.dto.UserDto;

@Controller
public class RegistrationController {
	
    @Autowired
    private IUserService userService;
    
    public RegistrationController() {
        super();
    }
	
    // API
    
	@RequestMapping(value = "/user/registration", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
	    UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "registration";
	}
 
	
	// Registration
	
	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto, BindingResult result, WebRequest request, Errors errors) { 
		
	    User registered = new User();
	    if (!result.hasErrors()) {
	        registered = createUserAccount(accountDto, result);
	    }
	    if (registered == null) {
	        result.rejectValue("email", "message.regError");
	    }
	    return new ModelAndView("success");
	}
	
	private User createUserAccount(UserDto accountDto, BindingResult result) {
	    User registered = null;
 
	        registered = userService.registerNewUserAccount(accountDto);
	       
	    return registered;
	}

}
