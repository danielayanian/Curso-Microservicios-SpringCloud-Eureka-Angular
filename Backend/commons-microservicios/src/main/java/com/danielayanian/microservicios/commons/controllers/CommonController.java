package com.danielayanian.microservicios.commons.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.danielayanian.microservicios.commons.services.CommonService;

import jakarta.validation.Valid;

public class CommonController<E, S extends CommonService<E>> {

	@Autowired
	protected S service;//Siempre inyectamos la interface
	
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
		Optional<E> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();//Devuelve codigo 400, y el cuerpo vacio
		}
		return ResponseEntity.ok(o.get());//Codigo 200 y el alumno que devuelve el get
	}
	
	@PostMapping
	//Saca el alumno del cuerpo (un JSON) de la peticion post recibida
	//Con los datos del JSON que venga poblara el objeto alumno
	public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result){
		
		//Validamos los campos
		if(result.hasErrors()) {
			return this.validar(result);
		}
		E entityDB = service.save(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(entityDB);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.deleteById(id);
		//No content es codigo 204, y devolvemos el cuerpo vacio
		return ResponseEntity.noContent().build();
	}
	
	protected ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), " El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
	
}
