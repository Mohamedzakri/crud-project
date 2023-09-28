import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfig } from 'src/app/config/config';
import { TaskPriority } from 'src/app/enums/TaskPriority';
import { TaskStatus } from 'src/app/enums/TaskStatus';
import { ITask } from 'src/app/models/Task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  
  private baseUrl: string = AppConfig.apiUrl;
  private taskListByPriority: ITask[] = [];

  constructor(private http: HttpClient) { }

  createTask (task: ITask): Observable<any>{
    const url: string = this.baseUrl + "user/createUser"; 
    return this.http.post(url, task);
  }

getTaskList (taskListByPriority)

}
