import { Component } from '@angular/core';
import { CommonFormComponent } from '../common-form.component';
import { CursoService } from 'src/app/services/curso.service';
import { Curso } from 'src/app/models/curso';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-curso-form',
  templateUrl: './curso-form.component.html',
  styleUrls: ['./curso-form.component.css']
})
export class CursoFormComponent extends CommonFormComponent<Curso, CursoService> {

  tituloCrear = 'Crear Curso';
  tituloEditar = 'Editar Curso';

  constructor(service: CursoService, router: Router,
    route: ActivatedRoute){
    super(service, router, route);
    this.titulo = 'Crear Curso';
    this.model = new Curso();
    this.redirect = '/cursos';
    this.nombreModel = Curso.name;
  }
}
