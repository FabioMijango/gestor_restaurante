package com.fabiomijango.gestor_restaurante.security.filter;

import com.fabiomijango.gestor_restaurante.entity.dto.user.UserLoginDTO;
import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.fabiomijango.gestor_restaurante.security.JwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        try {
            UserLoginDTO user = new ObjectMapper().readValue(request.getInputStream(), UserLoginDTO.class);
            if(user.getUsername() == null || user.getPassword() == null){
                throw new IllegalArgumentException("Username and password must be provided");
            }

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    );
            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            String msg;
            if(e.getMessage().contains("User not found")){
                msg = "Bad credentials";
            }else{
                msg = e.getMessage().split("\n")[0];
            }

            GenericResponse.ErrorResponse errorResponse = GenericResponse.ErrorResponse.builder()
                    .path(request.getRequestURI())
                    .method(request.getMethod())
                    .errors(
                            Map.of("error", msg)
                    ).build();
            GenericResponse genericResponse = GenericResponse.builder()
                    .data(errorResponse)
                    .message("Login data is invalid")
                    .build();

            response.getWriter().write(new ObjectMapper().writeValueAsString(genericResponse));
            response.setContentType(CONTENT_TYPE);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request,
                                         HttpServletResponse response,
                                         FilterChain chain,
                                         Authentication authResult)
            throws IOException {

        User user =
                (User) authResult.getPrincipal();

        String username = user.getUsername();
        List<String> roles = user.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .map(auth -> auth.startsWith("ROLE_") ? auth : "ROLE_" + auth)
            .toList();

        Claims claims = Jwts.claims()
                .add("authorities", roles)
                .add("username", username)
                .build();
        String token = Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 8))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        GenericResponse genericResponse = GenericResponse.builder()
                .data(Map.of("token", token))
                .message("User logged in successfully")
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(genericResponse));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException {

        GenericResponse genericResponse = GenericResponse.builder()
                .data(Map.of("error", failed.getMessage()))
                .message("Login data is invalid")
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(genericResponse));
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(CONTENT_TYPE);
    }
}
