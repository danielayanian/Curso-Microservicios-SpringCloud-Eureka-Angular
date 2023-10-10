import { Component } from '@angular/core';
import { CommonFormComponent } from '../common-form.component';
import { ExamenService } from 'src/app/services/examen.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Examen } from 'src/app/models/examen';

@Component({
  selector: 'app-examen-form',
  templateUrl: './examen-form.component.html',
  styleUrls: ['./examen-form.component.css']
})
export class ExamenFormComponent extends CommonFormComponent<Examen, ExamenService> {

  constructor(service: ExamenService, router: Router, route: ActivatedRoute){
    super(service, router, route);
    super.titulo = 'Crear Examen';
    this.model = new Examen();
    this.nombreModel = Examen.name;
    this.redirect = '/examenes';
  }

}
