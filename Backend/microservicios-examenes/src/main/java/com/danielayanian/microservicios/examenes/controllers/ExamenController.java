package com.danielayanian.microservicios.examenes.controllers;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielayanian.microservicios.commons.controllers.CommonController;

import com.danielayanian.microservicios.commons.examenes.models.entity.Examen;

import com.danielayanian.microservicios.examenes.services.ExamenService;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Examen> o = service.findById(id);
		
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Examen examenDB = o.get();
		examenDB.setNombre(examen.getNombre());
		
		List<Pregunta> eliminadas = new ArrayList<>();
		examenDB.getPreguntas().forEach(pdb -> {
			if(!examen.getPreguntas().contains(pdb)) {
				eliminadas.add(pdb);
			}
		});
		eliminadas.forEach(p -> {
			examenDB.removePregunta(p);
		});
		
		//Lo anterior lo podemos resumir asi usando programacion funcional
		//examenDB.getPreguntas()
		//.stream()
		//.filter(pdb -> !examen.getPreguntas().contains(pdb))
		//.forEach(examenDB::removePregunta);
		
		examenDB.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDB));
		
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	
	
}
