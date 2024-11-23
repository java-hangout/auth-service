/*
package com.kt.ts.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtilTest {

//    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static String SECRET_KEY = "sh2+3JRuzIaVMCGxBPeDMSzUFwDBLscv4R77LYntGns=";
    private static Key secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(secretKey)
                .compact();  // Generates and returns the JWT token
    }

    public static void main(String[] args) {
        String jwt = generateToken("user");
        System.out.println("Generated JWT: " + jwt);
    }
}
*/
