package com.company.isf.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
@EnableAsync
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll()

//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/vacancy")
//                        .failureUrl("/hr/login?error=true")
//                        .permitAll()
                );
        return httpSecurity.build();
    }
}
