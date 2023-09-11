package com.danielayanian.microservicios.cursos.controllers;

import java.util.List;
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
import com.danielayanian.microservicios.cursos.models.entity.Curso;
import com.danielayanian.microservicios.cursos.services.CursoService;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();
		cursoDB.setNombre(curso.getNombre());
		
		//Persistimos los cambios en el curso, y luego lo enviamos en el cuerpo de la respuesta
		//El codigo del created es 201
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?>  asignarAlumnos(@RequestBody List<Alumno> alumnos, @PathVariable Long id){
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();
		alumnos.forEach(a -> {cursoDB.addAlumno(a);});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?>  eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();
		cursoDB.removeAlumno(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}
	
}
