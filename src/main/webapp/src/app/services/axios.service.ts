import { Injectable } from '@angular/core';
import axios from 'axios';
@Injectable({
  providedIn: 'root'
})
export class AxiosService {
  constructor() {
    axios.defaults.baseURL = 'http://localhost:8080';
    axios.defaults.headers.post['Content-Type'] = 'application/json';
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }

  setAuthToken(token: string | null): void {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
  }
  /*  setAuthToken
    it takes a token parameter of type String or null
    in case it's not null it :
    window.localStorage.setItem("auth_token", token);
    else it removes it from the web storage 
  */


  request(method: string, url: string, data: any): Promise<any> {
      let headers: any = {};

      if (this.getAuthToken() !== null) {
          headers = {"Authorization": "Bearer " + this.getAuthToken()};
      }

      return axios({
          method: method,
          url: url,
          data: data,
          headers: headers
      });
  }

  /* request Method
  
  it requires a method of type string, a url, and data and returns a promise
  it then calls the getAuthToken to check if the local storage has some
  item with key auth_token and it creates the object headers and assings it
  {"Authorization": "Bearer " + this.getAuthToken()}
  and retunrs a object with the four params
  */
}
