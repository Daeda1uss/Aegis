package com.aegis.auth.configuration;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecuritySupportConfiguration {
    public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UsernamePasswordTokenAuthenticationProvider provider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Lists.newArrayList(provider));
    }

    @Bean
    public JdbcUserDetailsManager userDetailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource) {{
            setPasswordEncoder(encoder);
        }};
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}
