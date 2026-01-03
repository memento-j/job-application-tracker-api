package com.memento.practice.api.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.memento.practice.api.auth.dto.SignupRequest;
import com.memento.practice.api.exceptions.UserAlreadyExistsException;
import com.memento.practice.api.user.User;
import com.memento.practice.api.user.UserRepository;

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

    public void signupUser(SignupRequest request) {
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
    }

    //return jwt on user login
    public String loginUser() {

        return null;
    }

}
