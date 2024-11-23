/*
package com.kt.ts.authservice.util;

import com.kt.ts.cs.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        */
/*String token = request.getHeader("Authorization");
        System.out.println("token --->>> " + token);
        if (token != null && token.startsWith("Bearer ")) {
            System.out.println("token --->>> 222 " + token);
//            token = token.substring(7); // Remove "Bearer " prefix
            token = token.split(" ")[1].trim();*//*

        String token = extractToken(request);
        if (token != null && jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var authentication = new UsernamePasswordAuthenticationToken(username, null, jwtUtil.extractRoles(token));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            */
/*String[] roles = jwtUtil.extractRoles(token);  // Extract roles from the token

//            if (username != null && jwtUtil.validateToken(token, username)) {
//            if (token != null && jwtUtil.validateToken(token)) {
                // Create authentication token and set it in SecurityContext
                *//*
*/
/*UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>()
                );*//*
*/
/*
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, jwtUtil.getAuthoritiesFromRoles(roles)
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);*//*

            }
//        }

        filterChain.doFilter(request, response);  // Proceed to the next filter in the chain
    }
    // Extract token from Authorization header
    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
//            return header.substring(7);  // Remove "Bearer " prefix
            return header.split(" ")[1].trim();
        }
        return null;
    }
}
*/
