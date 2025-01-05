package com.openwebinars.rest.security;

import ch.qos.logback.core.joran.spi.HttpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/product/**").permitAll()//.authenticated()
                        .anyRequest().permitAll()
                );


        http.csrf((csrf) -> {
            csrf.disable();
        });
        http.headers((headers) ->
                headers.frameOptions((opts) -> opts.disable()));
        return http.build();
    }

}

