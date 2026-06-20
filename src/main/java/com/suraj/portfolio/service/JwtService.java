package com.suraj.portfolio.service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final Key key;

    public JwtService(
            @Value("${jwt.secret}") String secret
    ) {

        this.key =
                Keys.hmacShaKeyFor(
                        secret.getBytes()
                );
    }

    public String generateToken(
            String username
    ) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(
                        new Date()
                )
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000L * 60 * 60 * 24
                        )
                )
                .signWith(key)
                .compact();
    }

    public String extractUsername(
            String token
    ) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(
            String token
    ) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}