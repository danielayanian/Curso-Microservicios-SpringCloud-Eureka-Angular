package com.danielayanian.microservicios.commons.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public class CommonServiceImpl<E, R extends PagingAndSortingRepository<E,Long> & CrudRepository<E,Long>> implements CommonService<E> {
	
	@Autowired //Inyecta la dependencia, Spring crea el objeto, y lo podemos usar en los metodos
	protected R repository;
	
	@Override
	@Transactional(readOnly = true) //Indicamos que es una transaccion a la BD
	public Iterable<E> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional findById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional //Modificara la BD, por eso no es de solo lectura como los anteriores
	public E save(E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
