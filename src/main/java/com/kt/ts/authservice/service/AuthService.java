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
        // In a real scenario, validate against a user store or database
        if ("admin".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            return getAuthResponse(request.getUsername(), roles);
        } else if ("agent".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            return getAuthResponse(request.getUsername(), roles);
        } else if ("customer".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            return getAuthResponse(request.getUsername(), roles);
        }
       /* if ("user".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            String token= jwtUtil.generateToken(request.getUsername(),roles);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(token);
            return authResponse;
        }*/
        throw new RuntimeException("Invalid credentials");
    }

    private AuthResponse getAuthResponse(String userName, List<SimpleGrantedAuthority> roles) {
        String token = jwtUtil.generateToken(userName, roles);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        return authResponse;
    }

    // Authenticate user and generate token
    /*@PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // In a real scenario, validate against a user store or database
        if ("admin".equals(username) && "adminpassword".equals(password)) {
            return jwtUtil.generateToken(username, Arrays.asList("ROLE_ADMIN"));
        } else if ("agent".equals(username) && "agentpassword".equals(password)) {
            return jwtUtil.generateToken(username, Arrays.asList("ROLE_AGENT"));
        } else if ("customer".equals(username) && "customerpassword".equals(password)) {
            return jwtUtil.generateToken(username, Arrays.asList("ROLE_CUSTOMER"));
        }
        throw new RuntimeException("Invalid credentials");
    }*/

    public AuthResponse refreshToken(String oldToken) {
        String username = jwtUtil.extractUsername(oldToken);
        List<SimpleGrantedAuthority> roles = jwtUtil.extractRoles(oldToken);
        AuthResponse authResponse = new AuthResponse();
        String refreshToken = jwtUtil.generateToken(username, roles);
        authResponse.setToken(refreshToken);
        return authResponse;
    }

    public boolean isTokenExpired(String oldToken) {
        return jwtUtil.isTokenExpired(oldToken);
    }
}
