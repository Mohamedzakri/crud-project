import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './views/login-form/login-form.component';
import { RegisterFormComponent } from './views/register-form/register-form.component';

const routes: Routes = [
  { path: 'login-form-component', component: LoginFormComponent },
  { path: 'register-form-component', component: RegisterFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
