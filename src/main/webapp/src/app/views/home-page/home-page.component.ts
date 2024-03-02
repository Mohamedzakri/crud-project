import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent {
  constructor(private router: Router) {}
  redirectToAnotherComponent() {
    this.router.navigate(['/register-form-component']);
  }
  redirectToAnotherComponent2() {
    this.router.navigate(['/login-form-component']);
  }
}
