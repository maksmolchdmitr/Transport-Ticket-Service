package maks.molch.dmitr.core.config.security;

import lombok.AllArgsConstructor;
import maks.molch.dmitr.core.service.auth.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static maks.molch.dmitr.core.service.entity.User.Role.ADMIN;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    public static final String[] AUTH_WHITELIST = {
            "/user/register",
            "/auth/login",
            "/auth/refresh-token",
            // Swagger
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/webjars/**",
            "/webjars/swagger-ui/index.html",
            "/swagger-ui.html",
    };

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http, JwtAuthorizationFilter jwtAuthorizationFilter) {
        return http
                .addFilterBefore(jwtAuthorizationFilter, SecurityWebFiltersOrder.AUTHORIZATION)
                .authorizeExchange(auth -> auth
                        .pathMatchers(AUTH_WHITELIST).permitAll()
                        .pathMatchers(POST, "/ticket", "/route", "/carrier").hasRole(ADMIN.name())
                        .pathMatchers(PUT, "/ticket", "/route", "/carrier").hasRole(ADMIN.name())
                        .pathMatchers(DELETE, "/ticket/**", "/route/**", "/carrier/**").hasRole(ADMIN.name())
                        .anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
