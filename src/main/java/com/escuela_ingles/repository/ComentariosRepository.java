package com.escuela_ingles.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.escuela_ingles.modelo.Comentario;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentario, Integer>{

	List<Comentario> findByUsername(String id);
	

}
