import { Component } from '@angular/core';
import { CommonFormComponent } from '../common-form.component';
import { ExamenService } from 'src/app/services/examen.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Examen } from 'src/app/models/examen';
import { Asignatura } from 'src/app/models/asignatura';

@Component({
  selector: 'app-examen-form',
  templateUrl: './examen-form.component.html',
  styleUrls: ['./examen-form.component.css']
})
export class ExamenFormComponent extends CommonFormComponent<Examen, ExamenService> {

  asignaturasPadre: Asignatura[] = [];

  asignaturasHija: Asignatura[] = [];

  constructor(service: ExamenService, router: Router, route: ActivatedRoute){
    super(service, router, route);
    super.titulo = 'Crear Examen';
    this.model = new Examen();
    this.nombreModel = Examen.name;
    this.redirect = '/examenes';
  }

  override ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      if(id){
        this.service.ver(id).subscribe(m => {
          this.model = m;
          this.titulo = 'Editar ' + this.nombreModel;
        })
      }
    });

    this.service.findAllAsignatura()
    .subscribe(asignaturas => 
      this.asignaturasPadre = asignaturas.filter(a => !a.padre));

  }

  cargarHijos(): void{
    this.asignaturasHija = this.model.asignaturaPadre? 
    this.model.asignaturaPadre.hijos: [];
  }

}
