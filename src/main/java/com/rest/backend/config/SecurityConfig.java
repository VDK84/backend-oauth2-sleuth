package com.rest.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/a/**").permitAll()
          .antMatchers("/c/**").hasIpAddress("127.0.0.1")
          .antMatchers("/c/**").hasIpAddress("localhost")
          .anyRequest().authenticated()
          .and()
          .csrf().disable();
    }
}