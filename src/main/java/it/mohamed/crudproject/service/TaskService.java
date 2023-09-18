package it.mohamed.crudproject.service;

import it.mohamed.crudproject.enums.TaskStatus;
import it.mohamed.crudproject.model.TaskEntity;
import it.mohamed.crudproject.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskEntity getTask(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> getTaskByStatus(TaskStatus status){
        return taskRepository.getTaskByStatus(status);
    }
}
