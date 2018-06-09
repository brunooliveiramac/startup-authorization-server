package com.authorization.authorizationserver.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorizationDetailsService implements UserDetailsService{

    private final UserService service;

    @Autowired
    public UserAuthorizationDetailsService(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return service.findByUsername(username);
    }
}
