package com.danielayanian.microservicios.examenes.services;

import java.util.List;

import com.danielayanian.microservicios.commons.examenes.models.entity.Examen;
import com.danielayanian.microservicios.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen> {
	public List<Examen> findByNombre(String term);
}
