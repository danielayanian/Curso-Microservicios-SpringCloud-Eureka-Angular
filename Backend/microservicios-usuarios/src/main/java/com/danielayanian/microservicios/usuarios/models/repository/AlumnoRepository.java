package com.danielayanian.microservicios.usuarios.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.danielayanian.microservicios.usuarios.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> { //Indico clase del entity y tipo de su id

}
