package com.rest.backend.config;

import java.io.IOException;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public class LoggingHeaderFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(LoggingHeaderFilter.class);

    /***
	 * Go to Security/Application security and add the filter.
	 * protected void configure(HttpSecurity http) throws Exception {
    	http.addFilterBefore(new LoggingHeaderFilter(), BasicAuthenticationFilter.class)
    			.cors()
	 */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpHeaders headers = Collections.list(httpRequest.getHeaderNames()).stream()
                .collect(Collectors.toMap(Function.identity(), h -> Collections.list(httpRequest.getHeaders(h)),
                        (oldValue, newValue) -> newValue, HttpHeaders::new));

        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
        chain.doFilter(request, response);
    }
}
