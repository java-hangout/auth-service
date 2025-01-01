package com.jh.tds.as.controller;

import com.jh.tds.as.model.AuthRequest;
import com.jh.tds.as.model.AuthResponse;
import com.jh.tds.as.service.AuthService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        System.out.println("Inside login-->> " + request);
        List<SimpleGrantedAuthority> list = Collections.singletonList(new SimpleGrantedAuthority(request.getRole().toUpperCase()));
        return authService.authenticateUser(request,list);
    }

    // Logout by clearing the Security Context
    @PostMapping("/logout")
    public String logout() {
        System.out.println("Inside logout method");
        SecurityContextHolder.clearContext(); // Clear the authentication context
        return "Logged out successfully";
    }

    @PostMapping("/isTokenExpired")
    public boolean isTokenExpired(@RequestHeader("Authorization") String token) {
        try {
            System.out.println("Original token: " + token);
            // Remove "Bearer " prefix and trim whitespace
            token = token.replace("Bearer ", "").trim();
            System.out.println("Processed token: " + token);

            return authService.isTokenExpired(token);
        } catch (Exception e) {
            System.err.println("Error in isTokenExpired: " + e.getMessage());
            return true; // Treat as expired if an error occurs
        }
    }


    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestHeader("Authorization") String oldToken) {
        try {
            System.out.println("Original token: " + oldToken);
            // Remove "Bearer " prefix and trim whitespace
            oldToken = oldToken.replace("Bearer ", "").trim();
            System.out.println("Processed token: " + oldToken);

            return authService.refreshToken(oldToken);
        } catch (Exception e) {
            System.err.println("Error in refresh: " + e.getMessage());
            throw new RuntimeException("Error refreshing token", e);
        }
    }

    @PostMapping("/verify")
    public boolean verifyToken(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "").trim();
        return authService.isTokenValid(token);
    }


}
