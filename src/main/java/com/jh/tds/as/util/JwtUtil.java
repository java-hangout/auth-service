package com.jh.tds.as.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    //    @Value("${jwt.secret}")
    private final String SECRET_KEY = "sh2+3JRuzIaVMCGxBPeDMSzUFwDBLscv4R77LYntGns=";
    private final Key secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));

    // Generate a JWT token with the given username
    public String generateToken(String username, List<SimpleGrantedAuthority> authorities) {
        System.out.println("generateToken --->>> " + username);
        System.out.println("secretKey --->>> " + secretKey);
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", authorities.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList()));
        // 1 hour in milliseconds
        long expirationTime = 1000 * 60 * 60;

        return Jwts.builder().setClaims(claims)
//                .setSubject(username)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expirationTime)).signWith(secretKey)  // Use the generated secure key to sign the token
                .compact();
    }

    public boolean isTokenExpired(String oldToken) {
        try {
            System.out.println("Received Token: " + oldToken);  // Log the token to verify format
            oldToken = oldToken.trim();
            Claims claims = extractClaims(oldToken);
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error extracting claims or checking expiration", e);
        }
    }


    public String extractUsername(String token) {
        try {
            System.out.println("Extracting username from token: " + token);
            Claims claims = extractClaims(token);
            String username = claims.getSubject();
            System.out.println("Extracted username: " + username);
            return username; // Return username (subject)
        } catch (Exception e) {
            System.err.println("Error while extracting username: " + e.getMessage());
            throw new RuntimeException("Error extracting username", e);
        }
    }


    public List<SimpleGrantedAuthority> extractRoles(String token) {
        try {
            Claims claims = extractClaims(token);
            List<String> roles = (List<String>) claims.get("roles");
            return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error extracting roles", e);
        }
    }


    public List<SimpleGrantedAuthority> getAuthoritiesFromRoles(String[] roles) {
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims extractClaims(String token) {
        try {
            System.out.println("Extracting claims from token: " + token);
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.err.println("Error parsing token: " + token + " | Error: " + e.getMessage());
            throw new RuntimeException("Error extracting claims from token", e);
        }
    }

}
