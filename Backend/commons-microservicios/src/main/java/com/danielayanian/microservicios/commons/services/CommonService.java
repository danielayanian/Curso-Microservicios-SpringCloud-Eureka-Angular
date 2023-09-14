package com.danielayanian.microservicios.commons.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<E> {

	//Los siguientes 4 metodos serian el CRUD
	
	public Iterable<E> findAll();
	
	//Para la paginacion
	public Page<E> findAll(Pageable pageable);
	
	public Optional findById(Long id);
	
	public E save(E entity);
	
	public void deleteById(Long id);
	
}
