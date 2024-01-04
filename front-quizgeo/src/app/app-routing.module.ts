import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegistrarComponent } from './registrar/registrar.component';
import { CreditosComponent } from './creditos/creditos.component';
import { AuthGuard } from './auth.guard';
import { QuizComponent } from './quiz/quiz.component';
import { ResultadoComponent } from './resultado/resultado.component';
import { SobreComponent } from './sobre/sobre.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'quiz/:id', component: QuizComponent, canActivate: [AuthGuard] },
  { path: 'sobre', component: SobreComponent, canActivate: [AuthGuard] },
  { path: 'registro', component: RegistrarComponent },
  { path: 'resultado/:pontuacao/:nota', component: ResultadoComponent, canActivate: [AuthGuard] },
  { path: 'creditos', component: CreditosComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'home' },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
