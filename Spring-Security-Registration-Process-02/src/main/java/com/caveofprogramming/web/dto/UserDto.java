package com.caveofprogramming.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.caveofprogramming.validation.PasswordMatches;
import com.caveofprogramming.validation.ValidEmail;
import com.caveofprogramming.validation.ValidPassword;

@PasswordMatches
public class UserDto {
	
    @NotNull
    @NotEmpty
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;
     
    @NotNull
    @NotEmpty
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;
     
    @ValidPassword
    @NotEmpty
    private String password;
    
    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String matchingPassword;
     
    @ValidEmail
    @NotNull
    @NotEmpty
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;
    
    
    
    // standard getters and setters

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDto [firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", matchingPassword="
				+ matchingPassword + ", email=" + email + "]";
	}
     
   
    
}
