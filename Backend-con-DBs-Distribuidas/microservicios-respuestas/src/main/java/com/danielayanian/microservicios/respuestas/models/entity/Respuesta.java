package com.danielayanian.microservicios.respuestas.models.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.danielayanian.microservicios.commons.alumnos.models.entity.Alumno;
import com.danielayanian.microservicios.commons.examenes.models.entity.Pregunta;

@Document(collection = "respuestas")
public class Respuesta {

	@Id
	private String id;//En mongo los ids deben ser strings
	
	private String texto;
	
	@Transient //No es el mismo Transient que usamos en las DB SQL
	private Alumno alumno;
	
	private Long alumnoId;
	
	@Transient //El atributo no sera parte de la coleccion de mongoDB, solo sera atributo de esta clase
	private Pregunta pregunta;
	
	private Long preguntaId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Long getAlumnoId() {
		return alumnoId;
	}

	public void setAlumnoId(Long alumnoId) {
		this.alumnoId = alumnoId;
	}

	public Long getPreguntaId() {
		return preguntaId;
	}

	public void setPreguntaId(Long preguntaId) {
		this.preguntaId = preguntaId;
	}

}
