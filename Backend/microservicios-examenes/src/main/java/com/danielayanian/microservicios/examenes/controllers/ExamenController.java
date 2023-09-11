package com.danielayanian.microservicios.examenes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielayanian.microservicios.commons.controllers.CommonController;
import com.danielayanian.microservicios.examenes.models.entity.Examen;
import com.danielayanian.microservicios.examenes.services.ExamenService;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){
		return null;
	}
	
}
