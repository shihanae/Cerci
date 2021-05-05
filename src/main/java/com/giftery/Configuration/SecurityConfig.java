package com.giftery.Configuration;

import com.giftery.Component.CustomAuthenticationProvider;
import com.giftery.Component.UserAuthenticationSuccessHandler;
import com.giftery.Service.User.UserDetailsServiceImpl;
import com.giftery.Service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http.authorizeRequests()
                .antMatchers("/home*").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/product-lookup*").permitAll()
                .antMatchers("/error*").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login.html?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
        .and()
                .csrf().disable()
                .headers().frameOptions().disable();
               //.logoutSuccessHandler(logoutSuccessHandler());
    }


    //authenticationProvider bean definition
    /*@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }*/

    @Bean
    public AuthenticationManager customAuthenticationManger() throws Exception
    {
        return authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authProvider);
    }

}