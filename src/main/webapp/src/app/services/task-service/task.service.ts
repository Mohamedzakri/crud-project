import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AppConfig } from 'src/app/config/config';
import { TaskStatus } from 'src/app/enums/TaskStatus';
import { ITask } from 'src/app/models/Task';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private baseUrl: string = AppConfig.apiUrl;

  constructor(private http: HttpClient) {}

  createTask(task: ITask): Observable<any> {
    const url: string = this.baseUrl + 'user/createUser';
    return this.http.post(url, task);
  }

  getDoneTasksList(id: number): Observable<ITask[]> {
    const url = `${this.baseUrl}task/taskList/${id}`;
    return this.http.get<ITask[]>(url).pipe(
      tap(data => {
        console.warn(" 1 List of tasks:", data);
      }));
  }
  getTasksList(id: number): Observable<ITask[]> {
    const url = `${this.baseUrl}task/userTasks/${id}`;
    return this.http.get<ITask[]>(url).pipe(
      tap(data => {
        console.warn(" 1 List of tasks:", data);
      }));
  }
}
