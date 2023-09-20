package com.danielayanian.microservicios.cursos.services;

import org.springframework.web.bind.annotation.PathVariable;

import com.danielayanian.microservicios.commons.services.CommonService;
import com.danielayanian.microservicios.cursos.models.entity.Curso;

public interface CursoService extends CommonService<Curso> {
	
	public Curso findCursoByAlumnoId(Long id);
	
	//Copiamos acá en el service de Curso el mismo método de RespuestaFeignClient, solo
	//eliminamos el @PathVariable, que acá no se requiere, solo se requiere en feign o en los
	//controladores
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
	
}
