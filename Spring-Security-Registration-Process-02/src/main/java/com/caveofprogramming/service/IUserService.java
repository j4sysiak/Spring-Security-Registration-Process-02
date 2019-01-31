package com.caveofprogramming.service;

import com.caveofprogramming.web.dto.UserDto;
//import org.baeldung.web.error.UserAlreadyExistException;
import com.caveofprogramming.model.User;

public interface IUserService {

	User registerNewUserAccount(UserDto accountDto); // throws UserAlreadyExistException;

}
