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

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    UserProfileElementComponent,
    UserTodoListElementComponent,
    UserSessionElementComponent,
    UserMainButtonElementComponent,
    UserEndElementComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
