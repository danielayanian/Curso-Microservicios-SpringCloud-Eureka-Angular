package com.danielayanian.microservicios.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielayanian.microservicios.commons.services.CommonServiceImpl;
import com.danielayanian.microservicios.cursos.clients.RespuestaFeignClient;
import com.danielayanian.microservicios.cursos.models.entity.Curso;
import com.danielayanian.microservicios.cursos.models.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	//Inyectamos el cliente feign
	private RespuestaFeignClient client;
	
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
	
}
