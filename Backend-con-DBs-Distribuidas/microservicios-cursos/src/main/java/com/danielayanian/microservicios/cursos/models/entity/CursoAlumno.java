package com.danielayanian.microservicios.cursos.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//Esta entidad estara relacionada con una tabla cursos-alumnos que usaremos para relacionar los cursos
//de la tabla de este microservicio cursos, con la tabla alumnos del microservicio usuarios
@Table(name = "cursos_alumnos") //Tabla intermedia que creamos, pero alumno_id no tendra integridad
//referencial con ninguna tabla de la DB de micro cursos
public class CursoAlumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "alumno_id", unique = true) //Unique = true indica que un alumno tiene que estar en un
	//solo curso
	private Long alumnoId;
	
	@JsonIgnoreProperties(value = {"cursoAlumnos"}) //Para evitar un Json infinito
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id")
	private Curso curso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAlumnoId() {
		return alumnoId;
	}

	public void setAlumnoId(Long alumnoId) {
		this.alumnoId = alumnoId;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof CursoAlumno)) {
			return false;
		}
		CursoAlumno a = (CursoAlumno)obj;
		
		return this.alumnoId != null && this.alumnoId.equals(a.getAlumnoId());
	}
	
}
