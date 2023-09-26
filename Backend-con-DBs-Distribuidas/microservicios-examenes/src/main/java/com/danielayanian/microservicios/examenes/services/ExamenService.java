package com.danielayanian.microservicios.examenes.services;

import java.util.List;

import com.danielayanian.microservicios.commons.examenes.models.entity.Asignatura;
import com.danielayanian.microservicios.commons.examenes.models.entity.Examen;
import com.danielayanian.microservicios.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen> {
	
	public List<Examen> findByNombre(String term);
	
	public Iterable<Asignatura> findAllAsignaturas();
	
	public Iterable<Long> findExamenesIdsConRespuestasByPreguntaIds(Iterable<Long> preguntaIds);
	
}
