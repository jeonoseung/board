package com.project.board.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf((csrf)->csrf.disable())
                .formLogin((formLogin)->formLogin.disable())
                .httpBasic((httpBasic)->httpBasic.disable());
        http.sessionManagement((sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)));
        return http.build();
    }
}