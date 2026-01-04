package com.memento.practice.api.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.memento.practice.api.auth.dto.LoginRequest;
import com.memento.practice.api.auth.dto.SignupRequest;
import com.memento.practice.api.exceptions.InvalidCredentialsException;
import com.memento.practice.api.exceptions.UserAlreadyExistsException;
import com.memento.practice.api.user.User;
import com.memento.practice.api.user.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    //httpservletresponse allows the service to set a cookie
    public void signupUser(SignupRequest request, HttpServletResponse response) {
        //cheeck if user exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(request.getEmail());
        }

        //hash the provideed password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        //now create the user object (entity)
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setHashedPassword(hashedPassword);

        //now save to the db
        userRepository.save(newUser);

        //then finally generate jwt
        String token =  jwtService.generateToken(newUser);
        //create cookie
        setJwtCookie(response, token);
    }

    //return jwt on user login
    public void loginUser(LoginRequest request, HttpServletResponse response) {
        //check if email exists, if not throw exception
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new InvalidCredentialsException());

        //compare provided password with hashed password in db
        if (!passwordEncoder.matches(request.getPassword(), user.getHashedPassword())) {
            throw new InvalidCredentialsException();
        }

        //then finally generate jwt
        String token =  jwtService.generateToken(user);
        //create cookie
        setJwtCookie(response, token);
    }

    public void logout(HttpServletResponse response) {
        //set the access token to empty, and set the max age to 0
        ResponseCookie cookie = ResponseCookie.from("accessToken", "")
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(0)
            .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    //helper function to set jwt cookie to response header
    private void setJwtCookie(HttpServletResponse response, String token) {
        //generates the cookie
        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
            .httpOnly(true)
            //set seecure to true in prod
            .secure(false)
            .path("/")
            .maxAge(60 * 60)
            .sameSite("Strict")
            .build();
        //stores it in response header
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

}
