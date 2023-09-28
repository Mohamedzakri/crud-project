import { TaskPriority } from "../enums/TaskPriority";
import { TaskStatus } from "../enums/TaskStatus";
import { TaskService } from "../services/task-service/task.service";
import { IUser } from "./User";

export interface ITask {
  id: number;

  taskObj: string;

  priority: TaskPriority;

  status: TaskStatus;

  user: IUser;

  taskDueDate: number;
}
