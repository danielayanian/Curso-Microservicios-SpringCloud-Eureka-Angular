import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})


//Se crea un servicio en Angular por cada microservicio del backend
export class AlumnoService {

  private http: HttpClient;

  private baseEndpoint = 'http://localhost:8090/api/alumnos';

  constructor(http: HttpClient) { 
    this.http = http;
  }
}
