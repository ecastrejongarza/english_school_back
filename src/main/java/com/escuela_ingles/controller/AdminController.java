package com.escuela_ingles.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.escuela_ingles.Responses.UserInfo;
import com.escuela_ingles.modelo.Comentario;
import com.escuela_ingles.modelo.Role;
import com.escuela_ingles.modelo.User;
import com.escuela_ingles.service.AdminService;
import com.escuela_ingles.service.ComentariosService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	AdminService adminService;

	// traer todos los alumnos para usuario admin
	@GetMapping("/all/{rol}")
	public List<User> getAllAlumnos(@PathVariable("rol") Role rol) {

		return adminService.buscarPorRole(rol);
	}

	// traer alumnos activos o inactivos
	@GetMapping("/{rol}/{active}")
	public List<User> getAlumnsActiveInactive(@PathVariable("active") Integer active, @PathVariable("rol") String rol) {

		return adminService.buscarAlumnosActivosInactivos(active, rol);
	}

	// actualizar estado activo o inactivo del alumno
	@PutMapping("/{id}")
	public User putAlumnsActiveInactive(@PathVariable("id") Integer id, @RequestBody User activo) {

		return adminService.actualizarAlumnosActivosInactivos(id, activo);
	}
	
	/////////modulo completo para comentarios
	@Autowired
	ComentariosService comentariosService;

	//agregar comentarios
	@PostMapping("/comentarios")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public Comentario saveComentario(@RequestBody @Valid Comentario coment) {
		System.out.println(coment);
		return comentariosService.save(coment);
	}
	
	//todos los comentarios por id
	@GetMapping("/comentarios/{id}")
	public List<Comentario> allComentarios(@PathVariable("id") String id) {
		return comentariosService.allComentariosById(id);
	}
}
