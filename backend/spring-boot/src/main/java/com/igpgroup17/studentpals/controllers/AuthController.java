package com.igpgroup17.studentpals.controllers;

import com.igpgroup17.studentpals.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/v1/auth/{token}")
    public ResponseEntity<?> auth(@PathVariable(name = "token") String token) {
        return ResponseEntity.ok(authService.auth(token));
    }
}
