package com.fabiomijango.gestor_restaurante.security.filter;

import com.fabiomijango.gestor_restaurante.util.GenericResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.fabiomijango.gestor_restaurante.security.JwtConfig.*;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader(HEADER_AUTHORIZATION);
        if(header == null || !header.startsWith(PREFIX_TOKEN)){
            chain.doFilter(request, response);
            return;
        }
        String token = header.replace(PREFIX_TOKEN, "");

        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build()
                    .parseSignedClaims(token).getPayload();

            String username = claims.getSubject();

            Object authoritiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = List.of(
                    new SimpleGrantedAuthority(authoritiesClaims.toString())
            );

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        } catch (Exception e){

            String msgError;
            if(e.getMessage().contains("Malformed protected header JSON")){
                msgError = "Malformed token, please provide a valid token";
            }else if (e.getMessage().contains("JWT signature does not match")){
                msgError = "Invalid token";
            } else if (e.getMessage().contains("JWT expired")) {
                msgError = "Token has expired";
            } else {
                msgError = e.getMessage();
            }

            GenericResponse.ErrorResponse errorResponse = GenericResponse.ErrorResponse.builder()
                    .path(request.getServletPath())
                    .method(request.getMethod())
                    .errors(
                            Map.of(
                                    "error", msgError
                            )
                    )
                    .build();

            GenericResponse genericResponse = GenericResponse.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .data(errorResponse)
                    .message("Unauthorized")
                    .build();

            response.getWriter().write(new ObjectMapper().writeValueAsString(genericResponse));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(CONTENT_TYPE);
        }
    }
}
