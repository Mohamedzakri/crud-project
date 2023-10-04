import { Component, OnInit } from '@angular/core';
import { TaskStatus } from 'src/app/enums/TaskStatus';
import { ITask } from 'src/app/models/Task';
import { TaskService } from 'src/app/services/task-service/task.service';

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
  constructor(private taskService: TaskService) {}
  ngOnInit(): void {
    //   this.taskService.getTasksList(this.id).subscribe((tasks) => {
    //     this.tasks = this.tasks;
    this.tasks;
    this.getAllTask();
    this.getAllDoneTasks();
  }

  getAllTask() {
    this.taskService.getTasksList(this.id).subscribe(
      (res) => {
        this.tasks = res;
      },
      (err) => {
        alert('Unable to get list of tasks ' + this.status);
      }
    );
  }
  getAllDoneTasks() {
    this.taskService.getDoneTasksList(this.id).subscribe(
      (res) => {
        this.Donetasks = res;
      },
      (err) => {
        alert('Unable to get list of tasks ' + this.id);
      }
    );
  }
}
