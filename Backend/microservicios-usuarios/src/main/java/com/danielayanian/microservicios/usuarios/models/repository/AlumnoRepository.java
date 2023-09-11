package com.danielayanian.microservicios.usuarios.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.danielayanian.microservicios.commons.alumnos.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> { //Indico clase del entity y tipo de su id

}
