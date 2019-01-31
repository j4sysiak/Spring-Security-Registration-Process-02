package com.caveofprogramming.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	
//    @Autowired
//    private UserDetailsService userDetailsService;
//    
//	@Autowired
//	private PasswordEncoder passwordEncoder;
    
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/", 
                		     "/home", 
                		     "/login*", 
                		     "/logout*",
                		     "/user/registration*",
                		     "/registration*").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
        // @formatter:on
    }

 
	
// 1 sposób
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
    	
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("aa")  // username
                .password("aa")     // password
                .roles("USER")      // role
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    
// 2 sposób
//	@Autowired
//  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		// @formatter:off
//
//	   auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//	   	.withUser("aa").password("aa").roles("USER").and()
//	   	.withUser("bb").password("bb").roles("ADMIN");
//	    
//	    // @formatter:on
//  }
	
// 3 sposób
//	@Override
//	//@Autowired
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		  
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//		System.out.println("auth: " + auth);
//	}
    
    
    
    /*
 The method withDefaultPasswordEncoder() from the type User is deprecated
 What you should be doing instead of using your current userDetailsService() method is the following:

private static final String ENCODED_PASSWORD = "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2";

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder())
        .withUser("user").password(ENCODED_PASSWORD).roles("USER");
}
 
     */
}
