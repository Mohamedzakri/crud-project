import { Component } from '@angular/core';
import { AxiosService } from 'src/app/services/axios.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css'],
})
export class ContentComponent {
  componentToShow: string = 'welcome';

  constructor(private axiosService: AxiosService) {}

  /**
   * Updates the value of the `componentToShow` property.
   *
   * @param componentToShow - The name of the component to show.
   * @returns void
   *
   * @example
   * const contentComponent = new ContentComponent();
   * contentComponent.showComponent("messages");
   * console.log(contentComponent.componentToShow); // Output: "messages"
   */
  showComponent(componentToShow: string): void {
    this.componentToShow = componentToShow;
  }

  onLogin(input: any): void {
    this.axiosService
      .request('POST', '/login', {
        login: input.login,
        password: input.password,
      })
      .then((response) => {
        this.axiosService.setAuthToken(response.data.token);
        this.componentToShow = 'messages';
      })
      .catch((error) => {
        this.axiosService.setAuthToken(null);
        this.componentToShow = 'home';
      });
    /* the request method returns a promise and the then and catch methods
	    are responsible for handling return asynchronous HTTP request
		.then(): method is executed when it the request method successfully
    returns a promise and it executes the inside code
    (response): is a parameter that represents the response data received from
    the server 
    it calls the setAuthToken and it sends it response.data.token and it 
    shows messages
    if anything goes wrong it calls the setAuthToken.
    and it removes the "auth_token" item
	   */
  }

  onRegister(input: any): void {
    this.axiosService
      .request('POST', '/register', {
        firstName: input.firstName,
        lastName: input.lastName,
        login: input.login,
        password: input.password,
      })
      .then((response) => {
        this.axiosService.setAuthToken(response.data.token);
        this.componentToShow = 'messages';
      })
      .catch((error) => {
        this.axiosService.setAuthToken(null);
        this.componentToShow = 'home';
      });
  }
}
