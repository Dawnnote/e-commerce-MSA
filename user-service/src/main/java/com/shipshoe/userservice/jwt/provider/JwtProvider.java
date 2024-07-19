package com.shipshoe.userservice.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${spring.jwt.secret-key}")
    private String secretKey;

    public String create(Long userId, String role) {

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(String.valueOf(userId))
                .setClaims(claims)
                .setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();
    }

    public Claims validate (String jwt) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

        } catch (Exception exception){
            exception.getStackTrace();
            return null;
        }
    }
}
