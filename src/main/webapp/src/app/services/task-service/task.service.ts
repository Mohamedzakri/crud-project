import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AppConfig } from 'src/app/config/config';
import { ITask } from 'src/app/models/Task';

@Injectable({
  providedIn: 'root',
})
export class TaskService {
  /**
   * The base URL for the API.
   */
  private baseUrl: string = AppConfig.apiUrl;

  constructor(private http: HttpClient) {}

  /**
   * Sends a POST request to the API to create a new task.
   * @param task - The task object to be created.
   * @returns An Observable that emits the response from the API.
   */
  createTask(task: ITask): Observable<any> {
    const url: string = this.baseUrl + 'user/createUser';
    return this.http.post(url, task);
  }

  /**
   * Retrieves a list of done tasks for a specific user.
   * @param id - The ID of the user.
   * @returns An Observable that emits an array of ITask objects.
   */
  getDoneTasksList(id: number): Observable<ITask[]> {
    const url = `${this.baseUrl}task/taskList/${id}`;
    return this.http.get<ITask[]>(url).pipe(
      tap((data) => {
        console.warn(' 1 List of tasks:', data);
      })
    );
  }

  /**
   * Retrieves a list of tasks for a specific user.
   * @param id - The ID of the user.
   * @returns An Observable that emits an array of ITask objects.
   */
  getTasksList(id: number): Observable<ITask[]> {
    const url = `${this.baseUrl}task/userTasks/${id}`;
    return this.http.get<ITask[]>(url).pipe(
      tap((data) => {
        console.warn(' 1 List of tasks:', data);
      })
    );
  }
}
