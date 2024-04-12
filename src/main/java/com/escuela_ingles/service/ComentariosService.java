package com.escuela_ingles.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escuela_ingles.modelo.Comentario;
import com.escuela_ingles.repository.ComentariosRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentariosService {
	
	@Autowired
	ComentariosRepository comentariosRepository;

	public Comentario save(@Valid Comentario coment) {
		Date fechaActual = new Date();
		SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yy"); 
		String fechaFormateada = fechaFormat.format(fechaActual);
		coment.setFecha(fechaFormateada);
		
		return comentariosRepository.save(coment);
	}

	public List<Comentario> allComentarios() {
		// TODO Auto-generated method stub
		return comentariosRepository.findAll();
	}

	public List<Comentario> allComentariosById(String id) {
		// TODO Auto-generated method stub
		return comentariosRepository.findByUsername(id);
	}
	

}
