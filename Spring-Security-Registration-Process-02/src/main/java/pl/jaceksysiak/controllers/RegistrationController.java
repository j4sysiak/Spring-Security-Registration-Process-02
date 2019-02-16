package pl.jaceksysiak.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.jaceksysiak.model.User;
import pl.jaceksysiak.registration.OnRegistrationCompleteEvent;
import pl.jaceksysiak.service.IUserService;
import pl.jaceksysiak.web.dto.UserDto;
import pl.jaceksysiak.web.util.GenericResponse;



@Controller
public class RegistrationController {
	
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    public RegistrationController() {
        super();
    }
	
    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request) {
        //LOGGER.debug("Registering user account with information: {}", accountDto);

        final User registered = userService.registerNewUserAccount(accountDto);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }

 
  
//	@RequestMapping(value = "/user/registration2", method = RequestMethod.POST)
//	@ResponseBody
//	public ModelAndView registerUserAccount( @ModelAttribute("user") @Valid UserDto accountDto, BindingResult result, WebRequest request, Errors errors) {
//		
//		User registered = new User();
//		if (!result.hasErrors()) {
//			registered = createUserAccount(accountDto, result);
//		}
//		
//		if (registered == null) {
//					result.rejectValue("email", "message.regError");
//		}
//		if (result.hasErrors()) {
//			return new ModelAndView("registration", "user", accountDto);
//			} 
//				else {
//						return new ModelAndView("successRegister", "user", accountDto);
//					 }
//     }
	
//			private User createUserAccount(UserDto accountDto, BindingResult result) {
//				
//				User registered = null;
//				try {
//					registered = userService.registerNewUserAccount(accountDto);
//				} catch (EmailExistsException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//					return registered;
//				}
	
//	@RequestMapping(value = "/user/registration", method = RequestMethod.POST)
//	@ResponseBody
//	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto, BindingResult result, WebRequest request, Errors errors) { 
//		
//	    User registered = new User();
//	    if (!result.hasErrors()) {
//	        registered = createUserAccount(accountDto, result);
//	    }
//	    if (registered == null) {
//	        result.rejectValue("email", "message.regError");
//	    }
//	    return new ModelAndView("success");
//	}
//	
//	private User createUserAccount(UserDto accountDto, BindingResult result) {
//	    User registered = null;
// 
//	        registered = userService.registerNewUserAccount(accountDto);
//	       
//	    return registered;
//	}
			
			
		    private String getAppUrl(HttpServletRequest request) {
		        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		    }

}
