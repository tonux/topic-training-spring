package com.workshop.testapp.services;

import com.workshop.testapp.domain.Role;
import com.workshop.testapp.domain.User;
import com.workshop.testapp.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authentificationManager;

    public AuthService(UserRepository userRepository, AuthenticationManager authentificationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authentificationManager = authentificationManager;
        this.passwordEncoder = passwordEncoder;
    }

    // auth register
    public String register(String username, String password) {
        if(userRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("Username already exist");
        }

        User user = new User();
        user.setRole(Role.USER);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return null;
    }

    // auth login
    public User login(String username, String password) {

        Authentication authentication = authentificationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = (User) authentication.getPrincipal();
        return user;
    }
}
