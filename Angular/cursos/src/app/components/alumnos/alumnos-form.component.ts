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
  
  private fotoSeleccionada: File;

  constructor(service: AlumnoService, router: Router,
              route: ActivatedRoute){

    super(service, router, route);
    this.titulo = "Crear Alumno";
    this.model = new Alumno();
    this.redirect = '/alumnos';
    this.nombreModel = 'Alumno';//O Alumno.name

  }

  public seleccionarFoto(event): void{
    this.fotoSeleccionada = event.target.files[0];
    console.info(this.fotoSeleccionada);

    if(this.fotoSeleccionada.type.indexOf('image') < 0){
      this.fotoSeleccionada = null;
      Swal.fire(
        'Error al seleccionar la foto:', 
        'El archivo debe ser del tipo imagen', 
        'error');
    }

  }

  public override crear(): void{
    if(!this.fotoSeleccionada){
      super.crear();
    }else{
      
      this.service.crearConFoto(this.model, this.fotoSeleccionada).subscribe(alumno => {
        console.log(alumno);
        Swal.fire('Nuevo:', `Alumno ${alumno.nombre} creado con éxito`, 'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if(err.status === 400){
          this.error = err.error;
          console.log(this.error);
        }
      });

    }
  }

  public override editar(): void{
    if(!this.fotoSeleccionada){
      super.editar();
    }else{
      
      this.service.editarConFoto(this.model, this.fotoSeleccionada).subscribe(alumno => {
        console.log(alumno);
        Swal.fire('Modificado:', `Alumno ${alumno.nombre} actualizado con éxito`, 'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if(err.status === 400){
          this.error = err.error;
          console.log(this.error);
        }
      });

    }
  }

}
