package com.danielayanian.microservicios.usuarios.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielayanian.microservicios.usuarios.models.entity.Alumno;
import com.danielayanian.microservicios.usuarios.models.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	@Autowired //Inyecta la dependencia, Spring crea el objeto, y lo podemos usar en los metodos
	private AlumnoRepository repository;
	
	@Override
	@Transactional(readOnly = true) //Indicamos que es una transaccion a la BD
	public Iterable<Alumno> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional //Modificara la BD, por eso no es de solo lectura como los anteriores
	public Alumno save(Alumno alumno) {
		return repository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

}
