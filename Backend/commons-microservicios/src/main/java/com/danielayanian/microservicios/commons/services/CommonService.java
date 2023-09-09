package com.danielayanian.microservicios.commons.services;

import java.util.Optional;

public interface CommonService<E> {

	//Los siguientes 4 metodos serian el CRUD
	
	public Iterable<E> findAll();
	
	public Optional findById(Long id);
	
	public E save(E entity);
	
	public void deleteById(Long id);
	
}
