package org.example.jwtsecurity.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf()
//                .cors()
                .authorizeHttpRequests(
auth ->
                     auth
                             .requestMatchers("/api/**", "/v3/api-docs/**", "/swagger-ui/**")
                                .permitAll()
                             .anyRequest()
                                .authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
        ;
        return http.build();
    }
}
