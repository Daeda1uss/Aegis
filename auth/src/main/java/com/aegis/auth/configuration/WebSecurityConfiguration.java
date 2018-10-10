package com.aegis.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

/**
 * Created by GarryGe on 2018/3/27.
 */
@EnableWebSecurity
@Configuration
@Order(99)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JdbcUserDetailsManager userDetailsManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .parentAuthenticationManager(manager)
                .userDetailsService(userDetailsManager)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .and()
                .formLogin()
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .and()
                .rememberMe().alwaysRemember(true)
                .and()
                .httpBasic();
    }

}
