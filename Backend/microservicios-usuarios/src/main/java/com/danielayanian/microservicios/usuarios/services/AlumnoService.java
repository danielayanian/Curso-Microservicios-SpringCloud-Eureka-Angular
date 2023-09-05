package com.danielayanian.microservicios.usuarios.services;

import java.util.Optional;

import com.danielayanian.microservicios.usuarios.models.entity.Alumno;

public interface AlumnoService {

	//Los siguientes 4 metodos serian el CRUD de Alumno
	
	public Iterable<Alumno> findAll();
	
	public Optional findById(Long id);
	
	public Alumno save(Alumno alumno);
	
	public void deleteById(Long id);
	
}
