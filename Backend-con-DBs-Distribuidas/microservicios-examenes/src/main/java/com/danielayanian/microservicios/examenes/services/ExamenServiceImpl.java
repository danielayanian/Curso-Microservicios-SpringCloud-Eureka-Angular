package com.danielayanian.microservicios.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danielayanian.microservicios.commons.examenes.models.entity.Asignatura;
import com.danielayanian.microservicios.commons.examenes.models.entity.Examen;
import com.danielayanian.microservicios.commons.services.CommonServiceImpl;
import com.danielayanian.microservicios.examenes.models.repository.AsignaturaRepository;
import com.danielayanian.microservicios.examenes.models.repository.ExamenRepository;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	@Override
	public Iterable<Asignatura> findAllAsignaturas() {
		return asignaturaRepository.findAll();
	}
	
}
