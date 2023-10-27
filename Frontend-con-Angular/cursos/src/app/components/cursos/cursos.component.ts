import { Component } from '@angular/core';
import { CommonListarComponent } from '../common-listar.component';
import { CursoService } from 'src/app/services/curso.service';
import { Curso } from 'src/app/models/curso';
import { MatPaginatorIntl } from '@angular/material/paginator';
import { customPaginator } from '../custom-paginator-configuration';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.css'],
  providers: [
    { provide: MatPaginatorIntl, useValue: customPaginator() }
  ]
})
export class CursosComponent extends CommonListarComponent<Curso, CursoService> {

  constructor(service: CursoService){
    super(service);
    this.titulo = 'Listado de Cursos';
    this.nombreModel = Curso.name;
  }

}



/*
acordarme de agregar:

providers: [
    { provide: MatPaginatorIntl, useValue: customPaginator() }
  ]

  en @Component, para la paginacion

  Ver alumnos-component.ts

*/