package com.escuela_ingles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escuela_ingles.modelo.Role;
import com.escuela_ingles.modelo.User;
import com.escuela_ingles.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	
	public List<User> getAll(){
		
		List<User> result = null;
		result = adminRepository.findAll();
		return result;
	}
	
	 public List<User> buscarPorRole(Role role) {
	        return adminRepository.findByActivoAndRole(1,role);
	    }
	 
	 public List<User> buscarAlumnosActivosInactivos(Integer activo, String rol){
		 
		 Role role = null;
		 if(rol.equals("1")) {
			 role= Role.MASTER;
		 }else if(rol.equals("2")) {
			 role= Role.ALUMN;
		 }else {
			 role= Role.ADMIN;
		 }
		 return adminRepository.findByActivoAndRole(activo,role);
	 }
	 
	 public User actualizarAlumnosActivosInactivos(Integer id, User user) {
		 User busqueda = adminRepository.findById(id).orElse(null);
		 
		 
		 busqueda.setActivo(user.getActivo());
		return adminRepository.save(busqueda);
		 
	 }

}
