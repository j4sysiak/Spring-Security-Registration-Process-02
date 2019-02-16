package pl.jaceksysiak.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import pl.jaceksysiak.dao.UserRepository;
import pl.jaceksysiak.security.CustomAuthenticationProvider;
import pl.jaceksysiak.security.CustomWebAuthenticationDetailsSource;


@Configuration
@ComponentScan(basePackages = { "pl.jaceksysiak.security" })
// @ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

//    @Autowired
//    private AuthenticationFailureHandler authenticationFailureHandler;

     @Autowired
     private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

    @Autowired
    private UserRepository userRepository;

    public SecSecurityConfig() {
        super();
    }
    
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
    
	
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
    
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
//            .csrf().disable()
//            .authorizeRequests()
//                .antMatchers("/login*","/login*", "/logout*", "/signin/**", "/signup/**", "/customLogin",
//                        "/user/registration*", "/registrationConfirm*", "/expiredAccount*", "/registration*",
//                        "/badUser*", "/user/resendRegistrationToken*" ,"/forgetPassword*", "/user/resetPassword*",
//                        "/user/changePassword*", "/emailError*", "/resources/**","/old/user/registration*","/successRegister*","/qrcode*").permitAll()
//                .antMatchers("/invalidSession*").anonymous()
//                .antMatchers("/user/updatePassword*","/user/savePassword*","/updatePassword*").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
//                .anyRequest().hasAuthority("READ_PRIVILEGE")
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/homepage.html")
//                .failureUrl("/login?error=true")
//                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(authenticationFailureHandler)
//                .authenticationDetailsSource(authenticationDetailsSource)
//            .permitAll()
//                .and()
//            .sessionManagement()
//                .invalidSessionUrl("/invalidSession.html")
//                .maximumSessions(1).sessionRegistry(sessionRegistry()).and()
//                .sessionFixation().none()
//            .and()
//            .logout()
//                .logoutSuccessHandler(myLogoutSuccessHandler)
//                .invalidateHttpSession(false)
//                .logoutSuccessUrl("/logout.html?logSucc=true")
//                .deleteCookies("JSESSIONID")
//                .permitAll() ;
//    // @formatter:on
//    }
    
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
    
    
    // beans

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

//    @Bean
//    public RememberMeServices rememberMeServices() {
//        CustomRememberMeServices rememberMeServices = new CustomRememberMeServices("theKey", userDetailsService, new InMemoryTokenRepositoryImpl());
//        return rememberMeServices;
//    }
}
