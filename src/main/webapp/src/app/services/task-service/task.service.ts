import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, retry, tap } from 'rxjs';
import { AppConfig } from 'src/app/config/config';
import { ITask } from 'src/app/models/Task';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  private baseUrl: string = AppConfig.apiUrl;
  private taskListByPriority: ITask[] = [];

  constructor(private http: HttpClient) {}

  createTask(task: ITask): Observable<any> {
    const url: string = this.baseUrl + 'user/createUser';
    return this.http.post(url, task);
  }

  getTasksList(id: number): Observable<ITask[]> {
    const url = `${this.baseUrl}task/userTasks/${id}`;
    console.warn("list of tasks is"+this.http.get<ITask[]>(url))
    return this.http.get<ITask[]>(url).pipe(
      tap(data => {
        console.warn(" 1 List of tasks:", data);
      }));
  }
}
