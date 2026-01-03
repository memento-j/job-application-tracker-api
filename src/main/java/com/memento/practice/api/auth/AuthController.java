package com.memento.practice.api.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memento.practice.api.auth.dto.SignupRequest;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/me")
    public String getMe(@RequestParam String param) {
        return new String();
    }
    
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest request) {
        authService.signupUser(request);
        //build finalizes response, response entity is http response wrapper in spring (201 staatus creeted)
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public String login(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PostMapping("/logout")
    public String logout(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    
    


}
