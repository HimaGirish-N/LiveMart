package com.livemart.controller;

import com.livemart.dto.LoginRequest;
import com.livemart.model.User;
import com.livemart.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        User saved = authService.register(user);
        return ResponseEntity.ok(saved);  // return full user details
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest req){
    User logged = authService.login(req.getEmail(), req.getPassword());
    return ResponseEntity.ok(logged);
}
}
