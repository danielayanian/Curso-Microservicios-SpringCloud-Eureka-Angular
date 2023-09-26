package com.danielayanian.microservicios.cursos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielayanian.microservicios.commons.alumnos.models.entity.Alumno;
import com.danielayanian.microservicios.commons.controllers.CommonController;
import com.danielayanian.microservicios.commons.examenes.models.entity.Examen;
import com.danielayanian.microservicios.cursos.models.entity.Curso;
import com.danielayanian.microservicios.cursos.models.entity.CursoAlumno;
import com.danielayanian.microservicios.cursos.services.CursoService;

import jakarta.validation.Valid;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {
	
	@Value("${config.balanceador.test}")
	//Spring va a inyectar en este atributo el valor que config.balanceador.test tenga en
	//el archivo properties
	private String balanceadorTest;
	
	@DeleteMapping("/eliminar-alumno/{id}")
	public ResponseEntity<?> eliminarCursoAlumnoPorId(@PathVariable Long id){
		service.eliminarCursoAlumnoPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	@Override
	public ResponseEntity<?> listar() {
		List<Curso> cursos = ((List<Curso>) service.findAll()).stream().map(c -> {
			c.getCursoAlumnos().forEach(ca -> {
				Alumno alumno = new Alumno();
				alumno.setId(ca.getAlumnoId());
				c.addAlumno(alumno);
			});
			return c;
		}).collect(Collectors.toList());
		return ResponseEntity.ok().body(cursos);
	}
	
	@GetMapping("/pagina") //Este metodo paginara, en cambio el listar anterior devolvera todo sin paginar
	@Override
	public ResponseEntity<?> listar(Pageable pageable) {
		Page<Curso> cursos = service.findAll(pageable).map(curso -> {
			curso.getCursoAlumnos().forEach(ca -> {
				Alumno alumno = new Alumno();
				alumno.setId(ca.getAlumnoId());
				curso.addAlumno(alumno);
			});
			return curso;
		});
		return ResponseEntity.ok().body(cursos);
	}
	///////////////////////
	@GetMapping("/{id}")
	@Override
	public ResponseEntity<?> ver(@PathVariable Long id) {//Indicamos que el id lo saque del id de la ruta
		Optional<Curso> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();//Devuelve codigo 400, y el cuerpo vacio
		}
		Curso curso = o.get();
		
		if(curso.getCursoAlumnos().isEmpty() == false) {
			
			List<Long> ids = curso.getCursoAlumnos().stream().map(ca -> ca.getAlumnoId())
					.collect(Collectors.toList());
			
			List<Alumno> alumnos = (List<Alumno>) service.obtenerAlumnosPorCurso(ids);
			
			curso.setAlumnos(alumnos);
		}
		
		return ResponseEntity.ok(o.get());//Codigo 200 y el alumno que devuelve el get
	}
	
	//Esto es solo para probar como el balanceador de carga hace el balanceo de csrga
	@GetMapping("/balanceador-test")
	public ResponseEntity<?> balanceadorTest() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("balanceador", balanceadorTest);
		response.put("cursos", service.findAll());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	//El BindingResult tiene que ir como parametro inmediatamente despues del entity (curso)
	public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id){
		
		//Validamos los campos
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
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
		alumnos.forEach(a -> {
			CursoAlumno cursoAlumno = new CursoAlumno();
			cursoAlumno.setAlumnoId(a.getId());
			cursoAlumno.setCurso(cursoDB);
			cursoDB.addCursoAlumno(cursoAlumno);
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?>  eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();
		CursoAlumno cursoAlumno = new CursoAlumno();
		cursoAlumno.setAlumnoId(alumno.getId());
		cursoDB.removeCursoAlumno(cursoAlumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable Long id){
		Curso curso = service.findCursoByAlumnoId(id);
		
		if(curso != null) {
			//Con este método que accede al micro respuestas, obtenemos todos los ids de los examenes
			//que fueron repondidos por el alumno
			//Como el método devuelve un Iterable, hacemos el casteo a List
			//Esta lista contiene todos los ids de los examenes respondidos por el alumno
			List<Long> examenesIds = (List<Long>) service.obtenerExamenesIdsConRespuestasAlumno(id);
			
			if(examenesIds != null && examenesIds.size() > 0) {
			
				//Usamos programación funcional
				//Queremos que todos los examenes del curso queden con la indicación de si
				//fueron respondidos o no
				List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
					if(examenesIds.contains(examen.getId())) {
						examen.setRespondido(true);//Al examen le seteamos que fue respondido
					}
					return examen;
				}).collect(Collectors.toList());
				
				//El objeto curso ahora sigue teniendo todos los examenes, pero con la indicación para cada
				//uno de si fue respondido o no
				curso.setExamenes(examenes);
				
			}
			
		}
		
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?>  asignarExamenes(@RequestBody List<Examen> examenes, @PathVariable Long id){
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();
		examenes.forEach(e -> {cursoDB.addExamen(e);});
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?>  eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Curso> o = service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Curso cursoDB = o.get();
		cursoDB.removeExamen(examen);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));
	}
	
}
 
 