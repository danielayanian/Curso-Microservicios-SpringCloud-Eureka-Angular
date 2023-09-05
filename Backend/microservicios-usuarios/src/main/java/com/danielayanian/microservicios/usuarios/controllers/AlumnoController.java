package com.danielayanian.microservicios.usuarios.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.danielayanian.microservicios.usuarios.models.entity.Alumno;
import com.danielayanian.microservicios.usuarios.services.AlumnoService;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoService service;//Siempre inyectamos la interface
	
	@GetMapping //Al no indicar ruta significa que nos referimos al dir raiz
	//Devuelve un objeto que representa el body de la respuesta, que puede
	//estar formado por cualquier cantidad de objetos de cualquier clase. Y 
	//asi se construiria un JSON con varios objetos anidados.
	public ResponseEntity<?> listar() {
		//OK significa status 200, y en el body pasamos la lista de objetos
		//Por debajo se construye el JSON de manera automatica
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> ver(@PathVariable Long id) {//Indicamos que el id lo saque del id de la ruta
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();//Devuelve codigo 400, y el cuerpo vacio
		}
		return ResponseEntity.ok(o.get());//Codigo 200 y el alumno que devuelve el get
	}
	
	@PostMapping
	//Saca el alumno del cuerpo (un JSON) de la peticion post recibida
	//Con los datos del JSON que venga poblara el objeto alumno
	public ResponseEntity<?> crear(@RequestBody Alumno alumno){
		Alumno alumnoDB = service.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDB);
	}
	
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
	
	@DeleteMapping("/{id]")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		//No content es codigo 204, y devolvemos el cuerpo vacio
		return ResponseEntity.noContent().build();
	}
	
}
