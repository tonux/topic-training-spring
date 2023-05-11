package com.workshop.testapp.rest;

import com.workshop.testapp.model.AuthLoginDto;
import com.workshop.testapp.model.AuthRegisterDto;
import com.workshop.testapp.model.AuthResponseDto;
import com.workshop.testapp.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private final AuthService authService;

    public AuthResource(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody AuthRegisterDto authRegisterDto) {
        return authService.register(authRegisterDto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthLoginDto authLoginDto) {
        return authService.login(authLoginDto);
    }
}
