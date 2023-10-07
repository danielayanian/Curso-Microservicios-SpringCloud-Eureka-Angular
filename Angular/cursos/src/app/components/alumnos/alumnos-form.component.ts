import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';
import { OnInit } from '@angular/core';
import Swal from 'sweetalert2'
import { CommonFormComponent } from '../common-form.component';

@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent extends CommonFormComponent<Alumno, AlumnoService> implements OnInit {

  tituloCrear = 'Crear Alumnos';
  tituloEditar = 'Editar Alumnos';
  

  constructor(service: AlumnoService, router: Router,
              route: ActivatedRoute){

    super(service, router, route);
    //this.titulo = "Crear Alumnos";
    this.model = new Alumno();
    this.redirect = '/alumnos';
    this.nombreModel = 'Alumno';//O Alumno.name

  }


}