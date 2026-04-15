package com.example.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(csrf -> csrf.disable())

                // 🔥 disable default login popup
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                .authorizeExchange(exchange -> exchange

                        // ✅ VERY IMPORTANT (fixes CORS preflight)
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()

                        // ✅ allow swagger + auth endpoints
                        .pathMatchers(
                                "/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/users/v3/api-docs",
                                "/auth/v3/api-docs"
                        ).permitAll()

                        // 🔒 everything else secured
                        .anyExchange().authenticated()
                )
                .build();
    }
}