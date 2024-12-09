package com.kt.ts.authservice.controller;

import com.kt.ts.authservice.model.AuthRequest;
import com.kt.ts.authservice.model.AuthResponse;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final com.kt.ts.authservice.service.AuthService authService;

    public AuthController(com.kt.ts.authservice.service.AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
//        List<String> roles = List.of("ROLE_USER");
//        return authService.authenticateUser(request,roles);
//        List<SimpleGrantedAuthority>  list= Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        List<SimpleGrantedAuthority>  list= Collections.singletonList(new SimpleGrantedAuthority(request.getRole()));
        return authService.authenticateUser(request,list);
    }

    // Check whether JWT Token  is expired
    @PostMapping("/isTokenExpired")
    public boolean isTokenExpired(@RequestHeader("Authorization") String oldToken) {
        return authService.isTokenExpired(oldToken);
    }

    // Refresh JWT Token (you can extend this logic to refresh tokens)
    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestHeader("Authorization") String oldToken) {
        return authService.refreshToken(oldToken);
    }



    @GetMapping("/prelogin")
    public String login() {
        return "Auth service is update and running ....";
    }
}
