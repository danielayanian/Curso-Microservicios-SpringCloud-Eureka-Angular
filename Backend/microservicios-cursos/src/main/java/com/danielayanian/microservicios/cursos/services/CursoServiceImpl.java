package com.danielayanian.microservicios.cursos.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielayanian.microservicios.commons.services.CommonServiceImpl;
import com.danielayanian.microservicios.cursos.models.entity.Curso;
import com.danielayanian.microservicios.cursos.models.repository.CursoRepository;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	
	
}
