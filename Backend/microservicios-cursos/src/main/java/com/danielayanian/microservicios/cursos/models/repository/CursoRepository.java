package com.danielayanian.microservicios.cursos.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.danielayanian.microservicios.cursos.models.entity.Curso;

//Si no queremos usar paginacion, en vez de PagingAndSortingRepository usamos CrudRepository
public interface CursoRepository extends PagingAndSortingRepository<Curso, Long>, CrudRepository<Curso, Long> {

	@Query("select c from Curso c join fetch c.alumnos a where a.id=?1")
	public Curso findCursoByAlumnoId(Long id);
	
}
