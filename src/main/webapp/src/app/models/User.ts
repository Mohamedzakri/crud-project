import { ITask } from './Task';

export interface IUser {
  id: number;

  userName: string | null;

  taskEntities: ITask[] | null;
}
