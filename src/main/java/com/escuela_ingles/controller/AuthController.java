package com.escuela_ingles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escuela_ingles.modelo.AuthResponse;
import com.escuela_ingles.modelo.LoginRequest;
import com.escuela_ingles.modelo.RegisterRequest;
import com.escuela_ingles.service.AuthService;
import com.escuela_ingles.service.RegistrarUsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    @Autowired
	RegistrarUsuarioService registrarUsuarioService;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }
    
    @PostMapping(value = "register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(registrarUsuarioService.register(request));
	}
   
}
