package com.danielayanian.microservicios.respuestas.models.entity;

import com.danielayanian.microservicios.commons.alumnos.models.entity.Alumno;
import com.danielayanian.microservicios.commons.examenes.models.entity.Pregunta;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="respuestas")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String texto;

	@ManyToOne(fetch = FetchType.LAZY)
	private Alumno alumno;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Pregunta respuesta;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Pregunta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Pregunta respuesta) {
		this.respuesta = respuesta;
	}
	
}
