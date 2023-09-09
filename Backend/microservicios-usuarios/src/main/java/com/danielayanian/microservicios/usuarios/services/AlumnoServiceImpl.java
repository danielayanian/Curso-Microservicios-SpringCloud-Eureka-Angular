package com.danielayanian.microservicios.usuarios.services;

import org.springframework.stereotype.Service;

import com.danielayanian.microservicios.commons.services.CommonServiceImpl;
import com.danielayanian.microservicios.usuarios.models.entity.Alumno;
import com.danielayanian.microservicios.usuarios.models.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {
	

}
