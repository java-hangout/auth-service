package com.kt.ts.authservice.controller;

import com.kt.ts.authservice.model.AuthRequest;
import com.kt.ts.authservice.model.AuthResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final com.kt.ts.authservice.service.AuthService authService;

    public AuthController(com.kt.ts.authservice.service.AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.authenticateUser(request);
    }
    @GetMapping("/prelogin")
    public String login() {
        return "Auth service is update and running ....";
    }
}
