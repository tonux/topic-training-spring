package com.workshop.testapp.config;

import com.workshop.testapp.config.jwt.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


   private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter, AuthenticationProvider authenticationProvider) {
        super();
        this.authenticationProvider = authenticationProvider;
        this.jwtFilter = jwtFilter;

        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(getAuthenticationEntryPoint())
                .and()
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers( "/api/auth/**","/api-docs", "/api-docs/**", "/swagger.html", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}
