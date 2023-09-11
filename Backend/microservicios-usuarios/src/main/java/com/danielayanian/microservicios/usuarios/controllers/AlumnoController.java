package com.danielayanian.microservicios.usuarios.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielayanian.microservicios.commons.alumnos.models.entity.Alumno;
import com.danielayanian.microservicios.commons.controllers.CommonController;
import com.danielayanian.microservicios.usuarios.services.AlumnoService;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Alumno alumno, @PathVariable Long id){
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDB = o.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		//Persistimos los cambios en el alumno, y luego lo enviamos en el cuerpo de la respuesta
		//El codigo del created es 201
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}
	
	
}
