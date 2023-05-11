package com.workshop.testapp.services;

import com.workshop.testapp.config.jwt.JwtService;
import com.workshop.testapp.domain.Role;
import com.workshop.testapp.domain.User;
import com.workshop.testapp.model.AuthLoginDto;
import com.workshop.testapp.model.AuthRegisterDto;
import com.workshop.testapp.model.AuthResponseDto;
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

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // auth register
    public AuthResponseDto register(AuthRegisterDto authRegisterDto) {
        if(userRepository.findByUsername(authRegisterDto.getUsername()).isPresent()){
            throw new RuntimeException("Username already exist");
        }

        User user = new User();
        user.setRole(Role.USER);
        user.setName(authRegisterDto.getName());
        user.setEmail(authRegisterDto.getEmail());
        user.setPhone(authRegisterDto.getPhone());
        user.setUsername(authRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(authRegisterDto.getPassword()));

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(jwtToken);
        authResponseDto.setUser(user.toUserDTO());
        return authResponseDto;
    }

    // auth login
    public AuthResponseDto login(AuthLoginDto authLoginDto) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authLoginDto.getUsername(), authLoginDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken(jwtService.generateToken(user));
        authResponseDto.setUser(user.toUserDTO());
        return authResponseDto;
    }
}
