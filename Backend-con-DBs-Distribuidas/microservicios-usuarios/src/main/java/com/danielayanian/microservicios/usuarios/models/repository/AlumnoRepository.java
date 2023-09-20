package com.danielayanian.microservicios.usuarios.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.danielayanian.microservicios.commons.alumnos.models.entity.Alumno;

//PagingAndSortingRepository es una clase para usar paginacion
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> ,CrudRepository<Alumno, Long> /*CrudRepository<Alumno, Long>*/ { //Indico clase del entity y tipo de su id

	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	public List<Alumno> findByNombreOrApellido(String term);
	
}
