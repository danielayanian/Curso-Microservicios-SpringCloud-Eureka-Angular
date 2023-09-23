package com.danielayanian.microservicios.cursos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielayanian.microservicios.commons.alumnos.models.entity.Alumno;
import com.danielayanian.microservicios.commons.services.CommonServiceImpl;
import com.danielayanian.microservicios.cursos.clients.AlumnosFeignClient;
import com.danielayanian.microservicios.cursos.clients.RespuestaFeignClient;
import com.danielayanian.microservicios.cursos.models.entity.Curso;
import com.danielayanian.microservicios.cursos.models.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	//Inyectamos el cliente feign para comunicarnos con el micro respuestas
	//Es un client feign por cada micro con el que nos queramos comunicar desde este micro
	private RespuestaFeignClient client;
	
	@Autowired
	//Inyectamos el cliente feign para comunicarnos con el micro usuarios
	private AlumnosFeignClient clientAlumno;
	
	@Override
	@Transactional(readOnly = true)
	//Se ponde transactional cuando habrá comunicación con una base de datos, en este caso
	//a través del repository
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	//Acá no va el transactional porque no es una comunicación con la base de datos, sino con
	//otro microservicio
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		return client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}

	@Override
	public Iterable<Alumno> obtenerAlumnosPorCurso(List<Long> ids) {
		return clientAlumno.obtenerAlumnosPorCurso(ids);
	}

	@Override
	@Transactional
	public void eliminarCursoAlumnoPorId(Long id) {
		repository.eliminarCursoAlumnoPorId(id);
	}
	
}
