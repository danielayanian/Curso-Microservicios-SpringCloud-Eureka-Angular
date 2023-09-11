package com.danielayanian.microservicios.examenes.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.danielayanian.microservicios.examenes.models.entity.Examen;

public interface ExamenRepository extends CrudRepository<Examen, Long> {

}
