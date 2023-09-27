import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfig } from 'src/app/config/config';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  
  private baseUrl: string = AppConfig.apiUrl;

  constructor(private http: HttpClient) { }

  createTask (task: any): Observable<any>{
    const url: string = this.baseUrl + "user/createUser";    
    return this.http.post(url, task);
  }

}
