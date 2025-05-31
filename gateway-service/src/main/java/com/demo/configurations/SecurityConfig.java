package com.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                                .pathMatchers("/user/login", "/user/register", "/user/findByEmail/**", "/user/findById/**", "/python/**", "/job/findAll"
                                        , "/job/category/**", "/job/experience/**", "/job/findAllPagination/**", "/job/findById/**", "/job/findByEmployerIdPagination/**"
                                        , "/job/worktype/**", "/job/reviews/**", "/job/location/findAll", "/job/searchJobs", "/notification/**"
                                        , "/application/auth-url/**", "/application/check-auth/**", "/application/oauth-callback/**", "/application/create-event/**"
                                        , "/application/save-event/**", "/application/get-saved-event/**", "/user-static/**", "/assets/**", "/user/employer/get-large-companies/**"
                                        , "/user-static/assets/**", "/ws-chat/**", "/job-static/**"
                                        , "/user/chat/**").permitAll()
//                        .pathMatchers(HttpMethod.PUT, "/user/update").permitAll()

                                .anyExchange().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHORIZATION)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Sử dụng CorsWebFilter
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4201", "http://localhost:4202"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        System.out.println("CORS Configuration Applied: " + configuration.getAllowedOrigins());
        return source;
    }

    @Bean
    public CorsWebFilter corsWebFilter(CorsConfigurationSource corsConfigurationSource) {
        return new CorsWebFilter(corsConfigurationSource);
    }
}