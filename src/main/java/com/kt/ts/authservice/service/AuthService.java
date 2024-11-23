package com.kt.ts.authservice.service;

import com.kt.ts.authservice.model.AuthRequest;
import com.kt.ts.authservice.model.AuthResponse;
import com.kt.ts.commonservice.util.JwtUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse authenticateUser(AuthRequest request, List<SimpleGrantedAuthority> roles) {
        // Authenticate user (for simplicity, assume any user with "user" and "password" is valid)
        if ("user".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            String token= jwtUtil.generateToken(request.getUsername(),roles);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            return authResponse;
        }
        throw new RuntimeException("Invalid credentials");
    }
}
