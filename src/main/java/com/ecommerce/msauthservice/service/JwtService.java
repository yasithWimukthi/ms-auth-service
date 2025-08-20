package com.ecommerce.msauthservice.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // In production, load from K8s Secret or environment variable
    private static final String SECRET_KEY = "fjj3j4we0tjh03g0g03gneb0930rj032hg0nr04";
    private static final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> validateToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    public String extractEmail(String token) {
        return validateToken(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return (String) validateToken(token).getBody().get("role");
    }
}