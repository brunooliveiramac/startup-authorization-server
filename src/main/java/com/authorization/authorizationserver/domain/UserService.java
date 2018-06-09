package com.authorization.authorizationserver.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDetails findByUsername(String username) {
        if (StringUtils.isNotEmpty(username)) {
            User user = repository.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            return user;
        }
        throw new RuntimeException("User not found");
    }
}
