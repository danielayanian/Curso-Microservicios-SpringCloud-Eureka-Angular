package com.danielayanian.microservicios.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Con el cliente feign vamos a poder comunicar dos microservicios, en este caso el
//microservicios-cursos con el microservicios-respuestas
@FeignClient(name = "microservicio-respuestas")//, url = "microservicio-respuestas")
public interface RespuestaFeignClient {

	//En Feign mapeamos todo igual a como lo hacemos en cualquier contolador, pero acá
	//el objetivo es comunicarnos con otro microservicio
	
	//Feign usa balanceo de carga, o sea, al tener que conectarse a un micro, elegirá la
	//instancia de ese micro más conveniente
	
	//Este GetMapping tiene que tener la misma ruta que la del GetMapping del micro con el que
	//nos queremos comunicar, en este caso micro respuestas
	@GetMapping("/alumno/{alumnoId}/examenes-respondidos")
	//También copiamos el mismo método y parámetros del RespuestaController
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId);
}
