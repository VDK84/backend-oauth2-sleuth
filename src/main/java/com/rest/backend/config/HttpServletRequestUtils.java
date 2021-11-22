package com.rest.backend.config;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpServletRequestUtils {
    private HttpServletRequestUtils() {
	}
	
	/** The Constant HEADER_AUTHORIZATION. */
	public static final String HEADER_AUTHORIZATION = "Authorization";

	/**
	 * Gets the current {@link HttpServletRequest}.
	 *
	 * @return the current {@link HttpServletRequest} or empty {@link Optional} if
	 *         not present.
	 */
	public static Optional<HttpServletRequest> getCurrentRequest() {
		return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
				.map(ServletRequestAttributes.class::cast).map(ServletRequestAttributes::getRequest);
	}

	/**
	 * Gets the Authorization header from the current {@link HttpServletRequest}.
	 *
	 * @return the Authorization header or empty String if not present.
	 */
	public static String getAuthorizationHeader() {
		return getCurrentRequest().map(request -> request.getHeader(HEADER_AUTHORIZATION)).orElse("");
	}  
}
