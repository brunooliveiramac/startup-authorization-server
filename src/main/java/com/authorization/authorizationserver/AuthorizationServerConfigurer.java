package com.authorization.authorizationserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    public AuthorizationServerConfigurer() {
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        if (this.clientDetailsService != null) {
            clients.withClientDetails(this.clientDetailsService);
        } else if (this.dataSource != null) {
            clients.jdbc(this.dataSource);
        }

    }

    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        if (this.tokenStore != null) {
            endpoints.tokenStore(this.tokenStore);
        } else if (this.dataSource != null) {
            TokenStore tokenStore = new JdbcTokenStore(this.dataSource);
            endpoints.tokenStore(tokenStore);
        }

        if (this.authenticationManager != null) {
            endpoints.authenticationManager(this.authenticationManager);
        }

        if (this.userDetailsService != null) {
            endpoints.userDetailsService(this.userDetailsService);
        }

    }

}
