package com.escuela_ingles.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.escuela_ingles.modelo.AuthResponse;
import com.escuela_ingles.modelo.RegisterRequest;
import com.escuela_ingles.modelo.Role;
import com.escuela_ingles.modelo.User;
import com.escuela_ingles.repository.RegistrarUsuarioRepository;
import com.escuela_ingles.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrarUsuarioService {

	@Autowired
	JwtService jwtService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RegistrarUsuarioRepository registrarUsuarioRepository;

	public AuthResponse register(RegisterRequest request, String token) {
		String tokenEntra = jwtService.getUsernameFromToken(token);
		System.out.println(tokenEntra);

		Role rol = null;
		if (request.getRole() != null) {
			if (request.getRole().equals("1")) {
				rol = Role.MASTER;
			} else if (request.getRole().equals("2")) {
				rol = Role.ALUMN;
			} else {
				rol = Role.ADMIN;
			}
		}

		Date fechaActual = new Date();
		SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yy");
		String fechaFormateada = fechaFormat.format(fechaActual);

		User user = User.builder().username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword())).nombre(request.getNombre())
				.paterno(request.getPaterno()).materno(request.getMaterno()).role(rol).activo(1)
				.email(request.getEmail()).telefono(request.getTelefono()).fechaInscripcion(fechaFormateada).build();

		registrarUsuarioRepository.save(user);

		return AuthResponse.builder().token(jwtService.getToken(user)).build();

	}

	public AuthResponse register(RegisterRequest request) {
		Role rol = null;
		if (request.getRole() != null) {
			if (request.getRole().equals("1")) {
				rol = Role.MASTER;
			} else if (request.getRole().equals("2")) {
				rol = Role.ALUMN;
			} else {
				rol = Role.ADMIN;
			}
		}

		Date fechaActual = new Date();
		SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yy");
		String fechaFormateada = fechaFormat.format(fechaActual);

		String newUserId = generateUserId(rol);
		User user = User.builder().username(newUserId).password(passwordEncoder.encode(request.getPassword()))
				.nombre(request.getNombre()).paterno(request.getPaterno()).materno(request.getMaterno()).role(rol)
				.activo(1).email(request.getEmail()).telefono(request.getTelefono()).fechaInscripcion(fechaFormateada)
				.build();

		registrarUsuarioRepository.save(user);

		return AuthResponse.builder().token(jwtService.getToken(user)).build();

	}

	private String generateUserId(Role rol) {
	    User lastUser = registrarUsuarioRepository.findTopByRoleOrderByUsernameDesc(rol);
	    int nextId = 1;
	    if (lastUser != null) {
	        // Extraer el número de ID del último ID de usuario y aumentarlo en uno
	        nextId = Integer.parseInt(lastUser.getUsername().substring(1)) + 1;
	    }
	    // Formatear el nuevo ID de usuario con ceros a la izquierda
	    String type = "";
	    if (rol.equals(Role.MASTER)) {
	        type = "M";
	    } else if (rol.equals(Role.ALUMN)) {
	        type = "A";
	    } else {
	        type = "ADMIN";
	    }
	    return type + String.format("%03d", nextId);
	}
}
