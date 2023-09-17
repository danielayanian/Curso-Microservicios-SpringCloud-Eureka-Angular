package com.danielayanian.microservicios.respuestas.services;

import com.danielayanian.microservicios.respuestas.models.entity.Respuesta;

public interface RespuestaService {

	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	
}
