package com.escuela_ingles.repository;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.escuela_ingles.modelo.Role;
import com.escuela_ingles.modelo.User;

public interface RegistrarUsuarioRepository extends JpaRepository<User,Integer> {

	 User findTopByRoleOrderByUsernameDesc(Role rol);

}
