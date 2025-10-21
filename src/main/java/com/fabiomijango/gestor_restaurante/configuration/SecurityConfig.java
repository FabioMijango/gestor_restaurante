package com.fabiomijango.gestor_restaurante.configuration;

import com.fabiomijango.gestor_restaurante.security.filter.JwtAuthenticationFilter;
import com.fabiomijango.gestor_restaurante.security.filter.JwtValidationFilter;
import com.fabiomijango.gestor_restaurante.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static com.fabiomijango.gestor_restaurante.util.Constants.*;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuth = new JwtAuthenticationFilter(authenticationManager());
        jwtAuth.setFilterProcessesUrl(API_BASE_PATH + USER_CONTROLLER + LOGIN_PATH);

        return http
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers(HttpMethod.POST, API_BASE_PATH + USER_CONTROLLER + LOGIN_PATH).permitAll()
                        .requestMatchers(HttpMethod.GET, API_BASE_PATH + DISH_CONTROLLER).authenticated()
                        .requestMatchers(API_BASE_PATH + USER_CONTROLLER + WILDCARD_PATH).hasAnyRole(Roles.ADMIN.name())
                        .requestMatchers(API_BASE_PATH + DISH_CONTROLLER + WILDCARD_PATH).hasAnyRole(Roles.ADMIN.name(), Roles.CHEF.name())
                        .requestMatchers(API_BASE_PATH + TABLE_CONTROLLER + WILDCARD_PATH).hasAnyRole(Roles.ADMIN.name(), Roles.WAITER.name())
                        .requestMatchers(API_BASE_PATH + ORDER_CONTROLLER + WILDCARD_PATH).authenticated()
                        .anyRequest().authenticated()
                )
                .addFilter(jwtAuth)
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf( config -> config.disable())
                .cors( cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement( management ->
                        management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
