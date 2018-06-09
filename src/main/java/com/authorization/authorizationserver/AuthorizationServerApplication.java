package com.authorization.authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Primary
    public ClientDetailsService getClientDetailsService() throws Exception {
        ClientDetailsServiceBuilder builder = new ClientDetailsServiceBuilder().inMemory();
        builder
                .withClient("startup")
                .secret("{noop}startup")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password", "client_credentials")
                .resourceIds("oauth2-resource")
                .scopes("read")
                .autoApprove(true);
        return builder.build();
    }


    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
