package com.memento.practice.api.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.memento.practice.api.models.User;
import com.memento.practice.api.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//for intercepting incoming http requests and validating the jwt in the request header
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtGenerator jwtGenerator;
    private UserRepository userRepository;

    public JwtAuthenticationFilter(JwtGenerator jwtGenerator, UserRepository userRepository) {
        this.jwtGenerator = jwtGenerator;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                    @NonNull HttpServletResponse response, 
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = getJWTFromRequest(request);

        if (token != null) {
            try {
                //verifies signature using secret key (ensures not  expires, correct structure, and not tampered with)
                //otherwise throws exception
                Claims claims = jwtGenerator.validateToken(token);

                //extracts the unique identifier (the email in this case)
                String email = claims.getSubject();

                User user = userRepository.findByEmail(email)
                    .orElseThrow();

                //creating spring security auth object 
                //principal -> user, 
                //credentials -> null(password not needed) 
                //authorities -> empty because no roles are made in this system)
                UsernamePasswordAuthenticationToken authentication =  
                    new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        Collections.emptyList()
                    );

                //this makes authentication global for this request
                //(controllers can access user, request treated as logged in, security rules are applied)
                SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
                
            } catch (Exception e) {
                //clear the context if any failure is caught
                SecurityContextHolder.clearContext();
            }
        }

        //once everything is checked, the request is passed forward to controllers, other potential filters, or exception handlers
        //authenticated -> request has user and the rest of the application treats it that way
        //not authenticated -> request is anonymous
        filterChain.doFilter(request, response);
    }

    //get token from request cookies
    private String getJWTFromRequest(HttpServletRequest request) {

        if (request.getCookies() == null) return null;

        //go through the cookies and retrieve the access token
        for (Cookie cookie : request.getCookies()) {
            if ("accessToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }
    
}
