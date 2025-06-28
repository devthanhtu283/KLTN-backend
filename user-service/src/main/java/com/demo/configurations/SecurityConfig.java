package com.demo.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/user/login").permitAll()
                        .requestMatchers("/user/findByEmail/**").permitAll()
                        .requestMatchers("/user/findById/**").permitAll()
                        .requestMatchers("/user-static/**").permitAll()
                        .requestMatchers("/user/findAll/**").permitAll()
                        .requestMatchers("/user/sendEmail").permitAll()
                        .requestMatchers("/user/seeker/findById/**").permitAll()
                        .requestMatchers("/user/verifyAccount/**").permitAll()
                        .requestMatchers("/user/employerMembership/**").permitAll()
                        .requestMatchers("/user/chat/**").permitAll()
                        .requestMatchers("/user/employer/get-large-companies/**").permitAll()
                        .requestMatchers("/user/employer/get-medium-companies/**").permitAll()
                        .requestMatchers("/user/employer/findById/**").permitAll()
                        .requestMatchers("/user/employer/search/**").permitAll()
                        .requestMatchers("/ws-chat/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/user/seeker/**").hasAnyRole("SEEKER", "ADMIN")
                        .requestMatchers("/user/employer/**").hasAnyRole("EMPLOYER", "ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("ADMIN", "SEEKER", "EMPLOYER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
