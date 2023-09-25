import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { AppConfig } from 'src/app/config/config';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = AppConfig.apiUrl;
  constructor(private http: HttpClient) { }

  createUser(user: any): Observable<any>{
    const url = `${this.baseUrl}/api/users`;
    return this.http.post(url, user);
  }
}
