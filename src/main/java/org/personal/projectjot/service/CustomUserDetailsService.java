package org.personal.projectjot.service;

import org.personal.projectjot.CustomUserRepository;
import org.personal.projectjot.models.CustomUser;
import org.personal.projectjot.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomUser> customUserOptional = customUserRepository.findCustomUserByUsername(username);

        customUserOptional.orElseThrow(() -> new UsernameNotFoundException("User with name :" + username + " was not found"));

        return customUserOptional.map(CustomUserDetails::new).get();
    }
}
