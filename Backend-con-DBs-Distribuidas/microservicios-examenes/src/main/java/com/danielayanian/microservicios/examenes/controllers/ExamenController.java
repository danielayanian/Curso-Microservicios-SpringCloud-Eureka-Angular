package com.danielayanian.microservicios.examenes.controllers;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielayanian.microservicios.commons.controllers.CommonController;

import com.danielayanian.microservicios.commons.examenes.models.entity.Examen;
import com.danielayanian.microservicios.commons.examenes.models.entity.Pregunta;
import com.danielayanian.microservicios.examenes.services.ExamenService;

import jakarta.validation.Valid;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

	@GetMapping("/respondidos-por-preguntas")
	public ResponseEntity<?> obtenerExamenesIdsPorPreguntasIdRespondidas(@RequestParam List<Long> preguntaIds){
		return ResponseEntity.ok().body(service.findExamenesIdsConRespuestasByPreguntaIds(preguntaIds));
	}
	
	@PutMapping("/{id}")
	//El BindingResult tiene que ir como parametro inmediatamente despues de examen
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){
		
		//Validamos los campos
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
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
		examenDB.setAsignaturaHija(examen.getAsignaturaHija());
		examenDB.setAsignaturaPadre(examen.getAsignaturaPadre());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDB));
		
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
	
}
