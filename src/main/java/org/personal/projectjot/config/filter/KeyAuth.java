package org.personal.projectjot.config.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class KeyAuth extends AbstractAuthenticationToken {
    private final String authenticationKey;
    public KeyAuth(String apiKey, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.authenticationKey = apiKey;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return authenticationKey;
    }
}