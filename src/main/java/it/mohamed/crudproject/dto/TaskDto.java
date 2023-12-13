package it.mohamed.crudproject.dto;

import it.mohamed.crudproject.enums.TaskPriority;
import it.mohamed.crudproject.enums.TaskStatus;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {

    private int id;

    private String taskObj;

    private TaskPriority priority;

    private TaskStatus status;

    private String user;

    private Date taskDueDate;
}
