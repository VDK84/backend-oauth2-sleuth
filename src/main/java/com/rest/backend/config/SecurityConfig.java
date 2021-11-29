package com.rest.backend.config;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/a/**").permitAll()
          .antMatchers("/c/**").hasIpAddress("127.0.0.1")
          .antMatchers("/c/**").hasIpAddress(getServerName())
          .antMatchers("/c/**").hasIpAddress("localhost")
          .anyRequest().authenticated()
          .and()
          .csrf().disable();
    }


    private String getServerName(){
        String jenkinsServer = "http://www.google.com/";
        String serverIP = null;
        if(StringUtils.hasText(jenkinsServer)) {
          try {
            jenkinsServer = new URI(jenkinsServer).getHost();
          } catch (URISyntaxException e1) {
            return null;
          }

          try {
            serverIP = InetAddress.getByName(jenkinsServer).getHostAddress();
          } catch (UnknownHostException e) {
          }
        }
        return serverIP;
      }
}