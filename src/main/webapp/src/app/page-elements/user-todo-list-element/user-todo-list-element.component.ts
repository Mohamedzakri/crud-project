import { Component, OnInit } from '@angular/core';
import { TaskStatus } from 'src/app/enums/TaskStatus';
import { ITask } from 'src/app/models/Task';
import { TaskService } from 'src/app/services/task-service/task.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-todo-list-element',
  templateUrl: './user-todo-list-element.component.html',
  styleUrls: ['./user-todo-list-element.component.css'],
})
export class UserTodoListElementComponent implements OnInit {
  tasks!: ITask[];
  Donetasks!: ITask[];
  status: TaskStatus = TaskStatus.DONE;
  id: number = 1;

  constructor(private taskService: TaskService, private router: Router) {}
  ngOnInit(): void {
    this.getAllTask();
    this.getAllDoneTasks();
  }

  /**
   * Retrieves the user's tasks from the `TaskService` and assigns them to the `tasks` field.
   */
  getAllTask() {
    this.taskService.getTasksList(this.id).subscribe(
      (res) => {
        this.tasks = res;
      },
      (err) => {
        //alert('Unable to get list of tasks ' + this.status);
      }
    );
  }

  /**
   * Retrieves the user's done tasks from the `TaskService` and assigns them to the `Donetasks` field.
   */
  getAllDoneTasks() {
    this.taskService.getDoneTasksList(this.id).subscribe(
      (res) => {
        this.Donetasks = res;
      },
      (err) => {
        //alert('Unable to get list of tasks ' + this.id);
      }
    );
  }
  // redirectToAnotherComponent() {
  //   this.router.navigate(['/register']);
  //(click)="redirectToAnotherComponent()"
  // }
}
