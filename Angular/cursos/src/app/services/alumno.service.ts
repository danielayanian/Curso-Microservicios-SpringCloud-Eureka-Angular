import { Injectable } from '@angular/core';
import { Alumno } from '../models/alumno';
import { CommonService } from './common.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

//Se crea un servicio en Angular por cada microservicio del backend
export class AlumnoService extends CommonService<Alumno> {

  protected override baseEndpoint = 'http://localhost:8090/api/alumnos';

  constructor(http: HttpClient) {
    super(http);
  }

}
