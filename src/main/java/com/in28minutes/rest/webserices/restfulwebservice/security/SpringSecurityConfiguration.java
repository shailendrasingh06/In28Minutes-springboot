package com.in28minutes.rest.webserices.restfulwebservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        //1. All req. must be authenticated

        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());

        //2. If a request is bot authenticated, then deny access
        httpSecurity.httpBasic(Customizer.withDefaults());

        //disable CSRF

        httpSecurity.csrf(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
