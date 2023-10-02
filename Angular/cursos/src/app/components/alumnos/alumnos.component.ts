import { Component } from '@angular/core';
import { Alumno } from 'src/app/models/alumno';
import { AlumnoService } from 'src/app/services/alumno.service';
import { OnInit } from '@angular/core';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit { 

  titulo: string = 'Listado de Alumnos';

  alumnos: Alumno[];

  constructor(private service: AlumnoService){}

  ngOnInit(){
    this.service.listar().subscribe(alumnos => {
      this.alumnos = alumnos;
    });
  }

}
