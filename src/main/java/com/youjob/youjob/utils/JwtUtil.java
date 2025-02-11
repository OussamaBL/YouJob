package com.youjob.youjob.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    // Generate the key once and reuse it
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final int jwtExpirationMs = 3600000;

    public String generateToken(String email, String username, String role) {
        HashedMap<String, String> infos = new HashedMap<>();
        infos.put("username", username);
        infos.put("role", role);
        return Jwts.builder()
                .setClaims(infos)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key)  // Use the SecretKey directly
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()  // Use parserBuilder() instead of deprecated parser()
                .setSigningKey(key)  // Use the SecretKey directly
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder()  // Use parserBuilder() instead of deprecated parser()
                    .setSigningKey(key)  // Use the SecretKey directly
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT unsupported: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Invalid signature: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }
}