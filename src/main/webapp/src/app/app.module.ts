import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './views/home-page/home-page.component';
import { UserProfileElementComponent } from 'src/app/page-elements/user-profile-element/user-profile-element.component';
import { UserTodoListElementComponent } from './page-elements/user-todo-list-element/user-todo-list-element.component';
import { UserSessionElementComponent } from './page-elements/user-session-element/user-session-element.component';
import { UserMainButtonElementComponent } from './page-elements/user-main-button-element/user-main-button-element.component';
import { UserEndElementComponent } from './page-elements/user-end-element/user-end-element.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginFormComponent } from './views/login-form/login-form.component';
import { ContentComponent } from './views/content/content.component';
import { ButtonsComponent } from './views/buttons/buttons.component';
import { AuthContentComponent } from './auth-content/auth-content.component';
import { AxiosService } from './services/axios.service';
import { FormsModule } from '@angular/forms';
import { AuthenticationPageComponent } from './views/authentication-page/authentication-page.component';
import { RegisterFormComponent } from './views/register-form/register-form.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    UserProfileElementComponent,
    UserTodoListElementComponent,
    UserSessionElementComponent,
    UserMainButtonElementComponent,
    UserEndElementComponent,
    LoginFormComponent,
    ContentComponent,
    ButtonsComponent,
    AuthContentComponent,
    AuthenticationPageComponent,
    RegisterFormComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [AxiosService],
  bootstrap: [AppComponent],
})
export class AppModule {}
