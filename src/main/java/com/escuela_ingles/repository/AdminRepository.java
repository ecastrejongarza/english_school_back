package com.escuela_ingles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.escuela_ingles.modelo.Role;
import com.escuela_ingles.modelo.User;

@Repository
public interface AdminRepository extends JpaRepository<User,Integer> {

	List<User> findByRole(Role role);

	List<User> findByActivoAndRole(Integer activo, Role role);
}
