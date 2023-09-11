package com.danielayanian.microservicios.examenes.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "examenes")
public class Examen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;
	
	//Como Examen tiene preguntas, y Pregunta tiene Examen, al construir el JSON se generaria una
	//construccion eterna e infinita. Entonces con esto lo evitamos
	//allowSetters = true evita algunos errores
	@JsonIgnoreProperties(value = {"examen"}, allowSetters = true)
	//cascade - Para que cuando cree el examen cree tambien sus preguntas anidadas en el JSON, y para
	//que cuando elimine un examen elimine tambien sus preguntas (como quiero que haga las dos cosas
	//uso ALL)
	//OrphanRemoval - Cualquier pregunta que no esté relacionada con ningun examen sera eliminada
	//Para cuando eliminamos una pregunta de la lista de preguntas de un examen.
	//Cuando la clave foranea de una pregunta sea NULL, la eliminara
	@OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<Pregunta> preguntas;

	
	
	public Examen() {
		this.preguntas = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		//Este metodo setPreguntas actulizara la lista de preguntas, entonces antes que nada con la
		//siguiente linea le indicamos el recolector de basura que elimine las referencias a esas preguntas,
		//que luego seran reemplazadas por las nuevas preguntas
		this.preguntas.clear();
		preguntas.forEach(p -> {this.addPregunta(p);});
		//preguntas.forEach(this::addPregunta);//Forma resumida de la linea anterior
	}
	
	public void addPregunta(Pregunta pregunta) {
		this.preguntas.add(pregunta);
		pregunta.setExamen(this);
	}
	
	public void removePregunta(Pregunta pregunta) {
		this.preguntas.remove(pregunta);
		pregunta.setExamen(null);
	}
	
}
