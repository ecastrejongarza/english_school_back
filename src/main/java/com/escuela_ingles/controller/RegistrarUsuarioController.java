package com.escuela_ingles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escuela_ingles.modelo.AuthResponse;
import com.escuela_ingles.modelo.RegisterRequest;
import com.escuela_ingles.service.AuthService;
import com.escuela_ingles.service.RegistrarUsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/registrar")
@RequiredArgsConstructor
public class RegistrarUsuarioController {

	@Autowired
	RegistrarUsuarioService registrarUsuarioService;
	@Autowired
	private HttpServletRequest request;

	@PostMapping()
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		// Obtener el token 
        //String token = extractTokenFromRequest();
		return ResponseEntity.ok(registrarUsuarioService.register(request));
	}

	private String extractTokenFromRequest() {
		// Obtener el encabezado de autorización de la solicitud
		String authorizationHeader = request.getHeader("Authorization");

		// Verificar si el encabezado no es nulo y comienza con "Bearer "
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			// Extraer el token después de "Bearer "
			return authorizationHeader.substring(7);
		}

		// Devolver nulo si no se encuentra el token en el encabezado de autorización
		return null;
	}
}
