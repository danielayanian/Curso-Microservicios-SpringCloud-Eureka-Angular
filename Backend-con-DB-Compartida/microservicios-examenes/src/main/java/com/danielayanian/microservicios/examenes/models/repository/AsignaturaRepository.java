package com.danielayanian.microservicios.examenes.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.danielayanian.microservicios.commons.examenes.models.entity.Asignatura;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {

}
