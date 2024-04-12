package com.escuela_ingles.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escuela_ingles.Responses.UserInfo;
import com.escuela_ingles.modelo.Comentario;
import com.escuela_ingles.modelo.User;
import com.escuela_ingles.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
public class UsuarioController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@GetMapping
	public ResponseEntity<?> userInfo(Principal username) {
		
		User userObj=(User) userDetailsService.loadUserByUsername(username.getName());
		
		UserInfo userInfo = new UserInfo();
		userInfo.setNombre(userObj.getNombre());
		userInfo.setPaterno(userObj.getPaterno());
		userInfo.setMaterno(userObj.getMaterno());
		userInfo.setUsername(userObj.getUsername());
		userInfo.setRol(userObj.getRole().name());
		
		return ResponseEntity.ok(userInfo);
	}
	

}
