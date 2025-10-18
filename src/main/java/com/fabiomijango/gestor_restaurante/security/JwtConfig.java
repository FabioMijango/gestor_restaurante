package com.fabiomijango.gestor_restaurante.security;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;

public final class JwtConfig {
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";

    public static String getSubjectFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1];
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload().get("sub").toString();
    }
}
