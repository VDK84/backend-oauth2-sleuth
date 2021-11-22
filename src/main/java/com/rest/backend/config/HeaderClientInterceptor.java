package com.rest.backend.config;

import static com.rest.backend.config.HttpServletRequestUtils.HEADER_AUTHORIZATION;
import static com.rest.backend.config.HttpServletRequestUtils.getAuthorizationHeader;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

public class HeaderClientInterceptor implements ClientHttpRequestInterceptor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
                if(StringUtils.hasText(getAuthorizationHeader())){
                    HttpHeaders headers = request.getHeaders();
                    log.info(HEADER_AUTHORIZATION+" Header added to the request.");
                    headers.add(HEADER_AUTHORIZATION, getAuthorizationHeader());
                }
        return execution.execute(request, body);
    }
}
