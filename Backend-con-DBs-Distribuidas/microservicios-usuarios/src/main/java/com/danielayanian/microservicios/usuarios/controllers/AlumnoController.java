package com.danielayanian.microservicios.usuarios.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danielayanian.microservicios.commons.alumnos.models.entity.Alumno;
import com.danielayanian.microservicios.commons.controllers.CommonController;
import com.danielayanian.microservicios.usuarios.services.AlumnoService;

import jakarta.validation.Valid;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
	
	@GetMapping("/alumnos-por-curso")
	public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
		return ResponseEntity.ok(service.findAllById(ids));
	}
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource imagen = new ByteArrayResource(o.get().getFoto());
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
		
	}
	
	@PutMapping("/{id}")
	//El BindingResult tiene que ir como parametro inmediatamente despues de alumno (después del entity)
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result,
			@PathVariable Long id){
		
		//Validamos los campos
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
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

	@PostMapping("/crear-con-foto")
	//Al enviar los datos, usar "archivo" como key, luego nombre, apellido e email
	//archivo porque el atributo aca lo llamamos archivo
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result, 
			@RequestParam MultipartFile archivo) throws IOException {
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.crear(alumno, result);
	}
	
	@PutMapping("editar-con-foto/{id}")
	//Cuando estamos recibiendo una foto o un archivo no es un Json, sino un multipart form data,
	//es otro tipo de request. Eso se maneja en la clase MultipartFile. Este metodo no recibira un
	//Json, sino un multipart
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result,
			@PathVariable Long id, @RequestParam MultipartFile archivo) throws IOException{
		
		//Validamos los campos
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDB = o.get();
		alumnoDB.setNombre(alumno.getNombre());
		alumnoDB.setApellido(alumno.getApellido());
		alumnoDB.setEmail(alumno.getEmail());
		
		if(!archivo.isEmpty()) {
			alumnoDB.setFoto(archivo.getBytes());
		}
		
		//Persistimos los cambios en el alumno, y luego lo enviamos en el cuerpo de la respuesta
		//El codigo del created es 201
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDB));
	}
	
}
