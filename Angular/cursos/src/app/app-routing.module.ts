import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlumnosComponent } from './components/alumnos/alumnos.component';
import { CursosComponent } from './components/cursos/cursos.component';
import { ExamenesComponent } from './components/examenes/examenes.component';

const routes: Routes = [
  {path:"", pathMatch: 'full', redirectTo: 'cursos'},//Si no indico ruta, solo la raiz, se vera el componente cursos
  {path:"alumnos", component:AlumnosComponent},
  {path:"cursos", component:CursosComponent},
  {path:"examenes", component:ExamenesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
