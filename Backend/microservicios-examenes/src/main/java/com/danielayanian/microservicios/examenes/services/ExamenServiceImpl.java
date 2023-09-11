package com.danielayanian.microservicios.examenes.services;

import org.springframework.stereotype.Service;

import com.danielayanian.microservicios.commons.services.CommonServiceImpl;
import com.danielayanian.microservicios.examenes.models.entity.Examen;
import com.danielayanian.microservicios.examenes.models.repository.ExamenRepository;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService {

}
