package com.jh.tds.as.service;

import com.jh.tds.as.exception.InvalidCredentialsException;
import com.jh.tds.as.exception.UserNotFoundException;
import com.jh.tds.as.model.AuthRequest;
import com.jh.tds.as.model.AuthResponse;
import com.jh.tds.as.model.User;
import com.jh.tds.as.repository.UserRepository;
import com.jh.tds.as.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    public AuthService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse authenticateUser(AuthRequest request, List<SimpleGrantedAuthority> roles) {
        System.out.println("Inside authenticateUser, roles-->> " + roles);
        User details = getUserDetailsByUserName(request.getUsername());
        if (details.getUserName().equals(request.getUsername()) && details.getPassword().equals(request.getPassword())) {
            System.out.println("Inside authenticateUser, generic userDetails-->> " + request.getUsername());
            return getAuthResponse(request.getUsername(), roles);
        }
        throw new InvalidCredentialsException("Invalid credentials");
    }

    private AuthResponse getAuthResponse(String userName, List<SimpleGrantedAuthority> roles) {
        System.out.println("Inside getAuthResponse, userName-->> " + userName);
        String token = jwtUtil.generateToken(userName, roles);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        return authResponse;
    }

    public AuthResponse refreshToken(String oldToken) {
        String username = jwtUtil.extractUsername(oldToken);
        List<SimpleGrantedAuthority> roles = jwtUtil.extractRoles(oldToken);
        AuthResponse authResponse = new AuthResponse();
        String refreshToken = jwtUtil.generateToken(username, roles);
        authResponse.setToken(refreshToken);
        return authResponse;
    }

    public boolean isTokenExpired(String token) {
        System.out.println("in service of isTokenExpired method..");
        return jwtUtil.isTokenExpired(token);
    }

    public boolean isTokenValid(String token) {
        try {
            jwtUtil.validateToken(token); // Assuming validateToken throws an exception if invalid
            return true;
        } catch (Exception e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    private User getUserDetailsByUserName(String userName) {
        User userDetails = userRepository.findByUserName(userName);
        System.out.println("userDetails : " + userDetails);
        if (userDetails == null) {
            // If the userDetails doesn't exist, throw an exception
            throw new UserNotFoundException("User " + userName + " is not found.");
        }
        return userDetails;
    }

}
