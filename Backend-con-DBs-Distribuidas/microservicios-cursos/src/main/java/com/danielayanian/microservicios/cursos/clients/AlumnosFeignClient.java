package com.danielayanian.microservicios.cursos.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-usuarios")
public interface AlumnosFeignClient {
	
	//Para comunicarnos con un microservicio, en el feign tenemos que tener la misma ruta del
	//GetMapping que haya en el GetMapping del micro al que nos queremos conectar
	@GetMapping("/alumnos-por-curso")
	public Iterable<AlumnosFeignClient> obtenerAlumnosPorCurso(@RequestParam List<Long> ids);

}
