package org.personal.projectjot.config.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthService {

    private static final String AUTH_TOKEN_HEADER_NAME = "KEY";
    private static final String AUTH_TOKEN = "&h3shDjqAo7FyG7BMW@QyUF9F8JyW@QZVQmjLd";

    public static Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new KeyAuth(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
